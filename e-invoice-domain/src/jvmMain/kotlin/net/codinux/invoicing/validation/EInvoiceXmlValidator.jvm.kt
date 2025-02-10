package net.codinux.invoicing.validation

import net.codinux.invoicing.format.EInvoiceFormat
import net.codinux.invoicing.format.EInvoiceFormatDetectionResult
import net.codinux.invoicing.format.EInvoiceFormatDetector
import net.codinux.invoicing.format.FacturXProfile
import net.codinux.invoicing.model.Result
import net.codinux.invoicing.pdf.ResourceUtil
import net.codinux.log.logger
import net.sf.saxon.s9api.Processor
import net.sf.saxon.s9api.XdmDestination
import net.sf.saxon.s9api.XdmNode
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource

actual open class EInvoiceXmlValidator(
    protected val formatDetector: EInvoiceFormatDetector = EInvoiceFormatDetector(),
    protected val mustangValidator: MustangEInvoiceValidator = MustangEInvoiceValidator()
) {

    private val log by logger()


    actual open suspend fun validateEInvoiceXml(xml: String) =
        validateEInvoiceXmlJvm(xml)

    actual open suspend fun validateEInvoicePdf(pdfBytes: ByteArray): Result<InvoiceValidationResult> =
        validateEInvoicePdfJvm(pdfBytes)

    open fun validateEInvoicePdfJvm(fileContent: ByteArray): Result<InvoiceValidationResult> =
        if (fileContent.size > 4 && fileContent[0].toInt().toChar() == '%' && fileContent[1].toInt().toChar() == 'P' &&
            fileContent[2].toInt().toChar() == 'D' && fileContent[3].toInt().toChar() == 'F') {
            mustangValidator.validateEInvoicePdf(fileContent)
        } else {
            val fileAsString = fileContent.decodeToString()
            val format = formatDetector.detectFormat(fileAsString)
            if (format == null) {
                validateEInvoiceXmlJvm(fileAsString)
            } else {
                mustangValidator.validateEInvoicePdf(fileContent)
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
            // we have to use Saxon anyway as the JVM transformer only supports XSLT 1.0, but Factur-X stylesheets use XSLT 2.0

            // A stylesheet can be compiled once and then executed several times with different source documents.
            // The Xslt30Transformer object is serially reusable, but not thread-safe.
            // The Serializer object is also serially reusable.
            val processor = Processor(false)
            val compiler = processor.newXsltCompiler().apply {
                isJustInTimeCompilation = false // to do static compilation only once and to be informed of stylesheet errors upfront, not during transformation
                setErrorReporter { error ->
                    log.error(error.cause) {
                        "XSLT contains${if (error.terminationMessage != null) " fatal" else ""} error: " +
                                "${error.errorCode} ${error.path ?: error.location}" +
                                "${error.failingExpression?.let { " (failing expression: $it)" } ?: ""}: ${error.message}"
                    }
                }
            }

            val xsltExecutable = compiler.compile(StreamSource(xslt))
            val trans = xsltExecutable.load30()

            val destination = XdmDestination()
            trans.transform(sourceFor(xml), destination)
            val resultNode = destination.xdmNode
            val root = resultNode.outermostElement

            // iterating over children and specifying the namespace URI turned out to be the fastest way (twice as fast
            // as leaving the ns URI away and 50 % faster than root.children { node -> } )
            val failedAsserts = root.children("http://purl.oclc.org/dsdl/svrl", "failed-assert")
//            val firedRules = root.children("http://purl.oclc.org/dsdl/svrl", "fired-rule")
//            val activePatterns = root.children("http://purl.oclc.org/dsdl/svrl", "active-pattern")
            val validationErrors = mapValidationErrors(failedAsserts)
            val isValid = validationErrors.isEmpty()

//            ValidationResult(failedAsserts.isEmpty(), firedRules.size, activePatterns.size, validationErrors)

            Result.success(InvoiceValidationResult(isValid, isValid, validationErrors, ""))
        } catch (e: Throwable) {
            log.warn(e) { "Validation with Xslt failed for XML file ${xml.substring(0, 250)}" }
            Result.error(e)
        }

    private fun sourceFor(string: String): Source =
        StreamSource(ByteArrayInputStream(string.encodeToByteArray()))

//    private fun mapValidationErrors(failedAsserts: List<Node>) = failedAsserts.map {
//        ValidationError(
//            ValidationErrorSeverity.Error,
//            it.attributes.getNamedItem("test")?.nodeValue,
//            it.attributes.getNamedItem("location")?.nodeValue,
//            it.childNodesList.filter { it.localName == "text" }.map { it.textContent.trim() })
//    }

    private fun mapValidationErrors(failedAsserts: Iterable<XdmNode>) = failedAsserts.map {
        ValidationResultItem(
            ValidationResultSeverity.Error,
            it.children { it.nodeName?.localName == "text" }.map { it.stringValue.trim() }.firstOrNull() ?: "",
            it.attribute("location"),
            it.attribute("test"),
        )
    }


    protected open fun getXsltFile(profile: EInvoiceFormatDetectionResult): InputStream? =
        profile.profile?.let {
            if (it != FacturXProfile.XRechnung) {
                ResourceUtil.getResourceAsStream("facturx/schematron/$it.xslt")
            } else {
                null
            }
        }

}