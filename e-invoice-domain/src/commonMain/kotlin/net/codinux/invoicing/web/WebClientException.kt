package net.codinux.invoicing.web

open class WebClientException(
    open val httpStatusCode: Int,
    open val headers: Map<String, List<String>>,
    errorMessage: String,
    cause: Throwable? = null
) : Exception(errorMessage, cause) {

    override fun toString(): String {
        return "$httpStatusCode $message"
    }

}