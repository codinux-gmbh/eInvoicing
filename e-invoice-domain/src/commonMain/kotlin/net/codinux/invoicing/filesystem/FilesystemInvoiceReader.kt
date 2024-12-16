package net.codinux.invoicing.filesystem

import net.codinux.invoicing.reader.EInvoiceReader
import java.nio.file.Path
import kotlin.io.path.*

open class FilesystemInvoiceReader(
    protected open val eInvoiceReader: EInvoiceReader = EInvoiceReader()
) {

    open fun readAllInvoicesOfDirectory(directory: Path, recursive: Boolean = false) =
        readInvoicesFromFiles(collectFiles(directory, recursive))

    protected open fun collectFiles(directory: Path, recursive: Boolean): List<Path> = buildList {
        directory.listDirectoryEntries().forEach { child ->
            if (child.isRegularFile()) {
                add(child)
            } else if (recursive && child.isDirectory()) {
                addAll(collectFiles(child, recursive))
            }
        }
    }

    open fun readInvoicesFromFiles(vararg files: Path) =
        readInvoicesFromFiles(files.toList())

    open fun readInvoicesFromFiles(files: List<Path>) =
        files.mapNotNull { file -> readInvoiceFromFile(file) }

    open fun readInvoiceFromFile(file: Path) =
        eInvoiceReader.extractFromFile(file.inputStream(), file.name, file.parent.absolutePathString())

}