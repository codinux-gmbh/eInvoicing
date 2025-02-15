package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class MonetaryTotal(
  @XmlSerialName(
    value = "LineExtensionAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val lineExtensionAmount: Amount? = null,
  @XmlSerialName(
    value = "TaxExclusiveAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val taxExclusiveAmount: Amount? = null,
  @XmlSerialName(
    value = "TaxInclusiveAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val taxInclusiveAmount: Amount? = null,
  @XmlSerialName(
    value = "AllowanceTotalAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val allowanceTotalAmount: Amount? = null,
  @XmlSerialName(
    value = "ChargeTotalAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val chargeTotalAmount: Amount? = null,
  @XmlSerialName(
    value = "PrepaidAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val prepaidAmount: Amount? = null,
  @XmlSerialName(
    value = "PayableRoundingAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val payableRoundingAmount: Amount? = null,
  @XmlSerialName(
    value = "PayableAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val payableAmount: Amount? = null,
  @XmlSerialName(
    value = "PayableAlternativeAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val payableAlternativeAmount: Amount? = null,
)
