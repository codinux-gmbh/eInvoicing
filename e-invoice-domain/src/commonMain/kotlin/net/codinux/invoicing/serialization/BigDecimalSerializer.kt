package net.codinux.invoicing.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.codinux.invoicing.model.BigDecimal

object BigDecimalSerializer : KSerializer<BigDecimal> {

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("net.codinux.invoicing.model.BigDecimal", BigDecimalSurrogate.serializer().descriptor)

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        val surrogate = BigDecimalSurrogate(value.toString())
        encoder.encodeSerializableValue(BigDecimalSurrogate.serializer(), surrogate)
    }

    override fun deserialize(decoder: Decoder): BigDecimal {
        val surrogate = decoder.decodeSerializableValue(BigDecimalSurrogate.serializer())
        return BigDecimal(surrogate.value)
    }

    @Serializable
    class BigDecimalSurrogate(val value: String)

}