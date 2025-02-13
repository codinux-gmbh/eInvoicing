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
    <svrl:schematron-output schemaVersion="iso" title="Schema for Factur-X; 1.07.2; Accounting, BASIC without Lines">
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
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M46" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M47" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M48" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M49" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M50" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M51" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M52" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M53" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M54" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M55" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M56" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M57" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M58" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M59" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M60" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M61" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M62" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M63" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M64" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M65" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M66" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M67" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M68" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M69" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M70" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M71" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M72" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M73" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M74" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M75" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M76" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M77" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M78" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M79" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M80" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M81" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M82" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M83" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M84" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M85" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M86" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M87" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M88" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M89" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M90" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M91" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M92" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M93" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M94" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M95" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M96" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M97" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M98" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M99" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M100" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M101" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M102" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M103" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M104" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M105" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M106" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M107" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M108" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M109" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M110" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M111" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M112" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M113" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M114" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M115" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M116" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M117" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M118" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M119" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M120" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M121" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M122" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M123" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M124" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M125" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M126" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M127" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M128" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M129" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M130" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M131" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M132" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M133" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M134" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M135" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M136" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M137" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M138" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M139" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M140" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M141" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M142" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M143" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M144" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M145" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M146" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M147" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M148" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M149" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M150" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M151" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M152" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M153" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M154" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M155" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M156" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M157" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M158" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M159" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M160" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M161" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M162" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M163" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M164" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M165" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M166" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M167" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M168" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M169" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M170" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M171" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M172" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M173" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M174" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M175" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M176" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M177" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M178" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M179" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M180" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M181" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M182" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M183" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M184" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M185" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M186" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M187" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M188" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M189" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M190" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M191" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M192" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M193" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M194" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M195" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M196" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M197" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M198" select="/" />
      <svrl:active-pattern>
        <xsl:attribute name="document">
          <xsl:value-of select="document-uri(/)" />
        </xsl:attribute>
        <xsl:apply-templates />
      </svrl:active-pattern>
      <xsl:apply-templates mode="M199" select="/" />
    </svrl:schematron-output>
  </xsl:template>

<svrl:text>Schema for Factur-X; 1.07.2; Accounting, BASIC without Lines</svrl:text>

	
<xsl:template match="//ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax" mode="M5" priority="1000">
    <svrl:fired-rule context="//ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax" />

		
<xsl:choose>
      <xsl:when test="(ram:BasisAmount)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:BasisAmount)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-45]-Each VAT breakdown (BG-23) shall have a VAT category taxable amount (BT-116).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:CalculatedAmount)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:CalculatedAmount)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-46]-Each VAT breakdown (BG-23) shall have a VAT category tax amount (BT-117).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:CategoryCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:CategoryCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-47]-Each VAT breakdown (BG-23) shall be defined through a VAT category code (BT-118).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:RateApplicablePercent) or (ram:CategoryCode = 'O')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:RateApplicablePercent) or (ram:CategoryCode = 'O')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-48]-Each VAT breakdown (BG-23) shall have a VAT category rate (BT-119), except if the Invoice is not subject to VAT.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="((ram:TaxPointDate) and not (ram:DueDateTypeCode)) or (not (ram:TaxPointDate) and (ram:DueDateTypeCode)) or (not (ram:TaxPointDate) and not (ram:DueDateTypeCode))" />
      <xsl:otherwise>
        <svrl:failed-assert test="((ram:TaxPointDate) and not (ram:DueDateTypeCode)) or (not (ram:TaxPointDate) and (ram:DueDateTypeCode)) or (not (ram:TaxPointDate) and not (ram:DueDateTypeCode))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-03]-Value added tax point date (BT-7) and Value added tax point date code (BT-8) are mutually exclusive.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(round(.[normalize-space(upper-case(ram:TypeCode)) = 'VAT']/xs:decimal(ram:RateApplicablePercent)) = 0 and (round(xs:decimal(ram:CalculatedAmount)) = 0)) or (round(.[normalize-space(upper-case(ram:TypeCode)) = 'VAT']/xs:decimal(ram:RateApplicablePercent)) != 0 and ((abs(xs:decimal(ram:CalculatedAmount)) - 1 &lt;= round(abs(xs:decimal(ram:BasisAmount)) * (.[normalize-space(upper-case(ram:TypeCode)) = 'VAT']/xs:decimal(ram:RateApplicablePercent) div 100) * 10 * 10) div 100 ) and (abs(xs:decimal(ram:CalculatedAmount)) + 1 >= round(abs(xs:decimal(ram:BasisAmount)) * (.[normalize-space(upper-case(ram:TypeCode)) = 'VAT']/xs:decimal(ram:RateApplicablePercent) div 100) * 10 * 10) div 100 ))) or (not(exists(.[normalize-space(upper-case(ram:TypeCode))='VAT']/xs:decimal(ram:RateApplicablePercent))) and (round(xs:decimal(ram:CalculatedAmount)) = 0))" />
      <xsl:otherwise>
        <svrl:failed-assert test="(round(.[normalize-space(upper-case(ram:TypeCode)) = 'VAT']/xs:decimal(ram:RateApplicablePercent)) = 0 and (round(xs:decimal(ram:CalculatedAmount)) = 0)) or (round(.[normalize-space(upper-case(ram:TypeCode)) = 'VAT']/xs:decimal(ram:RateApplicablePercent)) != 0 and ((abs(xs:decimal(ram:CalculatedAmount)) - 1 &lt;= round(abs(xs:decimal(ram:BasisAmount)) * (.[normalize-space(upper-case(ram:TypeCode)) = 'VAT']/xs:decimal(ram:RateApplicablePercent) div 100) * 10 * 10) div 100 ) and (abs(xs:decimal(ram:CalculatedAmount)) + 1 >= round(abs(xs:decimal(ram:BasisAmount)) * (.[normalize-space(upper-case(ram:TypeCode)) = 'VAT']/xs:decimal(ram:RateApplicablePercent) div 100) * 10 * 10) div 100 ))) or (not(exists(.[normalize-space(upper-case(ram:TypeCode))='VAT']/xs:decimal(ram:RateApplicablePercent))) and (round(xs:decimal(ram:CalculatedAmount)) = 0))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-17]-VAT category tax amount (BT-117) = VAT category taxable amount (BT-116) x (VAT category rate (BT-119) / 100), rounded to two decimals.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:BasisAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:BasisAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-19]-The allowed maximum number of decimals for the VAT category taxable amount (BT-116) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:CalculatedAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:CalculatedAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-20]-The allowed maximum number of decimals for the VAT category tax amount (BT-117) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M5" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M5" priority="-1" />
  <xsl:template match="@*|node()" mode="M5" priority="-2">
    <xsl:apply-templates mode="M5" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[. = 'Z']" mode="M6" priority="1000">
    <svrl:fired-rule context="//ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[. = 'Z']" />

		
<xsl:choose>
      <xsl:when test="../ram:CalculatedAmount = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="../ram:CalculatedAmount = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-Z-09]-The VAT category tax amount (BT-117) in a VAT breakdown (BG-23) where VAT category code (BT-118) is "Zero rated" shall equal 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(../ram:ExemptionReason) and not (../ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(../ram:ExemptionReason) and not (../ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-Z-10]-A VAT Breakdown (BG-23) with VAT Category code (BT-118) "Zero rated" shall not have a VAT exemption reason code (BT-121) or VAT exemption reason text (BT-120).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M6" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M6" priority="-1" />
  <xsl:template match="@*|node()" mode="M6" priority="-2">
    <xsl:apply-templates mode="M6" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[.='S']" mode="M7" priority="1000">
    <svrl:fired-rule context="//ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[.='S']" />

		
<xsl:choose>
      <xsl:when test="(abs(xs:decimal(../ram:CalculatedAmount)) - 1 &lt; round(abs(xs:decimal(../ram:BasisAmount)) * ../ram:RateApplicablePercent) div 100 ) and (abs(xs:decimal(../ram:CalculatedAmount)) + 1 > round(abs(xs:decimal(../ram:BasisAmount)) * ../ram:RateApplicablePercent) div 100 )" />
      <xsl:otherwise>
        <svrl:failed-assert test="(abs(xs:decimal(../ram:CalculatedAmount)) - 1 &lt; round(abs(xs:decimal(../ram:BasisAmount)) * ../ram:RateApplicablePercent) div 100 ) and (abs(xs:decimal(../ram:CalculatedAmount)) + 1 > round(abs(xs:decimal(../ram:BasisAmount)) * ../ram:RateApplicablePercent) div 100 )">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-S-09]-The VAT category tax amount (BT-117) in a VAT breakdown (BG-23) where VAT category code (BT-118) is "Standard rated" shall equal the VAT category taxable amount (BT-116) multiplied by the VAT category rate (BT-119).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(../ram:ExemptionReason) and not (../ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(../ram:ExemptionReason) and not (../ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-S-10]-A VAT Breakdown (BG-23) with VAT Category code (BT-118) "Standard rate" shall not have a VAT exemption reason code (BT-121) or VAT exemption reason text (BT-120).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M7" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M7" priority="-1" />
  <xsl:template match="@*|node()" mode="M7" priority="-2">
    <xsl:apply-templates mode="M7" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod" mode="M8" priority="1000">
    <svrl:fired-rule context="//ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod" />

		
<xsl:choose>
      <xsl:when test="(ram:EndDateTime/udt:DateTimeString[@format = '102']) >= (ram:StartDateTime/udt:DateTimeString[@format = '102']) or not (ram:EndDateTime) or not (ram:StartDateTime)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:EndDateTime/udt:DateTimeString[@format = '102']) >= (ram:StartDateTime/udt:DateTimeString[@format = '102']) or not (ram:EndDateTime) or not (ram:StartDateTime)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-29]-If both Invoicing period start date (BT-73) and Invoicing period end date (BT-74) are given then the Invoicing period end date (BT-74) shall be later or equal to the Invoicing period start date (BT-73).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:StartDateTime) or (ram:EndDateTime)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:StartDateTime) or (ram:EndDateTime)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-19]-If Invoicing period (BG-14) is used, the Invoicing period start date (BT-73) or the Invoicing period end date (BT-74) shall be filled, or both.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M8" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M8" priority="-1" />
  <xsl:template match="@*|node()" mode="M8" priority="-2">
    <xsl:apply-templates mode="M8" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge/ram:ChargeIndicator[udt:Indicator='false']" mode="M9" priority="1000">
    <svrl:fired-rule context="//ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge/ram:ChargeIndicator[udt:Indicator='false']" />

		
<xsl:choose>
      <xsl:when test="(../ram:ActualAmount)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:ActualAmount)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-31]-Each Document level allowance (BG-20) shall have a Document level allowance amount (BT-92).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:CategoryTradeTax[upper-case(ram:TypeCode) = 'VAT']/ram:CategoryCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:CategoryTradeTax[upper-case(ram:TypeCode) = 'VAT']/ram:CategoryCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-32]-Each Document level allowance (BG-20) shall have a Document level allowance VAT category code (BT-95).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:Reason) or (../ram:ReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:Reason) or (../ram:ReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-33]-Each Document level allowance (BG-20) shall have a Document level allowance reason (BT-97) or a Document level allowance reason code (BT-98).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="true()" />
      <xsl:otherwise>
        <svrl:failed-assert test="true()">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-05]-Document level allowance reason code (BT-98) and Document level allowance reason (BT-97) shall indicate the same type of allowance.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:Reason) or (../ram:ReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:Reason) or (../ram:ReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-21]-Each Document level allowance (BG-20) shall contain a Document level allowance reason (BT-97) or a Document level allowance reason code (BT-98), or both.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(../ram:ActualAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(../ram:ActualAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-01]-The allowed maximum number of decimals for the Document level allowance amount (BT-92) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(../ram:BasisAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(../ram:BasisAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-02]-The allowed maximum number of decimals for the Document level allowance base amount (BT-93) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M9" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M9" priority="-1" />
  <xsl:template match="@*|node()" mode="M9" priority="-2">
    <xsl:apply-templates mode="M9" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge/ram:ChargeIndicator[udt:Indicator='true']" mode="M10" priority="1000">
    <svrl:fired-rule context="//ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge/ram:ChargeIndicator[udt:Indicator='true']" />

		
<xsl:choose>
      <xsl:when test="(../ram:ActualAmount)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:ActualAmount)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-36]-Each Document level charge (BG-21) shall have a Document level charge amount (BT-99).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:CategoryTradeTax[upper-case(ram:TypeCode) = 'VAT']/ram:CategoryCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:CategoryTradeTax[upper-case(ram:TypeCode) = 'VAT']/ram:CategoryCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-37]-Each Document level charge (BG-21) shall have a Document level charge VAT category code (BT-102).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:Reason) or (../ram:ReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:Reason) or (../ram:ReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-38]-Each Document level charge (BG-21) shall have a Document level charge reason (BT-104) or a Document level charge reason code (BT-105).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="true()" />
      <xsl:otherwise>
        <svrl:failed-assert test="true()">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-06]-Document level charge reason code (BT-105) and Document level charge reason (BT-104) shall indicate the same type of charge.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:Reason) or (../ram:ReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:Reason) or (../ram:ReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-22]-Each Document level charge (BG-21) shall contain a Document level charge reason (BT-104) or a Document level charge reason code (BT-105), or both.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(../ram:ActualAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(../ram:ActualAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-05]-The allowed maximum number of decimals for the Document level charge amount (BT-99) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(../ram:BasisAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(../ram:BasisAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-06]-The allowed maximum number of decimals for the Document level charge base amount (BT-100) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M10" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M10" priority="-1" />
  <xsl:template match="@*|node()" mode="M10" priority="-2">
    <xsl:apply-templates mode="M10" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:PayeeTradeParty" mode="M11" priority="1000">
    <svrl:fired-rule context="//ram:PayeeTradeParty" />

		
<xsl:choose>
      <xsl:when test="(ram:Name) and (not(ram:Name = ../ram:SellerTradeParty/ram:Name) and not(ram:ID = ../ram:SellerTradeParty/ram:ID) and not(ram:SpecifiedLegalOrganization/ram:ID = ../ram:SellerTradeParty/ram:SpecifiedLegalOrganization/ram:ID))" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:Name) and (not(ram:Name = ../ram:SellerTradeParty/ram:Name) and not(ram:ID = ../ram:SellerTradeParty/ram:ID) and not(ram:SpecifiedLegalOrganization/ram:ID = ../ram:SellerTradeParty/ram:SpecifiedLegalOrganization/ram:ID))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-17]-The Payee name (BT-59) shall be provided in the Invoice, if the Payee (BG-10) is different from the Seller (BG-4).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M11" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M11" priority="-1" />
  <xsl:template match="@*|node()" mode="M11" priority="-2">
    <xsl:apply-templates mode="M11" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SellerTaxRepresentativeTradeParty" mode="M12" priority="1000">
    <svrl:fired-rule context="//ram:SellerTaxRepresentativeTradeParty" />

		
<xsl:choose>
      <xsl:when test="(ram:Name)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:Name)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-18]-The Seller tax representative name (BT-62) shall be provided in the Invoice, if the Seller (BG-4) has a Seller tax representative party (BG-11).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:PostalTradeAddress)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:PostalTradeAddress)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-19]-The Seller tax representative postal address (BG-12) shall be provided in the Invoice, if the Seller (BG-4) has a Seller tax representative party (BG-11).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:PostalTradeAddress/ram:CountryID)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:PostalTradeAddress/ram:CountryID)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-20]-The Seller tax representative postal address (BG-12) shall contain a Tax representative country code (BT-69), if the Seller (BG-4) has a Seller tax representative party (BG-11).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:SpecifiedTaxRegistration/ram:ID[@schemeID='VA']!='')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:SpecifiedTaxRegistration/ram:ID[@schemeID='VA']!='')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-56]-Each Seller tax representative party (BG-11) shall have a Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M12" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M12" priority="-1" />
  <xsl:template match="@*|node()" mode="M12" priority="-2">
    <xsl:apply-templates mode="M12" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SellerTradeParty" mode="M13" priority="1000">
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
    <xsl:apply-templates mode="M13" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M13" priority="-1" />
  <xsl:template match="@*|node()" mode="M13" priority="-2">
    <xsl:apply-templates mode="M13" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTaxRegistration/ram:ID[@schemeID='VA']" mode="M14" priority="1000">
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
    <xsl:apply-templates mode="M14" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M14" priority="-1" />
  <xsl:template match="@*|node()" mode="M14" priority="-2">
    <xsl:apply-templates mode="M14" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge" mode="M15" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge" />

		
