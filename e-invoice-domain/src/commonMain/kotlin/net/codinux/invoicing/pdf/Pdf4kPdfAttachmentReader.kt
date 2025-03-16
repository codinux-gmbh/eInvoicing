package net.codinux.invoicing.pdf

import net.codinux.invoicing.config.Constants
import net.codinux.pdf.api.EmbeddedFile
import net.codinux.pdf.core.parser.PdfParser

open class Pdf4kPdfAttachmentReader : PdfAttachmentReader {

    override fun getFileAttachments(pdfFile: ByteArray): PdfAttachmentExtractionResult {
        val parser = PdfParser(pdfFile)

        val embeddedFiles = getEmbeddedFiles(parser)

        val xmlFiles = embeddedFiles.filter { it.mimeType?.endsWith("/xml", ignoreCase = true) == true || it.filename.endsWith(".xml", ignoreCase = true) }
        val nonXmlFiles = (embeddedFiles - xmlFiles).map { mapToPdfEmbeddedFile(it, false) }

        return if (embeddedFiles.isEmpty()) {
            PdfAttachmentExtractionResult.NoAttachments()
        } else if (xmlFiles.isEmpty()) {
            PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.NoXmlAttachments, nonXmlFiles)
        } else {
            val mappedXmlFiles = xmlFiles.map { mapToPdfEmbeddedFile(it, true) }
            PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.HasXmlAttachments, mappedXmlFiles + nonXmlFiles)
        }
    }

    private fun getEmbeddedFiles(parser: PdfParser): List<EmbeddedFile> {
        val document = parser.parseDocument()
        val embeddedFiles = document.embeddedFiles

        return if (embeddedFiles.isNotEmpty()) {
            embeddedFiles
        } else {
            // Pdf4k and its parsing only the most relevant bytes implementation are not battle proven yet and may not
            // find embedded files. In this case parse the whole PDF byte by byte and try to find embedded files in this way
            val eagerlyParsedDocument = parser.parseDocumentEagerly()
            eagerlyParsedDocument.embeddedFiles
        }
    }

    override fun readPdfMetadata(pdfFile: ByteArray): Pair<PdfDocumentMetadata?, PdfDocumentMetadata?> {
        return null to null // TODO
    }


    protected open fun mapToPdfEmbeddedFile(embeddedFile: EmbeddedFile, isXml: Boolean) = PdfAttachment(
        embeddedFile.filename,
        isXml,
        if (isXml) Constants.isKnownEInvoiceXmlAttachmentName(embeddedFile.filename) else false,
        if (isXml) embeddedFile.fileContentAsString else null,
    )

}