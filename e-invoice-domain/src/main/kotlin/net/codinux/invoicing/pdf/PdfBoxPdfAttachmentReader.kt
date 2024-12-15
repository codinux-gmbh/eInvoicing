package net.codinux.invoicing.pdf

import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.log.logger
import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification
import java.io.InputStream
import kotlin.io.path.Path
import kotlin.io.path.extension

class PdfBoxPdfAttachmentReader : PdfAttachmentReader {

    private val log by logger()

    override fun getFileAttachments(pdfInputStream: InputStream): PdfAttachmentExtractionResult {
        try {
            Loader.loadPDF(pdfInputStream.readAllBytes()).use { document ->
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
                    PdfAttachment(name, true, name.lowercase() in EInvoiceReader.KnownEInvoiceXmlAttachmentNames, xml)
                }

                return PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.HasXmlAttachments, xmlAttachments + nonXmlAttachments)
            }
        } catch (e: Throwable) {
            log.info(e) { "Reading PDF attachments failed" }
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