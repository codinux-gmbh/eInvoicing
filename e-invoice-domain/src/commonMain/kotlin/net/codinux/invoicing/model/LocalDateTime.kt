package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
class LocalDateTime(
    val year: Int, val month: Int, val dayOfMonth: Int,
    val hour: Int, val minute: Int, val second: Int,
)