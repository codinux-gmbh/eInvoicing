package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class CardAccount(
  @XmlSerialName(
    value = "PrimaryAccountNumberID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val primaryAccountNumberID: Identifier? = null,
  @XmlSerialName(
    value = "NetworkID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val networkID: Identifier? = null,
  @XmlSerialName(
    value = "CardTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val cardTypeCode: Code? = null,
  @XmlSerialName(
    value = "ValidityStartDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val validityStartDate: Date? = null,
  @XmlSerialName(
    value = "ExpiryDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val expiryDate: Date? = null,
  @XmlSerialName(
    value = "IssuerID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val issuerID: Identifier? = null,
  @XmlSerialName(
    value = "IssueNumberID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val issueNumberID: Identifier? = null,
  @XmlSerialName(
    value = "CV2ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val cV2ID: Identifier? = null,
  @XmlSerialName(
    value = "CardChipCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val cardChipCode: Code? = null,
  @XmlSerialName(
    value = "ChipApplicationID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val chipApplicationID: Identifier? = null,
  @XmlSerialName(
    value = "HolderName",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val holderName: Text? = null,
)
