package net.codinux.invoicing.test

import java.io.InputStream
import java.nio.file.Path
import kotlin.io.path.toPath

object TestUtils {

    fun getTestFileAsStream(filename: String, testFileFolder: String = "files"): InputStream =
        this.javaClass.classLoader.getResourceAsStream("$testFileFolder/$filename")!!

    fun getInvalidInvoiceFileAsStream(filename: String) =
        getTestFileAsStream(filename, "erroneousInvoiceFiles")


    fun getTestFile(filename: String, testFileFolder: String = "files"): Path =
        this.javaClass.classLoader.getResource("$testFileFolder/$filename")!!.toURI().toPath()

    fun getInvalidInvoiceFile(filename: String, testFileFolder: String = "files") =
        getTestFile(filename, "erroneousInvoiceFiles")

}