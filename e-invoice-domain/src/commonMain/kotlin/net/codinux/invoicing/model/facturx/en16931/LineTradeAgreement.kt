package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class LineTradeAgreement(
  @XmlSerialName(
    value = "BuyerOrderReferencedDocument",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val buyerOrderReferencedDocument: ReferencedDocument? = null,

  /**
   * Detailinformationen zum Bruttopreis des Artikels
   *
   * ChargeAmount: Der Einheitspreis ohne Umsatzsteuer vor Abzug des Nachlass auf den Artikelpreis
   *
   * Der Postenpreis ohne Umsatzsteuer vor Abzug des Postenpreisrabatts.
   *
   * Brottoverkaufspreis / Bruttolistenpreis ohne Mehrwertsteuer und ohne Abzug von Rabatten.
   */
  @XmlSerialName(
    value = "GrossPriceProductTradePrice",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val grossPriceProductTradePrice: TradePrice? = null,

  /**
   * Im Nettopreis sind alle Zu- und Abschläge enthalten, jedoch nicht die Umsatzsteuer.
   *
   * ChargeAmount: Der Nettopreis muss gleich dem Bruttopreis abzüglich des Nachlass auf den Artikelpreis sein.
   *
   * Der Preis eines Postens, ohne Umsatzsteuer, nach Abzug des für diese Rechnungsposition geltenden Rabatts.
   * Anmerkung: Item net price muss gleich Item gross price abzüglich Item price discount sein.
   *
   * Der Bruttolistenpreis abzüglich gewährter Rabatte. Wurden keine Rabatte gewährt, entspricht der NetPrice dem GrossPrice.
   * Wie der GrossPrice ohne Mehrwertsteuer
   */
  @XmlSerialName(
    value = "NetPriceProductTradePrice",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val netPriceProductTradePrice: TradePrice,
)
