package net.codinux.invoicing.model

import kotlinx.serialization.Serializable
import net.codinux.invoicing.model.codes.Country

@Serializable
class Party(
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
    fun copy(name: String = this.name, address: String = this.address, additionalAddressLine: String? = this.additionalAddressLine,
             postalCode: String? = this.postalCode, city: String = this.city, country: Country = this.country,
             vatId: String? = this.vatId,
             email: String? = this.email, phone: String? = this.phone, fax: String? = this.fax, contactName: String? = this.contactName,
             bankDetails: BankDetails? = this.bankDetails) =
        Party(name, address, additionalAddressLine, postalCode, city, country, vatId, email, phone, fax, contactName, bankDetails)

    override fun toString() = "$name, $city"
}