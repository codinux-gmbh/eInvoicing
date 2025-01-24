package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class BasicWorkItem(
  @XmlSerialName(
    value = "ID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val id: ID? = null,
  @XmlSerialName(
    value = "ReferenceID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val referenceID: ID? = null,
  @XmlSerialName(
    value = "PrimaryClassificationCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val primaryClassificationCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "AlternativeClassificationCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val alternativeClassificationCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "Comment",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val comment: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TotalQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalQuantity: Quantity? = null,
  @XmlSerialName(
    value = "TotalQuantityClassificationCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalQuantityClassificationCode: Code? = null,
  @XmlSerialName(
    value = "Index",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val index: Text? = null,
  @XmlSerialName(
    value = "RequestedActionCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val requestedActionCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "PriceListItemID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val priceListItemID: ID? = null,
  @XmlSerialName(
    value = "ContractualLanguageCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val contractualLanguageCode: Code? = null,
  @XmlSerialName(
    value = "ActualWorkItemComplexDescription",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val actualWorkItemComplexDescription: List<WorkItemComplexDescription> = emptyList(),
  @XmlSerialName(
    value = "TotalQuantityWorkItemQuantityAnalysis",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalQuantityWorkItemQuantityAnalysis: List<WorkItemQuantityAnalysis> = emptyList(),
  @XmlSerialName(
    value = "UnitCalculatedPrice",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val unitCalculatedPrice: List<CalculatedPrice> = emptyList(),
  @XmlSerialName(
    value = "TotalCalculatedPrice",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalCalculatedPrice: List<CalculatedPrice> = emptyList(),
  @XmlSerialName(
    value = "ChangedRecordedStatus",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val changedRecordedStatus: List<RecordedStatus> = emptyList(),
  @XmlSerialName(
    value = "ItemBasicWorkItem",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val itemBasicWorkItem: List<BasicWorkItem> = emptyList(),
  @XmlSerialName(
    value = "ReferencedSpecifiedBinaryFile",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val referencedSpecifiedBinaryFile: List<SpecifiedBinaryFile> = emptyList(),
)
