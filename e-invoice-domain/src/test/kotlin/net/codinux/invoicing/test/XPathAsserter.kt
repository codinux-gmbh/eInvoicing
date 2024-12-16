package net.codinux.invoicing.test

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.xmlunit.builder.Input
import org.xmlunit.xpath.JAXPXPathEngine
import java.math.BigDecimal
import java.math.RoundingMode

class XPathAsserter(
    xml: String
) {

    private val source = Input.fromString(xml).build()

    private val xpathEngine = JAXPXPathEngine().apply {
        setNamespaceContext(mapOf(
            "rsm" to "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100",
            "ram" to "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
            "udt" to "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100"
        ))
    }


    fun xpathHasValue(xpath: String, value: String) {
        assertThat(xpathEngine.evaluate(xpath, source)).isEqualTo(value)
    }

    fun xpathHasValue(xpath: String, value: BigDecimal, countDecimalPlatform: Int = 2) {
        xpathHasValue(xpath, value.setScale(countDecimalPlatform, RoundingMode.HALF_UP).toString())
    }

}