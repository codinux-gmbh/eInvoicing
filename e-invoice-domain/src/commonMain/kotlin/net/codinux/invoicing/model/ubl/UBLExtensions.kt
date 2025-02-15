package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class UBLExtensions(
  @XmlSerialName(
    value = "UBLExtension",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val uBLExtension: List<UBLExtension> = emptyList(),
)
