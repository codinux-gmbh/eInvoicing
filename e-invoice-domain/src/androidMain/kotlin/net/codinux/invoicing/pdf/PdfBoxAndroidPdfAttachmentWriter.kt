package net.codinux.invoicing.pdf

import com.tom_roush.pdfbox.cos.COSName
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDDocumentNameDictionary
import com.tom_roush.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile
import net.codinux.invoicing.config.Constants
import net.codinux.invoicing.model.EInvoiceXmlFormat
import net.codinux.log.logger
import java.io.OutputStream

class PdfBoxAndroidPdfAttachmentWriter : PdfAttachmentWriter {

    private val log by logger()


    override fun addFileAttachment(pdfFile: ByteArray, format: EInvoiceXmlFormat, xml: String, output: OutputStream) {
        try {
            PDDocument.load(pdfFile).use { document ->
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
                fileSpec.file = Constants.getFilenameForFormat(format)
                fileSpec.embeddedFile = PDEmbeddedFile(cosStream)

                fileMap.put(fileSpec.file, fileSpec)

                embeddedFiles.names = fileMap

                names.embeddedFiles = embeddedFiles
                document.documentCatalog.names = names

                document.save(output)
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not add XML file attachments to PDF" }
        }
    }

}