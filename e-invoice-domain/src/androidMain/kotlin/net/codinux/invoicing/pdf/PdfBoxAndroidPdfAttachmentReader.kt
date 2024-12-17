package net.codinux.invoicing.pdf

import android.content.Context
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.cos.COSName
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDDocumentNameDictionary
import com.tom_roush.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode
import com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile
import net.codinux.invoicing.config.Constants
import net.codinux.log.logger
import java.io.InputStream
import java.io.OutputStream
import kotlin.io.path.Path
import kotlin.io.path.extension

class PdfBoxAndroidPdfAttachmentReader(
    applicationContext: Context
) : PdfAttachmentReader {

    init {
        PDFBoxResourceLoader.init(applicationContext)
    }

    private val log by logger()


    override fun getFileAttachments(pdfInputStream: InputStream): PdfAttachmentExtractionResult {
        try {
            PDDocument.load(pdfInputStream).use { document ->
                val names = PDDocumentNameDictionary(document.documentCatalog) // documentCatalog is never null
                val embeddedFiles = names.embeddedFiles
                if (embeddedFiles == null) {
                    return PdfAttachmentExtractionResult.NoAttachments()
                }

                val attachments = collectAllAttachmentsRecursively(embeddedFiles)
                if (attachments.isEmpty()) {
                    return PdfAttachmentExtractionResult.NoAttachments()
                }

                val xmlAttachmentsMap = attachments.filter { (name, fileSpec) -> Path(name).extension == "xml" || Path(fileSpec.filename).extension == "xml" }
                val nonXmlAttachments = attachments.filterNot { (name, _) -> xmlAttachmentsMap.containsKey(name) }
                    .map { (name, _) -> PdfAttachment(name, false, false, null) }
                if (xmlAttachmentsMap.isEmpty()) {
                    return PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.NoXmlAttachments, nonXmlAttachments)
                }

                val xmlAttachments = xmlAttachmentsMap.map { (name, fileSpec) ->
                    // fileSpec.embeddedFile.cosObject.toTextString() seems to get the encoding wrong
                    val xml: String? = if (fileSpec.embeddedFile != null) String(fileSpec.embeddedFile.toByteArray())
                                    else null
                    PdfAttachment(name, true, name.lowercase() in Constants.KnownEInvoiceXmlAttachmentNames, xml)
                }

                return PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.HasXmlAttachments, xmlAttachments + nonXmlAttachments)
            }
        } catch (e: Throwable) {
            log.info(e) { "Could not list XML file attachments of PDF" }
            return PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.NotAPdf, emptyList())
        }
    }

    private fun collectAllAttachmentsRecursively(embeddedFiles: PDNameTreeNode<PDComplexFileSpecification>): Map<String, PDComplexFileSpecification> = buildMap {
        if (embeddedFiles.names != null) {
            putAll(embeddedFiles.names)
        }

        if (embeddedFiles.kids != null) {
            embeddedFiles.kids.forEach {
                putAll(collectAllAttachmentsRecursively(it))
            }
        }
    }


    fun addFileAttachment(pdfFileInputStream: InputStream, attachmentName: String, xml: String, output: OutputStream) =
        addFileAttachment(pdfFileInputStream.readBytes(), attachmentName, xml, output)

    fun addFileAttachment(pdfFile: ByteArray, attachmentName: String, xml: String, output: OutputStream) {
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
                fileSpec.file = attachmentName
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