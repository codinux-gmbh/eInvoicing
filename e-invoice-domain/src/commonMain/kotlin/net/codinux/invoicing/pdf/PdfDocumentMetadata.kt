package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.Instant

@Serializable
data class PdfDocumentMetadata(
    val title: String? = null,
    val author: String? = null,
    val subject: String? = null,
    val keywords: List<String> = emptyList(),
    val creatorTool: String? = null,
    val producer: String? = null,

    val creationDate: Instant? = null,
    val modificationDate: Instant? = null,

    val language: String? = null,

    /**
     * the trapped flag in a PDF document indicates whether the document has been “trapped”, i.e. corrected for slight
     * color misregistrations.
     *
     * Possible values are: True, False, Unknown.
     */
    val trapped: Boolean? = null,
)
