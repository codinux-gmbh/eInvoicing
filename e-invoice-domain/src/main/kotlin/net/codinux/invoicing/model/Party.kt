package net.codinux.invoicing.model

class Party(
    val name: String,

    /**
     * Party's street and house number.
     */
    val address: String,
    val additionalAddressLine: String? = null,
    var postalCode: String?,
    val city: String,
    /**
     * Two letter country ISO code, e.g. "us" for USA, "fr" for France, ...
     */
    val countryIsoCode: String? = null, // TODO: use the full country name here and map to ISO code in MustangMapper?

    val vatId: String? = null,

    // actually there can be multiple contacts in eInvoice data model, all containing an email, phone, fax and contact name
    val email: String? = null,
    val phone: String? = null,
    val fax: String? = null,
    val contactName: String? = null,

    // actually there can be multiple bankDetails in eInvoice data model
    val bankDetails: BankDetails? = null,
) {
    override fun toString() = "$name, $city"
}