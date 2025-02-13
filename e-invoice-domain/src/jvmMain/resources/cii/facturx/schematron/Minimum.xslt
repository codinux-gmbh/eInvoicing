<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<xsl:stylesheet xmlns:svrl="http://purl.oclc.org/dsdl/svrl" xmlns:iso="http://purl.oclc.org/dsdl/schematron" xmlns:qdt="urn:un:unece:uncefact:data:standard:QualifiedDataType:100" xmlns:ram="urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100" xmlns:rsm="urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100" xmlns:saxon="http://saxon.sf.net/" xmlns:schold="http://www.ascc.net/xml/schematron" xmlns:udt="urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100" xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

<xsl:param name="archiveDirParameter" />
  <xsl:param name="archiveNameParameter" />
  <xsl:param name="fileNameParameter" />
  <xsl:param name="fileDirParameter" />
  <xsl:variable name="document-uri">
    <xsl:value-of select="document-uri(/)" />
  </xsl:variable>

<xsl:output indent="yes" method="xml" omit-xml-declaration="no" standalone="yes" />

<xsl:template match="*" mode="schematron-select-full-path">
    <xsl:text>/</xsl:text>
    <xsl:for-each select="ancestor-or-self::*">
        <xsl:text>:</xsl:text>
        <xsl:value-of select="name()" />
        <xsl:text>/</xsl:text>
    </xsl:for-each>
</xsl:template>

  

<xsl:template match="/" mode="generate-id-from-path" />
  <xsl:template match="text()" mode="generate-id-from-path">
    <xsl:apply-templates mode="generate-id-from-path" select="parent::*" />
    <xsl:value-of select="concat('.text-', 1+count(preceding-sibling::text()), '-')" />
  </xsl:template>
  <xsl:template match="comment()" mode="generate-id-from-path">
    <xsl:apply-templates mode="generate-id-from-path" select="parent::*" />
    <xsl:value-of select="concat('.comment-', 1+count(preceding-sibling::comment()), '-')" />
  </xsl:template>
  <xsl:template match="processing-instruction()" mode="generate-id-from-path">
    <xsl:apply-templates mode="generate-id-from-path" select="parent::*" />
    <xsl:value-of select="concat('.processing-instruction-', 1+count(preceding-sibling::processing-instruction()), '-')" />
  </xsl:template>
  <xsl:template match="@*" mode="generate-id-from-path">
    <xsl:apply-templates mode="generate-id-from-path" select="parent::*" />
    <xsl:value-of select="concat('.@', name())" />
  </xsl:template>
  <xsl:template match="*" mode="generate-id-from-path" priority="-0.5">
    <xsl:apply-templates mode="generate-id-from-path" select="parent::*" />
    <xsl:text>.</xsl:text>
    <xsl:value-of select="concat('.',name(),'-',1+count(preceding-sibling::*[name()=name(current())]),'-')" />
  </xsl:template>

<xsl:template match="/" mode="generate-id-2">U</xsl:template>
  <xsl:template match="*" mode="generate-id-2" priority="2">
    <xsl:text>U</xsl:text>
    <xsl:number count="*" level="multiple" />
  </xsl:template>
  <xsl:template match="node()" mode="generate-id-2">
    <xsl:text>U.</xsl:text>
    <xsl:number count="*" level="multiple" />
    <xsl:text>n</xsl:text>
    <xsl:number count="node()" />
  </xsl:template>
  <xsl:template match="@*" mode="generate-id-2">
    <xsl:text>U.</xsl:text>
    <xsl:number count="*" level="multiple" />
    <xsl:text>_</xsl:text>
    <xsl:value-of select="string-length(local-name(.))" />
    <xsl:text>_</xsl:text>
    <xsl:value-of select="translate(name(),':','.')" />
  </xsl:template>
  <xsl:template match="text()" priority="-1" />

<xsl:template match="/">
    <svrl:schematron-output schemaVersion="iso" title="Schema for Factur-X; 1.07.2; Accounting, MINIMUM">
      <xsl:comment>
        <xsl:value-of select="$archiveDirParameter" />   
		 <xsl:value-of select="$archiveNameParameter" />  
		 <xsl:value-of select="$fileNameParameter" />  
		 <xsl:value-of select="$fileDirParameter" />
      </xsl:comment>
      <svrl:ns-prefix-in-attribute-values prefix="rsm" uri="urn:un:unece:uncefact:data:standard:CrossIndustryInvoice:100" />
      <svrl:ns-prefix-in-attribute-values prefix="qdt" uri="urn:un:unece:uncefact:data:standard:QualifiedDataType:100" />
      <svrl:ns-prefix-in-attribute-values prefix="ram" uri="urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:100" />
      <svrl:ns-prefix-in-attribute-values prefix="udt" uri="urn:un:unece:uncefact:data:standard:UnqualifiedDataType:100" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M5" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M6" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M7" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M8" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M9" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M10" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M11" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M12" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M13" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M14" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M15" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M16" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M17" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M18" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M19" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M20" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M21" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M22" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M23" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M24" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M25" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M26" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M27" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M28" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M29" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M30" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M31" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M32" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M33" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M34" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M35" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M36" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M37" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M38" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M39" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M40" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M41" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M42" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M43" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M44" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M45" select="/" />
    </svrl:schematron-output>
  </xsl:template>

<svrl:text>Schema for Factur-X; 1.07.2; Accounting, MINIMUM</svrl:text>

	
<xsl:template match="//ram:SellerTradeParty" mode="M5" priority="1000">
    <svrl:fired-rule context="//ram:SellerTradeParty" />

		
<xsl:choose>
      <xsl:when test="(ram:ID) or (ram:GlobalID) or (ram:SpecifiedLegalOrganization/ram:ID) or (ram:SpecifiedTaxRegistration/ram:ID[@schemeID='VA'])" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:ID) or (ram:GlobalID) or (ram:SpecifiedLegalOrganization/ram:ID) or (ram:SpecifiedTaxRegistration/ram:ID[@schemeID='VA'])">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-26]-In order for the buyer to automatically identify a supplier, the Seller identifier (BT-29), the Seller legal registration identifier (BT-30) and/or the Seller VAT identifier (BT-31) shall be present.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M5" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M5" priority="-1" />
  <xsl:template match="@*|node()" mode="M5" priority="-2">
    <xsl:apply-templates mode="M5" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTaxRegistration/ram:ID[@schemeID='VA']" mode="M6" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTaxRegistration/ram:ID[@schemeID='VA']" />

		
