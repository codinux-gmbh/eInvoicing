package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class CustomerParty(
  @XmlSerialName(
    value = "CustomerAssignedAccountID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val customerAssignedAccountID: Identifier? = null,
  @XmlSerialName(
    value = "SupplierAssignedAccountID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val supplierAssignedAccountID: Identifier? = null,
  @XmlSerialName(
    value = "AdditionalAccountID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val additionalAccountID: List<Identifier> = emptyList(),
  @XmlSerialName(
    value = "Party",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val party: Party? = null,
  @XmlSerialName(
    value = "DeliveryContact",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val deliveryContact: Contact? = null,
  @XmlSerialName(
    value = "AccountingContact",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val accountingContact: Contact? = null,
  @XmlSerialName(
    value = "BuyerContact",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val buyerContact: Contact? = null,
)
