package net.codinux.invoicing.mail

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

@Ignore // not an automatic test, set your mail account settings below
class EmailsFetcherTest {

    companion object {
        // specify your mail account here
        private val emailAccount = EmailAccount(
            username = "",
            password = "",
            serverAddress = "",
            port = null // IMAP server port, can be left null for default port 993
        )
    }


    private val underTest = EmailsFetcher()


    @Test
    fun listAllMessagesWithEInvoice() {
        val result = underTest.listAllMessagesWithEInvoice(emailAccount, true)

        assertThat(result.emails).isNotEmpty()

        val emailsWithoutBody = result.emails.filter { it.plainTextOrHtmlBody == null }
        assertThat(emailsWithoutBody).isEmpty()
    }

}