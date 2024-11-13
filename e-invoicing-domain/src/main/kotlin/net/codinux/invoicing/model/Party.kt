package net.codinux.invoicing.model

class Party(
    val name: String,

    val streetName: String,
    val houseNumber: String,
    var postalCode: String?,
    val city: String,
    val country: String? = null,

    val taxNumber: String? = null, // better name like vatTaxNumber?

    val email: String? = null,
//    var telephoneNumber: String? = null, // simply telephone?
//    var website: String? = null,
) {
    override fun toString() = "$name, $city"
}