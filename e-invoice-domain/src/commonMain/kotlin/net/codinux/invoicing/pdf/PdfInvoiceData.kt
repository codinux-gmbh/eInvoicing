package net.codinux.invoicing.pdf

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.BigDecimal
import net.codinux.invoicing.model.LocalDate

/**
 * PDFs contain only unstructured data, so it's way harder to get invoice data from PDFs then from structured XML eInvoice files.
 *
 * So we can only guess which is the total amount, which the net and vat amount, which the invoice date, ...
 *
 * Therefor this class' properties all contain 'possible' in their name to reflect this circumstance.
 */
@Serializable
class PdfInvoiceData(
    val potentialTotalAmount: AmountOfMoney,
    val potentialNetAmount: AmountOfMoney? = null,
    val potentialValueAddedTax: AmountOfMoney? = null,
    val potentialValueAddedTaxRate: BigDecimal? = null,

    val potentialIban: String? = null,
    val potentialBic: String? = null,

    val foundAmounts: List<AmountOfMoney> = emptyList(),
    val foundPercentages: List<AmountOfMoney> = emptyList(),

    val foundDates: List<LocalDate> = emptyList(),

    val foundPotentialIbans: List<String> = emptyList(),
    val foundPotentialBics: List<String> = emptyList(),

    val pdfText: String
) {
    override fun toString() = "$potentialTotalAmount"
}