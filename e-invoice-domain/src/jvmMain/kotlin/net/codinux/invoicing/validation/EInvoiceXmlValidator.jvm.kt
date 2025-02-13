package net.codinux.invoicing.validation

import net.codinux.invoicing.format.*
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
    protected val formatDetector: EInvoiceFormatDetector = EInvoiceFormatDetector()
) {

    actual constructor() : this(EInvoiceFormatDetector())

    private val log by logger()


    actual open suspend fun validateEInvoiceXml(xml: String) =
        validateEInvoiceXmlJvm(xml)


    open fun validateEInvoiceXmlJvm(xml: String, format: EInvoiceFormatDetectionResult? = null): Result<InvoiceXmlValidationResult> =
        try {
            val detectedFormat = format ?: formatDetector.detectFormat(xml)
            val xslt = detectedFormat?.let { getXsltFile(it) }

            if (xslt != null) {
                validate(xslt, xml)
            } else {
                Result.error(IllegalArgumentException("Could not detect e-invoice format of provided XML"))
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not validate EInvoice file" }
            Result.error(e)
        }

    open fun validate(xslt: InputStream, xml: String): Result<InvoiceXmlValidationResult> =
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
//            val activePatterns = root.children("http://purl.oclc.org/dsdl/svrl", "active-pattern")
//            val testedInvoiceFields = root.children("http://purl.oclc.org/dsdl/svrl", "fired-rule")
            val validationErrors = mapValidationErrors(failedAsserts)
            val isValid = validationErrors.isEmpty()

            Result.success(InvoiceXmlValidationResult(isValid, validationErrors))
        } catch (e: Throwable) {
            log.warn(e) { "Validation with Xslt failed for XML file ${xml.substring(0, 250)}" }
            Result.error(e)
        }

    private fun sourceFor(string: String): Source =
        StreamSource(ByteArrayInputStream(string.encodeToByteArray()))

    protected open fun mapValidationErrors(failedAsserts: Iterable<XdmNode>) = failedAsserts.map {
        ValidationResultItem(
            mapSeverity(it.attribute("flag")),
            it.children { it.nodeName?.localName == "text" }.map { it.stringValue.trim() }.firstOrNull() ?: "",
            it.attribute("location"),
            it.attribute("test"),
            it.attribute("id")
        )
    }

    protected open fun mapSeverity(flag: String?): ValidationResultItemSeverity = when (flag?.lowercase()) {
        // the SVRL failed-assert.flag value is an open list, everyone can define their own error codes for it, so we can only try to map known values
        "info", "information", "informational", "notice", "hint", "debug", "trace" -> ValidationResultItemSeverity.Info
        "warn", "warning", "caution" -> ValidationResultItemSeverity.Warning
        "error", "fatal", "fatal-error" -> ValidationResultItemSeverity.Error
        else -> ValidationResultItemSeverity.Error // e.g. Factur-X Schematron XSLTs don't specify flag, there each failed-assert means an error
    }

    protected open fun getXsltFile(profile: EInvoiceFormatDetectionResult): InputStream? =
        if (profile.standard == EInvoicingStandard.CII) {
            if (profile.format == EInvoiceFormat.XRechnung || profile.profile == FacturXProfile.XRechnung) {
                ResourceUtil.getResourceAsStream("cii/xrechnung/schematron/XRechnung-CII-validation.xsl")
            } else if (profile.profile != null) { // Factur-X except XRechnung profile (see above)
                ResourceUtil.getResourceAsStream("facturx/schematron/${profile.profile}.xslt")
            } else {
                ResourceUtil.getResourceAsStream("cii/cen/schematron/EN16931-CII-validation.xslt")
            }
        } else {
            null
        }

}