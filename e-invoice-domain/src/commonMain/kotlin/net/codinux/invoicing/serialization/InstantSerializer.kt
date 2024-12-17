package net.codinux.invoicing.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.codinux.invoicing.model.Instant

object InstantSerializer : KSerializer<Instant> {

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("net.codinux.invoicing.model.Instant", InstantSurrogate.serializer().descriptor)

    override fun serialize(encoder: Encoder, value: Instant) {
        val surrogate = InstantSurrogate(value.epochSeconds, value.nanosecondsOfSecond)
        encoder.encodeSerializableValue(InstantSurrogate.serializer(), surrogate)
    }

    override fun deserialize(decoder: Decoder): Instant {
        val surrogate = decoder.decodeSerializableValue(InstantSurrogate.serializer())
        return Instant(surrogate.epochSeconds, surrogate.nanosecondsOfSecond)
    }

    @Serializable
    class InstantSurrogate(val epochSeconds: Long, val nanosecondsOfSecond: Int)

}