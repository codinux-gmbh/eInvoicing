package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TaxSubtotal(
  @XmlSerialName(
    value = "TaxableAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val taxableAmount: Amount? = null,
  @XmlSerialName(
    value = "TaxAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val taxAmount: Amount? = null,
  @XmlSerialName(
    value = "CalculationSequenceNumeric",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val calculationSequenceNumeric: Numeric? = null,
  @XmlSerialName(
    value = "TransactionCurrencyTaxAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transactionCurrencyTaxAmount: Amount? = null,
  @XmlSerialName(
    value = "Percent",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val percent: Numeric? = null,
  @XmlSerialName(
    value = "BaseUnitMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val baseUnitMeasure: Measure? = null,
  @XmlSerialName(
    value = "PerUnitAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val perUnitAmount: Amount? = null,
  @XmlSerialName(
    value = "TierRange",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val tierRange: Text? = null,
  @XmlSerialName(
    value = "TierRatePercent",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val tierRatePercent: Numeric? = null,
  @XmlSerialName(
    value = "TaxCategory",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val taxCategory: TaxCategory? = null,
)
