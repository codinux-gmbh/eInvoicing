package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.EInvoiceProfile
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.log.logger
import org.apache.pdfbox.Loader
import org.apache.pdfbox.cos.COSArray
import org.apache.pdfbox.cos.COSName
import org.apache.pdfbox.cos.COSObject
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
import java.io.ByteArrayInputStream
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

        val fileSpec = addInvoiceXmlToAssociatedFiles(document, xml, attachmentName)

        addInvoiceXmlToEmbeddedFilesDictionary(document, fileSpec)

        addFacturXXmp(document, profile)

        document.save(output)
    }


    // builds the structure outlined in Factur-X 1.0.07 DE.pdf p. 28
    protected open fun addInvoiceXmlToAssociatedFiles(document: PDDocument, xml: String, attachmentName: String): PDComplexFileSpecification {
        // Key Fields in /AF
        //    /F: The file name of the embedded file.
        //    /UF: The file name in Unicode, allowing non-ASCII characters.
        //    /Desc: A description of the file's purpose or contents.
        //    /EF: A dictionary linking to the embedded file's content stream.
        val fileSpecificationDictionary = PDComplexFileSpecification()
        fileSpecificationDictionary.file = attachmentName // /F
        fileSpecificationDictionary.fileUnicode = attachmentName // /UF
        fileSpecificationDictionary.fileDescription = "Factur-x invoice" // /Desc
        fileSpecificationDictionary.cosObject.setName(COSName.AF_RELATIONSHIP, "Alternative") // don't use setString(), VeraPDF gives an error then

        val xmlBytes = xml.encodeToByteArray()
        fileSpecificationDictionary.embeddedFile = PDEmbeddedFile(document, ByteArrayInputStream(xmlBytes)).apply {
            subtype = "application/xml" // according to spec "text/xml", but "application/text" is recommended by RFC 7303
            size = xmlBytes.size
            creationDate = Calendar.getInstance()
            modDate = Calendar.getInstance()
        }

        val afArray = getAssociatedFilesArray(document)
        afArray.add(fileSpecificationDictionary)

        return fileSpecificationDictionary
    }

    protected open fun getAssociatedFilesArray(document: PDDocument): COSArray {
        // The /AF (Associated Files) entry is used to attach external files to the PDF in a way that links the attachment
        // to a specific part of the document, such as the document as a whole, a page, or an annotation. This is a key feature
        // of PDF/A-3, which permits embedding arbitrary file formats for archival and document exchange purposes.
        // The /AF entry is an array of File Specification dictionaries. These dictionaries describe the embedded files
        // and contain details such as the file name, description, and content stream.

        val existingAfArray = document.documentCatalog.cosObject.getItem(COSName.AF)
        if (existingAfArray == null) {
            return COSArray().also {
                document.documentCatalog.cosObject.setItem(COSName.AF, it)
            }
        }

        return if (existingAfArray.isDirect) { // like all objects in a COSDictionary value can be a direct or indirect object
            existingAfArray as COSArray
        } else {
            (existingAfArray as COSObject).`object` as COSArray
        }
    }

    protected open fun addInvoiceXmlToEmbeddedFilesDictionary(document: PDDocument, fileSpec: PDComplexFileSpecification) {
        val catalog = document.documentCatalog
        val names = catalog.names ?: PDDocumentNameDictionary(document.documentCatalog).apply {
            catalog.names = this
        }
        val embeddedFiles = names.embeddedFiles ?: PDEmbeddedFilesNameTreeNode().apply {
            names.embeddedFiles = this
        }

        // embeddedFiles.names returned UnmodifiableMap, therefore embeddedFiles.names?.toMutableMap() did not work
        val fileMap = mutableMapOf<String, PDComplexFileSpecification>().apply {
            embeddedFiles.names?.let {
                it.forEach { (name, fileSpec) -> this.put(name.toString(), fileSpec) }
            }
        }

        fileMap.put(fileSpec.file, fileSpec)
        embeddedFiles.names = fileMap
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