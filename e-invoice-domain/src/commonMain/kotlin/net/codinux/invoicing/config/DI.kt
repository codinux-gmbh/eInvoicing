package net.codinux.invoicing.config

import net.codinux.invoicing.util.ExceptionHelper
import net.codinux.invoicing.web.KtorWebClient

object DI {

    var BackendBaseUrl = "http://0.0.0.0:8091/invoicing"

    val DefaultWebClient by lazy { KtorWebClient(BackendBaseUrl) }

    val ExceptionHelper = ExceptionHelper()

}