package net.codinux.invoicing.pdf

import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.log.logger
import org.mustangproject.ZUGFeRD.ZUGFeRDExporterFromA3
import java.io.OutputStream

class PdfBoxPdfAttachmentWriter : PdfAttachmentWriter {

    private val log by logger()


    // TODO: check 1. FACTUR-X 1.0.0.07 DE.pdf, p. 67 if below implementation is correct before removing Mustang code
//    override fun addFileAttachment(pdfFile: ByteArray, attachmentName: String, xml: String, output: OutputStream) {
//        try {
//            Loader.loadPDF(pdfFile).use { document ->
//                val names = PDDocumentNameDictionary(document.documentCatalog)
//                val embeddedFiles = names.embeddedFiles ?: PDEmbeddedFilesNameTreeNode()
//
//                val fileMap = (embeddedFiles.names?.toMutableMap() ?: mutableMapOf())
//
//                val cosStream = document.document.createCOSStream()
//                cosStream.createOutputStream().use {
//                    it.bufferedWriter().use { writer ->
//                        writer.write(xml)
//                    }
//                }
//                cosStream.setItem(COSName.TYPE, COSName.EMBEDDED_FILES)
//                cosStream.setString(COSName.SUBTYPE, "application/xml")
//
//                // TODO: but check 1. FACTUR-X 1.0.0.07 DE.pdf, p. 67: There should be also a /AF File Specification Dictionary /Desc Catalog entry for this attachment
//                val fileSpec = PDComplexFileSpecification()
//                fileSpec.file = attachmentName
//                fileSpec.embeddedFile = PDEmbeddedFile(cosStream)
//
//                fileMap.put(fileSpec.file, fileSpec)
//
//                embeddedFiles.names = fileMap
//
//                names.embeddedFiles = embeddedFiles
//                document.documentCatalog.names = names
//
//                document.save(output)
//            }
//        } catch (e: Throwable) {
//            log.error(e) { "Could not add XML file attachments to PDF" }
//        }
//    }


    override fun addFileAttachment(pdfFile: ByteArray, format: EInvoiceXmlFormat, xml: String, output: OutputStream) {
        val exporter = ZUGFeRDExporterFromA3()
            .setZUGFeRDVersion(2)
            .setProfile(Constants.getProfileNameForFormat(format))
            .setProducer("codinux GmbH & Co. KG")
            .setCreator(System.getProperty("user.name"))
            .setCreatorTool("eInvoicing von codinux GmbH & Co. KG")

        exporter.load(pdfFile)
        exporter.setXML(xml.toByteArray())

        exporter.export(output)
    }

}