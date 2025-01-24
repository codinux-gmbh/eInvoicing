package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ReferencedDocument(
  @XmlSerialName(
    value = "IssuerAssignedID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val issuerAssignedID: ID? = null,
  @XmlSerialName(
    value = "URIID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val uriid: ID? = null,
  @XmlSerialName(
    value = "StatusCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val statusCode: DocumentStatusCode? = null,
  @XmlSerialName(
    value = "CopyIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val copyIndicator: Indicator? = null,
  @XmlSerialName(
    value = "LineID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val lineID: ID? = null,
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: DocumentCode? = null,
  @XmlSerialName(
    value = "GlobalID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val globalID: ID? = null,
  @XmlSerialName(
    value = "RevisionID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val revisionID: ID? = null,
  @XmlSerialName(
    value = "Name",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val name: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ReceiptDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val receiptDateTime: DateTime? = null,
  @XmlSerialName(
    value = "AttachmentBinaryObject",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val attachmentBinaryObject: List<BinaryObject> = emptyList(),
  @XmlSerialName(
    value = "Information",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val information: List<Text> = emptyList(),
  @XmlSerialName(
    value = "CategoryCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val categoryCode: Code? = null,
  @XmlSerialName(
    value = "ReferenceTypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val referenceTypeCode: ReferenceCode? = null,
  @XmlSerialName(
    value = "SectionName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sectionName: List<Text> = emptyList(),
  @XmlSerialName(
    value = "PreviousRevisionID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val previousRevisionID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "FormattedIssueDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val formattedIssueDateTime: FormattedDateTime? = null,
  @XmlSerialName(
    value = "PageID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val pageID: ID? = null,
  @XmlSerialName(
    value = "SubordinateLineID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val subordinateLineID: ID? = null,
  @XmlSerialName(
    value = "SubtypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val subtypeCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "EffectiveSpecifiedPeriod",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val effectiveSpecifiedPeriod: SpecifiedPeriod? = null,
  @XmlSerialName(
    value = "IssuerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val issuerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "AttachedSpecifiedBinaryFile",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val attachedSpecifiedBinaryFile: List<SpecifiedBinaryFile> = emptyList(),
  @XmlSerialName(
    value = "IncludedNote",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedNote: List<Note> = emptyList(),
)
