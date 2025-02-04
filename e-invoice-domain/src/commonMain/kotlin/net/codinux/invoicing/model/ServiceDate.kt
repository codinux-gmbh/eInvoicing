package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
sealed class ServiceDate {

    fun asDeliveryDate(): DeliveryDate? = this as? DeliveryDate

    fun asServicePeriod(): ServicePeriod? = this as? ServicePeriod


    /**
     * Datum, an dem die Lieferung tatsächlich erfolgt bzw. die Dienstleistung tatsächlich erbracht wird.
     */
    @Serializable
    data class DeliveryDate(val deliveryDate: LocalDate) : ServiceDate()

    /**
     * Eine Gruppe von Informationselementen, die Informationen über den Abrechnungszeitraum enthalten.
     */
    @Serializable
    data class ServicePeriod(val startDate: LocalDate, val endDate: LocalDate) : ServiceDate() {
        init {
            require(startDate <= endDate) { "Start date cannot be after end date." }
        }
    }

}