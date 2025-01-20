package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.EInvoiceProfile
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.log.logger
import org.apache.pdfbox.Loader
import org.apache.pdfbox.cos.COSName
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.common.PDMetadata
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkInfo
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureTreeRoot
import org.apache.xmpbox.XMPMetadata
import org.apache.xmpbox.schema.PDFAExtensionSchema
import org.apache.xmpbox.schema.XMPSchema
import org.apache.xmpbox.xml.DomXmpParser
import org.apache.xmpbox.xml.XmpSerializer
import org.mustangproject.ZUGFeRD.PDFAConformanceLevel
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.nio.file.Path
import java.util.*

open class PdfBoxPdfAttachmentWriter : PdfAttachmentWriter {

    protected val FacturXPdfAExtensionSchema: XMPSchema by lazy {
        DomXmpParser().parse(PdfBoxHandlerCommon.FacturXPdfAExtensionSchemaString.byteInputStream()).getSchema(PdfBoxHandlerCommon.PdfAExtensionSchemaNamespace).apply {
            // don't know why but the namespaces are not set on deserialized FacturXPdfAExtensionSchema, so set them manually
            this.addNamespace("http://www.aiim.org/pdfa/ns/schema#", "pdfaSchema")
            this.addNamespace("http://www.aiim.org/pdfa/ns/property#", "pdfaProperty")
        }
    }

    private val log by logger()