<xsl:choose>
      <xsl:when test="(ram:ChargeIndicator)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:ChargeIndicator)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-66]-Each Specified Trade Allowance Charge (BG-20)(BG-21) shall contain a Charge Indicator.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M15" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M15" priority="-1" />
  <xsl:template match="@*|node()" mode="M15" priority="-2">
    <xsl:apply-templates mode="M15" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'AE']" mode="M16" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'AE']" />

		
<xsl:choose>
      <xsl:when test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and (//ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:ID)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and (//ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:ID)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AE-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Reverse charge" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63) and the Buyer VAT identifier (BT-48) and/or the Buyer legal registration identifier (BT-47).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AE-06]-In a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Reverse charge" the Document level allowance VAT rate (BT-96) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M16" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M16" priority="-1" />
  <xsl:template match="@*|node()" mode="M16" priority="-2">
    <xsl:apply-templates mode="M16" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'E']" mode="M17" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'E']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-E-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Exempt from VAT" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-E-06]-In a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Exempt from VAT", the Document level allowance VAT rate (BT-96) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M17" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M17" priority="-1" />
  <xsl:template match="@*|node()" mode="M17" priority="-2">
    <xsl:apply-templates mode="M17" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'G']" mode="M18" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'G']" />

		
<xsl:choose>
      <xsl:when test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'])" />
      <xsl:otherwise>
        <svrl:failed-assert test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'])">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-G-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Export outside the EU" shall contain the Seller VAT Identifier (BT-31) or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-G-06]-In a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Export outside the EU" the Document level allowance VAT rate (BT-96) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M18" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M18" priority="-1" />
  <xsl:template match="@*|node()" mode="M18" priority="-2">
    <xsl:apply-templates mode="M18" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'K']" mode="M19" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'K']" />

		
<xsl:choose>
      <xsl:when test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and //ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and //ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-IC-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Intra-community supply" shall contain the Seller VAT Identifier (BT-31) or the Seller tax representative VAT identifier (BT-63) and the Buyer VAT identifier (BT-48).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-IC-06]-In a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Intra-community supply" the Document level allowance VAT rate (BT-96) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M19" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M19" priority="-1" />
  <xsl:template match="@*|node()" mode="M19" priority="-2">
    <xsl:apply-templates mode="M19" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'L']" mode="M20" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'L']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AF-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "IGIC" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent > 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent > 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AF-06]-In a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "IGIC" the Document level allowance VAT rate (BT-96) shall be 0 (zero) or greater than zero.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M20" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M20" priority="-1" />
  <xsl:template match="@*|node()" mode="M20" priority="-2">
    <xsl:apply-templates mode="M20" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'M']" mode="M21" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'M']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AG-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "IPSI" shall contain the Seller VAT Identifier (BT-31), the Seller Tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent >= 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent >= 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AG-06]-In a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "IPSI" the Document level allowance VAT rate (BT-96) shall be 0 (zero) or greater than zero.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M21" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M21" priority="-1" />
  <xsl:template match="@*|node()" mode="M21" priority="-2">
    <xsl:apply-templates mode="M21" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'O']" mode="M22" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'O']" />

		
<xsl:choose>
      <xsl:when test="not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and not (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and not (/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'])" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and not (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and not (/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'])">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Not subject to VAT" shall not contain the Seller VAT identifier (BT-31), the Seller tax representative VAT identifier (BT-63) or the Buyer VAT identifier (BT-48).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(ram:RateApplicablePercent)" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(ram:RateApplicablePercent)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-06]-A Document level allowance (BG-20) where VAT category code (BT-95) is "Not subject to VAT" shall not contain a Document level allowance VAT rate (BT-96).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M22" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M22" priority="-1" />
  <xsl:template match="@*|node()" mode="M22" priority="-2">
    <xsl:apply-templates mode="M22" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'S']" mode="M23" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'S']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-S-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Standard rated" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent > 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent > 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-S-06]-In a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Standard rated" the Document level allowance VAT rate (BT-96) shall be greater than zero.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M23" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M23" priority="-1" />
  <xsl:template match="@*|node()" mode="M23" priority="-2">
    <xsl:apply-templates mode="M23" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'Z']" mode="M24" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:CategoryTradeTax[ram:CategoryCode = 'Z']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-Z-03]-An Invoice that contains a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Zero rated" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-Z-06]-In a Document level allowance (BG-20) where the Document level allowance VAT category code (BT-95) is "Zero rated" the Document level allowance VAT rate (BT-96) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M24" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M24" priority="-1" />
  <xsl:template match="@*|node()" mode="M24" priority="-2">
    <xsl:apply-templates mode="M24" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'AE']" mode="M25" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'AE']" />

		
<xsl:choose>
      <xsl:when test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and (//ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:ID)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and (//ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:ID)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AE-04]-An Invoice that contains a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Reverse charge" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63) and the Buyer VAT identifier (BT-48) and/or the Buyer legal registration identifier (BT-47).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AE-07]-In a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Reverse charge" the Document level charge VAT rate (BT-103) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M25" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M25" priority="-1" />
  <xsl:template match="@*|node()" mode="M25" priority="-2">
    <xsl:apply-templates mode="M25" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'E']" mode="M26" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'E']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-E-04]-An Invoice that contains a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Exempt from VAT" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-E-07]-In a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Exempt from VAT", the Document level charge VAT rate (BT-103) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M26" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M26" priority="-1" />
  <xsl:template match="@*|node()" mode="M26" priority="-2">
    <xsl:apply-templates mode="M26" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'G']" mode="M27" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'G']" />

		
<xsl:choose>
      <xsl:when test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'])" />
      <xsl:otherwise>
        <svrl:failed-assert test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'])">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-G-04]-An Invoice that contains a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Export outside the EU" shall contain the Seller VAT Identifier (BT-31) or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-G-07]-In a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Export outside the EU" the Document level charge VAT rate (BT-103) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M27" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M27" priority="-1" />
  <xsl:template match="@*|node()" mode="M27" priority="-2">
    <xsl:apply-templates mode="M27" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'K']" mode="M28" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'K']" />

		
<xsl:choose>
      <xsl:when test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and //ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="(//ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'] or //ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and //ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-IC-04]-An Invoice that contains a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Intra-community supply" shall contain the Seller VAT Identifier (BT-31) or the Seller tax representative VAT identifier (BT-63) and the Buyer VAT identifier (BT-48).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-IC-07]-In a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Intra-community supply" the Document level charge VAT rate (BT-103) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M28" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M28" priority="-1" />
  <xsl:template match="@*|node()" mode="M28" priority="-2">
    <xsl:apply-templates mode="M28" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'L']" mode="M29" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'L']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AF-04]-An Invoice that contains a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "IGIC" shall contain the Seller VAT Identifier (BT-31), the Seller Tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent > 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent > 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AF-07]-In a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "IGIC" the Document level charge VAT rate (BT-103) shall be 0 (zero) or greater than zero.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M29" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M29" priority="-1" />
  <xsl:template match="@*|node()" mode="M29" priority="-2">
    <xsl:apply-templates mode="M29" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'M']" mode="M30" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'M']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AG-04]-An Invoice that contains a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "IPSI" shall contain the Seller VAT Identifier (BT-31), the Seller Tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent >= 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent >= 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AG-07]-In a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "IPSI" the Document level charge VAT rate (BT-103) shall be 0 (zero) or greater than zero.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M30" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M30" priority="-1" />
  <xsl:template match="@*|node()" mode="M30" priority="-2">
    <xsl:apply-templates mode="M30" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'O']" mode="M31" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'O']" />

		
<xsl:choose>
      <xsl:when test="not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and not (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and not (/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'])" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and not (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']) and not (/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA'])">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-04]-An Invoice that contains a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Not subject to VAT" shall not contain the Seller VAT identifier (BT-31), the Seller tax representative VAT identifier (BT-63) or the Buyer VAT identifier (BT-48).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(ram:RateApplicablePercent)" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(ram:RateApplicablePercent)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-07]-A Document level charge (BG-21) where the VAT category code (BT-102) is "Not subject to VAT" shall not contain a Document level charge VAT rate (BT-103).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M31" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M31" priority="-1" />
  <xsl:template match="@*|node()" mode="M31" priority="-2">
    <xsl:apply-templates mode="M31" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'S']" mode="M32" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'S']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-S-04]-An Invoice that contains a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Standard rated" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent > 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent > 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-S-07]-In a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Standard rated" the Document level charge VAT rate (BT-103) shall be greater than zero.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M32" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M32" priority="-1" />
  <xsl:template match="@*|node()" mode="M32" priority="-2">
    <xsl:apply-templates mode="M32" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'Z']" mode="M33" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:CategoryTradeTax[ram:CategoryCode = 'Z']" />

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = ('VA', 'FC')] or /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID = 'VA']">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-Z-04]-An Invoice that contains a Document level charge where the Document level charge VAT category code (BT-102) is "Zero rated" shall contain the Seller VAT Identifier (BT-31), the Seller tax registration identifier (BT-32) and/or the Seller tax representative VAT identifier (BT-63).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="ram:RateApplicablePercent = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:RateApplicablePercent = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-Z-07]-In a Document level charge (BG-21) where the Document level charge VAT category code (BT-102) is "Zero rated" the Document level charge VAT rate (BT-103) shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M33" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M33" priority="-1" />
  <xsl:template match="@*|node()" mode="M33" priority="-2">
    <xsl:apply-templates mode="M33" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeSettlementHeaderMonetarySummation" mode="M34" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeSettlementHeaderMonetarySummation" />

		
<xsl:choose>
      <xsl:when test="(ram:LineTotalAmount)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:LineTotalAmount)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-12]-An Invoice shall have the Sum of Invoice line net amount (BT-106).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
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
      <xsl:when test="(not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false'])and not (ram:AllowanceTotalAmount)) or ram:AllowanceTotalAmount = (round(sum(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:ActualAmount)* 10 * 10 ) div 100)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false'])and not (ram:AllowanceTotalAmount)) or ram:AllowanceTotalAmount = (round(sum(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='false']/ram:ActualAmount)* 10 * 10 ) div 100)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-11]-Sum of allowances on document level (BT-107) = Σ Document level allowance amount (BT-92).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true'])and not (ram:ChargeTotalAmount)) or (round (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:ChargeTotalAmount * 10 * 10) div 100)= 
round(((round(sum(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:ActualAmount)* 10 * 10 ) div 100) + (round(sum(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedLogisticsServiceCharge/ram:AppliedAmount)* 10 * 10 ) div 100))*10*10) div 100" />
      <xsl:otherwise>
        <svrl:failed-assert test="(not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true'])and not (ram:ChargeTotalAmount)) or (round (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:ChargeTotalAmount * 10 * 10) div 100)= round(((round(sum(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator='true']/ram:ActualAmount)* 10 * 10 ) div 100) + (round(sum(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedLogisticsServiceCharge/ram:AppliedAmount)* 10 * 10 ) div 100))*10*10) div 100">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-12]-Sum of charges on document level (BT-108) = Σ Document level charge amount (BT-99).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(xs:decimal(ram:TaxBasisTotalAmount) = round((xs:decimal(ram:LineTotalAmount) - xs:decimal(ram:AllowanceTotalAmount) + xs:decimal(ram:ChargeTotalAmount)) *10 * 10) div 100) or 
    ((xs:decimal(ram:TaxBasisTotalAmount) = round((xs:decimal(ram:LineTotalAmount) - xs:decimal(ram:AllowanceTotalAmount)) *10 * 10) div 100)  and not (ram:ChargeTotalAmount)) or 
    ((xs:decimal(ram:TaxBasisTotalAmount) = round((xs:decimal(ram:LineTotalAmount) + xs:decimal(ram:ChargeTotalAmount)) *10 * 10) div 100)  and not (ram:AllowanceTotalAmount)) or 
    ((xs:decimal(ram:TaxBasisTotalAmount) = round((xs:decimal(ram:LineTotalAmount))  *10 * 10) div 100) and not (ram:ChargeTotalAmount) and not (ram:AllowanceTotalAmount))" />
      <xsl:otherwise>
        <svrl:failed-assert test="(xs:decimal(ram:TaxBasisTotalAmount) = round((xs:decimal(ram:LineTotalAmount) - xs:decimal(ram:AllowanceTotalAmount) + xs:decimal(ram:ChargeTotalAmount)) *10 * 10) div 100) or ((xs:decimal(ram:TaxBasisTotalAmount) = round((xs:decimal(ram:LineTotalAmount) - xs:decimal(ram:AllowanceTotalAmount)) *10 * 10) div 100) and not (ram:ChargeTotalAmount)) or ((xs:decimal(ram:TaxBasisTotalAmount) = round((xs:decimal(ram:LineTotalAmount) + xs:decimal(ram:ChargeTotalAmount)) *10 * 10) div 100) and not (ram:AllowanceTotalAmount)) or ((xs:decimal(ram:TaxBasisTotalAmount) = round((xs:decimal(ram:LineTotalAmount)) *10 * 10) div 100) and not (ram:ChargeTotalAmount) and not (ram:AllowanceTotalAmount))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-13]-Invoice total amount without VAT (BT-109) = Σ Invoice line net amount (BT-131) - Sum of allowances on document level (BT-107) + Sum of charges on document level (BT-108).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="every $Currency 
                                in rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode
                                satisfies (  
                                  count ( rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=$Currency] ) eq 1 and  
                                  (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:GrandTotalAmount) = round( 
                                    (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:TaxBasisTotalAmount) + 
                                    (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:TaxTotalAmount[@currencyID=$Currency]))) * 10 * 10) div 100)) or
                                (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:GrandTotalAmount) = (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:TaxBasisTotalAmount)))" />
      <xsl:otherwise>
        <svrl:failed-assert test="every $Currency in rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode satisfies ( count ( rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=$Currency] ) eq 1 and (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:GrandTotalAmount) = round( (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:TaxBasisTotalAmount) + (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:TaxTotalAmount[@currencyID=$Currency]))) * 10 * 10) div 100)) or (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:GrandTotalAmount) = (//ram:SpecifiedTradeSettlementHeaderMonetarySummation/xs:decimal(ram:TaxBasisTotalAmount)))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-15]-Invoice total amount with VAT (BT-112) = Invoice total amount without VAT (BT-109) + Invoice total VAT amount (BT-110).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(xs:decimal(ram:DuePayableAmount) = xs:decimal(ram:GrandTotalAmount) - xs:decimal(ram:TotalPrepaidAmount) + xs:decimal(ram:RoundingAmount)) or 
    ((xs:decimal(ram:DuePayableAmount) = xs:decimal(ram:GrandTotalAmount) + xs:decimal(ram:RoundingAmount)) and not (xs:decimal(ram:TotalPrepaidAmount))) or 
    ((xs:decimal(ram:DuePayableAmount) = xs:decimal(ram:GrandTotalAmount) - xs:decimal(ram:TotalPrepaidAmount)) and not (xs:decimal(ram:RoundingAmount))) or 
    ((xs:decimal(ram:DuePayableAmount) = xs:decimal(ram:GrandTotalAmount)) and not (xs:decimal(ram:TotalPrepaidAmount)) and not (xs:decimal(ram:RoundingAmount)))" />
      <xsl:otherwise>
        <svrl:failed-assert test="(xs:decimal(ram:DuePayableAmount) = xs:decimal(ram:GrandTotalAmount) - xs:decimal(ram:TotalPrepaidAmount) + xs:decimal(ram:RoundingAmount)) or ((xs:decimal(ram:DuePayableAmount) = xs:decimal(ram:GrandTotalAmount) + xs:decimal(ram:RoundingAmount)) and not (xs:decimal(ram:TotalPrepaidAmount))) or ((xs:decimal(ram:DuePayableAmount) = xs:decimal(ram:GrandTotalAmount) - xs:decimal(ram:TotalPrepaidAmount)) and not (xs:decimal(ram:RoundingAmount))) or ((xs:decimal(ram:DuePayableAmount) = xs:decimal(ram:GrandTotalAmount)) and not (xs:decimal(ram:TotalPrepaidAmount)) and not (xs:decimal(ram:RoundingAmount)))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-16]-Amount due for payment (BT-115) = Invoice total amount with VAT (BT-112) -Paid amount (BT-113) +Rounding amount (BT-114).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:LineTotalAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:LineTotalAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-09]-The allowed maximum number of decimals for the Sum of Invoice line net amount (BT-106) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:AllowanceTotalAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:AllowanceTotalAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-10]-The allowed maximum number of decimals for the Sum of allowanced on document level (BT-107) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:ChargeTotalAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:ChargeTotalAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-11]-The allowed maximum number of decimals for the Sum of charges on document level (BT-108) is 2.</svrl:text>
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
      <xsl:when test="not(ram:TaxTotalAmount) or ram:TaxTotalAmount[(@currencyID =/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode and . = round(. * 100) div 100) or not (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode)]" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(ram:TaxTotalAmount) or ram:TaxTotalAmount[(@currencyID =/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode and . = round(. * 100) div 100) or not (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode)]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-15]-The allowed maximum number of decimals for the Invoice total VAT amount in accounting currency (BT-111) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:TotalPrepaidAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:TotalPrepaidAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-16]-The allowed maximum number of decimals for the Paid amount (BT-113) is 2.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="string-length(substring-after(ram:RoundingAmount,'.'))&lt;=2" />
      <xsl:otherwise>
        <svrl:failed-assert test="string-length(substring-after(ram:RoundingAmount,'.'))&lt;=2">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-DEC-17]-The allowed maximum number of decimals for the Rounding amount (BT-114) is 2.</svrl:text>
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

		
<xsl:choose>
      <xsl:when test="not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode) or (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode and (ram:TaxTotalAmount/@currencyID = /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode) and not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode = /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode))" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode) or (/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode and (ram:TaxTotalAmount/@currencyID = /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode) and not(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode = /rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-53]-If the VAT accounting currency code (BT-6) is present, then the Invoice total VAT amount in accounting currency (BT-111) shall be provided.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M34" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M34" priority="-1" />
  <xsl:template match="@*|node()" mode="M34" priority="-2">
    <xsl:apply-templates mode="M34" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode]" mode="M35" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode]" />

		
