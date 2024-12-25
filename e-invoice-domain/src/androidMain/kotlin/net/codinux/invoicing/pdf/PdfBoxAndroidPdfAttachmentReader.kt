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


    override fun getFileAttachments(pdfFile: ByteArray): PdfAttachmentExtractionResult {
        try {
            PDDocument.load(pdfFile).use { document ->
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

}