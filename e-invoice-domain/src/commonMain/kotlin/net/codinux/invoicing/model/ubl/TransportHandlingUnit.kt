package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TransportHandlingUnit(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "TransportHandlingUnitTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val transportHandlingUnitTypeCode: Code? = null,
  @XmlSerialName(
    value = "HandlingCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val handlingCode: Code? = null,
  @XmlSerialName(
    value = "HandlingInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val handlingInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "HazardousRiskIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val hazardousRiskIndicator: Boolean? = null,
  @XmlSerialName(
    value = "TotalGoodsItemQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val totalGoodsItemQuantity: Quantity? = null,
  @XmlSerialName(
    value = "TotalPackageQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val totalPackageQuantity: Quantity? = null,
  @XmlSerialName(
    value = "DamageRemarks",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val damageRemarks: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ShippingMarks",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val shippingMarks: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TraceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val traceID: Identifier? = null,
  @XmlSerialName(
    value = "HandlingUnitDespatchLine",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val handlingUnitDespatchLine: List<DespatchLine> = emptyList(),
  @XmlSerialName(
    value = "ActualPackage",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val actualPackage: List<Package> = emptyList(),
  @XmlSerialName(
    value = "ReceivedHandlingUnitReceiptLine",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val receivedHandlingUnitReceiptLine: List<ReceiptLine> = emptyList(),
  @XmlSerialName(
    value = "TransportEquipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportEquipment: List<TransportEquipment> = emptyList(),
  @XmlSerialName(
    value = "TransportMeans",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportMeans: List<TransportMeans> = emptyList(),
  @XmlSerialName(
    value = "HazardousGoodsTransit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val hazardousGoodsTransit: List<HazardousGoodsTransit> = emptyList(),
  @XmlSerialName(
    value = "MeasurementDimension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val measurementDimension: List<Dimension> = emptyList(),
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
  @XmlSerialName(
    value = "GoodsItem",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val goodsItem: List<GoodsItem> = emptyList(),
  @XmlSerialName(
    value = "FloorSpaceMeasurementDimension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val floorSpaceMeasurementDimension: Dimension? = null,
  @XmlSerialName(
    value = "PalletSpaceMeasurementDimension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val palletSpaceMeasurementDimension: Dimension? = null,
  @XmlSerialName(
    value = "ShipmentDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shipmentDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "Status",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val status: List<Status> = emptyList(),
  @XmlSerialName(
    value = "CustomsDeclaration",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val customsDeclaration: List<CustomsDeclaration> = emptyList(),
  @XmlSerialName(
    value = "ReferencedShipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val referencedShipment: List<Shipment> = emptyList(),
  @XmlSerialName(
    value = "Package",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val `package`: List<Package> = emptyList(),
)