<xsl:choose>
      <xsl:when test=". = (round(sum(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CalculatedAmount)*10*10)div 100)" />
      <xsl:otherwise>
        <svrl:failed-assert test=". = (round(sum(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CalculatedAmount)*10*10)div 100)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-14]-Invoice total VAT amount (BT-110) = Σ VAT category tax amount (BT-117).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M35" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M35" priority="-1" />
  <xsl:template match="@*|node()" mode="M35" priority="-2">
    <xsl:apply-templates mode="M35" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeSettlementPaymentMeans" mode="M36" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeSettlementPaymentMeans" />

		
<xsl:choose>
      <xsl:when test="(ram:TypeCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:TypeCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-49]-A Payment instruction (BG-16) shall specify the Payment means type code (BT-81).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:PayeePartyCreditorFinancialAccount/ram:IBANID) or (ram:PayeePartyCreditorFinancialAccount/ram:ProprietaryID) or (not(ram:PayeePartyCreditorFinancialAccount/ram:IBANID) and not(ram:PayeePartyCreditorFinancialAccount/ram:ProprietaryID))" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:PayeePartyCreditorFinancialAccount/ram:IBANID) or (ram:PayeePartyCreditorFinancialAccount/ram:ProprietaryID) or (not(ram:PayeePartyCreditorFinancialAccount/ram:IBANID) and not(ram:PayeePartyCreditorFinancialAccount/ram:ProprietaryID))">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-27]-Either the IBAN or a Proprietary ID (BT-84) shall be used.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M36" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M36" priority="-1" />
  <xsl:template match="@*|node()" mode="M36" priority="-2">
    <xsl:apply-templates mode="M36" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeSettlementPaymentMeans[ram:TypeCode='30' or ram:TypeCode='58']/ram:PayeePartyCreditorFinancialAccount" mode="M37" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeSettlementPaymentMeans[ram:TypeCode='30' or ram:TypeCode='58']/ram:PayeePartyCreditorFinancialAccount" />

		
<xsl:choose>
      <xsl:when test="(ram:IBANID) or (ram:ProprietaryID)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:IBANID) or (ram:ProprietaryID)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-61]-If the Payment means type code (BT-81) means SEPA credit transfer, Local credit transfer or Non-SEPA international credit transfer, the Payment account identifier (BT-84) shall be present.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M37" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M37" priority="-1" />
  <xsl:template match="@*|node()" mode="M37" priority="-2">
    <xsl:apply-templates mode="M37" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//ram:SpecifiedTradeSettlementPaymentMeans[ram:TypeCode='30' or ram:TypeCode='58']/ram:PayerPartyDebtorFinancialAccount" mode="M38" priority="1000">
    <svrl:fired-rule context="//ram:SpecifiedTradeSettlementPaymentMeans[ram:TypeCode='30' or ram:TypeCode='58']/ram:PayerPartyDebtorFinancialAccount" />

		
<xsl:choose>
      <xsl:when test="(ram:IBANID) or (ram:ProprietaryID)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:IBANID) or (ram:ProprietaryID)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-50]-A Payment account identifier (BT-84) shall be present if Credit transfer (BG-16) information is provided in the Invoice.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M38" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M38" priority="-1" />
  <xsl:template match="@*|node()" mode="M38" priority="-2">
    <xsl:apply-templates mode="M38" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement" mode="M39" priority="1000">
    <svrl:fired-rule context="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement" />

		
<xsl:choose>
      <xsl:when test="ram:ApplicableTradeTax" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:ApplicableTradeTax">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-18]-An Invoice shall at least have one VAT breakdown group (BG-23).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M39" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M39" priority="-1" />
  <xsl:template match="@*|node()" mode="M39" priority="-2">
    <xsl:apply-templates mode="M39" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[. = 'AE']" mode="M40" priority="1000">
    <svrl:fired-rule context="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[. = 'AE']" />

		
<xsl:choose>
      <xsl:when test="../ram:CalculatedAmount = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="../ram:CalculatedAmount = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AE-09]-The VAT category tax amount (BT-117) in a VAT breakdown (BG-23) where the VAT category code (BT-118) is "Reverse charge" shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:ExemptionReason) or (../ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:ExemptionReason) or (../ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AE-10]-A VAT Breakdown (BG-23) with VAT Category code (BT-118) "Reverse charge" shall have a VAT exemption reason code (BT-121), meaning "Reverse charge" or the VAT exemption reason text (BT-120) "Reverse charge" (or the equivalent standard text in another language).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M40" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M40" priority="-1" />
  <xsl:template match="@*|node()" mode="M40" priority="-2">
    <xsl:apply-templates mode="M40" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[. = 'E']" mode="M41" priority="1000">
    <svrl:fired-rule context="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[. = 'E']" />

		
<xsl:choose>
      <xsl:when test="../ram:CalculatedAmount = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="../ram:CalculatedAmount = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-E-09]-The VAT category tax amount (BT-117) In a VAT breakdown (BG-23) where the VAT category code (BT-118) equals "Exempt from VAT" shall equal 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:ExemptionReason) or (../ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:ExemptionReason) or (../ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-E-10]-A VAT Breakdown (BG-23) with VAT Category code (BT-118) "Exempt from VAT" shall have a VAT exemption reason code (BT-121) or a VAT exemption reason text (BT-120).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M41" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M41" priority="-1" />
  <xsl:template match="@*|node()" mode="M41" priority="-2">
    <xsl:apply-templates mode="M41" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[. = 'G']" mode="M42" priority="1000">
    <svrl:fired-rule context="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[. = 'G']" />

		
<xsl:choose>
      <xsl:when test="../ram:CalculatedAmount = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="../ram:CalculatedAmount = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-G-09]-The VAT category tax amount (BT-117) in a VAT breakdown (BG-23) where the VAT category code (BT-118) is "Export outside the EU" shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:ExemptionReason) or (../ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:ExemptionReason) or (../ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-G-10]-A VAT Breakdown (BG-23) with the VAT Category code (BT-118) "Export outside the EU" shall have a VAT exemption reason code (BT-121), meaning "Export outside the EU" or the VAT exemption reason text (BT-120) "Export outside the EU" (or the equivalent standard text in another language).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M42" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M42" priority="-1" />
  <xsl:template match="@*|node()" mode="M42" priority="-2">
    <xsl:apply-templates mode="M42" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[.= 'K']" mode="M43" priority="1000">
    <svrl:fired-rule context="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode[.= 'K']" />

		
<xsl:choose>
      <xsl:when test="../ram:CalculatedAmount = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="../ram:CalculatedAmount = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-IC-09]-The VAT category tax amount (BT-117) in a VAT breakdown (BG-23) where the VAT category code (BT-118) is "Intra-community supply" shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(../ram:ExemptionReason) or (../ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(../ram:ExemptionReason) or (../ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-IC-10]-A VAT Breakdown (BG-23) with the VAT Category code (BT-118) "Intra-community supply" shall have a VAT exemption reason code (BT-121), meaning "Intra-community supply" or the VAT exemption reason text (BT-120) "Intra-community supply" (or the equivalent standard text in another language).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ActualDeliverySupplyChainEvent/ram:OccurrenceDateTime/udt:DateTimeString) or (../../ram:BillingSpecifiedPeriod/ram:StartDateTime) or (../../ram:BillingSpecifiedPeriod/ram:EndDateTime)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ActualDeliverySupplyChainEvent/ram:OccurrenceDateTime/udt:DateTimeString) or (../../ram:BillingSpecifiedPeriod/ram:StartDateTime) or (../../ram:BillingSpecifiedPeriod/ram:EndDateTime)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-IC-11]-In an Invoice with a VAT breakdown (BG-23) where the VAT category code (BT-118) is "Intra-community supply" the Actual delivery date (BT-72) or the Invoicing period (BG-14) shall not be blank.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:PostalTradeAddress/ram:CountryID" />
      <xsl:otherwise>
        <svrl:failed-assert test="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:PostalTradeAddress/ram:CountryID">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-IC-12]-In an Invoice with a VAT breakdown (BG-23) where the VAT category code (BT-118) is "Intra-community supply" the Deliver to country code (BT-80) shall not be blank.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M43" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M43" priority="-1" />
  <xsl:template match="@*|node()" mode="M43" priority="-2">
    <xsl:apply-templates mode="M43" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax[ram:CategoryCode = 'L']" mode="M44" priority="1000">
    <svrl:fired-rule context="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax[ram:CategoryCode = 'L']" />

		
<xsl:choose>
      <xsl:when test="true()" />
      <xsl:otherwise>
        <svrl:failed-assert test="true()">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AF-09]-The VAT category tax amount (BT-117) in a VAT breakdown (BG-23) where VAT category code (BT-118) is "IGIC" shall equal the VAT category taxable amount (BT-116) multiplied by the VAT category rate (BT-119).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(ram:ExemptionReason) and not (ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(ram:ExemptionReason) and not (ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AF-10]-A VAT Breakdown (BG-23) with VAT Category code (BT-118) "IGIC" shall not have a VAT exemption reason code (BT-121) or VAT exemption reason text (BT-120).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M44" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M44" priority="-1" />
  <xsl:template match="@*|node()" mode="M44" priority="-2">
    <xsl:apply-templates mode="M44" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax[ram:CategoryCode = 'M']" mode="M45" priority="1000">
    <svrl:fired-rule context="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax[ram:CategoryCode = 'M']" />

		
<xsl:choose>
      <xsl:when test="true()" />
      <xsl:otherwise>
        <svrl:failed-assert test="true()">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AG-09]-The VAT category tax amount (BT-117) in a VAT breakdown (BG-23) where VAT category code (BT-118) is "IPSI" shall equal the VAT category taxable amount (BT-116) multiplied by the VAT category rate (BT-119).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(ram:ExemptionReason) and not (ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(ram:ExemptionReason) and not (ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-AG-10]-A VAT Breakdown (BG-23) with VAT Category code (BT-118) "IPSI" shall not have a VAT exemption reason code (BT-121) or VAT exemption reason text (BT-120).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M45" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M45" priority="-1" />
  <xsl:template match="@*|node()" mode="M45" priority="-2">
    <xsl:apply-templates mode="M45" select="@*|*" />
  </xsl:template>

	
<xsl:template match="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax[ram:CategoryCode = 'O']" mode="M46" priority="1000">
    <svrl:fired-rule context="//rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax[ram:CategoryCode = 'O']" />

		
<xsl:choose>
      <xsl:when test="ram:CalculatedAmount = 0" />
      <xsl:otherwise>
        <svrl:failed-assert test="ram:CalculatedAmount = 0">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-09]-The VAT category tax amount (BT-117) in a VAT breakdown (BG-23) where the VAT category code (BT-118) is "Not subject to VAT" shall be 0 (zero).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="(ram:ExemptionReason) or (ram:ExemptionReasonCode)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:ExemptionReason) or (ram:ExemptionReasonCode)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-10]-A VAT Breakdown (BG-23) with VAT Category code (BT-118) " Not subject to VAT" shall have a VAT exemption reason code (BT-121), meaning " Not subject to VAT" or a VAT exemption reason text (BT-120) " Not subject to VAT" (or the equivalent standard text in another language).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(//ram:ApplicableTradeTax[ram:CategoryCode != 'O'])" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(//ram:ApplicableTradeTax[ram:CategoryCode != 'O'])">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-11]-An Invoice that contains a VAT breakdown group (BG-23) with a VAT category code (BT-118) "Not subject to VAT" shall not contain other VAT breakdown groups (BG-23).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(//ram:CategoryTradeTax[ram:CategoryCode != 'O'])" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(//ram:CategoryTradeTax[ram:CategoryCode != 'O'])">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-13]-An Invoice that contains a VAT breakdown group (BG-23) with a VAT category code (BT-118) "Not subject to VAT" shall not contain Document level allowances (BG-20) where Document level allowance VAT category code (BT-95) is not "Not subject to VAT".</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="not(//ram:CategoryTradeTax[ram:CategoryCode != 'O'])" />
      <xsl:otherwise>
        <svrl:failed-assert test="not(//ram:CategoryTradeTax[ram:CategoryCode != 'O'])">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-O-14]-An Invoice that contains a VAT breakdown group (BG-23) with a VAT category code (BT-118) "Not subject to VAT" shall not contain Document level charges (BG-21) where Document level charge VAT category code (BT-102) is not "Not subject to VAT".</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M46" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M46" priority="-1" />
  <xsl:template match="@*|node()" mode="M46" priority="-2">
    <xsl:apply-templates mode="M46" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice" mode="M47" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice" />

		
<xsl:choose>
      <xsl:when test="(number(//ram:DuePayableAmount) > 0 and ((//ram:SpecifiedTradePaymentTerms/ram:DueDateDateTime) or (//ram:SpecifiedTradePaymentTerms/ram:Description))) or not(number(//ram:DuePayableAmount) > 0)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(number(//ram:DuePayableAmount) > 0 and ((//ram:SpecifiedTradePaymentTerms/ram:DueDateDateTime) or (//ram:SpecifiedTradePaymentTerms/ram:Description))) or not(number(//ram:DuePayableAmount) > 0)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-CO-25]-In case the Amount due for payment (BT-115) is positive, either the Payment due date (BT-9) or the Payment terms (BT-20) shall be present.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
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

		
<xsl:choose>
      <xsl:when test="//ram:BuyerTradeParty/ram:PostalTradeAddress" />
      <xsl:otherwise>
        <svrl:failed-assert test="//ram:BuyerTradeParty/ram:PostalTradeAddress">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-10]-An Invoice shall contain the Buyer postal address (BG-8).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="//ram:BuyerTradeParty/ram:PostalTradeAddress/ram:CountryID!=''" />
      <xsl:otherwise>
        <svrl:failed-assert test="//ram:BuyerTradeParty/ram:PostalTradeAddress/ram:CountryID!=''">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-11]-The Buyer postal address shall contain a Buyer country code (BT-55).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="normalize-space(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication[1]/ram:URIID/@schemeID) != '' or not (rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication)" />
      <xsl:otherwise>
        <svrl:failed-assert test="normalize-space(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication[1]/ram:URIID/@schemeID) != '' or not (rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-62]-The Seller electronic address (BT-34) shall have a Scheme identifier.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="normalize-space(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication[1]/ram:URIID/@schemeID) != '' or not (rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication)" />
      <xsl:otherwise>
        <svrl:failed-assert test="normalize-space(rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication[1]/ram:URIID/@schemeID) != '' or not (rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-63]-The Buyer electronic address (BT-49) shall have a Scheme identifier.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M47" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M47" priority="-1" />
  <xsl:template match="@*|node()" mode="M47" priority="-2">
    <xsl:apply-templates mode="M47" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument" mode="M48" priority="1000">
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
    <xsl:apply-templates mode="M48" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M48" priority="-1" />
  <xsl:template match="@*|node()" mode="M48" priority="-2">
    <xsl:apply-templates mode="M48" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:ID[@schemeID]" mode="M49" priority="1000">
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
    <xsl:apply-templates mode="M49" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M49" priority="-1" />
  <xsl:template match="@*|node()" mode="M49" priority="-2">
    <xsl:apply-templates mode="M49" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IncludedNote" mode="M50" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IncludedNote" />

		
