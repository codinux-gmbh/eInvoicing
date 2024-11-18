package net.codinux.invoicing.test

import java.io.File
import java.io.InputStream

object TestUtils {

    fun getTestFileAsStream(filename: String): InputStream =
        this.javaClass.classLoader.getResourceAsStream("files/$filename")!!

    fun getTestFile(filename: String): File =
        File(this.javaClass.classLoader.getResource("files/$filename")!!.toURI())

}