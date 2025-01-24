package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeSettlementPaymentMeans(
  @XmlSerialName(
    value = "PaymentChannelCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val paymentChannelCode: PaymentMeansChannelCode? = null,
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: PaymentMeansCode? = null,
  @XmlSerialName(
    value = "GuaranteeMethodCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val guaranteeMethodCode: PaymentGuaranteeMeansCode? = null,
  @XmlSerialName(
    value = "PaymentMethodCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val paymentMethodCode: Code? = null,
  @XmlSerialName(
    value = "Information",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val information: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val id: List<ID> = emptyList(),
  @XmlSerialName(
    value = "ApplicableTradeSettlementFinancialCard",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTradeSettlementFinancialCard: TradeSettlementFinancialCard? = null,
  @XmlSerialName(
    value = "PayerPartyDebtorFinancialAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payerPartyDebtorFinancialAccount: DebtorFinancialAccount? = null,
  @XmlSerialName(
    value = "PayeePartyCreditorFinancialAccount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payeePartyCreditorFinancialAccount: List<CreditorFinancialAccount> = emptyList(),
  @XmlSerialName(
    value = "PayerSpecifiedDebtorFinancialInstitution",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payerSpecifiedDebtorFinancialInstitution: DebtorFinancialInstitution? = null,
  @XmlSerialName(
    value = "PayeeSpecifiedCreditorFinancialInstitution",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payeeSpecifiedCreditorFinancialInstitution: CreditorFinancialInstitution? = null,
)
