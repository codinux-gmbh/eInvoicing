package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.codes.Country

@Serializable
data class Party(
    val name: String,

    /**
     * Party's street and house number.
     */
    val address: String,
    val additionalAddressLine: String? = null,
    var postalCode: String? = null,
    val city: String,
    val country: Country = Country.Germany,

    val vatId: String? = null,

    // actually there can be multiple contacts in eInvoice data model, all containing an email, phone, fax and contact name
    val email: String? = null,
    val phone: String? = null,
    val fax: String? = null,
    val contactName: String? = null,

    // actually there can be multiple bankDetails in eInvoice data model
    val bankDetails: BankDetails? = null,

    /**
     * Currently only used to display the logo of the supplier in generated PDF. There is an element for it in Factur-X
     * and XRechnung, but the underlying library doesn't map it.
     */
    val logoUrl: String? = null,
) {
    override fun toString() = "$name, $city"
}