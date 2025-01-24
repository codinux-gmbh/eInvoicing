package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeCurrencyExchange(
  @XmlSerialName(
    value = "SourceCurrencyCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sourceCurrencyCode: CurrencyCode? = null,
  @XmlSerialName(
    value = "SourceUnitBasisNumeric",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sourceUnitBasisNumeric: Numeric? = null,
  @XmlSerialName(
    value = "TargetCurrencyCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val targetCurrencyCode: CurrencyCode? = null,
  @XmlSerialName(
    value = "TargetUnitBaseNumeric",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val targetUnitBaseNumeric: Numeric? = null,
  @XmlSerialName(
    value = "MarketID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val marketID: ID? = null,
  @XmlSerialName(
    value = "ConversionRate",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val conversionRate: Rate? = null,
  @XmlSerialName(
    value = "ConversionRateDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val conversionRateDateTime: DateTime? = null,
  @XmlSerialName(
    value = "AssociatedReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val associatedReferencedDocument: List<ReferencedDocument> = emptyList(),
)
