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