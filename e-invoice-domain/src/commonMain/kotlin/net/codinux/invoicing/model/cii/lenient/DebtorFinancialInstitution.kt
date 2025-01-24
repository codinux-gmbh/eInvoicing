package net.codinux.invoicing.model.cii.lenient

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class DebtorFinancialInstitution(
  @XmlSerialName(
    value = "BICID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val bicid: ID? = null,
  @XmlSerialName(
    value = "ClearingSystemName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val clearingSystemName: Text? = null,
  @XmlSerialName(
    value = "CHIPSUniversalID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val cHIPSUniversalID: ID? = null,
  @XmlSerialName(
    value = "NewZealandNCCID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val newZealandNCCID: ID? = null,
  @XmlSerialName(
    value = "IrishNSCID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val irishNSCID: ID? = null,
  @XmlSerialName(
    value = "UKSortCodeID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val uKSortCodeID: ID? = null,
  @XmlSerialName(
    value = "CHIPSParticipantID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val cHIPSParticipantID: ID? = null,
  @XmlSerialName(
    value = "SwissBCID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val swissBCID: ID? = null,
  @XmlSerialName(
    value = "FedwireRoutingNumberID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val fedwireRoutingNumberID: ID? = null,
  @XmlSerialName(
    value = "PortugueseNCCID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val portugueseNCCID: ID? = null,
  @XmlSerialName(
    value = "RussianCentralBankID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val russianCentralBankID: ID? = null,
  @XmlSerialName(
    value = "ItalianDomesticID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val italianDomesticID: ID? = null,
  @XmlSerialName(
    value = "AustrianBankleitzahlID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val austrianBankleitzahlID: ID? = null,
  @XmlSerialName(
    value = "CanadianPaymentsAssociationID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val canadianPaymentsAssociationID: ID? = null,
  @XmlSerialName(
    value = "SICID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sicid: ID? = null,
  @XmlSerialName(
    value = "GermanBankleitzahlID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val germanBankleitzahlID: ID? = null,
  @XmlSerialName(
    value = "SpanishDomesticInterbankingID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val spanishDomesticInterbankingID: ID? = null,
  @XmlSerialName(
    value = "SouthAfricanNCCID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val southAfricanNCCID: ID? = null,
  @XmlSerialName(
    value = "HongKongBankID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val hongKongBankID: ID? = null,
  @XmlSerialName(
    value = "AustralianBSBID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val australianBSBID: ID? = null,
  @XmlSerialName(
    value = "IndianFinancialSystemID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val indianFinancialSystemID: ID? = null,
  @XmlSerialName(
    value = "HellenicBankID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val hellenicBankID: ID? = null,
  @XmlSerialName(
    value = "PolishNationalClearingID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val polishNationalClearingID: ID? = null,
  @XmlSerialName(
    value = "Name",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val name: Text? = null,
  @XmlSerialName(
    value = "JapanFinancialInstitutionCommonID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val japanFinancialInstitutionCommonID: ID? = null,
  @XmlSerialName(
    value = "LocationFinancialInstitutionAddress",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val locationFinancialInstitutionAddress: FinancialInstitutionAddress? = null,
  @XmlSerialName(
    value = "SubDivisionBranchFinancialInstitution",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val subDivisionBranchFinancialInstitution: BranchFinancialInstitution? = null,
)
