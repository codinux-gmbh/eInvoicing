package net.codinux.invoicing.web

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import net.codinux.log.logger

open class KtorWebClient(
    protected open val baseUrl: String? = null
) : WebClient {

    protected open val json = Json {
        ignoreUnknownKeys = true
    }

    protected val log by logger()

    protected open val client = HttpClient { configureClient(this) }

    private fun configureClient(config: HttpClientConfig<*>) {
        config.apply {
            install(HttpTimeout)
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                baseUrl?.let {
                    url(baseUrl)
                }
            }
        }
    }


    override suspend fun <T : Any> getAsync(parameters: RequestParameters<T>) =
        makeRequest(HttpMethod.Get, parameters)

    override suspend fun headAsync(parameters: RequestParameters<Unit>) =
        makeRequest(HttpMethod.Head, parameters)

    override suspend fun <T : Any> postAsync(parameters: RequestParameters<T>) =
        makeRequest(HttpMethod.Post, parameters)

    override suspend fun <T : Any> putAsync(parameters: RequestParameters<T>) =
        makeRequest(HttpMethod.Put, parameters)

    override suspend fun <T : Any> deleteAsync(parameters: RequestParameters<T>) =
        makeRequest(HttpMethod.Delete, parameters)


    protected open suspend fun <T : Any> makeRequest(method: HttpMethod, parameters: RequestParameters<T>): WebClientResponse<T> {
        return try {
            val httpResponse = client.request {
                configureRequest(this, method, parameters)
            }

            mapHttResponse(method, parameters, httpResponse)
        } catch (e: Throwable) {
            log.error(e) { "Error during request to ${method.value} ${parameters.url}" }
            WebClientResponse(false, error = e)
        }
    }

    protected open suspend fun <T : Any> configureRequest(builder: HttpRequestBuilder, method: HttpMethod, parameters: RequestParameters<T>) {
        builder.apply {
            this.method = method

            url {
                val url = parameters.url
                if (url.startsWith("http", true)) { // absolute url
                    takeFrom(url)
                } else { // relative url
                    baseUrl?.let { takeFrom(it) }
                    appendPathSegments(url)
                }

                parameters.queryParameters.forEach { (name, value) -> this.parameters.append(name, value.toString()) }
            }

            parameters.headers.forEach { (name, value) ->
                this.headers.append(name, value)
            }

            parameters.userAgent?.let {
                this.userAgent(it)
            }

            parameters.accept?.let {
                this.accept(ContentType.parse(it))
            }

            timeout {
                connectTimeoutMillis = 10_000 // TODO: make configurable
            }

            parameters.body?.let {
                contentType(parameters.contentType?.let { ContentType.parse(it) } ?: ContentType.Application.Json)

                setBody(it)
            }
        }
    }

    protected open suspend fun <T : Any> mapHttResponse(method: HttpMethod, parameters: RequestParameters<T>, httpResponse: HttpResponse): WebClientResponse<T> {
        val statusCode = httpResponse.status.value
        val headers = httpResponse.headers.toMap()

        return if (httpResponse.status.isSuccess()) {
            try {
                WebClientResponse(true, statusCode, headers, body = decodeResponse(parameters, httpResponse))
            } catch (e: Throwable) {
                log.error(e) { "Error while mapping response of: ${method.value} ${httpResponse.request.url}, ${httpResponse.headers.toMap()}" }
                WebClientResponse(true, statusCode, headers, WebClientException(statusCode, e.message ?: ""))
            }
        } else {
            val responseBody = httpResponse.bodyAsText()

            WebClientResponse(false, statusCode, headers, WebClientException(statusCode, responseBody))
        }
    }

    @Suppress("UNCHECKED_CAST")
    @OptIn(InternalSerializationApi::class)
    protected open suspend fun <T : Any> decodeResponse(parameters: RequestParameters<T>, clientResponse: HttpResponse): T {
        val responseClass = parameters.responseClass

        return if (responseClass == null || responseClass == Unit::class) {
            Unit as T
        } else if(responseClass == String::class) {
            clientResponse.bodyAsText() as T
        } else if (responseClass == ByteArray::class) {
            val bytes: ByteArray = clientResponse.body()
            bytes as T
        } else {
            // TODO: add cache for Serializers
            // TODO: stream response (at least on JVM)
            json.decodeFromString(responseClass.serializer(), clientResponse.body())
        }
    }
}