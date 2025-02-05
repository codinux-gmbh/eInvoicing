package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class CreditorFinancialInstitution(
  /**
   * Factur-X: Wie z. B. der BIC oder eine nationale Bankleitzahl, sofern gefordert. Es ist kein Identifikationsschema zu
   * verwenden.
   *
   * Die anderen Felder kennt Factur-X nicht
   */
  @XmlSerialName(
    value = "BICID",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val bicid: ID,

  /**
   * Financial institution name. But be aware that this field is neither in Factur-X nor in XRechnung, so most
   * applications won't be able to read this field.
   */
  @XmlSerialName(
    value = "Name",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val name: Text? = null,
)
