package net.codinux.invoicing.config

import net.codinux.invoicing.util.ExceptionHelper
import net.codinux.invoicing.web.KtorWebClient

internal object DI {

    val DefaultWebClient by lazy { KtorWebClient(Config.BackendBaseUrl) }

    val ExceptionHelper = ExceptionHelper()

}