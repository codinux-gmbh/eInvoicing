package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Shipment(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "ShippingPriorityLevelCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val shippingPriorityLevelCode: Code? = null,
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
    value = "Information",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val information: List<Text> = emptyList(),
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
    value = "TotalGoodsItemQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val totalGoodsItemQuantity: Quantity? = null,
  @XmlSerialName(
    value = "TotalTransportHandlingUnitQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val totalTransportHandlingUnitQuantity: Quantity? = null,
  @XmlSerialName(
    value = "InsuranceValueAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val insuranceValueAmount: Amount? = null,
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
    value = "SpecialInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val specialInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "DeliveryInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val deliveryInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "SplitConsignmentIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val splitConsignmentIndicator: Boolean? = null,
  @XmlSerialName(
    value = "ConsignmentQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val consignmentQuantity: Quantity? = null,
  @XmlSerialName(
    value = "Consignment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val consignment: List<Consignment> = emptyList(),
  @XmlSerialName(
    value = "GoodsItem",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val goodsItem: List<GoodsItem> = emptyList(),
  @XmlSerialName(
    value = "ShipmentStage",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val shipmentStage: List<ShipmentStage> = emptyList(),
  @XmlSerialName(
    value = "Delivery",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val delivery: Delivery? = null,
  @XmlSerialName(
    value = "TransportHandlingUnit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportHandlingUnit: List<TransportHandlingUnit> = emptyList(),
  @XmlSerialName(
    value = "ReturnAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val returnAddress: Address? = null,
  @XmlSerialName(
    value = "OriginAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originAddress: Address? = null,
  @XmlSerialName(
    value = "FirstArrivalPortLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val firstArrivalPortLocation: Location? = null,
  @XmlSerialName(
    value = "LastExitPortLocation",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val lastExitPortLocation: Location? = null,
  @XmlSerialName(
    value = "ExportCountry",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val exportCountry: Country? = null,
  @XmlSerialName(
    value = "FreightAllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val freightAllowanceCharge: List<AllowanceCharge> = emptyList(),
)
