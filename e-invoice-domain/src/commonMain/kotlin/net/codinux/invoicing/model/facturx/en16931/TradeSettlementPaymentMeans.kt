package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeSettlementPaymentMeans(
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: PaymentMeansCode,
  @XmlSerialName(
    value = "Information",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val information: Text? = null,
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
  val payeePartyCreditorFinancialAccount: CreditorFinancialAccount? = null,
  @XmlSerialName(
    value = "PayeeSpecifiedCreditorFinancialInstitution",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val payeeSpecifiedCreditorFinancialInstitution: CreditorFinancialInstitution? = null,
)
