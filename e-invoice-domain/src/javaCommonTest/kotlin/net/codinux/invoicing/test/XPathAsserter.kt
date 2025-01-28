package net.codinux.invoicing.test

import assertk.assertThat
import assertk.assertions.isEqualByComparingTo
import net.codinux.invoicing.model.BigDecimal
import org.xmlunit.builder.Input
import org.xmlunit.xpath.JAXPXPathEngine

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
        assertThat(evaluate(xpath)).isEqualByComparingTo(value)
    }

    fun xpathHasValue(xpath: String, value: BigDecimal) {
        assertThat(BigDecimal(evaluate(xpath))).isEqualByComparingTo(value)
    }

    fun evaluate(xpath: String): String =
        xpathEngine.evaluate(xpath, source)

}