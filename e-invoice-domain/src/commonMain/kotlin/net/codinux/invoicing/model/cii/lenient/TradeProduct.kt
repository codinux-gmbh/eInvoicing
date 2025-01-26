package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeProduct(
  @XmlSerialName(
    value = "ID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val id: ID? = null,
  @XmlSerialName(
    value = "GlobalID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val globalID: ID? = null,
  @XmlSerialName(
    value = "SellerAssignedID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sellerAssignedID: ID? = null,
  @XmlSerialName(
    value = "BuyerAssignedID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerAssignedID: ID? = null,
  @XmlSerialName(
    value = "ManufacturerAssignedID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val manufacturerAssignedID: ID? = null,
  @XmlSerialName(
    value = "IndustryAssignedID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val industryAssignedID: ID? = null,
  @XmlSerialName(
    value = "ModelID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val modelID: ID? = null,
  @XmlSerialName(
    value = "Name",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val name: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TradeName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val tradeName: Text? = null,
  @XmlSerialName(
    value = "Description",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val description: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: Code? = null,
  @XmlSerialName(
    value = "NetWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "GrossWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "StatusCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val statusCode: Code? = null,
  @XmlSerialName(
    value = "ProductGroupID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val productGroupID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "NetVolumeMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netVolumeMeasure: Measure? = null,
  @XmlSerialName(
    value = "GrossVolumeMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossVolumeMeasure: Measure? = null,
  @XmlSerialName(
    value = "EndItemTypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val endItemTypeCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "EndItemName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val endItemName: List<Text> = emptyList(),
  @XmlSerialName(
    value = "CustomerAssignedID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val customerAssignedID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "BatchID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val batchID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "AreaDensityMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val areaDensityMeasure: Measure? = null,
  @XmlSerialName(
    value = "UseDescription",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val useDescription: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ConciseDescription",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val conciseDescription: List<Text> = emptyList(),
  @XmlSerialName(
    value = "AdditionalDescription",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalDescription: List<Text> = emptyList(),
  @XmlSerialName(
    value = "BrandName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val brandName: Text? = null,
  @XmlSerialName(
    value = "SubBrandName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val subBrandName: Text? = null,
  @XmlSerialName(
    value = "DrainedNetWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val drainedNetWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "VariableMeasureIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val variableMeasureIndicator: Indicator? = null,
  @XmlSerialName(
    value = "ConfigurableIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val configurableIndicator: Indicator? = null,
  @XmlSerialName(
    value = "ColourCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val colourCode: Code? = null,
  @XmlSerialName(
    value = "ColourDescription",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val colourDescription: List<Text> = emptyList(),
  @XmlSerialName(
    value = "RecyclingTypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val recyclingTypeCode: Code? = null,
  @XmlSerialName(
    value = "UnitTypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val unitTypeCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "ContentUnitQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val contentUnitQuantity: Quantity? = null,
  @XmlSerialName(
    value = "CommonName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val commonName: Text? = null,
  @XmlSerialName(
    value = "ModelName",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val modelName: Text? = null,
  @XmlSerialName(
    value = "Designation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val designation: List<Text> = emptyList(),
  @XmlSerialName(
    value = "FormattedCancellationAnnouncedLaunchDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val formattedCancellationAnnouncedLaunchDateTime: FormattedDateTime? = null,
  @XmlSerialName(
    value = "FormattedLatestProductDataChangeDateTime",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val formattedLatestProductDataChangeDateTime: FormattedDateTime? = null,
  @XmlSerialName(
    value = "ExportIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val exportIndicator: Indicator? = null,
  @XmlSerialName(
    value = "UltimateCustomerAssignedExtensionID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val ultimateCustomerAssignedExtensionID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "SizeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val sizeCode: Code? = null,
  @XmlSerialName(
    value = "ApplicableProductCharacteristic",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableProductCharacteristic: List<ProductCharacteristic> = emptyList(),
  @XmlSerialName(
    value = "ApplicableMaterialGoodsCharacteristic",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableMaterialGoodsCharacteristic: List<MaterialGoodsCharacteristic> = emptyList(),
  @XmlSerialName(
    value = "DesignatedProductClassification",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val designatedProductClassification: List<ProductClassification> = emptyList(),
  @XmlSerialName(
    value = "IndividualTradeProductInstance",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val individualTradeProductInstance: List<TradeProductInstance> = emptyList(),
  @XmlSerialName(
    value = "CertificationEvidenceReferenceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val certificationEvidenceReferenceReferencedDocument:
      List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "InspectionReferenceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val inspectionReferenceReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "OriginTradeCountry",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val originTradeCountry: TradeCountry? = null,
  @XmlSerialName(
    value = "LinearSpatialDimension",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val linearSpatialDimension: List<SpatialDimension> = emptyList(),
  @XmlSerialName(
    value = "MinimumLinearSpatialDimension",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val minimumLinearSpatialDimension: SpatialDimension? = null,
  @XmlSerialName(
    value = "MaximumLinearSpatialDimension",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val maximumLinearSpatialDimension: SpatialDimension? = null,
  @XmlSerialName(
    value = "ManufacturerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val manufacturerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "PresentationSpecifiedBinaryFile",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val presentationSpecifiedBinaryFile: List<SpecifiedBinaryFile> = emptyList(),
  @XmlSerialName(
    value = "MSDSReferenceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val mSDSReferenceReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "AdditionalReferenceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalReferenceReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "LegalRightsOwnerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val legalRightsOwnerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "BrandOwnerTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val brandOwnerTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "IncludedReferencedProduct",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedReferencedProduct: List<ReferencedProduct> = emptyList(),
  @XmlSerialName(
    value = "InformationNote",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val informationNote: List<Note> = emptyList(),
  @XmlSerialName(
    value = "BuyerSuppliedPartsReferenceReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerSuppliedPartsReferenceReferencedDocument: List<ReferencedDocument> = emptyList(),
)
