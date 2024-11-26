# eInvoicing

Tools for working with eInvoicing according to EU standard EU 16931.

As ZUGFeRD 2 and Factur-X unified their specification, these two names are used synonymously and interchangeably 
throughout the documentation and code.

## Reading

### Extract eInvoice from a PDF or XML file:

```kotlin
val reader = EInvoiceReader()

// extract invoice data from a ZUGFeRD or Factor-X PDF that contains eInvoice XML as attachment
val invoiceFromPDF = reader.extractFromPdf(File("ZUGFeRD.pdf"))

// extract eInvoice data from a XML file like XRechnung:
val invoiceFromXml = reader.extractFromXml(File("XRechnung.xml"))
```

### Find all invoices of an IMAP email account

```kotlin
val emailsFetcher = EmailsFetcher()

val fetchResult = emailsFetcher.fetchAllEmails(EmailAccount(
    username = "", // your email account username
    password = "", // your email account username
    serverAddress = "", // IMAP server address
    port = null // IMAP server port, can be left null for default port 993
))

fetchResult.emails.forEach { email ->
    println("${email.sender}: ${email.attachmentsWithEInvoice.firstNotNullOfOrNull { it.invoice }?.totalAmounts?.duePayableAmount}")
}
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
    creator.createFacturXPdf(invoice, pdfResultFile)

    // create only the XML file
    val xml = creator.createFacturXXml(invoice)

    // create a XRechnung
    val xRechnung = creator.createXRechnungXml(invoice)
}

private fun createInvoice() = Invoice(
    invoiceNumber = "RE-00001",
    invoicingDate = LocalDate.now(),
    sender = Party("codinux GmbH & Co. KG", "Fun Street 1", "12345", "Glückstadt"),
    recipient = Party("Abzock GmbH", "Ausbeutstr.", "12345", "Abzockhausen"),
    items = listOf(InvoiceItem("Erbrachte Dienstleistungen", BigDecimal(170), "HUR", BigDecimal(105), BigDecimal(19))) // HUR = EN code for hour
)
```

### Attach invoice XML to existing PDF

```kotlin
val invoice: Invoice = createInvoice()
val existingPdf = File("Invoice.pdf")
val output = File("Zugferd.pdf")

val creator = EInvoiceCreator()

creator.attachInvoiceXmlToPdf(invoice, existingPdf, output)

// or if you already have the invoice XML:
val invoiceXml: String = "..." // e.g. creator.createZugferdXml(invoice)

creator.attachInvoiceXmlToPdf(invoiceXml, existingPdf, output)
```