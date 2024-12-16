package net.codinux.invoicing.email.model

class EmailAddress(
    /**
     * The email address, like "a@b.com"
     */
    val address: String,
    /**
     * Sender or recipient's name like "Mahatma Gandhi"
     */
    val name: String? = null
) {
    override fun toString() =
        if (name == null) address
        else "$name <$address>"
}