<xsl:choose>
      <xsl:when test="contains(' 1A AD AE AF AG AI AL AM AN AO AQ AR AS AT AU AW AX AZ BA BB BD BE BF BG BH BI BL BJ BM BN BO BQ BR BS BT BV BW BY BZ CA CC CD CF CG CH CI CK CL CM CN CO CR CU CV CW CX CY CZ DE DJ DK DM DO DZ EC EE EG EH EL ER ES ET FI FJ FK FM FO FR GA GB GD GE GF GG GH GI GL GM GN GP GQ GR GS GT GU GW GY HK HM HN HR HT HU ID IE IL IM IN IO IQ IR IS IT JE JM JO JP KE KG KH KI KM KN KP KR KW KY KZ LA LB LC LI LK LR LS LT LU LV LY MA MC MD ME MF MG MH MK ML MM MN MO MP MQ MR MS MT MU MV MW MX MY MZ NA NC NE NF NG NI NL NO NP NR NU NZ OM PA PE PF PG PH PK PL PM PN PR PS PT PW PY QA RE RO RS RU RW SA SB SC SD SE SG SH SI SJ SK SL SM SN SO SR ST SV SX SY SZ TC TD TF TG TH TJ TK TL TM TN TO TR TT TV TW TZ UA UG UM US UY UZ VA VC VE VG VI VN VU WF WS XI YE YT ZA ZM ZW ', concat(' ', substring(.,1,2), ' '))" />
      <xsl:otherwise>
        <svrl:failed-assert test="contains(' 1A AD AE AF AG AI AL AM AN AO AQ AR AS AT AU AW AX AZ BA BB BD BE BF BG BH BI BL BJ BM BN BO BQ BR BS BT BV BW BY BZ CA CC CD CF CG CH CI CK CL CM CN CO CR CU CV CW CX CY CZ DE DJ DK DM DO DZ EC EE EG EH EL ER ES ET FI FJ FK FM FO FR GA GB GD GE GF GG GH GI GL GM GN GP GQ GR GS GT GU GW GY HK HM HN HR HT HU ID IE IL IM IN IO IQ IR IS IT JE JM JO JP KE KG KH KI KM KN KP KR KW KY KZ LA LB LC LI LK LR LS LT LU LV LY MA MC MD ME MF MG MH MK ML MM MN MO MP MQ MR MS MT MU MV MW MX MY MZ NA NC NE NF NG NI NL NO NP NR NU NZ OM PA PE PF PG PH PK PL PM PN PR PS PT PW PY QA RE RO RS RU RW SA SB SC SD SE SG SH SI SJ SK SL SM SN SO SR ST SV SX SY SZ TC TD TF TG TH TJ TK TL TM TN TO TR TT TV TW TZ UA UG UM US UY UZ VA VC VE VG VI VN VU WF WS XI YE YT ZA ZM ZW ', concat(' ', substring(.,1,2), ' '))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-09]-The Seller VAT identifier (BT-31), the Seller tax representative VAT identifier (BT-63) and the Buyer VAT identifier (BT-48) shall have a prefix in accordance with ISO code ISO 3166-1 alpha-2 by which the country of issue may be identified. Nevertheless, Greece may use the prefix ‘EL’.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M6" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M6" priority="-1" />
  <xsl:template match="@*|node()" mode="M6" priority="-2">
    <xsl:apply-templates mode="M6" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeSettlementHeaderMonetarySummation" mode="M7" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeSettlementHeaderMonetarySummation" />

		
<xsl:choose>
      <xsl:when test="(ram:TaxBasisTotalAmount)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:TaxBasisTotalAmount)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-13]-An Invoice shall have the Invoice total amount without VAT (BT-109).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:GrandTotalAmount)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:GrandTotalAmount)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-14]-An Invoice shall have the Invoice total amount with VAT (BT-112).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:DuePayableAmount)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:DuePayableAmount)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-15]-An Invoice shall have the Amount due for payment (BT-115).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:TaxBasisTotalAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:TaxBasisTotalAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-12]-The allowed maximum number of decimals for the Invoice total amount without VAT (BT-109) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(ram:TaxTotalAmount) or ram:TaxTotalAmount[(@currencyID =/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode and . = round(. * 100) div 100) or not (@currencyID =/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode)]" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(ram:TaxTotalAmount) or ram:TaxTotalAmount[(@currencyID =/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode and . = round(. * 100) div 100) or not (@currencyID =/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode)]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-13]-The allowed maximum number of decimals for the Invoice total VAT amount (BT-110) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:GrandTotalAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:GrandTotalAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-14]-The allowed maximum number of decimals for the Invoice total amount with VAT (BT-112) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:DuePayableAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:DuePayableAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-18]-The allowed maximum number of decimals for the Amount due for payment (BT-115) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M7" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M7" priority="-1" />
  <xsl:template match="@*|node()" mode="M7" priority="-2">
    <xsl:apply-templates mode="M7" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice" mode="M8" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice" />

		
<xsl:choose>
      <xsl:when test="(rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter/ram:ID != '')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter/ram:ID != '')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-01]-An Invoice shall have a Specification identifier (BT-24).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(rsm:ExchangedDocument/ram:ID !='')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(rsm:ExchangedDocument/ram:ID !='')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-02]-An Invoice shall have an Invoice number (BT-1).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString[@format='102']!='')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString[@format='102']!='')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-03]-An Invoice shall have an Invoice issue date (BT-2).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(rsm:ExchangedDocument/ram:TypeCode!='')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(rsm:ExchangedDocument/ram:TypeCode!='')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-04]-An Invoice shall have an Invoice type code (BT-3).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode!='')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode!='')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-05]-An Invoice shall have an Invoice currency code (BT-5).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:Name!='')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:Name!='')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-06]-An Invoice shall contain the Seller name (BT-27).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:Name!='')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:Name!='')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-07]-An Invoice shall contain the Buyer name (BT-44).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="//ram:SellerTradeParty/ram:PostalTradeAddress" />
      <xsl:otherwise>
        <svrl:failed-assert test="//ram:SellerTradeParty/ram:PostalTradeAddress">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-08]-An Invoice shall contain the Seller postal address (BG-5).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="//ram:SellerTradeParty/ram:PostalTradeAddress/ram:CountryID!=''" />
      <xsl:otherwise>
        <svrl:failed-assert test="//ram:SellerTradeParty/ram:PostalTradeAddress/ram:CountryID!=''">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-09]-The Seller postal address (BG-5) shall contain a Seller country code (BT-40).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M8" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M8" priority="-1" />
  <xsl:template match="@*|node()" mode="M8" priority="-2">
    <xsl:apply-templates mode="M8" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument" mode="M9" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:TypeCode)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:TypeCode)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:TypeCode' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M9" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M9" priority="-1" />
  <xsl:template match="@*|node()" mode="M9" priority="-2">
    <xsl:apply-templates mode="M9" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:ID[@schemeID]" mode="M10" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:ID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M10" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M10" priority="-1" />
  <xsl:template match="@*|node()" mode="M10" priority="-2">
    <xsl:apply-templates mode="M10" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString" mode="M11" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString" />

		
<xsl:choose>
      <xsl:when test="@format" />
      <xsl:otherwise>
        <svrl:failed-assert test="@format">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Attribute '@format' is required in this context.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M11" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M11" priority="-1" />
  <xsl:template match="@*|node()" mode="M11" priority="-2">
    <xsl:apply-templates mode="M11" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString[@format]" mode="M12" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString[@format]" />
    <xsl:variable name="codeValue3" select="@format" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=3]/enumeration[@value=$codeValue3]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=3]/enumeration[@value=$codeValue3]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@format' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M12" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M12" priority="-1" />
  <xsl:template match="@*|node()" mode="M12" priority="-2">
    <xsl:apply-templates mode="M12" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:TypeCode" mode="M13" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:TypeCode" />
    <xsl:variable name="codeValue2" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=2]/enumeration[@value=$codeValue2]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=2]/enumeration[@value=$codeValue2]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:TypeCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M13" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M13" priority="-1" />
  <xsl:template match="@*|node()" mode="M13" priority="-2">
    <xsl:apply-templates mode="M13" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext" mode="M14" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext" />

		
