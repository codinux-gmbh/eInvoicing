package net.codinux.invoicing.parser.genericode

import com.helger.genericode.Genericode10CodeListMarshaller
import com.helger.xml.serialize.read.DOMReader
import net.codinux.invoicing.parser.model.CodeListType
import net.codinux.invoicing.parser.model.Column
import net.codinux.invoicing.parser.model.Row
import net.codinux.log.logger
import java.io.File
import java.io.InputStream
import java.util.zip.ZipFile

class CefGenericodeCodelistsParser {

    private val log by logger()


    fun parse(zipFile: File): List<CodeList> =
        ZipFile(zipFile).use { zip ->
            zip.entries().toList().filter { it.isDirectory == false && it.name.endsWith(".gc", true) }
                .mapNotNull { parse(zip.getInputStream(it), it.name) }
        }

    private fun parse(genericodeInputStream: InputStream, filename: String): CodeList? {
        val doc = DOMReader.readXMLDOM(genericodeInputStream)
        val marshaller = Genericode10CodeListMarshaller()

        if (doc == null) {
            log.info { "Could not read XML document from file $filename" }
            return null
        }

        val codeListDoc = marshaller.read(doc)
        if (codeListDoc == null) {
            log.info { "Could not read Code List from file $filename" }
            return null
        }

        val columnSet = codeListDoc.columnSet
        val identification = codeListDoc.identification
        val simpleCodeList = codeListDoc.simpleCodeList

        val name = File(filename).nameWithoutExtension
        val (version, canonicalUri, canonicalVersionUri) = Triple(identification?.version, identification?.canonicalUri, identification?.canonicalVersionUri)
        val columns = columnSet?.columnChoice.orEmpty().filterIsInstance<com.helger.genericode.v10.Column>().mapIndexed { index, col -> Column(index, col.id!!, col.data?.type!!, col.shortNameValue!!) }
        val rows = simpleCodeList?.row.orEmpty().map { row -> columns.map { column -> row.value.firstOrNull { (it.columnRef as? com.helger.genericode.v10.Column)?.id == column.id }?.simpleValueValue } }

        return CodeList(getType(name), name, version, canonicalUri, canonicalVersionUri, columns, rows.map { Row(it) })
    }

    private fun getType(name: String): CodeListType = when (name) {
        "Country" -> CodeListType.IsoCountryCodes
        "Currency" -> CodeListType.IsoCurrencyCodes
        "ICD" -> CodeListType.Iso_6523_IdentificationSchemeIdentifier

        "1001" -> CodeListType.UN_1001_InvoiceType
        "1153" -> CodeListType.UN_1153_ReferenceCode

        "Text" -> CodeListType.UN_4451_TextSubjectCodeQualifier
        "Payment" -> CodeListType.UN_4461_PaymentCodes
        "5305" -> CodeListType.UN_5305_DutyOrTaxOrFeeCategory
        "Allowance" -> CodeListType.UN_5189_AllowanceIdentificationCode
        "Item" -> CodeListType.UN_7143_ItemTypeIdentificationCode
        "Charge" -> CodeListType.UN_7161_SpecialServiceDescriptionCodes

        "Unit" -> CodeListType.Units

        "EAS" -> CodeListType.EAS
        "VATEX" -> CodeListType.VATEX
        "MIME" -> CodeListType.Mime

        else -> throw IllegalArgumentException("No known Code List of name '$name' found")
    }

}