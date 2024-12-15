package net.codinux.invoicing.test

import java.io.InputStream
import java.nio.file.Path
import kotlin.io.path.toPath

object TestUtils {

    fun getTestFileAsStream(filename: String): InputStream =
        this.javaClass.classLoader.getResourceAsStream("files/$filename")!!

    fun getTestFile(filename: String): Path =
        this.javaClass.classLoader.getResource("files/$filename")!!.toURI().toPath()

}