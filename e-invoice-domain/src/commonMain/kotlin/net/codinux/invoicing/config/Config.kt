package net.codinux.invoicing.config

import net.codinux.kotlin.Platform

object Config {

    var InvoicingApiBaseUrl = "http://0.0.0.0:8091"

    var InvoicingApiRootPath = "/invoicing/v0"

    var OsInfo = "${Platform.osName} ${Platform.osVersion}${Platform.cpuArchitecture?.let { " ($it)" } ?: ""}"

    var DefaultUserAgent: String? = "eInvoicing ${Version.ProjectVersion} ${Platform.type}, $OsInfo"

}