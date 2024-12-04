
## Sources

Sources of Code Lists according to XRechnung specification p. 105, enhanced by information from [EN16931 code lists file](https://ec.europa.eu/digital-building-blocks/sites/display/DIGITAL/Registry+of+supporting+artefacts+to+implement+EN16931)

| Name         | Beschreibung                                                                                       | Version | XRepository Versionskennung und Link          | Usage     | Using in fields                                                         |
|--------------|----------------------------------------------------------------------------------------------------|---------|-----------------------------------------------|-----------|-------------------------------------------------------------------------|
| ISO 3166-1   | Country codes (kompatibel zu ISO 3166-1)                                                           | 2022    | urn:xoev-de:kosit:codeliste:country-codes_8   | extended  | BT-40, BT-55, BT-69, BT-80, BT-159                                      |
| ISO 4217     | Currency codes (kompatibel zu ISO 4217)                                                            | 2021    | urn:xoev-de:kosit:codeliste:currency-codes_3  | full list | BT-5, BT-6                                                              |
| ISO/IEC 6523 | ICD — Identifier scheme code (kompatibel zu ISO 6523)                                              | 2023    | urn:xoev-de:kosit:codeliste:icd_5             | full list | BT-29-1, BT-30-1, BT-46-1, BT-47-1, BT-60-1, BT-61-1, BT-71-1, BT-157-1 |
| UNTDID 1001  | Document name coded                                                                                | 21a     | urn:xoev-de:kosit:codeliste:untdid.1001_4     | subset    | BT-3                                                                    |
| UNTDID 1153  | Reference code qualifier                                                                           | d20a    | urn:xoev-de:kosit:codeliste:untdid.1153_3     | full list | BT-18-1, BT-128-1                                                       |
| UNTDID 2005  | Date or time or period function code qualifier                                                     | d21a    | urn:xoev-de:kosit:codeliste:untdid.2005_4     | subset    | BT-8                                                                    |
| UNTDID 4451  | Text subject code qualifier                                                                        | d21a    | urn:xoev-de:kosit:codeliste:untdid.4451_4     | full list | BT-21                                                                   |
| UNTDID 4461  | Payment means coded                                                                                | d20a    | urn:xoev-de:xrechnung:codeliste:untdid.4461_3 | full list | BT-81                                                                   |
| UNTDID 5189  | Allowance or charge identification coded                                                           | d20a    | urn:xoev-de:kosit:codeliste:untdid.5189_3     | subset    | BT-98, BT-140                                                           |
| UNTDID 5305  | Duty or tax or fee category coded                                                                  | d20a    | urn:xoev-de:kosit:codeliste:untdid.5305_3     | subset    | BT-95, BT-102, BT-118, BT-151                                           |
| UNTDID 7143  | Item type identification coded                                                                     | d21a    | urn:xoev-de:kosit:codeliste:untdid.7143_4     | full list | BT-158-1                                                                |
| UNTDID 7161  | Special service description coded                                                                  | d20a    | urn:xoev-de:kosit:codeliste:untdid.7161_3     | full list | BT-105, BT-145                                                          |
| EAS          | Electronic Address Scheme Code list                                                                | 9.0     | urn:xoev-de:kosit:codeliste:eas_5             | full list | BT-34-1, BT-49-1                                                        |
| VATEX        | VAT exemption reason code list                                                                     | 4.0     | urn:xoev-de:kosit:codeliste:vatex_1           | full list | BT-121                                                                  |
| Rec 20       | UN/EC Recommendation Nº20 – Codes for Units of Measure Used in International Trade                 | Rev. 17 | urn:xoev-de:kosit:codeliste:rec20_3           | full list | BT-130, BT-150                                                          |
| Rec 21       | UN/EC Recommendation Nº21 – Codes for Passengers, Types of Cargo, Packages and Packaging Materials | Rev. 12 | urn:xoev-de:kosit:codeliste:rec21_3           | full list | BT-130, BT-150                                                          |
| VAT ID       | VAT Identifier; has only code "VAT" for Value added tax; code list only in EN Excel file           |         |                                               | subset    | BT-31, BT-48, BT-63                                                     |
| VAT Cat      | VAT Category code; has only code "VAT" for Value added tax; code list only in EN Excel file        |         |                                               | subset    | BT-95, BT-102, BT-118, BT-151                                           |
| MIME         | Mime type codes — Mime codes; code list only in EN Excel file                                      |         |                                               | subset    | BT-125-1                                                                |


### Where to find

All **UNTDID code lists** can be found at https://unece.org/fileadmin/DAM/trade/untdid/d16b/tred/tredXXXX.htm, where XXXX is the four-digit number from above's table, e.g.,

https://unece.org/fileadmin/DAM/trade/untdid/d16b/tred/tred1001.htm

The **UN/EC Recommendation Nº20 and Nº21** can be found at:

https://unece.org/trade/uncefact/cl-recommendations



## Evaluation of sources

Usually it's not necessary to parse above's sources directly, there are already compilations of these, which also contain only the required values for EN16931: 

### EN / CEF Genericode Code Lists

URL: https://ec.europa.eu/digital-building-blocks/sites/display/DIGITAL/Registry+of+supporting+artefacts+to+implement+EN16931

It Contains almost all of the above's code lists, even though some additional data is missing.

\+ good to parse

\- no descriptions
\- only English names


### Factur-X / ZUGFeRD Code Lists .xslx

Download .zip from: https://www.ferd-net.de/standards/zugferd-2.3.2/zugferd-2.3.2.html?acceptCookie=1

\+ all code lists in one file
\+ incl. English descriptions and invoice fields in which codes are used

\- difficult to parse


### UNTDID

URL e.g.: https://unece.org/fileadmin/DAM/trade/untdid/d16b/tred/tred1001.htm

\+ incl. English descriptions

\- difficult to parse, plain text only (on a website!)
\- only English names and descriptions
\- partially more codes than XRechnung standard allows


### UNECE Rec. 20 & 21

Recommendation 20 Codes for Units of Measure Used in International Trade

Recommendation 21 Codes for Passengers, Types of Cargo, Packages and Packaging Materials (with Complementary Codes for Package Names)

URL: https://unece.org/trade/uncefact/cl-recommendations

\+ good to parse
\+ incl. English descriptions
\+ incl. unit symbols

\- only units, no other code lists
\- only English names and descriptions


## Generation

Most of the Code List enums in this folder are generated by [CodeGenerator](../../../../../../../../../e-invoice-spec-parser/src/main/kotlin/net/codinux/invoicing/parser/CodeGenerator.kt) in project `e-invoice-spec-parser`.

It uses the code lists provided by ZUGFerd and the EN / CEF Genericode Code Lists.