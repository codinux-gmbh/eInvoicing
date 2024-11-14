package net.codinux.invoicing.model

class Party(
    val name: String,

    /**
     * Party's street and house number.
     */
    val street: String,
    var postalCode: String?,
    val city: String,
    /**
     * Two letter country ISO code, e.g. "us" for USA, "fr" for France, ...
     */
    val countryIsoCode: String? = null, // TODO: use the full country name here and map to ISO code in MustangMapper?

    val vatId: String? = null,

    // actually there can be multiple contacts in eInvoice data model, all containing an email, phone, fax and contact name
    val email: String? = null,
    var phone: String? = null,
    var fax: String? = null,
    var contactName: String? = null,
) {
    override fun toString() = "$name, $city"
}