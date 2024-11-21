package net.codinux.invoicing.mail

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

@Ignore // not an automatic test, set your mail account settings below
class MailReaderTest {

    companion object {
        // specify your mail account here
        private val mailAccount = MailAccount(
            username = "",
            password = "",
            serverAddress = "",
            port = null // can be left as null if default port 993 is used
        )
    }


    private val underTest = MailReader()


    @Test
    fun listAllMessagesWithEInvoice() {
        val result = underTest.listAllMessagesWithEInvoice(mailAccount)

        assertThat(result).isNotEmpty()

        val messagesWithoutBody = result.filter { it.plainTextOrHtmlBody == null }
        assertThat(messagesWithoutBody).isEmpty()
    }

}