<xsl:choose>
      <xsl:when test="count(ram:Content)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:Content)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:Content' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:SubjectCode)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:SubjectCode)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:SubjectCode' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M50" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M50" priority="-1" />
  <xsl:template match="@*|node()" mode="M50" priority="-2">
    <xsl:apply-templates mode="M50" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IncludedNote/ram:SubjectCode" mode="M51" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IncludedNote/ram:SubjectCode" />
    <xsl:variable name="codeValue4" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=4]/enumeration[@value=$codeValue4]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=4]/enumeration[@value=$codeValue4]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:SubjectCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M51" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M51" priority="-1" />
  <xsl:template match="@*|node()" mode="M51" priority="-2">
    <xsl:apply-templates mode="M51" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString" mode="M52" priority="1000">
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
    <xsl:apply-templates mode="M52" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M52" priority="-1" />
  <xsl:template match="@*|node()" mode="M52" priority="-2">
    <xsl:apply-templates mode="M52" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:IssueDateTime/udt:DateTimeString[@format]" mode="M53" priority="1000">
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
    <xsl:apply-templates mode="M53" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M53" priority="-1" />
  <xsl:template match="@*|node()" mode="M53" priority="-2">
    <xsl:apply-templates mode="M53" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocument/ram:TypeCode" mode="M54" priority="1000">
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
    <xsl:apply-templates mode="M54" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M54" priority="-1" />
  <xsl:template match="@*|node()" mode="M54" priority="-2">
    <xsl:apply-templates mode="M54" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext" mode="M55" priority="1000">
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
    <xsl:apply-templates mode="M55" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M55" priority="-1" />
  <xsl:template match="@*|node()" mode="M55" priority="-2">
    <xsl:apply-templates mode="M55" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:BusinessProcessSpecifiedDocumentContextParameter" mode="M56" priority="1000">
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
    <xsl:apply-templates mode="M56" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M56" priority="-1" />
  <xsl:template match="@*|node()" mode="M56" priority="-2">
    <xsl:apply-templates mode="M56" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:BusinessProcessSpecifiedDocumentContextParameter/ram:ID[@schemeID]" mode="M57" priority="1000">
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
    <xsl:apply-templates mode="M57" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M57" priority="-1" />
  <xsl:template match="@*|node()" mode="M57" priority="-2">
    <xsl:apply-templates mode="M57" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter" mode="M58" priority="1000">
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
    <xsl:apply-templates mode="M58" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M58" priority="-1" />
  <xsl:template match="@*|node()" mode="M58" priority="-2">
    <xsl:apply-templates mode="M58" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter/ram:ID" mode="M59" priority="1000">
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
    <xsl:apply-templates mode="M59" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M59" priority="-1" />
  <xsl:template match="@*|node()" mode="M59" priority="-2">
    <xsl:apply-templates mode="M59" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:ExchangedDocumentContext/ram:GuidelineSpecifiedDocumentContextParameter/ram:ID[@schemeID]" mode="M60" priority="1000">
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
    <xsl:apply-templates mode="M60" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M60" priority="-1" />
  <xsl:template match="@*|node()" mode="M60" priority="-2">
    <xsl:apply-templates mode="M60" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement" mode="M61" priority="1000">
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
    <xsl:apply-templates mode="M61" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M61" priority="-1" />
  <xsl:template match="@*|node()" mode="M61" priority="-2">
    <xsl:apply-templates mode="M61" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerOrderReferencedDocument" mode="M62" priority="1000">
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
    <xsl:apply-templates mode="M62" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M62" priority="-1" />
  <xsl:template match="@*|node()" mode="M62" priority="-2">
    <xsl:apply-templates mode="M62" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerOrderReferencedDocument/ram:FormattedIssueDateTime" mode="M63" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerOrderReferencedDocument/ram:FormattedIssueDateTime" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:FormattedIssueDateTime' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M63" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M63" priority="-1" />
  <xsl:template match="@*|node()" mode="M63" priority="-2">
    <xsl:apply-templates mode="M63" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerOrderReferencedDocument/ram:IssuerAssignedID[@schemeID]" mode="M64" priority="1000">
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
    <xsl:apply-templates mode="M64" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M64" priority="-1" />
  <xsl:template match="@*|node()" mode="M64" priority="-2">
    <xsl:apply-templates mode="M64" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty" mode="M65" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:GlobalID)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:GlobalID)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:GlobalID' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
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
      <xsl:when test="count(ram:URIUniversalCommunication)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:URIUniversalCommunication)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:URIUniversalCommunication' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:SpecifiedTaxRegistration)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:SpecifiedTaxRegistration)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:SpecifiedTaxRegistration' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M65" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M65" priority="-1" />
  <xsl:template match="@*|node()" mode="M65" priority="-2">
    <xsl:apply-templates mode="M65" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:GlobalID" mode="M66" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:GlobalID" />

		
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
    <xsl:apply-templates mode="M66" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M66" priority="-1" />
  <xsl:template match="@*|node()" mode="M66" priority="-2">
    <xsl:apply-templates mode="M66" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:GlobalID[@schemeID]" mode="M67" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:GlobalID[@schemeID]" />
    <xsl:variable name="codeValue5" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M67" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M67" priority="-1" />
  <xsl:template match="@*|node()" mode="M67" priority="-2">
    <xsl:apply-templates mode="M67" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:ID[@schemeID]" mode="M68" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:ID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M68" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M68" priority="-1" />
  <xsl:template match="@*|node()" mode="M68" priority="-2">
    <xsl:apply-templates mode="M68" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:PostalTradeAddress" mode="M69" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:PostalTradeAddress" />

		
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

		
<xsl:choose>
      <xsl:when test="count(ram:CountrySubDivisionName)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CountrySubDivisionName)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CountrySubDivisionName' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M69" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M69" priority="-1" />
  <xsl:template match="@*|node()" mode="M69" priority="-2">
    <xsl:apply-templates mode="M69" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:PostalTradeAddress/ram:CountryID" mode="M70" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:PostalTradeAddress/ram:CountryID" />
    <xsl:variable name="codeValue7" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:CountryID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M70" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M70" priority="-1" />
  <xsl:template match="@*|node()" mode="M70" priority="-2">
    <xsl:apply-templates mode="M70" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" mode="M71" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" />
    <xsl:variable name="codeValue6" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=6]/enumeration[@value=$codeValue6]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=6]/enumeration[@value=$codeValue6]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M71" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M71" priority="-1" />
  <xsl:template match="@*|node()" mode="M71" priority="-2">
    <xsl:apply-templates mode="M71" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:TradingBusinessName" mode="M72" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedLegalOrganization/ram:TradingBusinessName" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:TradingBusinessName' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M72" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M72" priority="-1" />
  <xsl:template match="@*|node()" mode="M72" priority="-2">
    <xsl:apply-templates mode="M72" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration" mode="M73" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration" />

		
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
    <xsl:apply-templates mode="M73" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M73" priority="-1" />
  <xsl:template match="@*|node()" mode="M73" priority="-2">
    <xsl:apply-templates mode="M73" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID" mode="M74" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID" />

		
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
    <xsl:apply-templates mode="M74" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M74" priority="-1" />
  <xsl:template match="@*|node()" mode="M74" priority="-2">
    <xsl:apply-templates mode="M74" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID]" mode="M75" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID]" />
    <xsl:variable name="codeValue9" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=9]/enumeration[@value=$codeValue9]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=9]/enumeration[@value=$codeValue9]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M75" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M75" priority="-1" />
  <xsl:template match="@*|node()" mode="M75" priority="-2">
    <xsl:apply-templates mode="M75" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication" mode="M76" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication" />

		
<xsl:choose>
      <xsl:when test="count(ram:URIID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:URIID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:URIID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M76" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M76" priority="-1" />
  <xsl:template match="@*|node()" mode="M76" priority="-2">
    <xsl:apply-templates mode="M76" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication/ram:URIID" mode="M77" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication/ram:URIID" />

		
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
    <xsl:apply-templates mode="M77" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M77" priority="-1" />
  <xsl:template match="@*|node()" mode="M77" priority="-2">
    <xsl:apply-templates mode="M77" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication/ram:URIID[@schemeID]" mode="M78" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:BuyerTradeParty/ram:URIUniversalCommunication/ram:URIID[@schemeID]" />
    <xsl:variable name="codeValue8" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=8]/enumeration[@value=$codeValue8]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=8]/enumeration[@value=$codeValue8]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M78" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M78" priority="-1" />
  <xsl:template match="@*|node()" mode="M78" priority="-2">
    <xsl:apply-templates mode="M78" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:ContractReferencedDocument" mode="M79" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:ContractReferencedDocument" />

		
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
    <xsl:apply-templates mode="M79" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M79" priority="-1" />
  <xsl:template match="@*|node()" mode="M79" priority="-2">
    <xsl:apply-templates mode="M79" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:ContractReferencedDocument/ram:FormattedIssueDateTime" mode="M80" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:ContractReferencedDocument/ram:FormattedIssueDateTime" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:FormattedIssueDateTime' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M80" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M80" priority="-1" />
  <xsl:template match="@*|node()" mode="M80" priority="-2">
    <xsl:apply-templates mode="M80" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:ContractReferencedDocument/ram:IssuerAssignedID[@schemeID]" mode="M81" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:ContractReferencedDocument/ram:IssuerAssignedID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M81" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M81" priority="-1" />
  <xsl:template match="@*|node()" mode="M81" priority="-2">
    <xsl:apply-templates mode="M81" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty" mode="M82" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty" />

		
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
      <xsl:when test="count(ram:SpecifiedTaxRegistration)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:SpecifiedTaxRegistration)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:SpecifiedTaxRegistration' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M82" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M82" priority="-1" />
  <xsl:template match="@*|node()" mode="M82" priority="-2">
    <xsl:apply-templates mode="M82" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:GlobalID" mode="M83" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:GlobalID" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:GlobalID' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M83" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M83" priority="-1" />
  <xsl:template match="@*|node()" mode="M83" priority="-2">
    <xsl:apply-templates mode="M83" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:ID" mode="M84" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:ID" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:ID' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M84" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M84" priority="-1" />
  <xsl:template match="@*|node()" mode="M84" priority="-2">
    <xsl:apply-templates mode="M84" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:PostalTradeAddress" mode="M85" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:PostalTradeAddress" />

		
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

		
<xsl:choose>
      <xsl:when test="count(ram:CountrySubDivisionName)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CountrySubDivisionName)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CountrySubDivisionName' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M85" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M85" priority="-1" />
  <xsl:template match="@*|node()" mode="M85" priority="-2">
    <xsl:apply-templates mode="M85" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:PostalTradeAddress/ram:CountryID" mode="M86" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:PostalTradeAddress/ram:CountryID" />
    <xsl:variable name="codeValue7" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:CountryID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M86" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M86" priority="-1" />
  <xsl:template match="@*|node()" mode="M86" priority="-2">
    <xsl:apply-templates mode="M86" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedLegalOrganization" mode="M87" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedLegalOrganization" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:SpecifiedLegalOrganization' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M87" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M87" priority="-1" />
  <xsl:template match="@*|node()" mode="M87" priority="-2">
    <xsl:apply-templates mode="M87" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration" mode="M88" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration" />

		
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
    <xsl:apply-templates mode="M88" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M88" priority="-1" />
  <xsl:template match="@*|node()" mode="M88" priority="-2">
    <xsl:apply-templates mode="M88" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID" mode="M89" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID" />

		
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
    <xsl:apply-templates mode="M89" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M89" priority="-1" />
  <xsl:template match="@*|node()" mode="M89" priority="-2">
    <xsl:apply-templates mode="M89" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID]" mode="M90" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:SpecifiedTaxRegistration/ram:ID[@schemeID]" />
    <xsl:variable name="codeValue10" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=10]/enumeration[@value=$codeValue10]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=10]/enumeration[@value=$codeValue10]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M90" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M90" priority="-1" />
  <xsl:template match="@*|node()" mode="M90" priority="-2">
    <xsl:apply-templates mode="M90" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:URIUniversalCommunication" mode="M91" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTaxRepresentativeTradeParty/ram:URIUniversalCommunication" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:URIUniversalCommunication' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M91" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M91" priority="-1" />
  <xsl:template match="@*|node()" mode="M91" priority="-2">
    <xsl:apply-templates mode="M91" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty" mode="M92" priority="1000">
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
      <xsl:when test="count(ram:URIUniversalCommunication)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:URIUniversalCommunication)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:URIUniversalCommunication' may occur at maximum 1 times.</svrl:text>
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
    <xsl:apply-templates mode="M92" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M92" priority="-1" />
  <xsl:template match="@*|node()" mode="M92" priority="-2">
    <xsl:apply-templates mode="M92" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:GlobalID" mode="M93" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:GlobalID" />

		
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
    <xsl:apply-templates mode="M93" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M93" priority="-1" />
  <xsl:template match="@*|node()" mode="M93" priority="-2">
    <xsl:apply-templates mode="M93" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:GlobalID[@schemeID]" mode="M94" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:GlobalID[@schemeID]" />
    <xsl:variable name="codeValue5" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M94" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M94" priority="-1" />
  <xsl:template match="@*|node()" mode="M94" priority="-2">
    <xsl:apply-templates mode="M94" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:ID[@schemeID]" mode="M95" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:ID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M95" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M95" priority="-1" />
  <xsl:template match="@*|node()" mode="M95" priority="-2">
    <xsl:apply-templates mode="M95" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:PostalTradeAddress" mode="M96" priority="1000">
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

		
<xsl:choose>
      <xsl:when test="count(ram:CountrySubDivisionName)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CountrySubDivisionName)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CountrySubDivisionName' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M96" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M96" priority="-1" />
  <xsl:template match="@*|node()" mode="M96" priority="-2">
    <xsl:apply-templates mode="M96" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:PostalTradeAddress/ram:CountryID" mode="M97" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:PostalTradeAddress/ram:CountryID" />
    <xsl:variable name="codeValue7" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:CountryID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M97" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M97" priority="-1" />
  <xsl:template match="@*|node()" mode="M97" priority="-2">
    <xsl:apply-templates mode="M97" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" mode="M98" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" />
    <xsl:variable name="codeValue6" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=6]/enumeration[@value=$codeValue6]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=6]/enumeration[@value=$codeValue6]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M98" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M98" priority="-1" />
  <xsl:template match="@*|node()" mode="M98" priority="-2">
    <xsl:apply-templates mode="M98" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ not(ram:ID/@schemeID=&quot;VA&quot;) and  not(ram:ID/@schemeID=&quot;FC&quot;)]" mode="M99" priority="1000">
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
    <xsl:apply-templates mode="M99" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M99" priority="-1" />
  <xsl:template match="@*|node()" mode="M99" priority="-2">
    <xsl:apply-templates mode="M99" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;FC&quot;]" mode="M100" priority="1000">
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
    <xsl:apply-templates mode="M100" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M100" priority="-1" />
  <xsl:template match="@*|node()" mode="M100" priority="-2">
    <xsl:apply-templates mode="M100" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;FC&quot;]/ram:ID" mode="M101" priority="1000">
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
    <xsl:apply-templates mode="M101" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M101" priority="-1" />
  <xsl:template match="@*|node()" mode="M101" priority="-2">
    <xsl:apply-templates mode="M101" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;VA&quot;]" mode="M102" priority="1000">
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
    <xsl:apply-templates mode="M102" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M102" priority="-1" />
  <xsl:template match="@*|node()" mode="M102" priority="-2">
    <xsl:apply-templates mode="M102" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:SpecifiedTaxRegistration[ram:ID/@schemeID=&quot;VA&quot;]/ram:ID" mode="M103" priority="1000">
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
    <xsl:apply-templates mode="M103" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M103" priority="-1" />
  <xsl:template match="@*|node()" mode="M103" priority="-2">
    <xsl:apply-templates mode="M103" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication" mode="M104" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication" />

		
<xsl:choose>
      <xsl:when test="count(ram:URIID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:URIID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:URIID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M104" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M104" priority="-1" />
  <xsl:template match="@*|node()" mode="M104" priority="-2">
    <xsl:apply-templates mode="M104" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication/ram:URIID" mode="M105" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication/ram:URIID" />

		
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
    <xsl:apply-templates mode="M105" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M105" priority="-1" />
  <xsl:template match="@*|node()" mode="M105" priority="-2">
    <xsl:apply-templates mode="M105" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication/ram:URIID[@schemeID]" mode="M106" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeAgreement/ram:SellerTradeParty/ram:URIUniversalCommunication/ram:URIID[@schemeID]" />
    <xsl:variable name="codeValue8" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=8]/enumeration[@value=$codeValue8]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=8]/enumeration[@value=$codeValue8]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M106" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M106" priority="-1" />
  <xsl:template match="@*|node()" mode="M106" priority="-2">
    <xsl:apply-templates mode="M106" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery" mode="M107" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery" />

		
