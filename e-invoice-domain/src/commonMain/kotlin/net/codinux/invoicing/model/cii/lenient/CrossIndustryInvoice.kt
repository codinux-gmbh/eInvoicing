package net.codinux.invoicing.model.cii.lenient

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

// TODO: this data model won't be able to deserialize 24A CII files due to the simple fact that udt namespace changed from
//   "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100" to
//   "urn:un:unece:uncefact:data:standard:UnqualifiedDataType:35"
//   (only "100" changed to "35", that's the only difference, and whoops it fails)
@Serializable
@XmlSerialName("CrossIndustryInvoice", "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100", "rsm")
data class CrossIndustryInvoice(
  @XmlSerialName(
    value = "ExchangedDocumentContext",
    prefix = "rsm",
    namespace = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100",
  )
  @XmlElement(value = true)
  val exchangedDocumentContext: ExchangedDocumentContext? = null,
  @XmlSerialName(
    value = "ExchangedDocument",
    prefix = "rsm",
    namespace = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100",
  )
  @XmlElement(value = true)
  val exchangedDocument: ExchangedDocument? = null,
  @XmlSerialName(
    value = "SupplyChainTradeTransaction",
    prefix = "rsm",
    namespace = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100",
  )
  @XmlElement(value = true)
  val supplyChainTradeTransaction: SupplyChainTradeTransaction? = null,
  @XmlSerialName(
    value = "ValuationBreakdownStatement",
    prefix = "rsm",
    namespace = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100",
  )
  @XmlElement(value = true)
  val valuationBreakdownStatement: ValuationBreakdownStatement? = null,
)
