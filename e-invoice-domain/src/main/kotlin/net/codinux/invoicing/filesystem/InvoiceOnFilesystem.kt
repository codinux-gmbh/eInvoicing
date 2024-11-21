package net.codinux.invoicing.filesystem

import net.codinux.invoicing.model.Invoice
import java.nio.file.Path
import kotlin.io.path.name

class InvoiceOnFilesystem(
    val file: Path,
    val invoice: Invoice
) {
    override fun toString() = "${file.name} $invoice"
}