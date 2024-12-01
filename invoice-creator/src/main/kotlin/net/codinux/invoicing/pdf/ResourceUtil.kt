package net.codinux.invoicing.pdf

import java.io.InputStream
import java.net.URL

object ResourceUtil {

    fun getResourceUrl(resourcePath: String): URL? =
        javaClass.classLoader.getResource(resourcePath)

    fun getResourceAsStream(resourcePath: String): InputStream =
        javaClass.classLoader.getResourceAsStream(resourcePath)!!

    fun getResourceBytes(resourcePath: String): ByteArray =
        getResourceAsStream(resourcePath).use {
            it.readBytes()
        }

    fun getResourceAsText(resourcePath: String): String =
        getResourceAsStream(resourcePath).use { inputStream ->
            inputStream.reader().use { it.readText() }
        }

}