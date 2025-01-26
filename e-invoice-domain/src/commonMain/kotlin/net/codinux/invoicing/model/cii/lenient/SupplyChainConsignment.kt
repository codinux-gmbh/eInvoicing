package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class SupplyChainConsignment(
  @XmlSerialName(
    value = "ID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val id: ID? = null,
  @XmlSerialName(
    value = "GrossWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossWeightMeasure: List<WeightUnitMeasure> = emptyList(),
  @XmlSerialName(
    value = "NetWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netWeightMeasure: List<WeightUnitMeasure> = emptyList(),
  @XmlSerialName(
    value = "GrossVolumeMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossVolumeMeasure: List<VolumeUnitMeasure> = emptyList(),
  @XmlSerialName(
    value = "ChargeableWeightMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val chargeableWeightMeasure: WeightUnitMeasure? = null,
  @XmlSerialName(
    value = "InsurancePremiumAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val insurancePremiumAmount: Amount? = null,
  @XmlSerialName(
    value = "AssociatedInvoiceAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val associatedInvoiceAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "TotalChargeAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val totalChargeAmount: Amount? = null,
  @XmlSerialName(
    value = "DeclaredValueForCustomsAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val declaredValueForCustomsAmount: Amount? = null,
  @XmlSerialName(
    value = "PackageQuantity",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val packageQuantity: Quantity? = null,
  @XmlSerialName(
    value = "NetVolumeMeasure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netVolumeMeasure: List<VolumeUnitMeasure> = emptyList(),
  @XmlSerialName(
    value = "ConsignorTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val consignorTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "ConsigneeTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val consigneeTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "CarrierTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val carrierTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "FreightForwarderTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val freightForwarderTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "DeliveryTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val deliveryTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "CustomsImportAgentTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val customsImportAgentTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "CustomsExportAgentTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val customsExportAgentTradeParty: TradeParty? = null,
  @XmlSerialName(
    value = "GroupingCentreTradeParty",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val groupingCentreTradeParty: List<TradeParty> = emptyList(),
  @XmlSerialName(
    value = "TransitLogisticsLocation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val transitLogisticsLocation: List<LogisticsLocation> = emptyList(),
  @XmlSerialName(
    value = "TransportContractReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val transportContractReferencedDocument: ReferencedDocument? = null,
  @XmlSerialName(
    value = "AssociatedReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val associatedReferencedDocument: List<ReferencedDocument> = emptyList(),
  @XmlSerialName(
    value = "IncludedSupplyChainConsignmentItem",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val includedSupplyChainConsignmentItem: List<SupplyChainConsignmentItem> = emptyList(),
  @XmlSerialName(
    value = "UtilizedLogisticsTransportEquipment",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val utilizedLogisticsTransportEquipment: List<LogisticsTransportEquipment> = emptyList(),
  @XmlSerialName(
    value = "SpecifiedLogisticsTransportMovement",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val specifiedLogisticsTransportMovement: List<LogisticsTransportMovement> = emptyList(),
  @XmlSerialName(
    value = "ApplicableTransportCargoInsurance",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableTransportCargoInsurance: TransportCargoInsurance? = null,
  @XmlSerialName(
    value = "ApplicableCrossBorderRegulatoryProcedure",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableCrossBorderRegulatoryProcedure:
      List<CrossBorderRegulatoryProcedure> = emptyList(),
  @XmlSerialName(
    value = "ApplicableCrossBorderCustomsValuation",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val applicableCrossBorderCustomsValuation: List<CrossBorderCustomsValuation> = emptyList(),
)