<xsl:choose>
      <xsl:when test="(ram:ShipToTradeParty/ram:PostalTradeAddress and ram:ShipToTradeParty/ram:PostalTradeAddress/ram:CountryID!='') or not (ram:ShipToTradeParty/ram:PostalTradeAddress)" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:ShipToTradeParty/ram:PostalTradeAddress and ram:ShipToTradeParty/ram:PostalTradeAddress/ram:CountryID!='') or not (ram:ShipToTradeParty/ram:PostalTradeAddress)">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-57]-Each Deliver to address (BG-15) shall contain a Deliver to country code (BT-80).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M107" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M107" priority="-1" />
  <xsl:template match="@*|node()" mode="M107" priority="-2">
    <xsl:apply-templates mode="M107" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ActualDeliverySupplyChainEvent" mode="M108" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ActualDeliverySupplyChainEvent" />

		
<xsl:choose>
      <xsl:when test="count(ram:OccurrenceDateTime)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:OccurrenceDateTime)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:OccurrenceDateTime' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M108" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M108" priority="-1" />
  <xsl:template match="@*|node()" mode="M108" priority="-2">
    <xsl:apply-templates mode="M108" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ActualDeliverySupplyChainEvent/ram:OccurrenceDateTime/udt:DateTimeString" mode="M109" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ActualDeliverySupplyChainEvent/ram:OccurrenceDateTime/udt:DateTimeString" />

		
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
    <xsl:apply-templates mode="M109" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M109" priority="-1" />
  <xsl:template match="@*|node()" mode="M109" priority="-2">
    <xsl:apply-templates mode="M109" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ActualDeliverySupplyChainEvent/ram:OccurrenceDateTime/udt:DateTimeString[@format]" mode="M110" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ActualDeliverySupplyChainEvent/ram:OccurrenceDateTime/udt:DateTimeString[@format]" />
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
    <xsl:apply-templates mode="M110" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M110" priority="-1" />
  <xsl:template match="@*|node()" mode="M110" priority="-2">
    <xsl:apply-templates mode="M110" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:DespatchAdviceReferencedDocument" mode="M111" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:DespatchAdviceReferencedDocument" />

		
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
    <xsl:apply-templates mode="M111" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M111" priority="-1" />
  <xsl:template match="@*|node()" mode="M111" priority="-2">
    <xsl:apply-templates mode="M111" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:DespatchAdviceReferencedDocument/ram:FormattedIssueDateTime" mode="M112" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:DespatchAdviceReferencedDocument/ram:FormattedIssueDateTime" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:FormattedIssueDateTime' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M112" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M112" priority="-1" />
  <xsl:template match="@*|node()" mode="M112" priority="-2">
    <xsl:apply-templates mode="M112" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:DespatchAdviceReferencedDocument/ram:IssuerAssignedID[@schemeID]" mode="M113" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:DespatchAdviceReferencedDocument/ram:IssuerAssignedID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M113" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M113" priority="-1" />
  <xsl:template match="@*|node()" mode="M113" priority="-2">
    <xsl:apply-templates mode="M113" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty" mode="M114" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:GlobalID)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:GlobalID)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:GlobalID' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M114" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M114" priority="-1" />
  <xsl:template match="@*|node()" mode="M114" priority="-2">
    <xsl:apply-templates mode="M114" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:GlobalID" mode="M115" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:GlobalID" />

		
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
    <xsl:apply-templates mode="M115" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M115" priority="-1" />
  <xsl:template match="@*|node()" mode="M115" priority="-2">
    <xsl:apply-templates mode="M115" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:GlobalID[@schemeID]" mode="M116" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:GlobalID[@schemeID]" />
    <xsl:variable name="codeValue5" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M116" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M116" priority="-1" />
  <xsl:template match="@*|node()" mode="M116" priority="-2">
    <xsl:apply-templates mode="M116" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:ID[@schemeID]" mode="M117" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:ID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M117" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M117" priority="-1" />
  <xsl:template match="@*|node()" mode="M117" priority="-2">
    <xsl:apply-templates mode="M117" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:PostalTradeAddress" mode="M118" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:PostalTradeAddress" />

		
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

		
<xsl:choose>
      <xsl:when test="count(ram:CountrySubDivisionName)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CountrySubDivisionName)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CountrySubDivisionName' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M118" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M118" priority="-1" />
  <xsl:template match="@*|node()" mode="M118" priority="-2">
    <xsl:apply-templates mode="M118" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:PostalTradeAddress/ram:CountryID" mode="M119" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:PostalTradeAddress/ram:CountryID" />
    <xsl:variable name="codeValue7" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=7]/enumeration[@value=$codeValue7]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:CountryID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M119" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M119" priority="-1" />
  <xsl:template match="@*|node()" mode="M119" priority="-2">
    <xsl:apply-templates mode="M119" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:SpecifiedLegalOrganization" mode="M120" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:SpecifiedLegalOrganization" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:SpecifiedLegalOrganization' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M120" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M120" priority="-1" />
  <xsl:template match="@*|node()" mode="M120" priority="-2">
    <xsl:apply-templates mode="M120" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:SpecifiedTaxRegistration" mode="M121" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:SpecifiedTaxRegistration" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:SpecifiedTaxRegistration' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M121" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M121" priority="-1" />
  <xsl:template match="@*|node()" mode="M121" priority="-2">
    <xsl:apply-templates mode="M121" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:URIUniversalCommunication" mode="M122" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeDelivery/ram:ShipToTradeParty/ram:URIUniversalCommunication" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:URIUniversalCommunication' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M122" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M122" priority="-1" />
  <xsl:template match="@*|node()" mode="M122" priority="-2">
    <xsl:apply-templates mode="M122" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement" mode="M123" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement" />

		
<xsl:choose>
      <xsl:when test="count(ram:PaymentReference)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:PaymentReference)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:PaymentReference' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
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
      <xsl:when test="count(ram:ApplicableTradeTax)>=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ApplicableTradeTax)>=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ApplicableTradeTax' must occur at least 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:SpecifiedTradePaymentTerms)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:SpecifiedTradePaymentTerms)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:SpecifiedTradePaymentTerms' may occur at maximum 1 times.</svrl:text>
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

		
<xsl:choose>
      <xsl:when test="count(ram:ReceivableSpecifiedTradeAccountingAccount)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ReceivableSpecifiedTradeAccountingAccount)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ReceivableSpecifiedTradeAccountingAccount' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M123" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M123" priority="-1" />
  <xsl:template match="@*|node()" mode="M123" priority="-2">
    <xsl:apply-templates mode="M123" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax" mode="M124" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax" />

		
<xsl:choose>
      <xsl:when test="count(ram:CalculatedAmount)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CalculatedAmount)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CalculatedAmount' must occur exactly 1 times.</svrl:text>
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

		
<xsl:choose>
      <xsl:when test="count(ram:BasisAmount)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:BasisAmount)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:BasisAmount' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:CategoryCode)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CategoryCode)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CategoryCode' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M124" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M124" priority="-1" />
  <xsl:template match="@*|node()" mode="M124" priority="-2">
    <xsl:apply-templates mode="M124" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:BasisAmount[@currencyID]" mode="M125" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:BasisAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M125" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M125" priority="-1" />
  <xsl:template match="@*|node()" mode="M125" priority="-2">
    <xsl:apply-templates mode="M125" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CalculatedAmount[@currencyID]" mode="M126" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CalculatedAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M126" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M126" priority="-1" />
  <xsl:template match="@*|node()" mode="M126" priority="-2">
    <xsl:apply-templates mode="M126" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode" mode="M127" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:CategoryCode" />
    <xsl:variable name="codeValue14" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=14]/enumeration[@value=$codeValue14]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=14]/enumeration[@value=$codeValue14]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:CategoryCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M127" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M127" priority="-1" />
  <xsl:template match="@*|node()" mode="M127" priority="-2">
    <xsl:apply-templates mode="M127" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:DueDateTypeCode" mode="M128" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:DueDateTypeCode" />
    <xsl:variable name="codeValue16" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=16]/enumeration[@value=$codeValue16]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=16]/enumeration[@value=$codeValue16]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:DueDateTypeCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M128" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M128" priority="-1" />
  <xsl:template match="@*|node()" mode="M128" priority="-2">
    <xsl:apply-templates mode="M128" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:ExemptionReasonCode" mode="M129" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:ExemptionReasonCode" />
    <xsl:variable name="codeValue15" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=15]/enumeration[@value=$codeValue15]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=15]/enumeration[@value=$codeValue15]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:ExemptionReasonCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M129" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M129" priority="-1" />
  <xsl:template match="@*|node()" mode="M129" priority="-2">
    <xsl:apply-templates mode="M129" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:TypeCode" mode="M130" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ApplicableTradeTax/ram:TypeCode" />
    <xsl:variable name="codeValue13" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=13]/enumeration[@value=$codeValue13]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=13]/enumeration[@value=$codeValue13]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:TypeCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M130" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M130" priority="-1" />
  <xsl:template match="@*|node()" mode="M130" priority="-2">
    <xsl:apply-templates mode="M130" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod/ram:EndDateTime/udt:DateTimeString" mode="M131" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod/ram:EndDateTime/udt:DateTimeString" />

		
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
    <xsl:apply-templates mode="M131" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M131" priority="-1" />
  <xsl:template match="@*|node()" mode="M131" priority="-2">
    <xsl:apply-templates mode="M131" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod/ram:EndDateTime/udt:DateTimeString[@format]" mode="M132" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod/ram:EndDateTime/udt:DateTimeString[@format]" />
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
    <xsl:apply-templates mode="M132" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M132" priority="-1" />
  <xsl:template match="@*|node()" mode="M132" priority="-2">
    <xsl:apply-templates mode="M132" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod/ram:StartDateTime/udt:DateTimeString" mode="M133" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod/ram:StartDateTime/udt:DateTimeString" />

		
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
    <xsl:apply-templates mode="M133" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M133" priority="-1" />
  <xsl:template match="@*|node()" mode="M133" priority="-2">
    <xsl:apply-templates mode="M133" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod/ram:StartDateTime/udt:DateTimeString[@format]" mode="M134" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:BillingSpecifiedPeriod/ram:StartDateTime/udt:DateTimeString[@format]" />
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
    <xsl:apply-templates mode="M134" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M134" priority="-1" />
  <xsl:template match="@*|node()" mode="M134" priority="-2">
    <xsl:apply-templates mode="M134" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:CreditorReferenceID[@schemeID]" mode="M135" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:CreditorReferenceID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M135" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M135" priority="-1" />
  <xsl:template match="@*|node()" mode="M135" priority="-2">
    <xsl:apply-templates mode="M135" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode" mode="M136" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceCurrencyCode" />
    <xsl:variable name="codeValue11" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=11]/enumeration[@value=$codeValue11]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=11]/enumeration[@value=$codeValue11]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:InvoiceCurrencyCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M136" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M136" priority="-1" />
  <xsl:template match="@*|node()" mode="M136" priority="-2">
    <xsl:apply-templates mode="M136" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceReferencedDocument" mode="M137" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceReferencedDocument" />

		
<xsl:choose>
      <xsl:when test="(ram:IssuerAssignedID!='')" />
      <xsl:otherwise>
        <svrl:failed-assert test="(ram:IssuerAssignedID!='')">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	[BR-55]-Each Preceding Invoice reference (BG-3) shall contain a Preceding Invoice reference (BT-25).</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
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
    <xsl:apply-templates mode="M137" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M137" priority="-1" />
  <xsl:template match="@*|node()" mode="M137" priority="-2">
    <xsl:apply-templates mode="M137" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceReferencedDocument/ram:FormattedIssueDateTime/qdt:DateTimeString" mode="M138" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceReferencedDocument/ram:FormattedIssueDateTime/qdt:DateTimeString" />

		
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
    <xsl:apply-templates mode="M138" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M138" priority="-1" />
  <xsl:template match="@*|node()" mode="M138" priority="-2">
    <xsl:apply-templates mode="M138" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceReferencedDocument/ram:FormattedIssueDateTime/qdt:DateTimeString[@format]" mode="M139" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceReferencedDocument/ram:FormattedIssueDateTime/qdt:DateTimeString[@format]" />
    <xsl:variable name="codeValue21" select="@format" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=21]/enumeration[@value=$codeValue21]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=21]/enumeration[@value=$codeValue21]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@format' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M139" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M139" priority="-1" />
  <xsl:template match="@*|node()" mode="M139" priority="-2">
    <xsl:apply-templates mode="M139" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceReferencedDocument/ram:IssuerAssignedID[@schemeID]" mode="M140" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:InvoiceReferencedDocument/ram:IssuerAssignedID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M140" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M140" priority="-1" />
  <xsl:template match="@*|node()" mode="M140" priority="-2">
    <xsl:apply-templates mode="M140" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty" mode="M141" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty" />

		
<xsl:choose>
      <xsl:when test="count(ram:ID)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ID)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ID' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:GlobalID)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:GlobalID)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:GlobalID' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
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
    <xsl:apply-templates mode="M141" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M141" priority="-1" />
  <xsl:template match="@*|node()" mode="M141" priority="-2">
    <xsl:apply-templates mode="M141" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:GlobalID" mode="M142" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:GlobalID" />

		
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
    <xsl:apply-templates mode="M142" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M142" priority="-1" />
  <xsl:template match="@*|node()" mode="M142" priority="-2">
    <xsl:apply-templates mode="M142" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:GlobalID[@schemeID]" mode="M143" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:GlobalID[@schemeID]" />
    <xsl:variable name="codeValue5" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=5]/enumeration[@value=$codeValue5]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M143" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M143" priority="-1" />
  <xsl:template match="@*|node()" mode="M143" priority="-2">
    <xsl:apply-templates mode="M143" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:ID[@schemeID]" mode="M144" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:ID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M144" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M144" priority="-1" />
  <xsl:template match="@*|node()" mode="M144" priority="-2">
    <xsl:apply-templates mode="M144" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:PostalTradeAddress" mode="M145" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:PostalTradeAddress" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:PostalTradeAddress' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M145" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M145" priority="-1" />
  <xsl:template match="@*|node()" mode="M145" priority="-2">
    <xsl:apply-templates mode="M145" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" mode="M146" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:SpecifiedLegalOrganization/ram:ID[@schemeID]" />
    <xsl:variable name="codeValue6" select="@schemeID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=6]/enumeration[@value=$codeValue6]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=6]/enumeration[@value=$codeValue6]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@schemeID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M146" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M146" priority="-1" />
  <xsl:template match="@*|node()" mode="M146" priority="-2">
    <xsl:apply-templates mode="M146" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:SpecifiedLegalOrganization/ram:TradingBusinessName" mode="M147" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:SpecifiedLegalOrganization/ram:TradingBusinessName" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:TradingBusinessName' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M147" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M147" priority="-1" />
  <xsl:template match="@*|node()" mode="M147" priority="-2">
    <xsl:apply-templates mode="M147" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:SpecifiedTaxRegistration" mode="M148" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:SpecifiedTaxRegistration" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:SpecifiedTaxRegistration' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M148" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M148" priority="-1" />
  <xsl:template match="@*|node()" mode="M148" priority="-2">
    <xsl:apply-templates mode="M148" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:URIUniversalCommunication" mode="M149" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:PayeeTradeParty/ram:URIUniversalCommunication" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:URIUniversalCommunication' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M149" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M149" priority="-1" />
  <xsl:template match="@*|node()" mode="M149" priority="-2">
    <xsl:apply-templates mode="M149" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ReceivableSpecifiedTradeAccountingAccount/ram:ID[@schemeID]" mode="M150" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:ReceivableSpecifiedTradeAccountingAccount/ram:ID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M150" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M150" priority="-1" />
  <xsl:template match="@*|node()" mode="M150" priority="-2">
    <xsl:apply-templates mode="M150" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ not(ram:ChargeIndicator/udt:Indicator=&quot;false&quot;) and  not(ram:ChargeIndicator/udt:Indicator=&quot;true&quot;)]" mode="M151" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ not(ram:ChargeIndicator/udt:Indicator=&quot;false&quot;) and  not(ram:ChargeIndicator/udt:Indicator=&quot;true&quot;)]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element variant 'ram:SpecifiedTradeAllowanceCharge[ not(ram:ChargeIndicator/udt:Indicator="false") and  not(ram:ChargeIndicator/udt:Indicator="true")]' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M151" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M151" priority="-1" />
  <xsl:template match="@*|node()" mode="M151" priority="-2">
    <xsl:apply-templates mode="M151" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]" mode="M152" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]" />

		
