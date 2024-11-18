# eInvoicing

Tools for working with eInvoicing according to EU standard EU 16931.

## Reading

### Extract eInvoice from a PDF or XML file:

```kotlin
val reader = EInvoiceReader()

// read a ZUGFeRD or Factor-X PDF that contains eInvoice XML as attachment
val invoiceFromPDF = reader.extractFromPdf(File("ZUGFeRD.pdf"))

// read a eInvoice XML file like XRechnung:
val invoiceFromXml = reader.readFromXml(File("XRechnung.xml"))
```

### Find all invoices of an IMAP email account

```kotlin
val mailReader = MailReader()

val mailsWithEInvoices = mailReader.listAllMessagesWithEInvoice(MailAccount(
    username = "", // your mail account username
    password = "", // your mail account username
    serverAddress = "", // IMAP server address
    port = null // IMAP server port, leave null if default port 993
))
```

### Validate eInvoice

```kotlin
val validator = EInvoiceValidator()
val invoiceFile = File("ZUGFeRD.pdf") // or XRechnung,xml, ...

val result = validator.validate(invoiceFile)

println("Is valid? ${result.isValid}")
println(result.report)
```

## Create eInvoice

```kotlin
fun create() {
    val invoice = createInvoice()
    val pdfResultFile = File.createTempFile("Zugferd", ".pdf")

    val creator = EInvoiceCreator()

    // create a PDF that also contains the eInvoice as XML attachment
    creator.createZugferdPdf(invoice, pdfResultFile)

    // create only the XML file
    val xml = creator.createZugferdXml(invoice)

    // create a XRechnung
    val xRechnung = creator.createXRechnungXml(invoice)
}

private fun createInvoice() = Invoice(
    invoiceNumber = "RE-00001",
    invoicingDate = LocalDate.now(),
    sender = Party("codinux GmbH & Co. KG", "Fun Street 1", "12345", "Glückstadt"),
    recipient = Party("Abzock GmbH", "Ausbeutstr.", "12345", "Abzockhausen"),
    items = listOf(LineItem("Erbrachte Dienstleistungen", "HUR", BigDecimal(170), BigDecimal(1_000_000), BigDecimal(19))) // HUR = EN code for hour
)
```