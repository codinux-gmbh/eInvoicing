package net.codinux.invoicing.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.jackson.ObjectMapperCustomizer
import jakarta.inject.Singleton
import net.codinux.invoicing.model.ServiceDate
import net.codinux.invoicing.serialization.ServiceDateMixin

@Singleton
class JacksonObjectMapperCustomizer : ObjectMapperCustomizer {

    override fun customize(objectMapper: ObjectMapper) {
        objectMapper.addMixIn(ServiceDate::class.java, ServiceDateMixin::class.java)

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

}