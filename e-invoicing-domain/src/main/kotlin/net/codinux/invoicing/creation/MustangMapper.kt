package net.codinux.invoicing.creation

import net.codinux.invoicing.model.LineItem
import org.mustangproject.Invoice
import org.mustangproject.Item
import org.mustangproject.Product
import org.mustangproject.TradeParty
import org.mustangproject.ZUGFeRD.IExportableTransaction
import org.mustangproject.ZUGFeRD.IZUGFeRDExportableItem
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class MustangMapper {

    fun mapToTransaction(invoice: net.codinux.invoicing.model.Invoice): IExportableTransaction = Invoice().apply {
        this.number = invoice.invoiceNumber
        this.issueDate = map(invoice.invoicingDate)
        this.sender = mapParty(invoice.sender)
        this.recipient = mapParty(invoice.recipient)

        this.setZFItems(ArrayList(invoice.items.map { mapLineItem(it) }))

        this.dueDate = map(invoice.dueDate)
    }

    fun mapParty(party: net.codinux.invoicing.model.Party): TradeParty = TradeParty(
        party.name, "${party.streetName} ${party.houseNumber}", party.postalCode, party.city, party.country
    ).apply {
        this.taxID = party.taxNumber
        // TODO: vatID?
        // TODO: ID?
        // TODO: description?

        this.email = party.email
    }

    fun mapLineItem(item: LineItem): IZUGFeRDExportableItem = Item(
        // description has to be an empty string if not set
        Product(item.name, item.description ?: "", item.unit, item.vatPercentage), item.price, item.quantity
    ).apply {

    }


    @JvmName("mapNullable")
    private fun map(date: LocalDate?) =
        date?.let { map(it) }

    private fun map(date: LocalDate): Date =
        Date.from(mapToInstant(date))

    private fun mapToInstant(date: LocalDate): Instant =
        date.atStartOfDay(ZoneId.systemDefault()).toInstant()

}