package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Consignment(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "CarrierAssignedID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val carrierAssignedID: Identifier? = null,
  @XmlSerialName(
    value = "ConsigneeAssignedID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val consigneeAssignedID: Identifier? = null,
  @XmlSerialName(
    value = "ConsignorAssignedID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val consignorAssignedID: Identifier? = null,
  @XmlSerialName(
    value = "FreightForwarderAssignedID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val freightForwarderAssignedID: Identifier? = null,
  @XmlSerialName(
    value = "BrokerAssignedID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val brokerAssignedID: Identifier? = null,
  @XmlSerialName(
    value = "ContractedCarrierAssignedID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val contractedCarrierAssignedID: Identifier? = null,
  @XmlSerialName(
    value = "PerformingCarrierAssignedID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val performingCarrierAssignedID: Identifier? = null,
  @XmlSerialName(
    value = "SummaryDescription",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val summaryDescription: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TotalInvoiceAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val totalInvoiceAmount: Amount? = null,
  @XmlSerialName(
    value = "DeclaredCustomsValueAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val declaredCustomsValueAmount: Amount? = null,
  @XmlSerialName(
    value = "TariffDescription",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val tariffDescription: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TariffCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val tariffCode: Code? = null,
  @XmlSerialName(
    value = "InsurancePremiumAmount",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val insurancePremiumAmount: Amount? = null,
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
    value = "LoadingLengthMeasure",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val loadingLengthMeasure: Measure? = null,
  @XmlSerialName(
    value = "Remarks",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val remarks: List<Text> = emptyList(),
  @XmlSerialName(
    value = "HazardousRiskIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val hazardousRiskIndicator: Boolean? = null,
  @XmlSerialName(
    value = "AnimalFoodIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val animalFoodIndicator: Boolean? = null,
  @XmlSerialName(
    value = "HumanFoodIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val humanFoodIndicator: Boolean? = null,
  @XmlSerialName(
    value = "LivestockIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val livestockIndicator: Boolean? = null,
  @XmlSerialName(
    value = "BulkCargoIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val bulkCargoIndicator: Boolean? = null,
  @XmlSerialName(
    value = "ContainerizedIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val containerizedIndicator: Boolean? = null,
  @XmlSerialName(
    value = "GeneralCargoIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val generalCargoIndicator: Boolean? = null,
  @XmlSerialName(
    value = "SpecialSecurityIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val specialSecurityIndicator: Boolean? = null,
  @XmlSerialName(
    value = "ThirdPartyPayerIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val thirdPartyPayerIndicator: Boolean? = null,
  @XmlSerialName(
    value = "CarrierServiceInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val carrierServiceInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "CustomsClearanceServiceInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val customsClearanceServiceInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ForwarderServiceInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val forwarderServiceInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "SpecialServiceInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val specialServiceInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "SequenceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val sequenceID: Identifier? = null,
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
    value = "SplitConsignmentIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val splitConsignmentIndicator: Boolean? = null,
  @XmlSerialName(
    value = "DeliveryInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val deliveryInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ConsignmentQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val consignmentQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ConsolidatableIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val consolidatableIndicator: Boolean? = null,
  @XmlSerialName(
    value = "HaulageInstructions",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val haulageInstructions: List<Text> = emptyList(),
  @XmlSerialName(
    value = "LoadingSequenceID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val loadingSequenceID: Identifier? = null,
  @XmlSerialName(
    value = "ChildConsignmentQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val childConsignmentQuantity: Quantity? = null,
  @XmlSerialName(
    value = "TotalPackagesQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val totalPackagesQuantity: Quantity? = null,
  @XmlSerialName(
    value = "ConsolidatedShipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val consolidatedShipment: List<Shipment> = emptyList(),
  @XmlSerialName(
    value = "CustomsDeclaration",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val customsDeclaration: List<CustomsDeclaration> = emptyList(),
  @XmlSerialName(
    value = "RequestedPickupTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val requestedPickupTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "RequestedDeliveryTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val requestedDeliveryTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "PlannedPickupTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val plannedPickupTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "PlannedDeliveryTransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val plannedDeliveryTransportEvent: TransportEvent? = null,
  @XmlSerialName(
    value = "Status",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val status: List<Status> = emptyList(),
  @XmlSerialName(
    value = "ChildConsignment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val childConsignment: List<Consignment> = emptyList(),
  @XmlSerialName(
    value = "ConsigneeParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val consigneeParty: Party? = null,
  @XmlSerialName(
    value = "ExporterParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val exporterParty: Party? = null,
  @XmlSerialName(
    value = "ConsignorParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val consignorParty: Party? = null,
  @XmlSerialName(
    value = "ImporterParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val importerParty: Party? = null,
  @XmlSerialName(
    value = "CarrierParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val carrierParty: Party? = null,
  @XmlSerialName(
    value = "FreightForwarderParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val freightForwarderParty: Party? = null,
  @XmlSerialName(
    value = "NotifyParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val notifyParty: Party? = null,
  @XmlSerialName(
    value = "OriginalDespatchParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originalDespatchParty: Party? = null,
  @XmlSerialName(
    value = "FinalDeliveryParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val finalDeliveryParty: Party? = null,
  @XmlSerialName(
    value = "PerformingCarrierParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val performingCarrierParty: Party? = null,
  @XmlSerialName(
    value = "SubstituteCarrierParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val substituteCarrierParty: Party? = null,
  @XmlSerialName(
    value = "LogisticsOperatorParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val logisticsOperatorParty: Party? = null,
  @XmlSerialName(
    value = "TransportAdvisorParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportAdvisorParty: Party? = null,
  @XmlSerialName(
    value = "HazardousItemNotificationParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val hazardousItemNotificationParty: Party? = null,
  @XmlSerialName(
    value = "InsuranceParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val insuranceParty: Party? = null,
  @XmlSerialName(
    value = "MortgageHolderParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val mortgageHolderParty: Party? = null,
  @XmlSerialName(
    value = "BillOfLadingHolderParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val billOfLadingHolderParty: Party? = null,
  @XmlSerialName(
    value = "OriginalDepartureCountry",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originalDepartureCountry: Country? = null,
  @XmlSerialName(
    value = "FinalDestinationCountry",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val finalDestinationCountry: Country? = null,
  @XmlSerialName(
    value = "TransitCountry",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transitCountry: List<Country> = emptyList(),
  @XmlSerialName(
    value = "TransportContract",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportContract: Contract? = null,
  @XmlSerialName(
    value = "TransportEvent",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportEvent: List<TransportEvent> = emptyList(),
  @XmlSerialName(
    value = "OriginalDespatchTransportationService",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originalDespatchTransportationService: TransportationService? = null,
  @XmlSerialName(
    value = "FinalDeliveryTransportationService",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val finalDeliveryTransportationService: TransportationService? = null,
  @XmlSerialName(
    value = "DeliveryTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryTerms: DeliveryTerms? = null,
  @XmlSerialName(
    value = "PaymentTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val paymentTerms: PaymentTerms? = null,
  @XmlSerialName(
    value = "CollectPaymentTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val collectPaymentTerms: PaymentTerms? = null,
  @XmlSerialName(
    value = "DisbursementPaymentTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val disbursementPaymentTerms: PaymentTerms? = null,
  @XmlSerialName(
    value = "PrepaidPaymentTerms",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val prepaidPaymentTerms: PaymentTerms? = null,
  @XmlSerialName(
    value = "FreightAllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val freightAllowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "ExtraAllowanceCharge",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val extraAllowanceCharge: List<AllowanceCharge> = emptyList(),
  @XmlSerialName(
    value = "MainCarriageShipmentStage",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val mainCarriageShipmentStage: List<ShipmentStage> = emptyList(),
  @XmlSerialName(
    value = "PreCarriageShipmentStage",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val preCarriageShipmentStage: List<ShipmentStage> = emptyList(),
  @XmlSerialName(
    value = "OnCarriageShipmentStage",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val onCarriageShipmentStage: List<ShipmentStage> = emptyList(),
  @XmlSerialName(
    value = "TransportHandlingUnit",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportHandlingUnit: List<TransportHandlingUnit> = emptyList(),
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
)
