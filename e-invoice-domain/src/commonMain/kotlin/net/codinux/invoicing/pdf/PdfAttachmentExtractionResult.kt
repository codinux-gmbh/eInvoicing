package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.dto.SerializableException
import net.codinux.kotlin.annotation.JsonIgnore

@Serializable
data class PdfAttachmentExtractionResult(
    val type: PdfAttachmentExtractionResultType,
    val attachments: List<PdfAttachment>,
    val readError: SerializableException? = null
) {
    companion object {
        fun NoAttachments() =
            PdfAttachmentExtractionResult(PdfAttachmentExtractionResultType.NoAttachments, emptyList())
    }


    constructor(type: PdfAttachmentExtractionResultType, readError: Throwable) : this(type, emptyList(), SerializableException(readError))


    @Suppress("RUNTIME_ANNOTATION_NOT_SUPPORTED")
    @get:JsonIgnore // not that obvious, but Jackson affords @get:JsonIgnore instead of @delegate:JsonIgnore on delegates in order to work
    val invoiceXml: String? by lazy { attachments.firstOrNull { it.isProbablyEN16931InvoiceXml && it.xml != null }?.xml }

    override fun toString() = "$type: ${attachments.joinToString()}"
}