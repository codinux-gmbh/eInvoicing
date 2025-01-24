package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class WorkItemComplexDescription(
  @XmlSerialName(
    value = "Abstract",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val `abstract`: List<Text> = emptyList(),
  @XmlSerialName(
    value = "Content",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val content: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ContractualLanguageCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val contractualLanguageCode: Code? = null,
  @XmlSerialName(
    value = "RequestingSpecificationQuery",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val requestingSpecificationQuery: List<SpecificationQuery> = emptyList(),
  @XmlSerialName(
    value = "RespondingSpecificationResponse",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val respondingSpecificationResponse: List<SpecificationResponse> = emptyList(),
  @XmlSerialName(
    value = "SubsetWorkItemComplexDescription",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val subsetWorkItemComplexDescription: WorkItemComplexDescription? = null,
)
