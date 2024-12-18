package net.codinux.invoicing.test

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.serialization.json.Json

object TestInstances {

    val objectMapper by lazy {
        ObjectMapper().apply {
            findAndRegisterModules()

            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }

    val json = Json {
        ignoreUnknownKeys = true

        // encodes default enum values like Currency.Euro (which is good), but also null values (which is bad)
//        encodeDefaults = true
    }

}