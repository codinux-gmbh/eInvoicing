package net.codinux.invoicing.model.cii.lenient

import kotlin.ByteArray
import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.invoicing.serialization.ByteArrayBase64Serializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class BinaryObject(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35",
  )
  @XmlElement(value = true)
  @XmlValue
  @Serializable(with = ByteArrayBase64Serializer::class)
  val `value`: ByteArray? = null,
  @XmlSerialName(value = "format")
  @XmlElement(value = false)
  val format: String? = null,
  @XmlSerialName(value = "mimeCode")
  @XmlElement(value = false)
  val mimeCode: String? = null,
  @XmlSerialName(value = "encodingCode")
  @XmlElement(value = false)
  val encodingCode: String? = null,
  @XmlSerialName(value = "characterSetCode")
  @XmlElement(value = false)
  val characterSetCode: String? = null,
  @XmlSerialName(value = "uri")
  @XmlElement(value = false)
  val uri: String? = null,
  @XmlSerialName(value = "filename")
  @XmlElement(value = false)
  val filename: String? = null,
)
