package net.codinux.invoicing.filesystem

open class FilesystemService {

    protected open val IllegalFileCharacters = listOf('\\', '/', ':', '*', '?', '"', '<', '>', '|', '\u0000')


    open fun removeIllegalFileCharacters(name: String, replacementChar: Char = '_') = name
        .map { if (it in IllegalFileCharacters || it.code < 32) replacementChar else it }
        .joinToString("")

}