# eInvoicing

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.codinux.invoicing/e-invoice/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.codinux.invoicing/e-invoice)


Tools for working with eInvoicing according to EU standard EU 16931.

As ZUGFeRD 2 and Factur-X unified their specification, these two names are used synonymously and interchangeably 
throughout the documentation and code.

## Setup

### Gradle

```
implementation("net.codinux.invoicing:e-invoice:0.7.1")
```

### Android

On Android for working with PDFs the Android application context is required. Call this line before creating and PDF relevant classes (like EInvoiceReader or EInvoiceCreator):

```kotlin
net.codinux.kotlin.android.AndroidContext.applicationContext = this.applicationContext
```

E.g. in MainActivity:

```kotlin
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        net.codinux.invoicing.platform.AndroidContext.applicationContext = this.applicationContext
        // ...
    }
}
```

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
    println("${email.sender}: ${email.attachments.firstNotNullOfOrNull { it.invoice }?.totals?.duePayableAmount}")
}
```

### Validate eInvoice

```kotlin
val validator = EInvoiceValidator()
val invoiceFile = File("ZUGFeRD.pdf") // or XRechnung,xml, ...

val result = validator.validate(invoiceFile)

println("Is valid? ${result.isValid}")
println(result.reportAsXml)
```

## Create eInvoice

```kotlin
fun create() {
    val invoice = createInvoice()
    val pdfResultFile = File.createTempFile("Zugferd", ".pdf")

    // create a PDF that also contains the eInvoice as XML attachment
    val pdfCreator = JvmEInvoicePdfCreator()
    pdfCreator.createPdfWithAttachedXml(invoice, pdfResultFile)

    val xmlCreator = EInvoiceXmlCreator()

    // create only the XML file
    val xml = xmlCreator.createFacturXXml(invoice)

    // create a XRechnung
    val xRechnung = xmlCreator.createXRechnungXml(invoice)
}

private fun createInvoice() = Invoice(
    details = InvoiceDetails("RE-00001", LocalDate.now()),
    supplier = Party("codinux GmbH & Co. KG", "Fun Street 1", null, "12345", "Glückstadt"),
    customer = Party("Abzock GmbH", "Ausbeutstr.", null, "12345", "Abzockhausen"),
    items = listOf(InvoiceItem("Erbrachte Dienstleistungen", BigDecimal(170), "HUR", BigDecimal(105), BigDecimal(19))) // HUR = EN code for hour
)
```

### Attach invoice XML to existing PDF

```kotlin
val invoice: Invoice = createInvoice()
val existingPdf = File("Invoice.pdf")
val output = File("Zugferd.pdf")

val attacher = EInvoiceXmlToPdfAttacher()
attacher.attachInvoiceXmlToPdf(invoice, existingPdf, output)

// or if you already have the invoice XML:
val xmlCreator = EInvoiceXmlCreator()
val invoiceXml = xmlCreator.createXRechnungXml(invoice) // or creator.createZugferdXml(invoice), ...

attacher.attachInvoiceXmlToPdf(invoiceXml, EInvoiceXmlFormat.XRechnung, existingPdf, output)
```