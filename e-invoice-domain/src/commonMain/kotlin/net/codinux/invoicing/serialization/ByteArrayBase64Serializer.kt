package net.codinux.invoicing.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * A serializer for [ByteArray] that Base64 encodes the bytes like XML or most webservices do.
 */
@OptIn(ExperimentalEncodingApi::class)
object ByteArrayBase64Serializer : KSerializer<ByteArray> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("HexByteArray", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): ByteArray {
        val hexString = decoder.decodeString()
        return Base64.decode(hexString)
    }

    override fun serialize(encoder: Encoder, value: ByteArray) {
        val hexString = Base64.encode(value)
        encoder.encodeString(hexString)
    }

}