package net.codinux.invoicing.email

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isNotEmpty
import net.codinux.invoicing.email.model.EmailAccount
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

@Ignore // not an automatic test, set your email account settings below
class EmailsFetcherTest {

    companion object {
        // specify your email account here
        private val emailAccount = EmailAccount(
            username = "",
            password = "",
            serverAddress = "",
            port = null // IMAP server port, can be left null for default port 993
        )
    }


    private val underTest = EmailsFetcher()


    @Test
    fun fetchAllEmails() {
        val result = underTest.fetchAllEmails(emailAccount, FetchEmailsOptions(true))

        assertThat(result.emails).isNotEmpty()

        val emailsWithoutBody = result.emails.filter { it.plainTextOrHtmlBody == null }
        assertThat(emailsWithoutBody).isEmpty()
    }

}