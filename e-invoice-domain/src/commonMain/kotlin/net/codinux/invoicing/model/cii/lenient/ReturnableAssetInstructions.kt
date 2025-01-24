package net.codinux.invoicing.model.cii.lenient

import kotlin.collections.List
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class ReturnableAssetInstructions(
  @XmlSerialName(
    value = "MaterialID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val materialID: List<ID> = emptyList(),
  @XmlSerialName(
    value = "TermsAndConditionsDescription",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val termsAndConditionsDescription: List<Text> = emptyList(),
  @XmlSerialName(
    value = "TermsAndConditionsDescriptionCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val termsAndConditionsDescriptionCode: Code? = null,
  @XmlSerialName(
    value = "DepositValueSpecifiedAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val depositValueSpecifiedAmount: List<Amount> = emptyList(),
  @XmlSerialName(
    value = "DepositValueValiditySpecifiedPeriod",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val depositValueValiditySpecifiedPeriod: SpecifiedPeriod? = null,
)
