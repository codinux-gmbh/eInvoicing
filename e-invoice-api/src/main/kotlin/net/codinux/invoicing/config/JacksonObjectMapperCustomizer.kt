package net.codinux.invoicing.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.jackson.ObjectMapperCustomizer
import jakarta.inject.Singleton

@Singleton
class JacksonObjectMapperCustomizer : ObjectMapperCustomizer {

    override fun customize(objectMapper: ObjectMapper) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

}