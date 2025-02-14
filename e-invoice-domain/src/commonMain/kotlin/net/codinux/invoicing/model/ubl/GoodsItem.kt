package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class GoodsItem(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "SequenceNumberID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val sequenceNumberID: Identifier? = null,
  @XmlSerialName(
    value = "Description",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val description: List<Text> = emptyList(),
  @XmlSerialName(
    value = "HazardousRiskIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val hazardousRiskIndicator: Boolean? = null,
  @XmlSerialName(
    value = "DeclaredCustomsValueAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val declaredCustomsValueAmount: Amount? = null,
  @XmlSerialName(
    value = "DeclaredForCarriageValueAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val declaredForCarriageValueAmount: Amount? = null,
  @XmlSerialName(
    value = "DeclaredStatisticsValueAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val declaredStatisticsValueAmount: Amount? = null,
  @XmlSerialName(
    value = "FreeOnBoardValueAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val freeOnBoardValueAmount: Amount? = null,
  @XmlSerialName(
    value = "InsuranceValueAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val insuranceValueAmount: Amount? = null,
  @XmlSerialName(
    value = "ValueAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val valueAmount: Amount? = null,
  @XmlSerialName(
    value = "GrossWeightMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val grossWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "NetWeightMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val netWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "NetNetWeightMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val netNetWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "ChargeableWeightMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val chargeableWeightMeasure: Measure? = null,
  @XmlSerialName(
    value = "GrossVolumeMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val grossVolumeMeasure: Measure? = null,
  @XmlSerialName(
    value = "NetVolumeMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val netVolumeMeasure: Measure? = null,
  @XmlSerialName(
    value = "Quantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val quantity: Quantity? = null,
  @XmlSerialName(
    value = "PreferenceCriterionCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val preferenceCriterionCode: Code? = null,
  @XmlSerialName(
    value = "RequiredCustomsID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val requiredCustomsID: Identifier? = null,
  @XmlSerialName(
    value = "CustomsStatusCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val customsStatusCode: Code? = null,
  @XmlSerialName(
    value = "CustomsTariffQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val customsTariffQuantity: Quantity? = null,
  @XmlSerialName(
    value = "CustomsImportClassifiedIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val customsImportClassifiedIndicator: Boolean? = null,
  @XmlSerialName(
    value = "ChargeableQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val chargeableQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ReturnableQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val returnableQuantity: Quantity? = null,
  @XmlSerialName(
    value = "TraceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val traceID: Identifier? = null,
  @XmlSerialName(
    value = "Item",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val item: List<Item> = emptyList(),
  @XmlSerialName(
    value = "GoodsItemContainer",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val goodsItemContainer: List<GoodsItemContainer> = emptyList(),
  @XmlSerialName(
    value = "FreightAllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val freightAllowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "InvoiceLine",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val invoiceLine: List<InvoiceLine> = emptyList(),
  @XmlSerialName(
    value = "Temperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val temperature: List<Temperature> = emptyList(),
  @XmlSerialName(
    value = "ContainedGoodsItem",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val containedGoodsItem: List<GoodsItem> = emptyList(),
  @XmlSerialName(
    value = "OriginAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originAddress: Address? = null,
  @XmlSerialName(
    value = "Delivery",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val delivery: Delivery? = null,
  @XmlSerialName(
    value = "Pickup",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val pickup: Pickup? = null,
  @XmlSerialName(
    value = "Despatch",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val despatch: Despatch? = null,
  @XmlSerialName(
    value = "MeasurementDimension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val measurementDimension: List<Dimension> = emptyList(),
  @XmlSerialName(
    value = "ContainingPackage",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val containingPackage: List<Package> = emptyList(),
  @XmlSerialName(
    value = "ShipmentDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shipmentDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "MinimumTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val minimumTemperature: Temperature? = null,
  @XmlSerialName(
    value = "MaximumTemperature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val maximumTemperature: Temperature? = null,
)
