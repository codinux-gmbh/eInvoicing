package net.codinux.invoicing.extension

import java.io.InputStream

/**
 * InputStream.readAllBytes() is not available on Android < API 33, so i extracted this method
 */
fun InputStream.readAllBytesAndClose(): ByteArray =
    this.use {
        this.readBytes()
    }