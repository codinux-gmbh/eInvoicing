package net.codinux.invoicing.model

import kotlinx.serialization.Serializable

@Serializable
class Image(
    val imageUrl: String? = null,
    val imageBytes: ByteArray? = null,
    /**
     * Only required if [imageBytes] is set as we cannot know image's mime type then.
     */
    val imageMimeType: String? = null,
) {
    companion object {
        fun forUrl(imageUrl: String) = Image(imageUrl)
        fun forImageBytes(imageBytes: ByteArray, imageMimeType: String) = Image(null, imageBytes, imageMimeType)
    }

    override fun toString() =
        if (imageUrl != null) imageUrl
        else "$imageMimeType, ${imageBytes?.size} bytes"
}
