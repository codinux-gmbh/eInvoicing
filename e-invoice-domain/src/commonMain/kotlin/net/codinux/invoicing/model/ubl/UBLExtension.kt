package net.codinux.invoicing.model.ubl

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class UBLExtension(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "Name",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val name: Text? = null,
  @XmlSerialName(
    value = "ExtensionAgencyID",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val extensionAgencyID: Identifier? = null,
  @XmlSerialName(
    value = "ExtensionAgencyName",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val extensionAgencyName: Text? = null,
  @XmlSerialName(
    value = "ExtensionVersionID",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val extensionVersionID: Identifier? = null,
  @XmlSerialName(
    value = "ExtensionAgencyURI",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val extensionAgencyURI: Identifier? = null,
  @XmlSerialName(
    value = "ExtensionURI",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val extensionURI: Identifier? = null,
  @XmlSerialName(
    value = "ExtensionReasonCode",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val extensionReasonCode: Code? = null,
  @XmlSerialName(
    value = "ExtensionReason",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val extensionReason: Text? = null,
  @XmlSerialName(
    value = "ExtensionContent",
    prefix = "ext",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2",
  )
  @XmlElement(value = true)
  val extensionContent: ExtensionContent? = null,
)
