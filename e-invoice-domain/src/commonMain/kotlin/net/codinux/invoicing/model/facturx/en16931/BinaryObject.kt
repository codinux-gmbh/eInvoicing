package net.codinux.invoicing.model.facturx.en16931

import kotlin.ByteArray
import kotlin.String
import kotlinx.serialization.Serializable
import net.codinux.kotlin.serialization.ByteArrayBase64Serializer
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
data class BinaryObject(
  @XmlSerialName(
    value = "value",
    prefix = "udt",
    namespace = "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100",
  )
  @XmlElement(value = true)
  @XmlValue
  @Serializable(with = ByteArrayBase64Serializer::class)
  val `value`: ByteArray? = null,
  @XmlSerialName(value = "mimeCode")
  @XmlElement(value = false)
  val mimeCode: String,
  @XmlSerialName(value = "filename")
  @XmlElement(value = false)
  val filename: String,
)
