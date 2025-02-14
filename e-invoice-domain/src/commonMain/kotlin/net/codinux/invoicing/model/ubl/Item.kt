package net.codinux.invoicing.model.ubl

import kotlin.Boolean
import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Item(
  @XmlSerialName(
    value = "Description",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val description: List<Text> = emptyList(),
  @XmlSerialName(
    value = "PackQuantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val packQuantity: Quantity? = null,
  @XmlSerialName(
    value = "PackSizeNumeric",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val packSizeNumeric: Numeric? = null,
  @XmlSerialName(
    value = "CatalogueIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val catalogueIndicator: Boolean? = null,
  @XmlSerialName(
    value = "Name",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val name: Text? = null,
  @XmlSerialName(
    value = "HazardousRiskIndicator",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val hazardousRiskIndicator: Boolean? = null,
  @XmlSerialName(
    value = "AdditionalInformation",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val additionalInformation: List<Text> = emptyList(),
  @XmlSerialName(
    value = "Keyword",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val keyword: List<Text> = emptyList(),
  @XmlSerialName(
    value = "BrandName",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val brandName: List<Text> = emptyList(),
  @XmlSerialName(
    value = "ModelName",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val modelName: List<Text> = emptyList(),
  @XmlSerialName(
    value = "BuyersItemIdentification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val buyersItemIdentification: ItemIdentification? = null,
  @XmlSerialName(
    value = "SellersItemIdentification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val sellersItemIdentification: ItemIdentification? = null,
  @XmlSerialName(
    value = "ManufacturersItemIdentification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val manufacturersItemIdentification: List<ItemIdentification> = emptyList(),
  @XmlSerialName(
    value = "StandardItemIdentification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val standardItemIdentification: ItemIdentification? = null,
  @XmlSerialName(
    value = "CatalogueItemIdentification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val catalogueItemIdentification: ItemIdentification? = null,
  @XmlSerialName(
    value = "AdditionalItemIdentification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val additionalItemIdentification: List<ItemIdentification> = emptyList(),
  @XmlSerialName(
    value = "CatalogueDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val catalogueDocumentReference: DocumentReference? = null,
  @XmlSerialName(
    value = "ItemSpecificationDocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val itemSpecificationDocumentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "OriginCountry",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originCountry: Country? = null,
  @XmlSerialName(
    value = "CommodityClassification",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val commodityClassification: List<CommodityClassification> = emptyList(),
  @XmlSerialName(
    value = "TransactionConditions",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transactionConditions: List<TransactionConditions> = emptyList(),
  @XmlSerialName(
    value = "HazardousItem",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val hazardousItem: List<HazardousItem> = emptyList(),
  @XmlSerialName(
    value = "ClassifiedTaxCategory",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val classifiedTaxCategory: List<TaxCategory> = emptyList(),
  @XmlSerialName(
    value = "AdditionalItemProperty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val additionalItemProperty: List<ItemProperty> = emptyList(),
  @XmlSerialName(
    value = "ManufacturerParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val manufacturerParty: List<Party> = emptyList(),
  @XmlSerialName(
    value = "InformationContentProviderParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val informationContentProviderParty: Party? = null,
  @XmlSerialName(
    value = "OriginAddress",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val originAddress: List<Address> = emptyList(),
  @XmlSerialName(
    value = "ItemInstance",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val itemInstance: List<ItemInstance> = emptyList(),
  @XmlSerialName(
    value = "Certificate",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val certificate: List<Certificate> = emptyList(),
  @XmlSerialName(
    value = "Dimension",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val dimension: List<Dimension> = emptyList(),
)
