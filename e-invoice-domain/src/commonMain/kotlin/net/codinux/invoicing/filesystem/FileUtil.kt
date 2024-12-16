package net.codinux.invoicing.filesystem

object FileUtil {

    private val IllegalFileCharacters = listOf('\\', '/', ':', '*', '?', '"', '<', '>', '|', '\u0000')


    fun removeIllegalFileCharacters(name: String, replacementChar: Char = '_') = name
        .map { if (it in IllegalFileCharacters || it.code < 32) replacementChar else it }
        .joinToString("")

}