package net.codinux.invoicing.model.ubl

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class Certificate(
  @XmlSerialName(
    value = "ID",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val id: Identifier? = null,
  @XmlSerialName(
    value = "CertificateTypeCode",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val certificateTypeCode: Code? = null,
  @XmlSerialName(
    value = "CertificateType",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val certificateType: Text? = null,
  @XmlSerialName(
    value = "Remarks",
    prefix = "cbc",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2",
  )
  @XmlElement(value = true)
  val remarks: List<Text> = emptyList(),
  @XmlSerialName(
    value = "IssuerParty",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val issuerParty: Party? = null,
  @XmlSerialName(
    value = "DocumentReference",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val documentReference: List<DocumentReference> = emptyList(),
  @XmlSerialName(
    value = "Signature",
    prefix = "cac",
    namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2",
  )
  @XmlElement(value = true)
  val signature: List<Signature> = emptyList(),
)
