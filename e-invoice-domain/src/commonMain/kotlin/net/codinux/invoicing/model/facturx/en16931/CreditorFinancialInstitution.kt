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

  // TODO: CII also has a name field for the name of the financial institution
)
