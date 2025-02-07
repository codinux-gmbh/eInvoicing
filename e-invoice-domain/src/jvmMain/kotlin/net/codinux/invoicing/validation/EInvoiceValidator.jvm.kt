package net.codinux.invoicing.validation

import net.codinux.invoicing.extension.childNodesList
import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.EInvoiceFormatDetector
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.ResourceUtil
import net.codinux.log.logger
import org.w3c.dom.Node
import org.xml.sax.ErrorHandler
import org.xml.sax.SAXParseException
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.StringWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.ErrorListener
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

actual open class EInvoiceValidator(
    protected val formatDetector: EInvoiceFormatDetector = EInvoiceFormatDetector(),
    protected val mustangValidator: MustangEInvoiceValidator = MustangEInvoiceValidator()
) {

    private val transformerFactory = TransformerFactory.newInstance()

    private val documentBuilderFactory = DocumentBuilderFactory.newInstance()

    private val log by logger()


    actual open suspend fun validateEInvoiceXml(xml: String) =
        validateEInvoiceXmlJvm(xml)

    actual open suspend fun validateEInvoiceFile(fileContent: ByteArray): Result<InvoiceValidationResult> =
        validateEInvoiceFileJvm(fileContent)

    open fun validateEInvoiceFileJvm(fileContent: ByteArray): Result<InvoiceValidationResult> =
        if (fileContent.size > 4 && fileContent[0].toInt().toChar() == '%' && fileContent[1].toInt().toChar() == 'P' &&
            fileContent[2].toInt().toChar() == 'D' && fileContent[3].toInt().toChar() == 'F') {
            mustangValidator.validateEInvoiceFileJvm(fileContent)
        } else {
            val fileAsString = fileContent.decodeToString()
            val format = formatDetector.detectFormat(fileAsString)
            if (format == null) {
                validateEInvoiceXmlJvm(fileAsString)
            } else {
                mustangValidator.validateEInvoiceFileJvm(fileContent)
            }
        }


    open fun validateEInvoiceXmlJvm(xml: String, format: EInvoiceFormatDetectionResult? = null): Result<InvoiceValidationResult> =
        try {
            val detectedFormat = format ?: formatDetector.detectFormat(xml)
            val xslt = detectedFormat?.let { getXsltFile(it) }

            if (xslt != null) {
                validate(xslt, xml)
            } else if (detectedFormat?.format == EInvoiceFormat.XRechnung) { // TODO: implement validating XRechnung
                mustangValidator.validateEInvoiceXmlJvm(xml)
            } else {
                Result.error(IllegalArgumentException("Could not detect e-invoice format of provided XML"))
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not validate EInvoice file" }
            Result.error(e)
        }

    open fun validate(xslt: InputStream, xml: String): Result<InvoiceValidationResult> =
        try {
            val xmlDocumentBuilder = documentBuilderFactory.newDocumentBuilder().apply {
                this.setErrorHandler(object : ErrorHandler {
                    override fun warning(e: SAXParseException?) {
                        log.warn(e) { "XML parser warning" }
                    }

                    override fun error(e: SAXParseException?) {
                        log.error(e) { "XML parser error" }
                    }

                    override fun fatalError(e: SAXParseException?) {
                        log.error(e) { "XML parser fatal error" }
                    }

                })
            }
            val xmlDocument =  xmlDocumentBuilder.parse(ByteArrayInputStream(xml.encodeToByteArray()))

            val xsltSource = StreamSource(xslt)
            val xmlSource = DOMSource(xmlDocument)
            val result = StringWriter()
            val resultDocument = documentBuilderFactory.newDocumentBuilder().newDocument()

            val transformer = transformerFactory.newTransformer(xsltSource)
            transformer.errorListener = object : ErrorListener {
                override fun warning(e: TransformerException?) {
                    log.warn(e) { "XSLT Transformer warning" }
                }

                override fun error(e: TransformerException?) {
                    log.error(e) { "XSLT Transformer error" }
                }

                override fun fatalError(e: TransformerException?) {
                    log.error(e) { "XSLT Transformer fatal error" }
                }

            }

//            transformer.transform(xmlSource, StreamResult(result))
            transformer.transform(xmlSource, DOMResult(resultDocument))

            val resultXml = result.toString()
            val resultNodes = resultDocument.documentElement.childNodesList

            val failedAsserts = resultNodes.filter { it.nodeName == "svrl:failed-assert" }
            val firedRules = resultNodes.filter { it.nodeName == "svrl:fired-rule" }
            val activePatterns = resultNodes.filter { it.nodeName == "svrl:active-pattern" }
            val validationErrors = mapValidationErrors(failedAsserts)
            val isValid = validationErrors.isEmpty()

//            ValidationResult(failedAsserts.isEmpty(), firedRules.size, activePatterns.size, validationErrors)

            Result.success(InvoiceValidationResult(isValid, isValid, validationErrors, ""))
        } catch (e: Throwable) {
            log.warn(e) { "Validation with Xslt failed for XML file ${xml.substring(0, 250)}" }
            Result.error(e)
        }

//    private fun mapValidationErrors(failedAsserts: List<Node>) = failedAsserts.map {
//        ValidationError(
//            ValidationErrorSeverity.Error,
//            it.attributes.getNamedItem("test")?.nodeValue,
//            it.attributes.getNamedItem("location")?.nodeValue,
//            it.childNodesList.filter { it.localName == "text" }.map { it.textContent.trim() })
//    }

    private fun mapValidationErrors(failedAsserts: List<Node>) = failedAsserts.map {
        ValidationResultItem(
            ValidationResultSeverity.Error,
            it.childNodesList.filter { it.localName == "text" }.map { it.textContent.trim() }.firstOrNull() ?: "",
            it.attributes.getNamedItem("location")?.nodeValue,
            it.attributes.getNamedItem("test")?.nodeValue,
        )
    }


    protected open fun getXsltFile(profile: EInvoiceFormatDetectionResult): InputStream? =
        profile.profile?.let {
            ResourceUtil.getResourceAsStream("facturx/schematron/$it.xslt")
        }

}