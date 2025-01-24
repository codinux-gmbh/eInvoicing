package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ExchangedDocumentContext(
  @XmlSerialName(
    value = "BusinessProcessSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val businessProcessSpecifiedDocumentContextParameter: DocumentContextParameter? = null,
  @XmlSerialName(
    value = "GuidelineSpecifiedDocumentContextParameter",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val guidelineSpecifiedDocumentContextParameter: DocumentContextParameter,
)
