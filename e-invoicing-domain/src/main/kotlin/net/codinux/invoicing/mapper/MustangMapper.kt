package net.codinux.invoicing.mapper

import net.codinux.invoicing.model.LineItem
import net.codinux.invoicing.model.Party
import org.mustangproject.*
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
        this.paymentTermDescription = invoice.paymentDescription

        this.referenceNumber = invoice.buyerReference
    }

    fun mapParty(party: Party): TradeParty = TradeParty(
        party.name, party.street, party.postalCode, party.city, party.countryIsoCode
    ).apply {
        this.setVATID(party.vatId)
        // TODO: description?

        this.email = party.email
        this.setContact(Contact(party.contactName, party.phone, party.email).apply {
            this.fax = party.fax
        })

        party.bankDetails?.let {
            this.addBankDetails(BankDetails(it.accountNumber, it.bankCode).apply {
                accountName = it.accountHolderName
                // TODO: there's currently no field for financialInstitutionName in Zugferd model even though it exists on CII and UBL
            })
        }
    }

    fun mapLineItem(item: LineItem): IZUGFeRDExportableItem = Item(
        // description has to be an empty string if not set
        Product(item.name, item.description ?: "", item.unit, item.vatPercentage), item.price, item.quantity
    ).apply {

    }


    fun mapToInvoice(invoice: Invoice) = net.codinux.invoicing.model.Invoice(
        invoiceNumber = invoice.number,
        invoicingDate = map(invoice.issueDate),
        sender = mapParty(invoice.sender),
        recipient = mapParty(invoice.recipient),
        items = invoice.zfItems.map { mapLineItem(it) },

        dueDate = map(invoice.dueDate ?: invoice.paymentTerms?.dueDate),
        paymentDescription = invoice.paymentTermDescription ?: invoice.paymentTerms?.description,

        buyerReference = invoice.referenceNumber
    )

    fun mapParty(party: TradeParty) = Party(
        party.name, party.street, party.zip, party.location, party.country, party.vatID,
        party.email ?: party.contact?.eMail, party.contact?.phone, party.contact?.fax, party.contact?.name,
        party.bankDetails?.firstOrNull()?.let { net.codinux.invoicing.model.BankDetails(it.iban, it.bic, it.accountName) }
    )

    fun mapLineItem(item: IZUGFeRDExportableItem) = LineItem(
        item.product.name, item.product.unit, item.quantity, item.price, item.product.vatPercent, item.product.description.takeUnless { it.isBlank() }
    )


    @JvmName("mapNullable")
    private fun map(date: LocalDate?) =
        date?.let { map(it) }

    private fun map(date: LocalDate): Date =
        Date.from(mapToInstant(date))

    private fun mapToInstant(date: LocalDate): Instant =
        date.atStartOfDay(ZoneId.systemDefault()).toInstant()

    @JvmName("mapNullable")
    private fun map(date: Date?) =
        date?.let { map(it) }

    private fun map(date: Date): LocalDate =
        date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

}