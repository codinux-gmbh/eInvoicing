package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class GoodsItemContainer(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "Quantity",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val quantity: Quantity? = null,
  @XmlSerialName(
    value = "TransportEquipment",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val transportEquipment: List<TransportEquipment> = emptyList(),
)
