package net.codinux.invoicing.test

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper

object TestInstances {

    val objectMapper by lazy {
        ObjectMapper().apply {
            findAndRegisterModules()

            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }
    }

}