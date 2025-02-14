package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Party(
  @XmlSerialName(
    value = "MarkCareIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val markCareIndicator: Boolean? = null,
  @XmlSerialName(
    value = "MarkAttentionIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val markAttentionIndicator: Boolean? = null,
  @XmlSerialName(
    value = "WebsiteURI",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val websiteURI: Identifier? = null,
  @XmlSerialName(
    value = "LogoReferenceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val logoReferenceID: Identifier? = null,
  @XmlSerialName(
    value = "EndpointID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val endpointID: Identifier? = null,
  @XmlSerialName(
    value = "IndustryClassificationCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val industryClassificationCode: Code? = null,
  @XmlSerialName(
    value = "PartyIdentification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val partyIdentification: List<PartyIdentification> = emptyList(),
  @XmlSerialName(
    value = "PartyName",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val partyName: List<PartyName> = emptyList(),
  @XmlSerialName(
    value = "Language",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val language: Language? = null,
  @XmlSerialName(
    value = "PostalAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val postalAddress: Address? = null,
  @XmlSerialName(
    value = "PhysicalLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val physicalLocation: Location? = null,
  @XmlSerialName(
    value = "PartyTaxScheme",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val partyTaxScheme: List<PartyTaxScheme> = emptyList(),
  @XmlSerialName(
    value = "PartyLegalEntity",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val partyLegalEntity: List<PartyLegalEntity> = emptyList(),
  @XmlSerialName(
    value = "Contact",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val contact: Contact? = null,
  @XmlSerialName(
    value = "Person",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val person: List<Person> = emptyList(),
  @XmlSerialName(
    value = "AgentParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val agentParty: Party? = null,
  @XmlSerialName(
    value = "ServiceProviderParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val serviceProviderParty: List<ServiceProviderParty> = emptyList(),
  @XmlSerialName(
    value = "PowerOfAttorney",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val powerOfAttorney: List<PowerOfAttorney> = emptyList(),
  @XmlSerialName(
    value = "FinancialAccount",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val financialAccount: FinancialAccount? = null,
)
