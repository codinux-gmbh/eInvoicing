package net.codinux.invoicing.email.model

class EmailAccount(
    val username: String,
    val password: String,
    /**
     * For fetching emails the IMAP server address, for sending emails the SMTP server address.
     */
    val serverAddress: String,
    /**
     * Even though not mandatory it's better to specify the port, otherwise default port (993 for IMAP, 587 for SMTP) is used.
     */
    val port: Int? = null
) {
    override fun toString() = "$username $serverAddress${port?.let { ":$it" } ?: ""}"
}