<xsl:choose>
      <xsl:when test="count(ram:BusinessProcessSpecifiedDocumentContextParameter)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:BusinessProcessSpecifiedDocumentContextParameter)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:BusinessProcessSpecifiedDocumentContextParameter' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:GuidelineSpecifiedDocumentContextParameter)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:GuidelineSpecifiedDocumentContextParameter)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:GuidelineSpecifiedDocumentContextParameter' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M14" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M14" priority="-1" />
  <xsl:template match="@*|node()" mode="M14" priority="-2">
    <xsl:apply-templates mode="M14" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:BusinessProcessSpecifiedDocumentContextParameter" mode="M15" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:BusinessProcessSpecifiedDocumentContextParameter" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M15" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M15" priority="-1" />
  <xsl:template match="@*|node()" mode="M15" priority="-2">
    <xsl:apply-templates mode="M15" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:BusinessProcessSpecifiedDocumentContextParameter/ram:ID[@schemeID]" mode="M16" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:BusinessProcessSpecifiedDocumentContextParameter/ram:ID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M16" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M16" priority="-1" />
  <xsl:template match="@*|node()" mode="M16" priority="-2">
    <xsl:apply-templates mode="M16" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter" mode="M17" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M17" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M17" priority="-1" />
  <xsl:template match="@*|node()" mode="M17" priority="-2">
    <xsl:apply-templates mode="M17" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter/ram:ID" mode="M18" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter/ram:ID" />
    <xsl:variable name="codeValue1" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=1]/enumeration[@value=$codeValue1]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=1]/enumeration[@value=$codeValue1]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:ID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M18" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M18" priority="-1" />
  <xsl:template match="@*|node()" mode="M18" priority="-2">
    <xsl:apply-templates mode="M18" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter/ram:ID[@schemeID]" mode="M19" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter/ram:ID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M19" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M19" priority="-1" />
  <xsl:template match="@*|node()" mode="M19" priority="-2">
    <xsl:apply-templates mode="M19" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement" mode="M20" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement" />

		
<xsl:choose>
      <xsl:when test="count(ram:SellerTradeParty)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:SellerTradeParty)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:SellerTradeParty' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:BuyerTradeParty)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:BuyerTradeParty)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:BuyerTradeParty' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M20" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M20" priority="-1" />
  <xsl:template match="@*|node()" mode="M20" priority="-2">
    <xsl:apply-templates mode="M20" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerOrderReferencedDocument" mode="M21" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerOrderReferencedDocument" />

		
<xsl:choose>
      <xsl:when test="count(ram:IssuerAssignedID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:IssuerAssignedID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:IssuerAssignedID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M21" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M21" priority="-1" />
  <xsl:template match="@*|node()" mode="M21" priority="-2">
    <xsl:apply-templates mode="M21" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerOrderReferencedDocument/ram:IssuerAssignedID[@schemeID]" mode="M22" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerOrderReferencedDocument/ram:IssuerAssignedID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M22" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M22" priority="-1" />
  <xsl:template match="@*|node()" mode="M22" priority="-2">
    <xsl:apply-templates mode="M22" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty" mode="M23" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty" />

		
<xsl:choose>
      <xsl:when test="count(ram:Name)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:Name)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:Name' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M23" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M23" priority="-1" />
  <xsl:template match="@*|node()" mode="M23" priority="-2">
    <xsl:apply-templates mode="M23" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:PostalTradeAddress" mode="M24" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:PostalTradeAddress" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:PostalTradeAddress' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M24" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M24" priority="-1" />
  <xsl:template match="@*|node()" mode="M24" priority="-2">
    <xsl:apply-templates mode="M24" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedLegalOrganization" mode="M25" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedLegalOrganization" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M25" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M25" priority="-1" />
  <xsl:template match="@*|node()" mode="M25" priority="-2">
    <xsl:apply-templates mode="M25" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" mode="M26" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" />
    <xsl:variable name="codeValue4" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=4]/enumeration[@value=$codeValue4]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=4]/enumeration[@value=$codeValue4]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M26" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M26" priority="-1" />
  <xsl:template match="@*|node()" mode="M26" priority="-2">
    <xsl:apply-templates mode="M26" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration" mode="M27" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:SpecifiedTaxRegistration' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M27" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M27" priority="-1" />
  <xsl:template match="@*|node()" mode="M27" priority="-2">
    <xsl:apply-templates mode="M27" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty" mode="M28" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty" />

		
<xsl:choose>
      <xsl:when test="count(ram:Name)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:Name)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:Name' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:PostalTradeAddress)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:PostalTradeAddress)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:PostalTradeAddress' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;VA&quot;])&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;VA&quot;])&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element variant 'ram:SpecifiedTaxRegistration[ram:ID/@schemeID="VA"]' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;FC&quot;])&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;FC&quot;])&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element variant 'ram:SpecifiedTaxRegistration[ram:ID/@schemeID="FC"]' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M28" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M28" priority="-1" />
  <xsl:template match="@*|node()" mode="M28" priority="-2">
    <xsl:apply-templates mode="M28" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:PostalTradeAddress" mode="M29" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:PostalTradeAddress" />

		
<xsl:choose>
      <xsl:when test="count(ram:CountryID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CountryID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CountryID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M29" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M29" priority="-1" />
  <xsl:template match="@*|node()" mode="M29" priority="-2">
    <xsl:apply-templates mode="M29" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:PostalTradeAddress/ram:CountryID" mode="M30" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:PostalTradeAddress/ram:CountryID" />
    <xsl:variable name="codeValue5" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:CountryID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M30" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M30" priority="-1" />
  <xsl:template match="@*|node()" mode="M30" priority="-2">
    <xsl:apply-templates mode="M30" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" mode="M31" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" />
    <xsl:variable name="codeValue4" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=4]/enumeration[@value=$codeValue4]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=4]/enumeration[@value=$codeValue4]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M31" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M31" priority="-1" />
  <xsl:template match="@*|node()" mode="M31" priority="-2">
    <xsl:apply-templates mode="M31" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ not(ram:ID/@schemeID=&quot;VA&quot;) and  not(ram:ID/@schemeID=&quot;FC&quot;)]" mode="M32" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ not(ram:ID/@schemeID=&quot;VA&quot;) and  not(ram:ID/@schemeID=&quot;FC&quot;)]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element variant 'ram:SpecifiedTaxRegistration[ not(ram:ID/@schemeID="VA") and  not(ram:ID/@schemeID="FC")]' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M32" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M32" priority="-1" />
  <xsl:template match="@*|node()" mode="M32" priority="-2">
    <xsl:apply-templates mode="M32" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;FC&quot;]" mode="M33" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;FC&quot;]" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M33" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M33" priority="-1" />
  <xsl:template match="@*|node()" mode="M33" priority="-2">
    <xsl:apply-templates mode="M33" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;FC&quot;]/ram:ID" mode="M34" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;FC&quot;]/ram:ID" />

		
<xsl:choose>
      <xsl:when test="@schemeID" />
      <xsl:otherwise>
        <svrl:failed-assert test="@schemeID">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Attribute '@schemeID' is required in this context.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M34" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M34" priority="-1" />
  <xsl:template match="@*|node()" mode="M34" priority="-2">
    <xsl:apply-templates mode="M34" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;VA&quot;]" mode="M35" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;VA&quot;]" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M35" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M35" priority="-1" />
  <xsl:template match="@*|node()" mode="M35" priority="-2">
    <xsl:apply-templates mode="M35" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;VA&quot;]/ram:ID" mode="M36" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;VA&quot;]/ram:ID" />

		
