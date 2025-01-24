package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ExchangedDocumentContext(
  @XmlSerialName(
    value = "SpecifiedTransactionID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedTransactionID: ID? = null,
  @XmlSerialName(
    value = "TestIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val testIndicator: Indicator? = null,
  @XmlSerialName(
    value = "BusinessProcessSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val businessProcessSpecifiedDocumentContextParameter:
      List<DocumentContextParameter> = emptyList(),
  @XmlSerialName(
    value = "BIMSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val bIMSpecifiedDocumentContextParameter: List<DocumentContextParameter> = emptyList(),
  @XmlSerialName(
    value = "ScenarioSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val scenarioSpecifiedDocumentContextParameter:
      List<DocumentContextParameter> = emptyList(),
  @XmlSerialName(
    value = "ApplicationSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicationSpecifiedDocumentContextParameter:
      List<DocumentContextParameter> = emptyList(),
  @XmlSerialName(
    value = "GuidelineSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val guidelineSpecifiedDocumentContextParameter:
      List<DocumentContextParameter> = emptyList(),
  @XmlSerialName(
    value = "SubsetSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val subsetSpecifiedDocumentContextParameter: List<DocumentContextParameter> = emptyList(),
  @XmlSerialName(
    value = "MessageStandardSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val messageStandardSpecifiedDocumentContextParameter: DocumentContextParameter? = null,
  @XmlSerialName(
    value = "UserSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val userSpecifiedDocumentContextParameter: List<DocumentContextParameter> = emptyList(),
)
