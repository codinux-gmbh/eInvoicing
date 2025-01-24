package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradePaymentTerms(
  @XmlSerialName(
    value = "ID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val id: PaymentTermsID? = null,
  @XmlSerialName(
    value = "FromEventCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val fromEventCode: PaymentTermsEventTimeReferenceCode? = null,
  @XmlSerialName(
    value = "SettlementPeriodMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val settlementPeriodMeasure: Measure? = null,
  @XmlSerialName(
    value = "Description",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val description: List<Text> = emptyList(),
  @XmlSerialName(
    value = "DueDateDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val dueDateDateTime: DateTime? = null,
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: PaymentTermsTypeCode? = null,
  @XmlSerialName(
    value = "InstructedAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val instructedAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "InstructionTypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val instructionTypeCode: Code? = null,
  @XmlSerialName(
    value = "DirectDebitMandateID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val directDebitMandateID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "PartialPaymentPercent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val partialPaymentPercent: Percent? = null,
  @XmlSerialName(
    value = "PaymentMeansID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val paymentMeansID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "PartialPaymentAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val partialPaymentAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "DueDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val dueDateTime: FormattedDateTime? = null,
  @XmlSerialName(
    value = "BillStartDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val billStartDateTime: DateTime? = null,
  @XmlSerialName(
    value = "PayeeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payeeTradeParty: List<TradeParty> = emptyList(),
  @XmlSerialName(
    value = "ApplicableTradePaymentPenaltyTerms",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradePaymentPenaltyTerms: TradePaymentPenaltyTerms? = null,
  @XmlSerialName(
    value = "ApplicableTradePaymentDiscountTerms",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradePaymentDiscountTerms: TradePaymentDiscountTerms? = null,
)
