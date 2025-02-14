package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ExchangeRate(
  @XmlSerialName(
    value = "SourceCurrencyCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val sourceCurrencyCode: Code,
  @XmlSerialName(
    value = "SourceCurrencyBaseRate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val sourceCurrencyBaseRate: Numeric? = null,
  @XmlSerialName(
    value = "TargetCurrencyCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val targetCurrencyCode: Code,
  @XmlSerialName(
    value = "TargetCurrencyBaseRate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val targetCurrencyBaseRate: Numeric? = null,
  @XmlSerialName(
    value = "ExchangeMarketID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val exchangeMarketID: Identifier? = null,
  @XmlSerialName(
    value = "CalculationRate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val calculationRate: Numeric? = null,
  @XmlSerialName(
    value = "MathematicOperatorCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val mathematicOperatorCode: Code? = null,
  @XmlSerialName(
    value = "Date",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val date: Date? = null,
  @XmlSerialName(
    value = "ForeignExchangeContract",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val foreignExchangeContract: Contract? = null,
)
