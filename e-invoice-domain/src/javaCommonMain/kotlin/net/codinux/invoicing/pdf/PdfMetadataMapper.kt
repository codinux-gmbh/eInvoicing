package net.codinux.invoicing.pdf

import net.codinux.invoicing.model.Instant
import net.codinux.invoicing.model.toEInvoicingInstant
import java.util.*

open class PdfMetadataMapper {

    open fun mapKeywords(keywords: String?): List<String> =
        keywords?.split(',')?.map { it.trim() }
            ?: emptyList()

    open fun mapDate(date: Calendar?): Instant? = date?.let {
        date.toInstant().toEInvoicingInstant()
    }

    open fun mapTrapped(trapped: String?): Boolean? = when (trapped?.lowercase()) {
        "true" -> true
        "false" -> false
        "unknown" -> null
        else -> null
    }

}