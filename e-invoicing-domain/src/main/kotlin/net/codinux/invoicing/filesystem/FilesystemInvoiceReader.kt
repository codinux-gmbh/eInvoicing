package net.codinux.invoicing.filesystem

import net.codinux.invoicing.model.Invoice
import net.codinux.invoicing.reader.EInvoiceReader
import net.codinux.log.logger
import java.nio.file.Path
import kotlin.io.path.*

class FilesystemInvoiceReader(
    private val eInvoiceReader: EInvoiceReader = EInvoiceReader()
) {

    private val log by logger()

    fun readAllInvoicesOfDirectory(directory: Path, recursive: Boolean = false) =
        readInvoicesFromFiles(collectFiles(directory, recursive))

    private fun collectFiles(directory: Path, recursive: Boolean): List<Path> = buildList {
        directory.listDirectoryEntries().forEach { child ->
            if (child.isRegularFile()) {
                add(child)
            } else if (recursive && child.isDirectory()) {
                addAll(collectFiles(child, recursive))
            }
        }
    }

    fun readInvoicesFromFiles(vararg files: Path) =
        readInvoicesFromFiles(files.toList())

    fun readInvoicesFromFiles(files: List<Path>): List<InvoiceOnFilesystem> =
        files.mapNotNull { file -> readInvoiceFromFile(file)?.let { InvoiceOnFilesystem(file, it) } }

    fun readInvoiceFromFile(file: Path): Invoice? = try {
        val extension = file.extension.lowercase()

        if (extension == "pdf") {
            eInvoiceReader.extractFromPdf(file.inputStream())
        } else if (extension == "xml") {
            eInvoiceReader.readFromXml(file.inputStream())
        } else {
            null
        }
    } catch (e: Throwable) {
        log.debug(e) { "Could not extract invoices from $file" }
        null
    }

}