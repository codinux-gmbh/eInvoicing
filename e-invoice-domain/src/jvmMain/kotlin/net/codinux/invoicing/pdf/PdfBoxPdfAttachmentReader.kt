package net.codinux.invoicing.pdf

import net.codinux.invoicing.config.Constants
import net.codinux.log.logger
import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification
import org.apache.xmpbox.xml.DomXmpParser
import kotlin.io.path.Path
import kotlin.io.path.extension

open class PdfBoxPdfAttachmentReader(protected val mapper: PdfMetadataMapper = PdfMetadataMapper()) : PdfAttachmentReader {

    private val log by logger()


    override fun getFileAttachments(pdfFile: ByteArray): PdfAttachmentExtractionResult {
        try {
            loadPdf(pdfFile).use { document ->
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
            return PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.NotAPdf, e)
        }
    }

    protected open fun collectAllAttachmentsRecursively(embeddedFiles: PDNameTreeNode<PDComplexFileSpecification>): Map<String, PDComplexFileSpecification> = buildMap {
        if (embeddedFiles.names != null) {
            putAll(embeddedFiles.names)
        }

        if (embeddedFiles.kids != null) {
            embeddedFiles.kids.forEach {
                putAll(collectAllAttachmentsRecursively(it))
            }
        }
    }


    override fun readPdfMetadata(pdfFile: ByteArray): Pair<PdfDocumentMetadata?, PdfDocumentMetadata?> =
        try {
            loadPdf(pdfFile).use { document ->
                val catalog = document.documentCatalog

                val info = document.documentInformation
                val documentInfoMetadata = PdfDocumentMetadata(
                    info.title, info.author, info.subject,
                    mapper.mapKeywords(info.keywords),
                    info.creator, info.producer,
                    mapper.mapDate(info.creationDate), mapper.mapDate(info.modificationDate),
                    catalog.language, mapper.mapTrapped(info.trapped)
                )

                val metadata = catalog.metadata
                val xmpMetadata = if (metadata == null || metadata.length == 0) null
                else {
                    try {
                        val xmp = DomXmpParser().parse(metadata.exportXMPMetadata())
                        val basicSchema = xmp.xmpBasicSchema
                        val dublinCoreSchema = xmp.dublinCoreSchema
                        val adobePdfSchema = xmp.adobePDFSchema

                        PdfDocumentMetadata(
                            dublinCoreSchema.title, null, dublinCoreSchema.description,
                            mapper.mapKeywords(adobePdfSchema.keywords),
                            basicSchema.creatorTool, adobePdfSchema.producer,
                            mapper.mapDate(basicSchema.createDate ?: basicSchema.metadataDate), mapper.mapDate(basicSchema.modifyDate)
                        )
                    } catch (e: Throwable) {
                        log.error(e) { "Could not read existing PDF XMP metadata" }
                        null
                    }
                }

                documentInfoMetadata to xmpMetadata
            }
        } catch (e: Throwable) {
            log.error(e) { "Could not read PDF metadata" }

            null to null
        }


    protected open fun loadPdf(pdfFile: ByteArray): PDDocument = Loader.loadPDF(pdfFile)

}