package net.codinux.invoicing.model.facturx.en16931

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
data class TradeTax(
  /**
   * On LineItem level only available in Extended profile
   */
  @XmlSerialName(
    value = "CalculatedAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val calculatedAmount: Amount? = null,

  /**
   * ### English
   * For more information on the recommended codes, please refer to subclause 6.3.3.2 - Specification of VAT
   * category codes.
   *
   * ### German
   * Fester Wert = "VAT"
   */
  @XmlSerialName(
    value = "TypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val typeCode: TaxTypeCode,
  @XmlSerialName(
    value = "ExemptionReason",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val exemptionReason: Text? = null,
  @XmlSerialName(
    value = "BasisAmount",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val basisAmount: Amount? = null,

  /**
   * ### English
   * The following entries of UNTDID 5305 [6] are used (further clarification between brackets):
   * - Standard rate (Liable for VAT in a standard way)
   * - Zero rated goods (Liable for VAT with a percentage rate of zero)
   * - Exempt from tax (VAT/IGIC/IPSI)
   * - VAT Reverse Charge (Reverse charge VAT/IGIC/IPSI rules apply)
   * - VAT exempt for intra community supply of goods (VAT/IGIC/IPSI not levied due to Intra-community supply
   * rules)
   * - Free export item, tax not charged (VAT/IGIC/IPSI not levied due to export outside of the EU)
   * - Services outside scope of tax (Sale is not subject to VAT/IGIC/IPSI)
   * - Canary Islands General Indirect Tax (Liable for IGIC tax)
   * - Liable for IPSI (Ceuta/Melilla tax)
   *
   * ### German
   * Folgende Einträge aus UNTDID 5305 [6] werden verwendet (nähere Angaben in Klammern):
   * - Normalsatz (Umsatzsteuer fällt nach Normalverfahren an);
   * - nach dem Nullsatz zu versteuernde Waren (Umsatzsteuer fällt mit einem Prozentsatz von null an);
   * - steuerbefreit (USt./IGIC/IPSI);
   * - Umkehrung der Steuerschuldnerschaft (es gelten die Regeln zur Umkehrung der Steuerschuldnerschaft bei
   * USt./IGIC/IPSI);
   * - umsatzsteuerbefreit für innergemeinschaftliche Warenlieferungen (USt./IGIC/IPSI nicht erhoben aufgrund von
   * Regeln zu innergemeinschaftlichen Lieferungen);
   * - freier Ausfuhrartikel, Steuer nicht erhoben (USt./IGIC/ IPSI nicht erhoben aufgrund von Export außerhalb der
   * EU);
   * - Dienstleistungen außerhalb des Steueranwendungsbereichs (Verkauf unterliegt nicht der USt./IGIC/IPSI)
   * - allgemeine indirekte Steuer der Kanarischen Inseln (IGIC-Steuer fällt an);
   * - IPSI (Steuer für Ceuta/Melilla) fällt an.
   */
  @XmlSerialName(
    value = "CategoryCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val categoryCode: TaxCategoryCode,
  @XmlSerialName(
    value = "ExemptionReasonCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val exemptionReasonCode: Code? = null,
  @XmlSerialName(
    value = "TaxPointDate",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val taxPointDate: Date? = null,
  @XmlSerialName(
    value = "DueDateTypeCode",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val dueDateTypeCode: TimeReferenceCode? = null,
  @XmlSerialName(
    value = "RateApplicablePercent",
    prefix = "ram",
    namespace = "urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100",
  )
  @XmlElement(value = true)
  val rateApplicablePercent: Percent? = null,
)
