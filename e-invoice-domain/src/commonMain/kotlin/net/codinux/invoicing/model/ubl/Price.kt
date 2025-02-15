package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Price(
  @XmlSerialName(
    value = "PriceAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val priceAmount: Amount? = null,
  @XmlSerialName(
    value = "BaseQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val baseQuantity: Quantity? = null,
  @XmlSerialName(
    value = "PriceChangeReason",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val priceChangeReason: List<Text> = emptyList(),
  @XmlSerialName(
    value = "PriceTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val priceTypeCode: Code? = null,
  @XmlSerialName(
    value = "PriceType",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val priceType: Text? = null,
  @XmlSerialName(
    value = "OrderableUnitFactorRate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val orderableUnitFactorRate: Numeric? = null,
  @XmlSerialName(
    value = "ValidityPeriod",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val validityPeriod: List<Period> = emptyList(),
  @XmlSerialName(
    value = "PriceList",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val priceList: PriceList? = null,
  @XmlSerialName(
    value = "AllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val allowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "PricingExchangeRate",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val pricingExchangeRate: ExchangeRate? = null,
)
