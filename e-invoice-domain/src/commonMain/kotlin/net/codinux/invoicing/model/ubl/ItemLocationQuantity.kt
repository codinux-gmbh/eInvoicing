package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ItemLocationQuantity(
  @XmlSerialName(
    value = "LeadTimeMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val leadTimeMeasure: Measure? = null,
  @XmlSerialName(
    value = "MinimumQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val minimumQuantity: Quantity? = null,
  @XmlSerialName(
    value = "MaximumQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val maximumQuantity: Quantity? = null,
  @XmlSerialName(
    value = "HazardousRiskIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val hazardousRiskIndicator: Boolean? = null,
  @XmlSerialName(
    value = "TradingRestrictions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val tradingRestrictions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ApplicableTerritoryAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val applicableTerritoryAddress: List<Address> = emptyList(),
  @XmlSerialName(
    value = "Price",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val price: Price? = null,
  @XmlSerialName(
    value = "DeliveryUnit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryUnit: List<DeliveryUnit> = emptyList(),
  @XmlSerialName(
    value = "ApplicableTaxCategory",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val applicableTaxCategory: List<TaxCategory> = emptyList(),
  @XmlSerialName(
    value = "Package",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val `package`: Package? = null,
  @XmlSerialName(
    value = "AllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val allowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "DependentPriceReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val dependentPriceReference: DependentPriceReference? = null,
)
