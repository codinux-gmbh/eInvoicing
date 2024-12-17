package net.codinux.invoicing.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.codinux.invoicing.model.LocalDate

object LocalDateSerializer : KSerializer<LocalDate> {

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("net.codinux.invoicing.model.LocalDate", LocalDateSurrogate.serializer().descriptor)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        val surrogate = LocalDateSurrogate(value.year, value.month, value.dayOfMonth)
        encoder.encodeSerializableValue(LocalDateSurrogate.serializer(), surrogate)
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        val surrogate = decoder.decodeSerializableValue(LocalDateSurrogate.serializer())
        return LocalDate(surrogate.year, surrogate.month, surrogate.dayOfMonth)
    }

    @Serializable
    class LocalDateSurrogate(val year: Int, val month: Int, val dayOfMonth: Int)
}