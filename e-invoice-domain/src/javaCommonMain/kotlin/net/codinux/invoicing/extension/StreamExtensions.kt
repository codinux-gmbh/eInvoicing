package net.codinux.invoicing.extension

import java.io.ByteArrayOutputStream
import java.io.InputStream

/**
 * InputStream.readAllBytes() is not available on Android < API 33, so i extracted this method
 */
fun InputStream.readAllBytesAndClose(): ByteArray =
    this.use {
        this.readBytes()
    }

fun ByteArrayOutputStream.useAndGet(block: (ByteArrayOutputStream) -> Unit): ByteArray = this.use {
    block(this)

    this.toByteArray()
}