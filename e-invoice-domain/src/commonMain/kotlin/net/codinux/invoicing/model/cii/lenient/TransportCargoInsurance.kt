package net.codinux.invoicing.model.cii.lenient

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TransportCargoInsurance(
  @XmlSerialName(
    value = "CoverageCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val coverageCode: Code? = null,
  @XmlSerialName(
    value = "CoverageDescription",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val coverageDescription: Text? = null,
  @XmlSerialName(
    value = "ContractGeneralConditions",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val contractGeneralConditions: Text? = null,
  @XmlSerialName(
    value = "CoverageTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val coverageTradeParty: TradeParty? = null,
)
