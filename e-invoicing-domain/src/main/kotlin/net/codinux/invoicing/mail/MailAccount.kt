package net.codinux.invoicing.mail

class MailAccount(
    val username: String,
    val password: String,
    /**
     * For reading mails the IMAP server address, for sending mails the SMTP server address.
     */
    val serverAddress: String,
    /**
     * Even though not mandatory it's better to specify the port, otherwise default port is tried.
     */
    val port: Int? = null
) {
    override fun toString() = "$username $serverAddress${port?.let { ":$it" } ?: ""}"
}