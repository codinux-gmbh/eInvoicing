package net.codinux.invoicing.model.cii.lenient

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
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
