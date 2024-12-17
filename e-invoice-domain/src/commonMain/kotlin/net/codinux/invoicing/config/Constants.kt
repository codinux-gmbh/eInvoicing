package net.codinux.invoicing.config

object Constants {

    val KnownEInvoiceXmlAttachmentNames = listOf(
        "factur-x.xml", "zugferd-invoice.xml", "xrechnung.xml" // also "ZUGFeRD-invoice.xml" is found but we make compare case insensitive anyway
    )

}