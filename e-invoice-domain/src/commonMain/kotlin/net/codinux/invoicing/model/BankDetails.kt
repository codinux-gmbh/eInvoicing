package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
class BankDetails(
    /**
     * In the EU / SEPA area the IBAN
     */
    val accountNumber: String,
    /**
     * The domestic bank id (like FedwireRoutingNumberID in USA, RussianCentralBankID in Russia, NCCID in South Africa, ...)
     * or international bank id (like BIC in SEPA area).
     *
     * Optional for countries like Germany
     */
    val bankCode: String? = null, // TODO: rename to (domestic)BankId

    val accountHolderName: String? = null,
    val financialInstitutionName: String? = null
) {
    override fun toString() = "${accountHolderName ?: financialInstitutionName ?: ""} ${bankCode ?: ""} $accountHolderName"
}