<xsl:choose>
      <xsl:when test="@schemeID" />
      <xsl:otherwise>
        <svrl:failed-assert test="@schemeID">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Attribute '@schemeID' is required in this context.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M36" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M36" priority="-1" />
  <xsl:template match="@*|node()" mode="M36" priority="-2">
    <xsl:apply-templates mode="M36" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement" mode="M37" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement" />

		
<xsl:choose>
      <xsl:when test="count(ram:InvoiceCurrencyCode)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:InvoiceCurrencyCode)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:InvoiceCurrencyCode' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:SpecifiedTradeSettlementHeaderMonetarySummation)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:SpecifiedTradeSettlementHeaderMonetarySummation)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:SpecifiedTradeSettlementHeaderMonetarySummation' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M37" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M37" priority="-1" />
  <xsl:template match="@*|node()" mode="M37" priority="-2">
    <xsl:apply-templates mode="M37" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode" mode="M38" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode" />
    <xsl:variable name="codeValue6" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=6]/enumeration[@value=$codeValue6]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=6]/enumeration[@value=$codeValue6]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:InvoiceCurrencyCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M38" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M38" priority="-1" />
  <xsl:template match="@*|node()" mode="M38" priority="-2">
    <xsl:apply-templates mode="M38" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation" mode="M39" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation" />

		
<xsl:choose>
      <xsl:when test="count(ram:TaxBasisTotalAmount)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:TaxBasisTotalAmount)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:TaxBasisTotalAmount' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode])&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode])&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element variant 'ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode]' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:GrandTotalAmount)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:GrandTotalAmount)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:GrandTotalAmount' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:DuePayableAmount)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:DuePayableAmount)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:DuePayableAmount' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M39" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M39" priority="-1" />
  <xsl:template match="@*|node()" mode="M39" priority="-2">
    <xsl:apply-templates mode="M39" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:DuePayableAmount[@currencyID]" mode="M40" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:DuePayableAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M40" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M40" priority="-1" />
  <xsl:template match="@*|node()" mode="M40" priority="-2">
    <xsl:apply-templates mode="M40" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:GrandTotalAmount[@currencyID]" mode="M41" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:GrandTotalAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M41" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M41" priority="-1" />
  <xsl:template match="@*|node()" mode="M41" priority="-2">
    <xsl:apply-templates mode="M41" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxBasisTotalAmount[@currencyID]" mode="M42" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxBasisTotalAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M42" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M42" priority="-1" />
  <xsl:template match="@*|node()" mode="M42" priority="-2">
    <xsl:apply-templates mode="M42" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[ not(@currencyID=../../ram:InvoiceCurrencyCode) and  not(@currencyID=../../ram:TaxCurrencyCode)]" mode="M43" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[ not(@currencyID=../../ram:InvoiceCurrencyCode) and  not(@currencyID=../../ram:TaxCurrencyCode)]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element variant 'ram:TaxTotalAmount[ not(@currencyID=../../ram:InvoiceCurrencyCode) and  not(@currencyID=../../ram:TaxCurrencyCode)]' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M43" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M43" priority="-1" />
  <xsl:template match="@*|node()" mode="M43" priority="-2">
    <xsl:apply-templates mode="M43" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode and @currencyID]" mode="M44" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode and @currencyID]" />
    <xsl:variable name="codeValue7" select="@currencyID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@currencyID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M44" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M44" priority="-1" />
  <xsl:template match="@*|node()" mode="M44" priority="-2">
    <xsl:apply-templates mode="M44" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode]" mode="M45" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode]" />

		