<xsl:choose>
      <xsl:when test="count(ram:ChargeIndicator)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ChargeIndicator)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ChargeIndicator' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:ActualAmount)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ActualAmount)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ActualAmount' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:CategoryTradeTax)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CategoryTradeTax)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CategoryTradeTax' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M152" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M152" priority="-1" />
  <xsl:template match="@*|node()" mode="M152" priority="-2">
    <xsl:apply-templates mode="M152" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:ActualAmount[@currencyID]" mode="M153" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:ActualAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M153" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M153" priority="-1" />
  <xsl:template match="@*|node()" mode="M153" priority="-2">
    <xsl:apply-templates mode="M153" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:BasisAmount[@currencyID]" mode="M154" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:BasisAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M154" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M154" priority="-1" />
  <xsl:template match="@*|node()" mode="M154" priority="-2">
    <xsl:apply-templates mode="M154" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax" mode="M155" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax" />

		
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

		
<xsl:choose>
      <xsl:when test="count(ram:CategoryCode)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CategoryCode)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CategoryCode' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M155" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M155" priority="-1" />
  <xsl:template match="@*|node()" mode="M155" priority="-2">
    <xsl:apply-templates mode="M155" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:BasisAmount" mode="M156" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:BasisAmount" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:BasisAmount' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M156" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M156" priority="-1" />
  <xsl:template match="@*|node()" mode="M156" priority="-2">
    <xsl:apply-templates mode="M156" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:CalculatedAmount" mode="M157" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:CalculatedAmount" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:CalculatedAmount' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M157" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M157" priority="-1" />
  <xsl:template match="@*|node()" mode="M157" priority="-2">
    <xsl:apply-templates mode="M157" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:CategoryCode" mode="M158" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:CategoryCode" />
    <xsl:variable name="codeValue14" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=14]/enumeration[@value=$codeValue14]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=14]/enumeration[@value=$codeValue14]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:CategoryCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M158" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M158" priority="-1" />
  <xsl:template match="@*|node()" mode="M158" priority="-2">
    <xsl:apply-templates mode="M158" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:DueDateTypeCode" mode="M159" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:DueDateTypeCode" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:DueDateTypeCode' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M159" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M159" priority="-1" />
  <xsl:template match="@*|node()" mode="M159" priority="-2">
    <xsl:apply-templates mode="M159" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:ExemptionReason" mode="M160" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:ExemptionReason" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:ExemptionReason' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M160" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M160" priority="-1" />
  <xsl:template match="@*|node()" mode="M160" priority="-2">
    <xsl:apply-templates mode="M160" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:ExemptionReasonCode" mode="M161" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:ExemptionReasonCode" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:ExemptionReasonCode' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M161" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M161" priority="-1" />
  <xsl:template match="@*|node()" mode="M161" priority="-2">
    <xsl:apply-templates mode="M161" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:TypeCode" mode="M162" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:CategoryTradeTax/ram:TypeCode" />
    <xsl:variable name="codeValue13" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=13]/enumeration[@value=$codeValue13]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=13]/enumeration[@value=$codeValue13]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:TypeCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M162" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M162" priority="-1" />
  <xsl:template match="@*|node()" mode="M162" priority="-2">
    <xsl:apply-templates mode="M162" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:ReasonCode" mode="M163" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;false&quot;]/ram:ReasonCode" />
    <xsl:variable name="codeValue17" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=17]/enumeration[@value=$codeValue17]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=17]/enumeration[@value=$codeValue17]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:ReasonCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M163" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M163" priority="-1" />
  <xsl:template match="@*|node()" mode="M163" priority="-2">
    <xsl:apply-templates mode="M163" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]" mode="M164" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]" />

		
<xsl:choose>
      <xsl:when test="count(ram:ChargeIndicator)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ChargeIndicator)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ChargeIndicator' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:ActualAmount)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ActualAmount)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ActualAmount' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:CategoryTradeTax)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CategoryTradeTax)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CategoryTradeTax' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M164" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M164" priority="-1" />
  <xsl:template match="@*|node()" mode="M164" priority="-2">
    <xsl:apply-templates mode="M164" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:ActualAmount[@currencyID]" mode="M165" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:ActualAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M165" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M165" priority="-1" />
  <xsl:template match="@*|node()" mode="M165" priority="-2">
    <xsl:apply-templates mode="M165" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:BasisAmount[@currencyID]" mode="M166" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:BasisAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M166" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M166" priority="-1" />
  <xsl:template match="@*|node()" mode="M166" priority="-2">
    <xsl:apply-templates mode="M166" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax" mode="M167" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax" />

		
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

		
<xsl:choose>
      <xsl:when test="count(ram:CategoryCode)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:CategoryCode)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:CategoryCode' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M167" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M167" priority="-1" />
  <xsl:template match="@*|node()" mode="M167" priority="-2">
    <xsl:apply-templates mode="M167" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:BasisAmount" mode="M168" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:BasisAmount" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:BasisAmount' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M168" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M168" priority="-1" />
  <xsl:template match="@*|node()" mode="M168" priority="-2">
    <xsl:apply-templates mode="M168" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:CalculatedAmount" mode="M169" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:CalculatedAmount" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:CalculatedAmount' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M169" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M169" priority="-1" />
  <xsl:template match="@*|node()" mode="M169" priority="-2">
    <xsl:apply-templates mode="M169" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:CategoryCode" mode="M170" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:CategoryCode" />
    <xsl:variable name="codeValue14" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=14]/enumeration[@value=$codeValue14]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=14]/enumeration[@value=$codeValue14]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:CategoryCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M170" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M170" priority="-1" />
  <xsl:template match="@*|node()" mode="M170" priority="-2">
    <xsl:apply-templates mode="M170" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:DueDateTypeCode" mode="M171" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:DueDateTypeCode" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:DueDateTypeCode' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M171" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M171" priority="-1" />
  <xsl:template match="@*|node()" mode="M171" priority="-2">
    <xsl:apply-templates mode="M171" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:ExemptionReason" mode="M172" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:ExemptionReason" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:ExemptionReason' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M172" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M172" priority="-1" />
  <xsl:template match="@*|node()" mode="M172" priority="-2">
    <xsl:apply-templates mode="M172" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:ExemptionReasonCode" mode="M173" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:ExemptionReasonCode" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Element 'ram:ExemptionReasonCode' is marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M173" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M173" priority="-1" />
  <xsl:template match="@*|node()" mode="M173" priority="-2">
    <xsl:apply-templates mode="M173" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:TypeCode" mode="M174" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:CategoryTradeTax/ram:TypeCode" />
    <xsl:variable name="codeValue13" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=13]/enumeration[@value=$codeValue13]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=13]/enumeration[@value=$codeValue13]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:TypeCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M174" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M174" priority="-1" />
  <xsl:template match="@*|node()" mode="M174" priority="-2">
    <xsl:apply-templates mode="M174" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:ReasonCode" mode="M175" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeAllowanceCharge[ram:ChargeIndicator/udt:Indicator=&quot;true&quot;]/ram:ReasonCode" />
    <xsl:variable name="codeValue18" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=18]/enumeration[@value=$codeValue18]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=18]/enumeration[@value=$codeValue18]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:ReasonCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M175" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M175" priority="-1" />
  <xsl:template match="@*|node()" mode="M175" priority="-2">
    <xsl:apply-templates mode="M175" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradePaymentTerms" mode="M176" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradePaymentTerms" />

		
<xsl:choose>
      <xsl:when test="count(ram:Description)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:Description)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:Description' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:DirectDebitMandateID)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:DirectDebitMandateID)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:DirectDebitMandateID' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M176" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M176" priority="-1" />
  <xsl:template match="@*|node()" mode="M176" priority="-2">
    <xsl:apply-templates mode="M176" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradePaymentTerms/ram:DirectDebitMandateID[@schemeID]" mode="M177" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradePaymentTerms/ram:DirectDebitMandateID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M177" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M177" priority="-1" />
  <xsl:template match="@*|node()" mode="M177" priority="-2">
    <xsl:apply-templates mode="M177" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradePaymentTerms/ram:DueDateDateTime/udt:DateTimeString" mode="M178" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradePaymentTerms/ram:DueDateDateTime/udt:DateTimeString" />

		
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
    <xsl:apply-templates mode="M178" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M178" priority="-1" />
  <xsl:template match="@*|node()" mode="M178" priority="-2">
    <xsl:apply-templates mode="M178" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradePaymentTerms/ram:DueDateDateTime/udt:DateTimeString[@format]" mode="M179" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradePaymentTerms/ram:DueDateDateTime/udt:DateTimeString[@format]" />
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
    <xsl:apply-templates mode="M179" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M179" priority="-1" />
  <xsl:template match="@*|node()" mode="M179" priority="-2">
    <xsl:apply-templates mode="M179" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation" mode="M180" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation" />

		
<xsl:choose>
      <xsl:when test="count(ram:LineTotalAmount)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:LineTotalAmount)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:LineTotalAmount' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:ChargeTotalAmount)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:ChargeTotalAmount)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:ChargeTotalAmount' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
<xsl:choose>
      <xsl:when test="count(ram:AllowanceTotalAmount)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:AllowanceTotalAmount)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:AllowanceTotalAmount' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>

		
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
      <xsl:when test="count(ram:TaxTotalAmount[@currencyID=../../ram:TaxCurrencyCode])&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:TaxTotalAmount[@currencyID=../../ram:TaxCurrencyCode])&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element variant 'ram:TaxTotalAmount[@currencyID=../../ram:TaxCurrencyCode]' may occur at maximum 1 times.</svrl:text>
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
      <xsl:when test="count(ram:TotalPrepaidAmount)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:TotalPrepaidAmount)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:TotalPrepaidAmount' may occur at maximum 1 times.</svrl:text>
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
    <xsl:apply-templates mode="M180" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M180" priority="-1" />
  <xsl:template match="@*|node()" mode="M180" priority="-2">
    <xsl:apply-templates mode="M180" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:AllowanceTotalAmount[@currencyID]" mode="M181" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:AllowanceTotalAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M181" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M181" priority="-1" />
  <xsl:template match="@*|node()" mode="M181" priority="-2">
    <xsl:apply-templates mode="M181" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:ChargeTotalAmount[@currencyID]" mode="M182" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:ChargeTotalAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M182" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M182" priority="-1" />
  <xsl:template match="@*|node()" mode="M182" priority="-2">
    <xsl:apply-templates mode="M182" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:DuePayableAmount[@currencyID]" mode="M183" priority="1000">
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
    <xsl:apply-templates mode="M183" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M183" priority="-1" />
  <xsl:template match="@*|node()" mode="M183" priority="-2">
    <xsl:apply-templates mode="M183" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:GrandTotalAmount[@currencyID]" mode="M184" priority="1000">
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
    <xsl:apply-templates mode="M184" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M184" priority="-1" />
  <xsl:template match="@*|node()" mode="M184" priority="-2">
    <xsl:apply-templates mode="M184" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:LineTotalAmount[@currencyID]" mode="M185" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:LineTotalAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M185" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M185" priority="-1" />
  <xsl:template match="@*|node()" mode="M185" priority="-2">
    <xsl:apply-templates mode="M185" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxBasisTotalAmount[@currencyID]" mode="M186" priority="1000">
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
    <xsl:apply-templates mode="M186" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M186" priority="-1" />
  <xsl:template match="@*|node()" mode="M186" priority="-2">
    <xsl:apply-templates mode="M186" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[ not(@currencyID=../../ram:InvoiceCurrencyCode) and  not(@currencyID=../../ram:TaxCurrencyCode)]" mode="M187" priority="1000">
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
    <xsl:apply-templates mode="M187" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M187" priority="-1" />
  <xsl:template match="@*|node()" mode="M187" priority="-2">
    <xsl:apply-templates mode="M187" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode and @currencyID]" mode="M188" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode and @currencyID]" />
    <xsl:variable name="codeValue19" select="@currencyID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=19]/enumeration[@value=$codeValue19]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=19]/enumeration[@value=$codeValue19]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@currencyID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M188" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M188" priority="-1" />
  <xsl:template match="@*|node()" mode="M188" priority="-2">
    <xsl:apply-templates mode="M188" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:InvoiceCurrencyCode]" mode="M189" priority="1000">
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
    <xsl:apply-templates mode="M189" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M189" priority="-1" />
  <xsl:template match="@*|node()" mode="M189" priority="-2">
    <xsl:apply-templates mode="M189" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:TaxCurrencyCode and @currencyID]" mode="M190" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:TaxCurrencyCode and @currencyID]" />
    <xsl:variable name="codeValue20" select="@currencyID" />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=20]/enumeration[@value=$codeValue20]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=20]/enumeration[@value=$codeValue20]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of '@currencyID' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M190" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M190" priority="-1" />
  <xsl:template match="@*|node()" mode="M190" priority="-2">
    <xsl:apply-templates mode="M190" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:TaxCurrencyCode]" mode="M191" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TaxTotalAmount[@currencyID=../../ram:TaxCurrencyCode]" />

		
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
    <xsl:apply-templates mode="M191" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M191" priority="-1" />
  <xsl:template match="@*|node()" mode="M191" priority="-2">
    <xsl:apply-templates mode="M191" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TotalPrepaidAmount[@currencyID]" mode="M192" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementHeaderMonetarySummation/ram:TotalPrepaidAmount[@currencyID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @currencyID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M192" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M192" priority="-1" />
  <xsl:template match="@*|node()" mode="M192" priority="-2">
    <xsl:apply-templates mode="M192" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans" mode="M193" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans" />

		
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

		
<xsl:choose>
      <xsl:when test="count(ram:PayeePartyCreditorFinancialAccount)&lt;=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:PayeePartyCreditorFinancialAccount)&lt;=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:PayeePartyCreditorFinancialAccount' may occur at maximum 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M193" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M193" priority="-1" />
  <xsl:template match="@*|node()" mode="M193" priority="-2">
    <xsl:apply-templates mode="M193" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:PayeePartyCreditorFinancialAccount/ram:IBANID[@schemeID]" mode="M194" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:PayeePartyCreditorFinancialAccount/ram:IBANID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M194" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M194" priority="-1" />
  <xsl:template match="@*|node()" mode="M194" priority="-2">
    <xsl:apply-templates mode="M194" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:PayeePartyCreditorFinancialAccount/ram:ProprietaryID[@schemeID]" mode="M195" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:PayeePartyCreditorFinancialAccount/ram:ProprietaryID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M195" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M195" priority="-1" />
  <xsl:template match="@*|node()" mode="M195" priority="-2">
    <xsl:apply-templates mode="M195" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:PayerPartyDebtorFinancialAccount" mode="M196" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:PayerPartyDebtorFinancialAccount" />

		
<xsl:choose>
      <xsl:when test="count(ram:IBANID)=1" />
      <xsl:otherwise>
        <svrl:failed-assert test="count(ram:IBANID)=1">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Element 'ram:IBANID' must occur exactly 1 times.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M196" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M196" priority="-1" />
  <xsl:template match="@*|node()" mode="M196" priority="-2">
    <xsl:apply-templates mode="M196" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:PayerPartyDebtorFinancialAccount/ram:IBANID[@schemeID]" mode="M197" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:PayerPartyDebtorFinancialAccount/ram:IBANID[@schemeID]" />

		
<xsl:if test="true()">
      <svrl:successful-report test="true()">
        <xsl:attribute name="location">
          <xsl:apply-templates mode="schematron-select-full-path" select="." />
        </xsl:attribute>
        <svrl:text>
	Attribute @schemeID' marked as not used in the given context.</svrl:text>
      </svrl:successful-report>
    </xsl:if>
    <xsl:apply-templates mode="M197" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M197" priority="-1" />
  <xsl:template match="@*|node()" mode="M197" priority="-2">
    <xsl:apply-templates mode="M197" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:TypeCode" mode="M198" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:SpecifiedTradeSettlementPaymentMeans/ram:TypeCode" />
    <xsl:variable name="codeValue12" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=12]/enumeration[@value=$codeValue12]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=12]/enumeration[@value=$codeValue12]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:TypeCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M198" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M198" priority="-1" />
  <xsl:template match="@*|node()" mode="M198" priority="-2">
    <xsl:apply-templates mode="M198" select="@*|*" />
  </xsl:template>

	
<xsl:template match="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode" mode="M199" priority="1000">
    <svrl:fired-rule context="/rsm:CrossIndustryInvoice/rsm:SupplyChainTradeTransaction/ram:ApplicableHeaderTradeSettlement/ram:TaxCurrencyCode" />
    <xsl:variable name="codeValue11" select="." />

		
