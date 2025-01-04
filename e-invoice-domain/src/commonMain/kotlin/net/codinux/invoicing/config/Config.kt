package net.codinux.invoicing.config

import net.codinux.kotlin.Platform

object Config {

    var InvoicingApiBaseUrl = "http://0.0.0.0:8091"

    var InvoicingApiRootPath = "/invoicing/v1"

    var DefaultUserAgent: String? = "${Platform.type} eInvoicing"

}