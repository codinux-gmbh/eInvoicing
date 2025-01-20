package net.codinux.invoicing.validation

import kotlinx.serialization.Serializable

@Serializable
data class PdfReference(
    val specification: String,
    val clause: String? = null
) {
    override fun toString(): String = "$specification${clause?.let { " $it" } ?: ""}"
}