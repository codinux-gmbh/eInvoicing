package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SupplyChainPackaging(
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: PackageTypeCode? = null,
  @XmlSerialName(
    value = "Type",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val type: Text? = null,
  @XmlSerialName(
    value = "Description",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val description: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ConditionCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val conditionCode: Code? = null,
  @XmlSerialName(
    value = "DisposalMethodCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val disposalMethodCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "WeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val weightMeasure: List<Measure> = emptyList(),
  @XmlSerialName(
    value = "MaximumStackabilityQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val maximumStackabilityQuantity: Quantity? = null,
  @XmlSerialName(
    value = "MaximumStackabilityWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val maximumStackabilityWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "CustomerFacingTotalUnitQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val customerFacingTotalUnitQuantity: Quantity? = null,
  @XmlSerialName(
    value = "LayerTotalUnitQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val layerTotalUnitQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ContentLayerQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val contentLayerQuantity: Quantity? = null,
  @XmlSerialName(
    value = "AdditionalInstructionCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalInstructionCode: List<Code> = emptyList(),
  @XmlSerialName(
    value = "AdditionalInstructionIndicator",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val additionalInstructionIndicator: Indicator? = null,
  @XmlSerialName(
    value = "LinearSpatialDimension",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val linearSpatialDimension: SpatialDimension? = null,
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
    value = "SpecifiedPackagingMarking",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedPackagingMarking: List<PackagingMarking> = emptyList(),
  @XmlSerialName(
    value = "ApplicableMaterialGoodsCharacteristic",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableMaterialGoodsCharacteristic: List<MaterialGoodsCharacteristic> = emptyList(),
  @XmlSerialName(
    value = "ApplicableDisposalInstructions",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableDisposalInstructions: List<DisposalInstructions> = emptyList(),
  @XmlSerialName(
    value = "ApplicableReturnableAssetInstructions",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableReturnableAssetInstructions: List<ReturnableAssetInstructions> = emptyList(),
)
