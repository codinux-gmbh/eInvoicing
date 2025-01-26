package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeSettlementHeaderMonetarySummation(
  @XmlSerialName(
    value = "LineTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val lineTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "ChargeTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val chargeTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "AllowanceTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val allowanceTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TaxBasisTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val taxBasisTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TaxTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val taxTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "RoundingAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val roundingAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "GrandTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grandTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "InformationAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val informationAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TotalPrepaidAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalPrepaidAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TotalDiscountAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalDiscountAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TotalAllowanceChargeAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalAllowanceChargeAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "DuePayableAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val duePayableAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "RetailValueExcludingTaxInformationAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val retailValueExcludingTaxInformationAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TotalDepositFeeInformationAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalDepositFeeInformationAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "ProductValueExcludingTobaccoTaxInformationAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val productValueExcludingTobaccoTaxInformationAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TotalRetailValueInformationAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalRetailValueInformationAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "GrossLineTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossLineTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "NetLineTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netLineTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "NetIncludingTaxesLineTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netIncludingTaxesLineTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "InsuranceChargeTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val insuranceChargeTotalAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "IncludingTaxesLineTotalAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includingTaxesLineTotalAmount: List<Amount> = emptyList(),
)
