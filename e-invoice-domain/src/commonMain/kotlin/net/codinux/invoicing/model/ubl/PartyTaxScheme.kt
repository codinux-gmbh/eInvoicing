package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class PartyTaxScheme(
  @XmlSerialName(
    value = "RegistrationName",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val registrationName: Text? = null,
  @XmlSerialName(
    value = "CompanyID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val companyID: Identifier? = null,
  @XmlSerialName(
    value = "TaxLevelCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val taxLevelCode: Code? = null,
  @XmlSerialName(
    value = "ExemptionReasonCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val exemptionReasonCode: Code? = null,
  @XmlSerialName(
    value = "ExemptionReason",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val exemptionReason: List<Text> = emptyList(),
  @XmlSerialName(
    value = "RegistrationAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val registrationAddress: Address? = null,
  @XmlSerialName(
    value = "TaxScheme",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val taxScheme: TaxScheme? = null,
)
