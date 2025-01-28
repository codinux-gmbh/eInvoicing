package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("CrossIndustryInvoice", "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100", "rsm")
data class CrossIndustryInvoice(
  @XmlSerialName(
    value = "ExchangedDocumentContext",
    prefix = "rsm",
    namespace = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100",
  )
  @XmlElement(value = true)
  val exchangedDocumentContext: ExchangedDocumentContext,
  @XmlSerialName(
    value = "ExchangedDocument",
    prefix = "rsm",
    namespace = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100",
  )
  @XmlElement(value = true)
  val exchangedDocument: ExchangedDocument,
  @XmlSerialName(
    value = "SupplyChainTradeTransaction",
    prefix = "rsm",
    namespace = "urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100",
  )
  @XmlElement(value = true)
  val supplyChainTradeTransaction: SupplyChainTradeTransaction,
)
