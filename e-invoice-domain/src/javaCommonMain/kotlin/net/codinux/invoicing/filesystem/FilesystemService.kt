package net.codinux.invoicing.filesystem

import java.io.File

open class FilesystemService {

    protected open val IllegalFileCharacters = listOf('\\', '/', ':', '*', '?', '"', '<', '>', '|', '\u0000')


    open fun removeIllegalFileCharacters(name: String, replacementChar: Char = '_') = name
        .map { if (it in IllegalFileCharacters || it.code < 32) replacementChar else it }
        .joinToString("")


    open fun createTempPdfFile(): File =
        File(getSystemTempDirectoryPath(), "eInvoicing")
            .also { it.mkdirs() }
            .let { File.createTempFile("factur-x", ".pdf", it) }
            .also { it.deleteOnExit() }

    protected open fun getSystemTempDirectoryPath() = System.getProperty("java.io.tmpdir")

}