package net.codinux.invoicing.pdf

import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.log.logger
import org.apache.pdfbox.Loader
import org.apache.pdfbox.cos.COSName
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile
import java.io.InputStream
import java.io.OutputStream
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


    fun addFileAttachment(pdfFileInputStream: InputStream, attachmentName: String, xml: String, output: OutputStream) =
        addFileAttachment(pdfFileInputStream.readBytes(), attachmentName, xml, output)

    fun addFileAttachment(pdfFile: ByteArray, attachmentName: String, xml: String, output: OutputStream) {
        try {
            Loader.loadPDF(pdfFile).use { document ->
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

                // TODO: but check 1. FACTUR-X 1.0.0.07 DE.pdf, p. 67: There should be also a /AF File Specification Dictionary /Desc Catalog entry for this attachment
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