<xsl:choose>
      <xsl:when test="$codedb//cl[@id=11]/enumeration[@value=$codeValue11]" />
      <xsl:otherwise>
        <svrl:failed-assert test="$codedb//cl[@id=11]/enumeration[@value=$codeValue11]">
          
          <xsl:attribute name="location">
            <xsl:apply-templates mode="schematron-select-full-path" select="." />
          </xsl:attribute>
          <svrl:text>
	Value of 'ram:TaxCurrencyCode' is not allowed.</svrl:text>
        </svrl:failed-assert>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates mode="M199" select="@*|*" />
  </xsl:template>
  <xsl:template match="text()" mode="M199" priority="-1" />
  <xsl:template match="@*|node()" mode="M199" priority="-2">
    <xsl:apply-templates mode="M199" select="@*|*" />
  </xsl:template>

  <!-- to not have to call external codedb.xml file, which is quite hard to also extract from .jar, i embedded external codedb.xml here (and wouldn't work anyway as the Factur-X team calls to a not existing external codedb.xml file): -->
  <xsl:variable name="codedb">
    
  <cl id="1">
    <enumeration value="urn:factur-x.eu:1p0:basicwl"/>
    <enumeration value="urn:zugferd.de:2p0:basicwl"/>
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
    <enumeration value="AAA"/>
    <enumeration value="AAB"/>
    <enumeration value="AAC"/>
    <enumeration value="AAD"/>
    <enumeration value="AAE"/>
    <enumeration value="AAF"/>
    <enumeration value="AAG"/>
    <enumeration value="AAI"/>
    <enumeration value="AAJ"/>
    <enumeration value="AAK"/>
    <enumeration value="AAL"/>
    <enumeration value="AAM"/>
    <enumeration value="AAN"/>
    <enumeration value="AAO"/>
    <enumeration value="AAP"/>
    <enumeration value="AAQ"/>
    <enumeration value="AAR"/>
    <enumeration value="AAS"/>
    <enumeration value="AAT"/>
    <enumeration value="AAU"/>
    <enumeration value="AAV"/>
    <enumeration value="AAW"/>
    <enumeration value="AAX"/>
    <enumeration value="AAY"/>
    <enumeration value="AAZ"/>
    <enumeration value="ABA"/>
    <enumeration value="ABB"/>
    <enumeration value="ABC"/>
    <enumeration value="ABD"/>
    <enumeration value="ABE"/>
    <enumeration value="ABF"/>
    <enumeration value="ABG"/>
    <enumeration value="ABH"/>
    <enumeration value="ABI"/>
    <enumeration value="ABJ"/>
    <enumeration value="ABK"/>
    <enumeration value="ABL"/>
    <enumeration value="ABM"/>
    <enumeration value="ABN"/>
    <enumeration value="ABO"/>
    <enumeration value="ABP"/>
    <enumeration value="ABQ"/>
    <enumeration value="ABR"/>
    <enumeration value="ABS"/>
    <enumeration value="ABT"/>
    <enumeration value="ABU"/>
    <enumeration value="ABV"/>
    <enumeration value="ABW"/>
    <enumeration value="ABX"/>
    <enumeration value="ABZ"/>
    <enumeration value="ACA"/>
    <enumeration value="ACB"/>
    <enumeration value="ACC"/>
    <enumeration value="ACD"/>
    <enumeration value="ACE"/>
    <enumeration value="ACF"/>
    <enumeration value="ACG"/>
    <enumeration value="ACH"/>
    <enumeration value="ACI"/>
    <enumeration value="ACJ"/>
    <enumeration value="ACK"/>
    <enumeration value="ACL"/>
    <enumeration value="ACM"/>
    <enumeration value="ACN"/>
    <enumeration value="ACO"/>
    <enumeration value="ACP"/>
    <enumeration value="ACQ"/>
    <enumeration value="ACR"/>
    <enumeration value="ACS"/>
    <enumeration value="ACT"/>
    <enumeration value="ACU"/>
    <enumeration value="ACV"/>
    <enumeration value="ACW"/>
    <enumeration value="ACX"/>
    <enumeration value="ACY"/>
    <enumeration value="ACZ"/>
    <enumeration value="ADA"/>
    <enumeration value="ADB"/>
    <enumeration value="ADC"/>
    <enumeration value="ADD"/>
    <enumeration value="ADE"/>
    <enumeration value="ADF"/>
    <enumeration value="ADG"/>
    <enumeration value="ADH"/>
    <enumeration value="ADI"/>
    <enumeration value="ADJ"/>
    <enumeration value="ADK"/>
    <enumeration value="ADL"/>
    <enumeration value="ADM"/>
    <enumeration value="ADN"/>
    <enumeration value="ADO"/>
    <enumeration value="ADP"/>
    <enumeration value="ADQ"/>
    <enumeration value="ADR"/>
    <enumeration value="ADS"/>
    <enumeration value="ADT"/>
    <enumeration value="ADU"/>
    <enumeration value="ADV"/>
    <enumeration value="ADW"/>
    <enumeration value="ADX"/>
    <enumeration value="ADY"/>
    <enumeration value="ADZ"/>
    <enumeration value="AEA"/>
    <enumeration value="AEB"/>
    <enumeration value="AEC"/>
    <enumeration value="AED"/>
    <enumeration value="AEE"/>
    <enumeration value="AEF"/>
    <enumeration value="AEG"/>
    <enumeration value="AEH"/>
    <enumeration value="AEI"/>
    <enumeration value="AEJ"/>
    <enumeration value="AEK"/>
    <enumeration value="AEL"/>
    <enumeration value="AEM"/>
    <enumeration value="AEN"/>
    <enumeration value="AEO"/>
    <enumeration value="AEP"/>
    <enumeration value="AEQ"/>
    <enumeration value="AER"/>
    <enumeration value="AES"/>
    <enumeration value="AET"/>
    <enumeration value="AEU"/>
    <enumeration value="AEV"/>
    <enumeration value="AEW"/>
    <enumeration value="AEX"/>
    <enumeration value="AEY"/>
    <enumeration value="AEZ"/>
    <enumeration value="AFA"/>
    <enumeration value="AFB"/>
    <enumeration value="AFC"/>
    <enumeration value="AFD"/>
    <enumeration value="AFE"/>
    <enumeration value="AFF"/>
    <enumeration value="AFG"/>
    <enumeration value="AFH"/>
    <enumeration value="AFI"/>
    <enumeration value="AFJ"/>
    <enumeration value="AFK"/>
    <enumeration value="AFL"/>
    <enumeration value="AFM"/>
    <enumeration value="AFN"/>
    <enumeration value="AFO"/>
    <enumeration value="AFP"/>
    <enumeration value="AFQ"/>
    <enumeration value="AFR"/>
    <enumeration value="AFS"/>
    <enumeration value="AFT"/>
    <enumeration value="AFU"/>
    <enumeration value="AFV"/>
    <enumeration value="AFW"/>
    <enumeration value="AFX"/>
    <enumeration value="AFY"/>
    <enumeration value="AFZ"/>
    <enumeration value="AGA"/>
    <enumeration value="AGB"/>
    <enumeration value="AGC"/>
    <enumeration value="AGD"/>
    <enumeration value="AGE"/>
    <enumeration value="AGF"/>
    <enumeration value="AGG"/>
    <enumeration value="AGH"/>
    <enumeration value="AGI"/>
    <enumeration value="AGJ"/>
    <enumeration value="AGK"/>
    <enumeration value="AGL"/>
    <enumeration value="AGM"/>
    <enumeration value="AGN"/>
    <enumeration value="AGO"/>
    <enumeration value="AGP"/>
    <enumeration value="AGQ"/>
    <enumeration value="AGR"/>
    <enumeration value="AGS"/>
    <enumeration value="AGT"/>
    <enumeration value="AGU"/>
    <enumeration value="AGV"/>
    <enumeration value="AGW"/>
    <enumeration value="AGX"/>
    <enumeration value="AGY"/>
    <enumeration value="AGZ"/>
    <enumeration value="AHA"/>
    <enumeration value="AHB"/>
    <enumeration value="AHC"/>
    <enumeration value="AHD"/>
    <enumeration value="AHE"/>
    <enumeration value="AHF"/>
    <enumeration value="AHG"/>
    <enumeration value="AHH"/>
    <enumeration value="AHI"/>
    <enumeration value="AHJ"/>
    <enumeration value="AHK"/>
    <enumeration value="AHL"/>
    <enumeration value="AHM"/>
    <enumeration value="AHN"/>
    <enumeration value="AHO"/>
    <enumeration value="AHP"/>
    <enumeration value="AHQ"/>
    <enumeration value="AHR"/>
    <enumeration value="AHS"/>
    <enumeration value="AHT"/>
    <enumeration value="AHU"/>
    <enumeration value="AHV"/>
    <enumeration value="AHW"/>
    <enumeration value="AHX"/>
    <enumeration value="AHY"/>
    <enumeration value="AHZ"/>
    <enumeration value="AIA"/>
    <enumeration value="AIB"/>
    <enumeration value="AIC"/>
    <enumeration value="AID"/>
    <enumeration value="AIE"/>
    <enumeration value="AIF"/>
    <enumeration value="AIG"/>
    <enumeration value="AIH"/>
    <enumeration value="AII"/>
    <enumeration value="AIJ"/>
    <enumeration value="AIK"/>
    <enumeration value="AIL"/>
    <enumeration value="AIM"/>
    <enumeration value="AIN"/>
    <enumeration value="AIO"/>
    <enumeration value="AIP"/>
    <enumeration value="AIQ"/>
    <enumeration value="AIR"/>
    <enumeration value="AIS"/>
    <enumeration value="AIT"/>
    <enumeration value="AIU"/>
    <enumeration value="AIV"/>
    <enumeration value="AIW"/>
    <enumeration value="AIX"/>
    <enumeration value="AIY"/>
    <enumeration value="AIZ"/>
    <enumeration value="AJA"/>
    <enumeration value="AJB"/>
    <enumeration value="ALC"/>
    <enumeration value="ALD"/>
    <enumeration value="ALE"/>
    <enumeration value="ALF"/>
    <enumeration value="ALG"/>
    <enumeration value="ALH"/>
    <enumeration value="ALI"/>
    <enumeration value="ALJ"/>
    <enumeration value="ALK"/>
    <enumeration value="ALL"/>
    <enumeration value="ALM"/>
    <enumeration value="ALN"/>
    <enumeration value="ALO"/>
    <enumeration value="ALP"/>
    <enumeration value="ALQ"/>
    <enumeration value="ARR"/>
    <enumeration value="ARS"/>
    <enumeration value="AUT"/>
    <enumeration value="AUU"/>
    <enumeration value="AUV"/>
    <enumeration value="AUW"/>
    <enumeration value="AUX"/>
    <enumeration value="AUY"/>
    <enumeration value="AUZ"/>
    <enumeration value="AVA"/>
    <enumeration value="AVB"/>
    <enumeration value="AVC"/>
    <enumeration value="AVD"/>
    <enumeration value="AVE"/>
    <enumeration value="AVF"/>
    <enumeration value="BAG"/>
    <enumeration value="BAH"/>
    <enumeration value="BAI"/>
    <enumeration value="BAJ"/>
    <enumeration value="BAK"/>
    <enumeration value="BAL"/>
    <enumeration value="BAM"/>
    <enumeration value="BAN"/>
    <enumeration value="BAO"/>
    <enumeration value="BAP"/>
    <enumeration value="BAQ"/>
    <enumeration value="BAR"/>
    <enumeration value="BAS"/>
    <enumeration value="BAT"/>
    <enumeration value="BAU"/>
    <enumeration value="BAV"/>
    <enumeration value="BAW"/>
    <enumeration value="BAX"/>
    <enumeration value="BAY"/>
    <enumeration value="BAZ"/>
    <enumeration value="BBA"/>
    <enumeration value="BBB"/>
    <enumeration value="BLC"/>
    <enumeration value="BLD"/>
    <enumeration value="BLE"/>
    <enumeration value="BLF"/>
    <enumeration value="BLG"/>
    <enumeration value="BLH"/>
    <enumeration value="BLI"/>
    <enumeration value="BLJ"/>
    <enumeration value="BLK"/>
    <enumeration value="BLL"/>
    <enumeration value="BLM"/>
    <enumeration value="BLN"/>
    <enumeration value="BLO"/>
    <enumeration value="BLP"/>
    <enumeration value="BLQ"/>
    <enumeration value="BLR"/>
    <enumeration value="BLS"/>
    <enumeration value="BLT"/>
    <enumeration value="BLU"/>
    <enumeration value="BLV"/>
    <enumeration value="BLW"/>
    <enumeration value="BLX"/>
    <enumeration value="BLY"/>
    <enumeration value="BLZ"/>
    <enumeration value="BMA"/>
    <enumeration value="BMB"/>
    <enumeration value="BMC"/>
    <enumeration value="BMD"/>
    <enumeration value="BME"/>
    <enumeration value="BMF"/>
    <enumeration value="BMG"/>
    <enumeration value="BMH"/>
    <enumeration value="CCI"/>
    <enumeration value="CCJ"/>
    <enumeration value="CCK"/>
    <enumeration value="CCL"/>
    <enumeration value="CCM"/>
    <enumeration value="CCN"/>
    <enumeration value="CCO"/>
    <enumeration value="CEX"/>
    <enumeration value="CHG"/>
    <enumeration value="CIP"/>
    <enumeration value="CLP"/>
    <enumeration value="CLR"/>
    <enumeration value="COI"/>
    <enumeration value="CUR"/>
    <enumeration value="CUS"/>
    <enumeration value="DAR"/>
    <enumeration value="DCL"/>
    <enumeration value="DEL"/>
    <enumeration value="DIN"/>
    <enumeration value="DOC"/>
    <enumeration value="DUT"/>
    <enumeration value="EUR"/>
    <enumeration value="FBC"/>
    <enumeration value="GBL"/>
    <enumeration value="GEN"/>
    <enumeration value="GS7"/>
    <enumeration value="HAN"/>
    <enumeration value="HAZ"/>
    <enumeration value="ICN"/>
    <enumeration value="IIN"/>
    <enumeration value="IMI"/>
    <enumeration value="IND"/>
    <enumeration value="INS"/>
    <enumeration value="INV"/>
    <enumeration value="IRP"/>
    <enumeration value="ITR"/>
    <enumeration value="ITS"/>
    <enumeration value="LAN"/>
    <enumeration value="LIN"/>
    <enumeration value="LOI"/>
    <enumeration value="MCO"/>
    <enumeration value="MDH"/>
    <enumeration value="MKS"/>
    <enumeration value="ORI"/>
    <enumeration value="OSI"/>
    <enumeration value="PAC"/>
    <enumeration value="PAI"/>
    <enumeration value="PAY"/>
    <enumeration value="PKG"/>
    <enumeration value="PKT"/>
    <enumeration value="PMD"/>
    <enumeration value="PMT"/>
    <enumeration value="PRD"/>
    <enumeration value="PRF"/>
    <enumeration value="PRI"/>
    <enumeration value="PUR"/>
    <enumeration value="QIN"/>
    <enumeration value="QQD"/>
    <enumeration value="QUT"/>
    <enumeration value="RAH"/>
    <enumeration value="REG"/>
    <enumeration value="RET"/>
    <enumeration value="REV"/>
    <enumeration value="RQR"/>
    <enumeration value="SAF"/>
    <enumeration value="SIC"/>
    <enumeration value="SIN"/>
    <enumeration value="SLR"/>
    <enumeration value="SPA"/>
    <enumeration value="SPG"/>
    <enumeration value="SPH"/>
    <enumeration value="SPP"/>
    <enumeration value="SPT"/>
    <enumeration value="SRN"/>
    <enumeration value="SSR"/>
    <enumeration value="SUR"/>
    <enumeration value="TCA"/>
    <enumeration value="TDT"/>
    <enumeration value="TRA"/>
    <enumeration value="TRR"/>
    <enumeration value="TXD"/>
    <enumeration value="WHI"/>
    <enumeration value="ZZZ"/>
  </cl>
  <cl id="5">
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
  <cl id="6">
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
  <cl id="7">
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
  <cl id="8">
    <enumeration value="0002"/>
    <enumeration value="0007"/>
    <enumeration value="0009"/>
    <enumeration value="0037"/>
    <enumeration value="0060"/>
    <enumeration value="0088"/>
    <enumeration value="0096"/>
    <enumeration value="0097"/>
    <enumeration value="0106"/>
    <enumeration value="0130"/>
    <enumeration value="0135"/>
    <enumeration value="0142"/>
    <enumeration value="0147"/>
    <enumeration value="0151"/>
    <enumeration value="0170"/>
    <enumeration value="0177"/>
    <enumeration value="0183"/>
    <enumeration value="0184"/>
    <enumeration value="0188"/>
    <enumeration value="0190"/>
    <enumeration value="0191"/>
    <enumeration value="0192"/>
    <enumeration value="0193"/>
    <enumeration value="0194"/>
    <enumeration value="0195"/>
    <enumeration value="0196"/>
    <enumeration value="0198"/>
    <enumeration value="0199"/>
    <enumeration value="0200"/>
    <enumeration value="0201"/>
    <enumeration value="0202"/>
    <enumeration value="0203"/>
    <enumeration value="0204"/>
    <enumeration value="0205"/>
    <enumeration value="0208"/>
    <enumeration value="0209"/>
    <enumeration value="0210"/>
    <enumeration value="0211"/>
    <enumeration value="0212"/>
    <enumeration value="0213"/>
    <enumeration value="0215"/>
    <enumeration value="0216"/>
    <enumeration value="0217"/>
    <enumeration value="0218"/>
    <enumeration value="0221"/>
    <enumeration value="0225"/>
    <enumeration value="0230"/>
    <enumeration value="0235"/>
    <enumeration value="9901"/>
    <enumeration value="9910"/>
    <enumeration value="9913"/>
    <enumeration value="9914"/>
    <enumeration value="9915"/>
    <enumeration value="9918"/>
    <enumeration value="9919"/>
    <enumeration value="9920"/>
    <enumeration value="9922"/>
    <enumeration value="9923"/>
    <enumeration value="9924"/>
    <enumeration value="9925"/>
    <enumeration value="9926"/>
    <enumeration value="9927"/>
    <enumeration value="9928"/>
    <enumeration value="9929"/>
    <enumeration value="9930"/>
    <enumeration value="9931"/>
    <enumeration value="9932"/>
    <enumeration value="9933"/>
    <enumeration value="9934"/>
    <enumeration value="9935"/>
    <enumeration value="9936"/>
    <enumeration value="9937"/>
    <enumeration value="9938"/>
    <enumeration value="9939"/>
    <enumeration value="9940"/>
    <enumeration value="9941"/>
    <enumeration value="9942"/>
    <enumeration value="9943"/>
    <enumeration value="9944"/>
    <enumeration value="9945"/>
    <enumeration value="9946"/>
    <enumeration value="9947"/>
    <enumeration value="9948"/>
    <enumeration value="9949"/>
    <enumeration value="9950"/>
    <enumeration value="9951"/>
    <enumeration value="9952"/>
    <enumeration value="9953"/>
    <enumeration value="9957"/>
    <enumeration value="9959"/>
    <enumeration value="AN"/>
    <enumeration value="AQ"/>
    <enumeration value="AS"/>
    <enumeration value="AU"/>
    <enumeration value="EM"/>
  </cl>
  <cl id="9">
    <enumeration value="VA"/>
  </cl>
  <cl id="10">
    <enumeration value="VA"/>
  </cl>
  <cl id="11">
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
  <cl id="12">
    <enumeration value="1"/>
    <enumeration value="2"/>
    <enumeration value="3"/>
    <enumeration value="4"/>
    <enumeration value="5"/>
    <enumeration value="6"/>
    <enumeration value="7"/>
    <enumeration value="8"/>
    <enumeration value="9"/>
    <enumeration value="10"/>
    <enumeration value="11"/>
    <enumeration value="12"/>
    <enumeration value="13"/>
    <enumeration value="14"/>
    <enumeration value="15"/>
    <enumeration value="16"/>
    <enumeration value="17"/>
    <enumeration value="18"/>
    <enumeration value="19"/>
    <enumeration value="20"/>
    <enumeration value="21"/>
    <enumeration value="22"/>
    <enumeration value="23"/>
    <enumeration value="24"/>
    <enumeration value="25"/>
    <enumeration value="26"/>
    <enumeration value="27"/>
    <enumeration value="28"/>
    <enumeration value="29"/>
    <enumeration value="30"/>
    <enumeration value="31"/>
    <enumeration value="32"/>
    <enumeration value="33"/>
    <enumeration value="34"/>
    <enumeration value="35"/>
    <enumeration value="36"/>
    <enumeration value="37"/>
    <enumeration value="38"/>
    <enumeration value="39"/>
    <enumeration value="40"/>
    <enumeration value="41"/>
    <enumeration value="42"/>
    <enumeration value="43"/>
    <enumeration value="44"/>
    <enumeration value="45"/>
    <enumeration value="46"/>
    <enumeration value="47"/>
    <enumeration value="48"/>
    <enumeration value="49"/>
    <enumeration value="50"/>
    <enumeration value="51"/>
    <enumeration value="52"/>
    <enumeration value="53"/>
    <enumeration value="54"/>
    <enumeration value="55"/>
    <enumeration value="56"/>
    <enumeration value="57"/>
    <enumeration value="58"/>
    <enumeration value="59"/>
    <enumeration value="60"/>
    <enumeration value="61"/>
    <enumeration value="62"/>
    <enumeration value="63"/>
    <enumeration value="64"/>
    <enumeration value="65"/>
    <enumeration value="66"/>
    <enumeration value="67"/>
    <enumeration value="68"/>
    <enumeration value="69"/>
    <enumeration value="70"/>
    <enumeration value="74"/>
    <enumeration value="75"/>
    <enumeration value="76"/>
    <enumeration value="77"/>
    <enumeration value="78"/>
    <enumeration value="91"/>
    <enumeration value="92"/>
    <enumeration value="93"/>
    <enumeration value="94"/>
    <enumeration value="95"/>
    <enumeration value="96"/>
    <enumeration value="97"/>
    <enumeration value="98"/>
    <enumeration value="ZZZ"/>
  </cl>
  <cl id="13">
    <enumeration value="VAT"/>
  </cl>
  <cl id="14">
    <enumeration value="AE"/>
    <enumeration value="E"/>
    <enumeration value="G"/>
    <enumeration value="K"/>
    <enumeration value="L"/>
    <enumeration value="M"/>
    <enumeration value="O"/>
    <enumeration value="S"/>
    <enumeration value="Z"/>
  </cl>
  <cl id="15">
    <enumeration value="VATEX-EU-132"/>
    <enumeration value="VATEX-EU-132-1A"/>
    <enumeration value="VATEX-EU-132-1B"/>
    <enumeration value="VATEX-EU-132-1C"/>
    <enumeration value="VATEX-EU-132-1D"/>
    <enumeration value="VATEX-EU-132-1E"/>
    <enumeration value="VATEX-EU-132-1F"/>
    <enumeration value="VATEX-EU-132-1G"/>
    <enumeration value="VATEX-EU-132-1H"/>
    <enumeration value="VATEX-EU-132-1I"/>
    <enumeration value="VATEX-EU-132-1J"/>
    <enumeration value="VATEX-EU-132-1K"/>
    <enumeration value="VATEX-EU-132-1L"/>
    <enumeration value="VATEX-EU-132-1M"/>
    <enumeration value="VATEX-EU-132-1N"/>
    <enumeration value="VATEX-EU-132-1O"/>
    <enumeration value="VATEX-EU-132-1P"/>
    <enumeration value="VATEX-EU-132-1Q"/>
    <enumeration value="VATEX-EU-143"/>
    <enumeration value="VATEX-EU-143-1A"/>
    <enumeration value="VATEX-EU-143-1B"/>
    <enumeration value="VATEX-EU-143-1C"/>
    <enumeration value="VATEX-EU-143-1D"/>
    <enumeration value="VATEX-EU-143-1E"/>
    <enumeration value="VATEX-EU-143-1F"/>
    <enumeration value="VATEX-EU-143-1FA"/>
    <enumeration value="VATEX-EU-143-1G"/>
    <enumeration value="VATEX-EU-143-1H"/>
    <enumeration value="VATEX-EU-143-1I"/>
    <enumeration value="VATEX-EU-143-1J"/>
    <enumeration value="VATEX-EU-143-1K"/>
    <enumeration value="VATEX-EU-143-1L"/>
    <enumeration value="VATEX-EU-144"/>
    <enumeration value="VATEX-EU-146-1E"/>
    <enumeration value="VATEX-EU-148"/>
    <enumeration value="VATEX-EU-148-A"/>
    <enumeration value="VATEX-EU-148-B"/>
    <enumeration value="VATEX-EU-148-C"/>
    <enumeration value="VATEX-EU-148-D"/>
    <enumeration value="VATEX-EU-148-E"/>
    <enumeration value="VATEX-EU-148-F"/>
    <enumeration value="VATEX-EU-148-G"/>
    <enumeration value="VATEX-EU-151"/>
    <enumeration value="VATEX-EU-151-1A"/>
    <enumeration value="VATEX-EU-151-1AA"/>
    <enumeration value="VATEX-EU-151-1B"/>
    <enumeration value="VATEX-EU-151-1C"/>
    <enumeration value="VATEX-EU-151-1D"/>
    <enumeration value="VATEX-EU-151-1E"/>
    <enumeration value="VATEX-EU-159"/>
    <enumeration value="VATEX-EU-309"/>
    <enumeration value="VATEX-EU-79-C"/>
    <enumeration value="VATEX-EU-AE"/>
    <enumeration value="VATEX-EU-D"/>
    <enumeration value="VATEX-EU-F"/>
    <enumeration value="VATEX-EU-G"/>
    <enumeration value="VATEX-EU-I"/>
    <enumeration value="VATEX-EU-IC"/>
    <enumeration value="VATEX-EU-J"/>
    <enumeration value="VATEX-EU-O"/>
    <enumeration value="VATEX-FR-CNWVAT"/>
    <enumeration value="VATEX-FR-FRANCHISE"/>
  </cl>
  <cl id="16">
    <enumeration value="5"/>
    <enumeration value="29"/>
    <enumeration value="72"/>
  </cl>
  <cl id="17">
    <enumeration value="41"/>
    <enumeration value="42"/>
    <enumeration value="60"/>
    <enumeration value="62"/>
    <enumeration value="63"/>
    <enumeration value="64"/>
    <enumeration value="65"/>
    <enumeration value="66"/>
    <enumeration value="67"/>
    <enumeration value="68"/>
    <enumeration value="70"/>
    <enumeration value="71"/>
    <enumeration value="88"/>
    <enumeration value="95"/>
    <enumeration value="100"/>
    <enumeration value="102"/>
    <enumeration value="103"/>
    <enumeration value="104"/>
    <enumeration value="105"/>
  </cl>
  <cl id="18">
    <enumeration value="AA"/>
    <enumeration value="AAA"/>
    <enumeration value="AAC"/>
    <enumeration value="AAD"/>
    <enumeration value="AAE"/>
    <enumeration value="AAF"/>
    <enumeration value="AAH"/>
    <enumeration value="AAI"/>
    <enumeration value="AAS"/>
    <enumeration value="AAT"/>
    <enumeration value="AAV"/>
    <enumeration value="AAY"/>
    <enumeration value="AAZ"/>
    <enumeration value="ABA"/>
    <enumeration value="ABB"/>
    <enumeration value="ABC"/>
    <enumeration value="ABD"/>
    <enumeration value="ABF"/>
    <enumeration value="ABK"/>
    <enumeration value="ABL"/>
    <enumeration value="ABN"/>
    <enumeration value="ABR"/>
    <enumeration value="ABS"/>
    <enumeration value="ABT"/>
    <enumeration value="ABU"/>
    <enumeration value="ACF"/>
    <enumeration value="ACG"/>
    <enumeration value="ACH"/>
    <enumeration value="ACI"/>
    <enumeration value="ACJ"/>
    <enumeration value="ACK"/>
    <enumeration value="ACL"/>
    <enumeration value="ACM"/>
    <enumeration value="ACS"/>
    <enumeration value="ADC"/>
    <enumeration value="ADE"/>
    <enumeration value="ADJ"/>
    <enumeration value="ADK"/>
    <enumeration value="ADL"/>
    <enumeration value="ADM"/>
    <enumeration value="ADN"/>
    <enumeration value="ADO"/>
    <enumeration value="ADP"/>
    <enumeration value="ADQ"/>
    <enumeration value="ADR"/>
    <enumeration value="ADT"/>
    <enumeration value="ADW"/>
    <enumeration value="ADY"/>
    <enumeration value="ADZ"/>
    <enumeration value="AEA"/>
    <enumeration value="AEB"/>
    <enumeration value="AEC"/>
    <enumeration value="AED"/>
    <enumeration value="AEF"/>
    <enumeration value="AEH"/>
    <enumeration value="AEI"/>
    <enumeration value="AEJ"/>
    <enumeration value="AEK"/>
    <enumeration value="AEL"/>
    <enumeration value="AEM"/>
    <enumeration value="AEN"/>
    <enumeration value="AEO"/>
    <enumeration value="AEP"/>
    <enumeration value="AES"/>
    <enumeration value="AET"/>
    <enumeration value="AEU"/>
    <enumeration value="AEV"/>
    <enumeration value="AEW"/>
    <enumeration value="AEX"/>
    <enumeration value="AEY"/>
    <enumeration value="AEZ"/>
    <enumeration value="AJ"/>
    <enumeration value="AU"/>
    <enumeration value="CA"/>
    <enumeration value="CAB"/>
    <enumeration value="CAD"/>
    <enumeration value="CAE"/>
    <enumeration value="CAF"/>
    <enumeration value="CAI"/>
    <enumeration value="CAJ"/>
    <enumeration value="CAK"/>
    <enumeration value="CAL"/>
    <enumeration value="CAM"/>
    <enumeration value="CAN"/>
    <enumeration value="CAO"/>
    <enumeration value="CAP"/>
    <enumeration value="CAQ"/>
    <enumeration value="CAR"/>
    <enumeration value="CAS"/>
    <enumeration value="CAT"/>
    <enumeration value="CAU"/>
    <enumeration value="CAV"/>
    <enumeration value="CAW"/>
    <enumeration value="CAX"/>
    <enumeration value="CAY"/>
    <enumeration value="CAZ"/>
    <enumeration value="CD"/>
    <enumeration value="CG"/>
    <enumeration value="CS"/>
    <enumeration value="CT"/>
    <enumeration value="DAB"/>
    <enumeration value="DAC"/>
    <enumeration value="DAD"/>
    <enumeration value="DAF"/>
    <enumeration value="DAG"/>
    <enumeration value="DAH"/>
    <enumeration value="DAI"/>
    <enumeration value="DAJ"/>
    <enumeration value="DAK"/>
    <enumeration value="DAL"/>
    <enumeration value="DAM"/>
    <enumeration value="DAN"/>
    <enumeration value="DAO"/>
    <enumeration value="DAP"/>
    <enumeration value="DAQ"/>
    <enumeration value="DL"/>
    <enumeration value="EG"/>
    <enumeration value="EP"/>
    <enumeration value="ER"/>
    <enumeration value="FAA"/>
    <enumeration value="FAB"/>
    <enumeration value="FAC"/>
    <enumeration value="FC"/>
    <enumeration value="FH"/>
    <enumeration value="FI"/>
    <enumeration value="GAA"/>
    <enumeration value="HAA"/>
    <enumeration value="HD"/>
    <enumeration value="HH"/>
    <enumeration value="IAA"/>
    <enumeration value="IAB"/>
    <enumeration value="ID"/>
    <enumeration value="IF"/>
    <enumeration value="IR"/>
    <enumeration value="IS"/>
    <enumeration value="KO"/>
    <enumeration value="L1"/>
    <enumeration value="LA"/>
    <enumeration value="LAA"/>
    <enumeration value="LAB"/>
    <enumeration value="LF"/>
    <enumeration value="MAE"/>
    <enumeration value="MI"/>
    <enumeration value="ML"/>
    <enumeration value="NAA"/>
    <enumeration value="OA"/>
    <enumeration value="PA"/>
    <enumeration value="PAA"/>
    <enumeration value="PC"/>
    <enumeration value="PL"/>
    <enumeration value="PRV"/>
    <enumeration value="RAB"/>
    <enumeration value="RAC"/>
    <enumeration value="RAD"/>
    <enumeration value="RAF"/>
    <enumeration value="RE"/>
    <enumeration value="RF"/>
    <enumeration value="RH"/>
    <enumeration value="RV"/>
    <enumeration value="SA"/>
    <enumeration value="SAA"/>
    <enumeration value="SAD"/>
    <enumeration value="SAE"/>
    <enumeration value="SAI"/>
    <enumeration value="SG"/>
    <enumeration value="SH"/>
    <enumeration value="SM"/>
    <enumeration value="SU"/>
    <enumeration value="TAB"/>
    <enumeration value="TAC"/>
    <enumeration value="TT"/>
    <enumeration value="TV"/>
    <enumeration value="V1"/>
    <enumeration value="V2"/>
    <enumeration value="WH"/>
    <enumeration value="XAA"/>
    <enumeration value="YY"/>
    <enumeration value="ZZZ"/>
  </cl>
  <cl id="19">
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
  <cl id="20">
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
  <cl id="21">
    <enumeration value="102"/>
  </cl>

  </xsl:variable>
</xsl:stylesheet>