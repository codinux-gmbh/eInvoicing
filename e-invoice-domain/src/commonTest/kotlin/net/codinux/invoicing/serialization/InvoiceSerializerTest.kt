package net.codinux.invoicing.serialization

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.test.DataGenerator
import net.codinux.invoicing.test.InvoiceAsserter
import kotlin.test.Test

class InvoiceSerializerTest {

    companion object {
        private val ExpectedSerializedJson = """{"details":{"invoiceNumber":"12345","invoiceDate":{"year":2015,"month":10,"dayOfMonth":21},"dueDate":{"year":2016,"month":6,"dayOfMonth":15},"paymentDescription":"Zahlbar ohne Abzug bis 15.6.2016}"},"supplier":{"name":"Hochwürdiger Leistungserbringer","address":"Fun Street 1","postalCode":"12345","city":"Glückstadt","vatId":"DE123456789","email":"working-class-hero@rock.me","phone":"+4917012345678","bankDetails":{"accountNumber":"DE00123456780987654321","bankCode":"ABZODEFFXXX","accountHolderName":"Manuela Musterfrau","financialInstitutionName":"Abzock-Bank"}},"customer":{"name":"Untertänigster Leistungsempfänger","address":"Party Street 1","postalCode":"12345","city":"Glückstadt","vatId":"DE987654321","email":"exploiter@your.boss","phone":"+491234567890"},"items":[{"name":"Erbrachte Dienstleistungen","quantity":{"value":"1"},"unit":"HUR","unitPrice":{"value":"99"},"vatRate":{"value":"19"}}]}"""
    }

    @Test
    fun serialize() {
        val result = Json.encodeToString(DataGenerator.createInvoice())

        assertThat(result).isEqualTo(ExpectedSerializedJson)
    }

    @Test
    fun deserialize() {
        val result = Json.decodeFromString<Invoice>(ExpectedSerializedJson)

        InvoiceAsserter.assertInvoice(result)
    }

}