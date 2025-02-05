package net.codinux.invoicing.extension

import java.io.ByteArrayOutputStream

fun ByteArrayOutputStream.useAndGet(block: (ByteArrayOutputStream) -> Unit): ByteArray = this.use {
    block(this)

    this.toByteArray()
}