<xsl:choose>
      <xsl:when test="@currencyID" />
      <xsl:otherwise>
        <svrl:failed-assert test="@currencyID">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Attribute '@currencyID' is required in this context.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M45" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M45" priority="-1" />
  <xsl:template match="@*|node()" mode="M45" priority="-2">
    <xsl:apply-templates mode="M45" select="@*|*" />
  </xsl:template>

  <!-- to not have to call external codedb.xml file, which is quite hard to also extract from .jar, i embedded external codedb.xml here (and wouldn't work anyway as the Factur-X team calls to a not existing external codedb.xml file): -->
  <xsl:variable name="codedb">
    
  <cl id="1">
    <enumeration value="urn:factur-x.eu:1p0:minimum"/>
    <enumeration value="urn:zugferd.de:2p0:minimum"/>
  </cl>
  <cl id="2">
    <enumeration value="71"/>
    <enumeration value="80"/>
    <enumeration value="81"/>
    <enumeration value="82"/>
    <enumeration value="83"/>
    <enumeration value="84"/>
    <enumeration value="102"/>
    <enumeration value="130"/>
    <enumeration value="202"/>
    <enumeration value="203"/>
    <enumeration value="204"/>
    <enumeration value="211"/>
    <enumeration value="218"/>
    <enumeration value="219"/>
    <enumeration value="261"/>
    <enumeration value="262"/>
    <enumeration value="295"/>
    <enumeration value="296"/>
    <enumeration value="308"/>
    <enumeration value="325"/>
    <enumeration value="326"/>
    <enumeration value="331"/>
    <enumeration value="380"/>
    <enumeration value="381"/>
    <enumeration value="382"/>
    <enumeration value="383"/>
    <enumeration value="384"/>
    <enumeration value="385"/>
    <enumeration value="386"/>
    <enumeration value="387"/>
    <enumeration value="388"/>
    <enumeration value="389"/>
    <enumeration value="390"/>
    <enumeration value="393"/>
    <enumeration value="394"/>
    <enumeration value="395"/>
    <enumeration value="396"/>
    <enumeration value="420"/>
    <enumeration value="456"/>
    <enumeration value="457"/>
    <enumeration value="458"/>
    <enumeration value="527"/>
    <enumeration value="532"/>
    <enumeration value="553"/>
    <enumeration value="575"/>
    <enumeration value="623"/>
    <enumeration value="633"/>
    <enumeration value="751"/>
    <enumeration value="780"/>
    <enumeration value="817"/>
    <enumeration value="870"/>
    <enumeration value="875"/>
    <enumeration value="876"/>
    <enumeration value="877"/>
    <enumeration value="935"/>
  </cl>
  <cl id="3">
    <enumeration value="102"/>
  </cl>
  <cl id="4">
    <enumeration value="0002"/>
    <enumeration value="0003"/>
    <enumeration value="0004"/>
    <enumeration value="0005"/>
    <enumeration value="0006"/>
    <enumeration value="0007"/>
    <enumeration value="0008"/>
    <enumeration value="0009"/>
    <enumeration value="0010"/>
    <enumeration value="0011"/>
    <enumeration value="0012"/>
    <enumeration value="0013"/>
    <enumeration value="0014"/>
    <enumeration value="0015"/>
    <enumeration value="0016"/>
    <enumeration value="0017"/>
    <enumeration value="0018"/>
    <enumeration value="0019"/>
    <enumeration value="0020"/>
    <enumeration value="0021"/>
    <enumeration value="0022"/>
    <enumeration value="0023"/>
    <enumeration value="0024"/>
    <enumeration value="0025"/>
    <enumeration value="0026"/>
    <enumeration value="0027"/>
    <enumeration value="0028"/>
    <enumeration value="0029"/>
    <enumeration value="0030"/>
    <enumeration value="0031"/>
    <enumeration value="0032"/>
    <enumeration value="0033"/>
    <enumeration value="0034"/>
    <enumeration value="0035"/>
    <enumeration value="0036"/>
    <enumeration value="0037"/>
    <enumeration value="0038"/>
    <enumeration value="0039"/>
    <enumeration value="0040"/>
    <enumeration value="0041"/>
    <enumeration value="0042"/>
    <enumeration value="0043"/>
    <enumeration value="0044"/>
    <enumeration value="0045"/>
    <enumeration value="0046"/>
    <enumeration value="0047"/>
    <enumeration value="0048"/>
    <enumeration value="0049"/>
    <enumeration value="0050"/>
    <enumeration value="0051"/>
    <enumeration value="0052"/>
    <enumeration value="0053"/>
    <enumeration value="0054"/>
    <enumeration value="0055"/>
    <enumeration value="0056"/>
    <enumeration value="0057"/>
    <enumeration value="0058"/>
    <enumeration value="0059"/>
    <enumeration value="0060"/>
    <enumeration value="0061"/>
    <enumeration value="0062"/>
    <enumeration value="0063"/>
    <enumeration value="0064"/>
    <enumeration value="0065"/>
    <enumeration value="0066"/>
    <enumeration value="0067"/>
    <enumeration value="0068"/>
    <enumeration value="0069"/>
    <enumeration value="0070"/>
    <enumeration value="0071"/>
    <enumeration value="0072"/>
    <enumeration value="0073"/>
    <enumeration value="0074"/>
    <enumeration value="0075"/>
    <enumeration value="0076"/>
    <enumeration value="0077"/>
    <enumeration value="0078"/>
    <enumeration value="0079"/>
    <enumeration value="0080"/>
    <enumeration value="0081"/>
    <enumeration value="0082"/>
    <enumeration value="0083"/>
    <enumeration value="0084"/>
    <enumeration value="0085"/>
    <enumeration value="0086"/>
    <enumeration value="0087"/>
    <enumeration value="0088"/>
    <enumeration value="0089"/>
    <enumeration value="0090"/>
    <enumeration value="0091"/>
    <enumeration value="0093"/>
    <enumeration value="0094"/>
    <enumeration value="0095"/>
    <enumeration value="0096"/>
    <enumeration value="0097"/>
    <enumeration value="0098"/>
    <enumeration value="0099"/>
    <enumeration value="0100"/>
    <enumeration value="0101"/>
    <enumeration value="0102"/>
    <enumeration value="0104"/>
    <enumeration value="0105"/>
    <enumeration value="0106"/>
    <enumeration value="0107"/>
    <enumeration value="0108"/>
    <enumeration value="0109"/>
    <enumeration value="0110"/>
    <enumeration value="0111"/>
    <enumeration value="0112"/>
    <enumeration value="0113"/>
    <enumeration value="0114"/>
    <enumeration value="0115"/>
    <enumeration value="0116"/>
    <enumeration value="0117"/>
    <enumeration value="0118"/>
    <enumeration value="0119"/>
    <enumeration value="0120"/>
    <enumeration value="0121"/>
    <enumeration value="0122"/>
    <enumeration value="0123"/>
    <enumeration value="0124"/>
    <enumeration value="0125"/>
    <enumeration value="0126"/>
    <enumeration value="0127"/>
    <enumeration value="0128"/>
    <enumeration value="0129"/>
    <enumeration value="0130"/>
    <enumeration value="0131"/>
    <enumeration value="0132"/>
    <enumeration value="0133"/>
    <enumeration value="0134"/>
    <enumeration value="0135"/>
    <enumeration value="0136"/>
    <enumeration value="0137"/>
    <enumeration value="0138"/>
    <enumeration value="0139"/>
    <enumeration value="0140"/>
    <enumeration value="0141"/>
    <enumeration value="0142"/>
    <enumeration value="0143"/>
    <enumeration value="0144"/>
    <enumeration value="0145"/>
    <enumeration value="0146"/>
    <enumeration value="0147"/>
    <enumeration value="0148"/>
    <enumeration value="0149"/>
    <enumeration value="0150"/>
    <enumeration value="0151"/>
    <enumeration value="0152"/>
    <enumeration value="0153"/>
    <enumeration value="0154"/>
    <enumeration value="0155"/>
    <enumeration value="0156"/>
    <enumeration value="0157"/>
    <enumeration value="0158"/>
    <enumeration value="0159"/>
    <enumeration value="0160"/>
    <enumeration value="0161"/>
    <enumeration value="0162"/>
    <enumeration value="0163"/>
    <enumeration value="0164"/>
    <enumeration value="0165"/>
    <enumeration value="0166"/>
    <enumeration value="0167"/>
    <enumeration value="0168"/>
    <enumeration value="0169"/>
    <enumeration value="0170"/>
    <enumeration value="0171"/>
    <enumeration value="0172"/>
    <enumeration value="0173"/>
    <enumeration value="0174"/>
    <enumeration value="0175"/>
    <enumeration value="0176"/>
    <enumeration value="0177"/>
    <enumeration value="0178"/>
    <enumeration value="0179"/>
    <enumeration value="0180"/>
    <enumeration value="0183"/>
    <enumeration value="0184"/>
    <enumeration value="0185"/>
    <enumeration value="0186"/>
    <enumeration value="0187"/>
    <enumeration value="0188"/>
    <enumeration value="0189"/>
    <enumeration value="0190"/>
    <enumeration value="0191"/>
    <enumeration value="0192"/>
    <enumeration value="0193"/>
    <enumeration value="0194"/>
    <enumeration value="0195"/>
    <enumeration value="0196"/>
    <enumeration value="0197"/>
    <enumeration value="0198"/>
    <enumeration value="0199"/>
    <enumeration value="0200"/>
    <enumeration value="0201"/>
    <enumeration value="0202"/>
    <enumeration value="0203"/>
    <enumeration value="0204"/>
    <enumeration value="0205"/>
    <enumeration value="0206"/>
    <enumeration value="0207"/>
    <enumeration value="0208"/>
    <enumeration value="0209"/>
    <enumeration value="0210"/>
    <enumeration value="0211"/>
    <enumeration value="0212"/>
    <enumeration value="0213"/>
    <enumeration value="0214"/>
    <enumeration value="0215"/>
    <enumeration value="0216"/>
    <enumeration value="0217"/>
    <enumeration value="0218"/>
    <enumeration value="0219"/>
    <enumeration value="0220"/>
    <enumeration value="0221"/>
    <enumeration value="0222"/>
    <enumeration value="0223"/>
    <enumeration value="0224"/>
    <enumeration value="0225"/>
    <enumeration value="0226"/>
    <enumeration value="0227"/>
    <enumeration value="0228"/>
    <enumeration value="0229"/>
    <enumeration value="0230"/>
    <enumeration value="0231"/>
    <enumeration value="0232"/>
    <enumeration value="0233"/>
    <enumeration value="0234"/>
    <enumeration value="0235"/>
    <enumeration value="0236"/>
    <enumeration value="0237"/>
    <enumeration value="0238"/>
  </cl>
  <cl id="5">
    <enumeration value="AD"/>
    <enumeration value="AE"/>
    <enumeration value="AF"/>
    <enumeration value="AG"/>
    <enumeration value="AI"/>
    <enumeration value="AL"/>
    <enumeration value="AM"/>
    <enumeration value="AO"/>
    <enumeration value="AQ"/>
    <enumeration value="AR"/>
    <enumeration value="AS"/>
    <enumeration value="AT"/>
    <enumeration value="AU"/>
    <enumeration value="AW"/>
    <enumeration value="AX"/>
    <enumeration value="AZ"/>
    <enumeration value="BA"/>
    <enumeration value="BB"/>
    <enumeration value="BD"/>
    <enumeration value="BE"/>
    <enumeration value="BF"/>
    <enumeration value="BG"/>
    <enumeration value="BH"/>
    <enumeration value="BI"/>
    <enumeration value="BJ"/>
    <enumeration value="BL"/>
    <enumeration value="BM"/>
    <enumeration value="BN"/>
    <enumeration value="BO"/>
    <enumeration value="BQ"/>
    <enumeration value="BR"/>
    <enumeration value="BS"/>
    <enumeration value="BT"/>
    <enumeration value="BV"/>
    <enumeration value="BW"/>
    <enumeration value="BY"/>
    <enumeration value="BZ"/>
    <enumeration value="CA"/>
    <enumeration value="CC"/>
    <enumeration value="CD"/>
    <enumeration value="CF"/>
    <enumeration value="CG"/>
    <enumeration value="CH"/>
    <enumeration value="CI"/>
    <enumeration value="CK"/>
    <enumeration value="CL"/>
    <enumeration value="CM"/>
    <enumeration value="CN"/>
    <enumeration value="CO"/>
    <enumeration value="CR"/>
    <enumeration value="CU"/>
    <enumeration value="CV"/>
    <enumeration value="CW"/>
    <enumeration value="CX"/>
    <enumeration value="CY"/>
    <enumeration value="CZ"/>
    <enumeration value="DE"/>
    <enumeration value="DJ"/>
    <enumeration value="DK"/>
    <enumeration value="DM"/>
    <enumeration value="DO"/>
    <enumeration value="DZ"/>
    <enumeration value="EC"/>
    <enumeration value="EE"/>
    <enumeration value="EG"/>
    <enumeration value="EH"/>
    <enumeration value="ER"/>
    <enumeration value="ES"/>
    <enumeration value="ET"/>
    <enumeration value="FI"/>
    <enumeration value="FJ"/>
    <enumeration value="FK"/>
    <enumeration value="FM"/>
    <enumeration value="FO"/>
    <enumeration value="FR"/>
    <enumeration value="GA"/>
    <enumeration value="GB"/>
    <enumeration value="GD"/>
    <enumeration value="GE"/>
    <enumeration value="GF"/>
    <enumeration value="GG"/>
    <enumeration value="GH"/>
    <enumeration value="GI"/>
    <enumeration value="GL"/>
    <enumeration value="GM"/>
    <enumeration value="GN"/>
    <enumeration value="GP"/>
    <enumeration value="GQ"/>
    <enumeration value="GR"/>
    <enumeration value="GS"/>
    <enumeration value="GT"/>
    <enumeration value="GU"/>
    <enumeration value="GW"/>
    <enumeration value="GY"/>
    <enumeration value="HK"/>
    <enumeration value="HM"/>
    <enumeration value="HN"/>
    <enumeration value="HR"/>
    <enumeration value="HT"/>
    <enumeration value="HU"/>
    <enumeration value="ID"/>
    <enumeration value="IE"/>
    <enumeration value="IL"/>
    <enumeration value="IM"/>
    <enumeration value="IN"/>
    <enumeration value="IO"/>
    <enumeration value="IQ"/>
    <enumeration value="IR"/>
    <enumeration value="IS"/>
    <enumeration value="IT"/>
    <enumeration value="JE"/>
    <enumeration value="JM"/>
    <enumeration value="JO"/>
    <enumeration value="JP"/>
    <enumeration value="KE"/>
    <enumeration value="KG"/>
    <enumeration value="KH"/>
    <enumeration value="KI"/>
    <enumeration value="KM"/>
    <enumeration value="KN"/>
    <enumeration value="KP"/>
    <enumeration value="KR"/>
    <enumeration value="KW"/>
    <enumeration value="KY"/>
    <enumeration value="KZ"/>
    <enumeration value="LA"/>
    <enumeration value="LB"/>
    <enumeration value="LC"/>
    <enumeration value="LI"/>
    <enumeration value="LK"/>
    <enumeration value="LR"/>
    <enumeration value="LS"/>
    <enumeration value="LT"/>
    <enumeration value="LU"/>
    <enumeration value="LV"/>
    <enumeration value="LY"/>
    <enumeration value="MA"/>
    <enumeration value="MC"/>
    <enumeration value="MD"/>
    <enumeration value="ME"/>
    <enumeration value="MF"/>
    <enumeration value="MG"/>
    <enumeration value="MH"/>
    <enumeration value="MK"/>
    <enumeration value="ML"/>
    <enumeration value="MM"/>
    <enumeration value="MN"/>
    <enumeration value="MO"/>
    <enumeration value="MP"/>
    <enumeration value="MQ"/>
    <enumeration value="MR"/>
    <enumeration value="MS"/>
    <enumeration value="MT"/>
    <enumeration value="MU"/>
    <enumeration value="MV"/>
    <enumeration value="MW"/>
    <enumeration value="MX"/>
    <enumeration value="MY"/>
    <enumeration value="MZ"/>
    <enumeration value="NA"/>
    <enumeration value="NC"/>
    <enumeration value="NE"/>
    <enumeration value="NF"/>
    <enumeration value="NG"/>
    <enumeration value="NI"/>
    <enumeration value="NL"/>
    <enumeration value="NO"/>
    <enumeration value="NP"/>
    <enumeration value="NR"/>
    <enumeration value="NU"/>
    <enumeration value="NZ"/>
    <enumeration value="OM"/>
    <enumeration value="PA"/>
    <enumeration value="PE"/>
    <enumeration value="PF"/>
    <enumeration value="PG"/>
    <enumeration value="PH"/>
    <enumeration value="PK"/>
    <enumeration value="PL"/>
    <enumeration value="PM"/>
    <enumeration value="PN"/>
    <enumeration value="PR"/>
    <enumeration value="PS"/>
    <enumeration value="PT"/>
    <enumeration value="PW"/>
    <enumeration value="PY"/>
    <enumeration value="QA"/>
    <enumeration value="RE"/>
    <enumeration value="RO"/>
    <enumeration value="RS"/>
    <enumeration value="RU"/>
    <enumeration value="RW"/>
    <enumeration value="SA"/>
    <enumeration value="SB"/>
    <enumeration value="SC"/>
    <enumeration value="SD"/>
    <enumeration value="SE"/>
    <enumeration value="SG"/>
    <enumeration value="SH"/>
    <enumeration value="SI"/>
    <enumeration value="SJ"/>
    <enumeration value="SK"/>
    <enumeration value="SL"/>
    <enumeration value="SM"/>
    <enumeration value="SN"/>
    <enumeration value="SO"/>
    <enumeration value="SR"/>
    <enumeration value="SS"/>
    <enumeration value="ST"/>
    <enumeration value="SV"/>
    <enumeration value="SX"/>
    <enumeration value="SY"/>
    <enumeration value="SZ"/>
    <enumeration value="TC"/>
    <enumeration value="TD"/>
    <enumeration value="TF"/>
    <enumeration value="TG"/>
    <enumeration value="TH"/>
    <enumeration value="TJ"/>
    <enumeration value="TK"/>
    <enumeration value="TL"/>
    <enumeration value="TM"/>
    <enumeration value="TN"/>
    <enumeration value="TO"/>
    <enumeration value="TR"/>
    <enumeration value="TT"/>
    <enumeration value="TV"/>
    <enumeration value="TW"/>
    <enumeration value="TZ"/>
    <enumeration value="UA"/>
    <enumeration value="UG"/>
    <enumeration value="UM"/>
    <enumeration value="US"/>
    <enumeration value="UY"/>
    <enumeration value="UZ"/>
    <enumeration value="VA"/>
    <enumeration value="VC"/>
    <enumeration value="VE"/>
    <enumeration value="VG"/>
    <enumeration value="VI"/>
    <enumeration value="VN"/>
    <enumeration value="VU"/>
    <enumeration value="WF"/>
    <enumeration value="WS"/>
    <enumeration value="YE"/>
    <enumeration value="YT"/>
    <enumeration value="ZA"/>
    <enumeration value="ZM"/>
    <enumeration value="ZW"/>
    <enumeration value="1A"/>
    <enumeration value="XI"/>
  </cl>
  <cl id="6">
    <enumeration value="AED"/>
    <enumeration value="AFN"/>
    <enumeration value="ALL"/>
    <enumeration value="AMD"/>
    <enumeration value="ANG"/>
    <enumeration value="AOA"/>
    <enumeration value="ARS"/>
    <enumeration value="AUD"/>
    <enumeration value="AWG"/>
    <enumeration value="AZN"/>
    <enumeration value="BAM"/>
    <enumeration value="BBD"/>
    <enumeration value="BDT"/>
    <enumeration value="BGN"/>
    <enumeration value="BHD"/>
    <enumeration value="BIF"/>
    <enumeration value="BMD"/>
    <enumeration value="BND"/>
    <enumeration value="BOB"/>
    <enumeration value="BOV"/>
    <enumeration value="BRL"/>
    <enumeration value="BSD"/>
    <enumeration value="BTN"/>
    <enumeration value="BWP"/>
    <enumeration value="BYN"/>
    <enumeration value="BZD"/>
    <enumeration value="CAD"/>
    <enumeration value="CDF"/>
    <enumeration value="CHE"/>
    <enumeration value="CHF"/>
    <enumeration value="CHW"/>
    <enumeration value="CLF"/>
    <enumeration value="CLP"/>
    <enumeration value="CNY"/>
    <enumeration value="COP"/>
    <enumeration value="COU"/>
    <enumeration value="CRC"/>
    <enumeration value="CUC"/>
    <enumeration value="CUP"/>
    <enumeration value="CVE"/>
    <enumeration value="CZK"/>
    <enumeration value="DJF"/>
    <enumeration value="DKK"/>
    <enumeration value="DOP"/>
    <enumeration value="DZD"/>
    <enumeration value="EGP"/>
    <enumeration value="ERN"/>
    <enumeration value="ETB"/>
    <enumeration value="EUR"/>
    <enumeration value="FJD"/>
    <enumeration value="FKP"/>
    <enumeration value="GBP"/>
    <enumeration value="GEL"/>
    <enumeration value="GHS"/>
    <enumeration value="GIP"/>
    <enumeration value="GMD"/>
    <enumeration value="GNF"/>
    <enumeration value="GTQ"/>
    <enumeration value="GYD"/>
    <enumeration value="HKD"/>
    <enumeration value="HNL"/>
    <enumeration value="HTG"/>
    <enumeration value="HUF"/>
    <enumeration value="IDR"/>
    <enumeration value="ILS"/>
    <enumeration value="INR"/>
    <enumeration value="IQD"/>
    <enumeration value="IRR"/>
    <enumeration value="ISK"/>
    <enumeration value="JMD"/>
    <enumeration value="JOD"/>
    <enumeration value="JPY"/>
    <enumeration value="KES"/>
    <enumeration value="KGS"/>
    <enumeration value="KHR"/>
    <enumeration value="KMF"/>
    <enumeration value="KPW"/>
    <enumeration value="KRW"/>
    <enumeration value="KWD"/>
    <enumeration value="KYD"/>
    <enumeration value="KZT"/>
    <enumeration value="LAK"/>
    <enumeration value="LBP"/>
    <enumeration value="LKR"/>
    <enumeration value="LRD"/>
    <enumeration value="LSL"/>
    <enumeration value="LYD"/>
    <enumeration value="MAD"/>
    <enumeration value="MDL"/>
    <enumeration value="MGA"/>
    <enumeration value="MKD"/>
    <enumeration value="MMK"/>
    <enumeration value="MNT"/>
    <enumeration value="MOP"/>
    <enumeration value="MRU"/>
    <enumeration value="MUR"/>
    <enumeration value="MVR"/>
    <enumeration value="MWK"/>
    <enumeration value="MXN"/>
    <enumeration value="MXV"/>
    <enumeration value="MYR"/>
    <enumeration value="MZN"/>
    <enumeration value="NAD"/>
    <enumeration value="NGN"/>
    <enumeration value="NIO"/>
    <enumeration value="NOK"/>
    <enumeration value="NPR"/>
    <enumeration value="NZD"/>
    <enumeration value="OMR"/>
    <enumeration value="PAB"/>
    <enumeration value="PEN"/>
    <enumeration value="PGK"/>
    <enumeration value="PHP"/>
    <enumeration value="PKR"/>
    <enumeration value="PLN"/>
    <enumeration value="PYG"/>
    <enumeration value="QAR"/>
    <enumeration value="RON"/>
    <enumeration value="RSD"/>
    <enumeration value="RUB"/>
    <enumeration value="RWF"/>
    <enumeration value="SAR"/>
    <enumeration value="SBD"/>
    <enumeration value="SCR"/>
    <enumeration value="SDG"/>
    <enumeration value="SEK"/>
    <enumeration value="SGD"/>
    <enumeration value="SHP"/>
    <enumeration value="SLE"/>
    <enumeration value="SOS"/>
    <enumeration value="SRD"/>
    <enumeration value="SSP"/>
    <enumeration value="STN"/>
    <enumeration value="SVC"/>
    <enumeration value="SYP"/>
    <enumeration value="SZL"/>
    <enumeration value="THB"/>
    <enumeration value="TJS"/>
    <enumeration value="TMT"/>
    <enumeration value="TND"/>
    <enumeration value="TOP"/>
    <enumeration value="TRY"/>
    <enumeration value="TTD"/>
    <enumeration value="TWD"/>
    <enumeration value="TZS"/>
    <enumeration value="UAH"/>
    <enumeration value="UGX"/>
    <enumeration value="USD"/>
    <enumeration value="USN"/>
    <enumeration value="UYI"/>
    <enumeration value="UYU"/>
    <enumeration value="UYW"/>
    <enumeration value="UZS"/>
    <enumeration value="VED"/>
    <enumeration value="VES"/>
    <enumeration value="VND"/>
    <enumeration value="VUV"/>
    <enumeration value="WST"/>
    <enumeration value="XAF"/>
    <enumeration value="XAG"/>
    <enumeration value="XAU"/>
    <enumeration value="XBA"/>
    <enumeration value="XBB"/>
    <enumeration value="XBC"/>
    <enumeration value="XBD"/>
    <enumeration value="XCD"/>
    <enumeration value="XDR"/>
    <enumeration value="XOF"/>
    <enumeration value="XPD"/>
    <enumeration value="XPF"/>
    <enumeration value="XPT"/>
    <enumeration value="XSU"/>
    <enumeration value="XTS"/>
    <enumeration value="XUA"/>
    <enumeration value="XXX"/>
    <enumeration value="YER"/>
    <enumeration value="ZAR"/>
    <enumeration value="ZMW"/>
    <enumeration value="ZWG"/>
    <enumeration value="ZWL"/>
  </cl>
  <cl id="7">
    <enumeration value="AED"/>
    <enumeration value="AFN"/>
    <enumeration value="ALL"/>
    <enumeration value="AMD"/>
    <enumeration value="ANG"/>
    <enumeration value="AOA"/>
    <enumeration value="ARS"/>
    <enumeration value="AUD"/>
    <enumeration value="AWG"/>
    <enumeration value="AZN"/>
    <enumeration value="BAM"/>
    <enumeration value="BBD"/>
    <enumeration value="BDT"/>
    <enumeration value="BGN"/>
    <enumeration value="BHD"/>
    <enumeration value="BIF"/>
    <enumeration value="BMD"/>
    <enumeration value="BND"/>
    <enumeration value="BOB"/>
    <enumeration value="BOV"/>
    <enumeration value="BRL"/>
    <enumeration value="BSD"/>
    <enumeration value="BTN"/>
    <enumeration value="BWP"/>
    <enumeration value="BYN"/>
    <enumeration value="BZD"/>
    <enumeration value="CAD"/>
    <enumeration value="CDF"/>
    <enumeration value="CHE"/>
    <enumeration value="CHF"/>
    <enumeration value="CHW"/>
    <enumeration value="CLF"/>
    <enumeration value="CLP"/>
    <enumeration value="CNY"/>
    <enumeration value="COP"/>
    <enumeration value="COU"/>
    <enumeration value="CRC"/>
    <enumeration value="CUC"/>
    <enumeration value="CUP"/>
    <enumeration value="CVE"/>
    <enumeration value="CZK"/>
    <enumeration value="DJF"/>
    <enumeration value="DKK"/>
    <enumeration value="DOP"/>
    <enumeration value="DZD"/>
    <enumeration value="EGP"/>
    <enumeration value="ERN"/>
    <enumeration value="ETB"/>
    <enumeration value="EUR"/>
    <enumeration value="FJD"/>
    <enumeration value="FKP"/>
    <enumeration value="GBP"/>
    <enumeration value="GEL"/>
    <enumeration value="GHS"/>
    <enumeration value="GIP"/>
    <enumeration value="GMD"/>
    <enumeration value="GNF"/>
    <enumeration value="GTQ"/>
    <enumeration value="GYD"/>
    <enumeration value="HKD"/>
    <enumeration value="HNL"/>
    <enumeration value="HTG"/>
    <enumeration value="HUF"/>
    <enumeration value="IDR"/>
    <enumeration value="ILS"/>
    <enumeration value="INR"/>
    <enumeration value="IQD"/>
    <enumeration value="IRR"/>
    <enumeration value="ISK"/>
    <enumeration value="JMD"/>
    <enumeration value="JOD"/>
    <enumeration value="JPY"/>
    <enumeration value="KES"/>
    <enumeration value="KGS"/>
    <enumeration value="KHR"/>
    <enumeration value="KMF"/>
    <enumeration value="KPW"/>
    <enumeration value="KRW"/>
    <enumeration value="KWD"/>
    <enumeration value="KYD"/>
    <enumeration value="KZT"/>
    <enumeration value="LAK"/>
    <enumeration value="LBP"/>
    <enumeration value="LKR"/>
    <enumeration value="LRD"/>
    <enumeration value="LSL"/>
    <enumeration value="LYD"/>
    <enumeration value="MAD"/>
    <enumeration value="MDL"/>
    <enumeration value="MGA"/>
    <enumeration value="MKD"/>
    <enumeration value="MMK"/>
    <enumeration value="MNT"/>
    <enumeration value="MOP"/>
    <enumeration value="MRU"/>
    <enumeration value="MUR"/>
    <enumeration value="MVR"/>
    <enumeration value="MWK"/>
    <enumeration value="MXN"/>
    <enumeration value="MXV"/>
    <enumeration value="MYR"/>
    <enumeration value="MZN"/>
    <enumeration value="NAD"/>
    <enumeration value="NGN"/>
    <enumeration value="NIO"/>
    <enumeration value="NOK"/>
    <enumeration value="NPR"/>
    <enumeration value="NZD"/>
    <enumeration value="OMR"/>
    <enumeration value="PAB"/>
    <enumeration value="PEN"/>
    <enumeration value="PGK"/>
    <enumeration value="PHP"/>
    <enumeration value="PKR"/>
    <enumeration value="PLN"/>
    <enumeration value="PYG"/>
    <enumeration value="QAR"/>
    <enumeration value="RON"/>
    <enumeration value="RSD"/>
    <enumeration value="RUB"/>
    <enumeration value="RWF"/>
    <enumeration value="SAR"/>
    <enumeration value="SBD"/>
    <enumeration value="SCR"/>
    <enumeration value="SDG"/>
    <enumeration value="SEK"/>
    <enumeration value="SGD"/>
    <enumeration value="SHP"/>
    <enumeration value="SLE"/>
    <enumeration value="SOS"/>
    <enumeration value="SRD"/>
    <enumeration value="SSP"/>
    <enumeration value="STN"/>
    <enumeration value="SVC"/>
    <enumeration value="SYP"/>
    <enumeration value="SZL"/>
    <enumeration value="THB"/>
    <enumeration value="TJS"/>
    <enumeration value="TMT"/>
    <enumeration value="TND"/>
    <enumeration value="TOP"/>
    <enumeration value="TRY"/>
    <enumeration value="TTD"/>
    <enumeration value="TWD"/>
    <enumeration value="TZS"/>
    <enumeration value="UAH"/>
    <enumeration value="UGX"/>
    <enumeration value="USD"/>
    <enumeration value="USN"/>
    <enumeration value="UYI"/>
    <enumeration value="UYU"/>
    <enumeration value="UYW"/>
    <enumeration value="UZS"/>
    <enumeration value="VED"/>
    <enumeration value="VES"/>
    <enumeration value="VND"/>
    <enumeration value="VUV"/>
    <enumeration value="WST"/>
    <enumeration value="XAF"/>
    <enumeration value="XAG"/>
    <enumeration value="XAU"/>
    <enumeration value="XBA"/>
    <enumeration value="XBB"/>
    <enumeration value="XBC"/>
    <enumeration value="XBD"/>
    <enumeration value="XCD"/>
    <enumeration value="XDR"/>
    <enumeration value="XOF"/>
    <enumeration value="XPD"/>
    <enumeration value="XPF"/>
    <enumeration value="XPT"/>
    <enumeration value="XSU"/>
    <enumeration value="XTS"/>
    <enumeration value="XUA"/>
    <enumeration value="XXX"/>
    <enumeration value="YER"/>
    <enumeration value="ZAR"/>
    <enumeration value="ZMW"/>
    <enumeration value="ZWG"/>
    <enumeration value="ZWL"/>
  </cl>

  </xsl:variable>
</xsl:stylesheet>