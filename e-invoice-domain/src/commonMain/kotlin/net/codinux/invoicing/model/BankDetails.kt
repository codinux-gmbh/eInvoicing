package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
class BankDetails(
    /**
     * In the EU / SEPA area the IBAN
     */
    val accountNumber: String,
    /**
     * In the EU / SEPA area the BIC. Optional for countries like Germany
     */
    val bankCode: String? = null,

    val accountHolderName: String? = null,
    val financialInstitutionName: String? = null
) {
    override fun toString() = "${accountHolderName ?: financialInstitutionName ?: ""} ${bankCode ?: ""} $accountHolderName"
}