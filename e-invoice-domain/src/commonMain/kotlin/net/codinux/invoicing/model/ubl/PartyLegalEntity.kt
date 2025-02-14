package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class PartyLegalEntity(
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
    value = "RegistrationDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val registrationDate: Date? = null,
  @XmlSerialName(
    value = "RegistrationExpirationDate",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val registrationExpirationDate: Date? = null,
  @XmlSerialName(
    value = "CompanyLegalFormCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val companyLegalFormCode: Code? = null,
  @XmlSerialName(
    value = "CompanyLegalForm",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val companyLegalForm: Text? = null,
  @XmlSerialName(
    value = "SoleProprietorshipIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val soleProprietorshipIndicator: Boolean? = null,
  @XmlSerialName(
    value = "CompanyLiquidationStatusCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val companyLiquidationStatusCode: Code? = null,
  @XmlSerialName(
    value = "CorporateStockAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val corporateStockAmount: Amount? = null,
  @XmlSerialName(
    value = "FullyPaidSharesIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val fullyPaidSharesIndicator: Boolean? = null,
  @XmlSerialName(
    value = "RegistrationAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val registrationAddress: Address? = null,
  @XmlSerialName(
    value = "CorporateRegistrationScheme",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val corporateRegistrationScheme: CorporateRegistrationScheme? = null,
  @XmlSerialName(
    value = "HeadOfficeParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val headOfficeParty: Party? = null,
  @XmlSerialName(
    value = "ShareholderParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shareholderParty: List<ShareholderParty> = emptyList(),
)