    override fun addFileAttachment(pdfFile: ByteArray, format: EInvoiceXmlFormat, xml: String, output: OutputStream) {
        try {
            Loader.loadPDF(pdfFile).use { document ->
                val profile = if (format == EInvoiceXmlFormat.XRechnung) EInvoiceProfile.XRechnung else EInvoiceProfile.EN16931
                attachInvoiceXmlToPdf(document, profile, xml, output)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not add invoice XML file attachments to PDF" }
        }
    }

    protected open fun attachInvoiceXmlToPdf(document: PDDocument, profile: EInvoiceProfile, xml: String, output: OutputStream) {
        val attachmentName = if (profile == EInvoiceProfile.XRechnung) "xrechnung.xml" else "factur-x.xml"

        embedInvoiceXml(document, xml, attachmentName)

        // TODO: create /AF array as depictured on Factur-X 1.0.07 DE.pdf, p. 28

        addFacturXXmp(document, profile)

        document.save(output)
    }

    protected open fun embedInvoiceXml(document: PDDocument, xml: String, attachmentName: String) {
        val names = PDDocumentNameDictionary(document.documentCatalog)
        val embeddedFiles = names.embeddedFiles ?: PDEmbeddedFilesNameTreeNode()

        val fileMap = (embeddedFiles.names?.toMutableMap() ?: mutableMapOf())

        val cosStream = document.document.createCOSStream()
        cosStream.createOutputStream().use {
            it.bufferedWriter().use { writer ->
                writer.write(xml)
            }
        }
        cosStream.setItem(COSName.TYPE, COSName.EMBEDDED_FILES)
        cosStream.setString(COSName.SUBTYPE, "application/xml")

        val fileSpec = PDComplexFileSpecification()
        fileSpec.file = attachmentName
        fileSpec.embeddedFile = PDEmbeddedFile(cosStream)

        fileMap.put(fileSpec.file, fileSpec)

        embeddedFiles.names = fileMap

        names.embeddedFiles = embeddedFiles
        document.documentCatalog.names = names
    }

    protected open fun addFacturXXmp(document: PDDocument, profile: EInvoiceProfile) {
        val catalog = document.documentCatalog
        val metadata = catalog.metadata ?: PDMetadata(document).also { catalog.metadata = it }

        val xmpMetadata = if (metadata.length == 0) { // if Metadata are available use that one, else create new ones
            XMPMetadata.createXMPMetadata()
        } else {
            try {
                DomXmpParser().parse(metadata.exportXMPMetadata())
            } catch (e: Throwable) {
                log.error(e) { "Could not read existing PDF metadata" }
                XMPMetadata.createXMPMetadata()
            }
        }

        if (xmpMetadata.xmpBasicSchema == null) {
            xmpMetadata.createAndAddXMPBasicSchema()
        }

        // see Factur-X 1.0.07 DE.pdf, p. 29
        xmpMetadata.addSchema(XMPSchema(xmpMetadata, "urn:factur-x:pdfa:CrossIndustryDocument:invoice:1p0#", "fx", "Factur-X PDFA Erweiterungsschema").apply {
            setTextPropertyValue("DocumentType", "INVOICE")
            setTextPropertyValue("DocumentFileName", "factur-x.xml")
            setTextPropertyValue("Version", "1.0")
            setTextPropertyValue("ConformanceLevel", getConformanceLevelForProfile(profile))
        })

        // add the extension schema for above schema - simply use the example XMP from Factur-X distribution
        xmpMetadata.getSchema(PdfBoxHandlerCommon.PdfAExtensionSchemaNamespace)?.let { existingPdfAExtensionSchema ->
            xmpMetadata.removeSchema(existingPdfAExtensionSchema)
        }
        addFacturXPdfAExtensionSchema(xmpMetadata)

        setXmpMetadata(metadata, xmpMetadata)
    }

    protected open fun addFacturXPdfAExtensionSchema(xmpMetadata: XMPMetadata) {
        xmpMetadata.addSchema(createFacturXPdfAExtensionSchema(xmpMetadata))
    }

    protected open fun createFacturXPdfAExtensionSchema(xmpMetadata: XMPMetadata): XMPSchema =
        // the deserialized FacturXPdfAExtensionSchema contains a wrong XMPMetadata instance, so merge with a new instance of PDFAExtensionSchema
        PDFAExtensionSchema(xmpMetadata).apply {
            FacturXPdfAExtensionSchema.allNamespacesWithPrefix.forEach { (namespace, prefix) ->
                this.addNamespace(namespace, prefix)
            }
            FacturXPdfAExtensionSchema.container.allProperties.forEach { property ->
                this.addProperty(property)
            }
        }

    protected open fun getConformanceLevelForProfile(profile: EInvoiceProfile): String = when (profile) {
        EInvoiceProfile.Minimum -> "MINIMUM"
        EInvoiceProfile.BasicWL -> "BASIC WL"
        EInvoiceProfile.Basic -> "BASIC"
        EInvoiceProfile.EN16931 -> "EN 16931"
        EInvoiceProfile.Extended -> "EXTENDED"
        EInvoiceProfile.XRechnung -> "XRECHNUNG"
    }


    open fun createEmptyPdfA3(outputPath: Path) {
        val document = createPdfA3()

        document.save(outputPath.toFile())
        document.close()
    }

    open fun createEmptyPdfA3(): ByteArray =
        createPdfA3().use { document ->
            ByteArrayOutputStream().use { stream ->
                document.save(stream)
                stream.toByteArray()
            }
        }

    private fun createPdfA3(): PDDocument {
        val document = PDDocument()
        document.addPage(PDPage()) // add an empty page

        val catalog = document.documentCatalog.apply {
            markInfo = PDMarkInfo(document.pages.cosObject).also { it.isMarked = true }
            structureTreeRoot = PDStructureTreeRoot()
        }

        // TODO: may write document.documentInformation (create- and modificationDate, author, producer, creator, title and subject)

        val metadata = PDMetadata(document)
        catalog.metadata = metadata
        setXmpMetadata(metadata, createPdfA3XmpMetadata())

        return document
    }

    protected open fun createPdfA3XmpMetadata(): XMPMetadata {
        val xmpMetadata = XMPMetadata.createXMPMetadata()

        xmpMetadata.createAndAddXMPBasicSchema().apply {
            createDate = Calendar.getInstance() // TODO: may set creator tool
        }
        xmpMetadata.createAndAddAdobePDFSchema() // TODO: may set producer
        xmpMetadata.createAndAddPDFAIdentificationSchema().apply {
            part = 3
            conformance = PDFAConformanceLevel.ACCESSIBLE.letter
        }
        xmpMetadata.createAndAddDublinCoreSchema().apply {
            format = "application/pdf"
            addDate(Calendar.getInstance()) // TODO: may set creator and title
        }

        // TODO: ChatGPT told me to also add an ICC profile (downloadable from here: https://www.adobe.com/support/downloads/iccprofiles/iccprofiles_win.html),
        //   but VeraPDF says it's a valid PDF/A-3 also without an ICC profile
        // Add an ICC profile
//        val iccProfileStream: InputStream = FileInputStream(File(iccPath))
//        val outputIntent = PDOutputIntent(document, iccProfileStream)
//        outputIntent.info = "sRGB IEC61966-2.1"
//        outputIntent.outputCondition = "sRGB IEC61966-2.1"
//        outputIntent.outputConditionIdentifier = "sRGB IEC61966-2.1"
//        outputIntent.registryName = "http://www.color.org"
//        document.documentCatalog.addOutputIntent(outputIntent)

        return xmpMetadata
    }

    protected open fun setXmpMetadata(metadata: PDMetadata, xmpMetadata: XMPMetadata) {
        val xmpBytes = ByteArrayOutputStream().use {
            XmpSerializer().serialize(xmpMetadata, it, true)
            it.toByteArray()
        }

        metadata.importXMPMetadata(xmpBytes)

        val xmpXml = xmpBytes.decodeToString()
        if (xmpXml.isNullOrBlank()) { }
    }

}