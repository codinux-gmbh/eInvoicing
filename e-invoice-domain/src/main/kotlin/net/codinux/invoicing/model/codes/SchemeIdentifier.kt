package net.codinux.invoicing.model.codes

enum class SchemeIdentifier(val schemeID: String?, val code: String, val issuingOrganization: String, val structureOfCode: String?) {
	_0002("FR:SIRENE", "0002", "System Information et Repertoire des Entreprise et des Etablissements: SIRENE", "1) Number of characters: 9 characters ('SIREN') 14 ' 9+5 ('SIRET'), The 9 character number designates an organization, The 14 character number designates a specific establishment of the organization designated by the first 9 characters. 2) Check digits: 9th & 14th character respectively"),
	_0060("DUNS", "0060", "Data Universal Numbering System (D-U-N-S Number)", "1) Eight identification digits and a check digit. A two digit prefix will be added in the future but it will not be used to calculate the check digit. 2) The Organization name is not part of the D-U-N-S number."),
	_0088("GLN", "0088", "EAN Location Code", "1) 13 digits including check digits, 2) None"),
	_0177("ODETTE", "0177", "Odette International Limited", "ICD 4 digits"),
	_0003(null, "0003", "Codification Numerique des Etablissments Financiers En Belgique", "1) 3 numeric digits, 2) None Display Requirements : In one group of three Character Repertoire :"),
	_0004(null, "0004", "NBS/OSI NETWORK", "1) 0004 OSINET Open System Interconnection Network, 2) No check digits are needed as the whole message has a checking mechanism."),
	_0005(null, "0005", "USA FED GOV OSI NETWORK", "1) 0005 GOSNET United States Federal Government Open System Interconnection Network, 2) No check digits are needed as the whole message has a checking mechanism."),
	_0006(null, "0006", "USA DOD OSI NETWORK", "1) 0006 DODNET Open System Interconnection Network for the Department of Defense USA, 2) No check digits are needed as the whole message has a checking mechanism."),
	_0007("SE:ORGNR", "0007", "Organisationsnummer", "1) 10 digits. 1st digit = Group number, 2nd - 9th digit = Ordinalnumber1st digit, = Group number, 10th digit = Check digit, 2) Last digit."),
	_0008(null, "0008", "LE NUMERO NATIONAL", "1) 13 characters, 2) 8th & 9th characters"),
	_0009("FR:SIRET", "0009", "SIRET-CODE", "1) 14 digits, 2) None"),
	_0010(null, "0010", "Organizational Identifiers for Structured Names under ISO 9541 Part 2", "1) Between 1 - 14 characters (letters, digits and hyphens only). 2) None"),
	_0011(null, "0011", "International Code Designator for the Identification of OSI-based, Amateur Radio Organizations, Network Objects and Application Services.", null),
	_0012(null, "0012", "European Computer Manufacturers Association: ECMA", "1) Three fields, First field = ICD, Second field = Organization Code, four-digit number, 1000-9989, Third field = Organization Name, upto 250 characters, 2) None"),
	_0013(null, "0013", "VSA FTP CODE (FTP = File Transfer Protocol)", "1) Four fields, First field = four digit, ICD code, Second field = six characters, Third field = eight characters, identification of organization. Fourth field = six characters, special identification (e.g. sub-address), if required. 2) None"),
	_0014(null, "0014", "NIST/OSI Implememts' Workshop", "1) 0014 OWI NIST Workshop for Implementors of OSI, 2) No check digits are needed as the whole message has checking mechanism"),
	_0015(null, "0015", "Electronic Data Interchange: EDI", "1) details not received yet, 2) Display Requirements : Details not received yet Character Repertoire :"),
	_0016(null, "0016", "EWOS Object Identifiers", "1) Digit ICD code = 0016, Organization Code = 4 characters, Organization Name = 34 characters, 2) None"),
	_0017(null, "0017", "COMMON LANGUAGE", "1) Two fields, a. Place Code = four characters, derived from location name. b."),
	_0018(null, "0018", "SNA/OSI Network", "1) xxx SNA-OSI NET Open Systems Interconnection Network, 2) None, as the whole message has a checking mechanism."),
	_0019(null, "0019", "Air Transport Industry Services Communications Network", "1) ICD IATA International Air Transport Association, 2) No check digits are needed as the whole message has a checking mechanism."),
	_0020(null, "0020", "European Laboratory for Particle Physics: CERN", "1) 4 Digit ICD code. Organization code upto 14 characters. Organization name upto 250 characters. 2) No check digits needed."),
	_0021("SWIFT", "0021", "SOCIETY FOR WORLDWIDE INTERBANK FINANCIAL, TELECOMMUNICATION S.W.I.F.T.", null),
	_0022(null, "0022", "OSF Distributed Computing Object Identification", "1) Organization code: full 4- character code without spaces or hyphens."),
	_0023(null, "0023", "Nordic University and Research Network: NORDUnet", "1) ICD Code - 4 digits, Organisation code - upto 14 characters, Organisation Name - upto 250 characters, 2) No check digits needed."),
	_0024(null, "0024", "Digital Equipment Corporation: DEC", "1) Four digit ICD code, Organisation code upto 14 characters, Organisation name upto 250 characters, 2) None"),
	_0025(null, "0025", "OSI ASIA-OCEANIA WORKSHOP", "1) Number of the characters and their significance as defined in clause 3 of ISO 6523, ICD = 4 characters, Organization code = upto 14 characters, Organization name = upto 250 characters, 2) No identification of check digits"),
	_0026(null, "0026", "NATO ISO 6523 ICDE coding scheme", "1) ICD Code - 4 digits, Organisation code up to 14 characters, Organisation name up to 250 characters, 2) No check digits"),
	_0027(null, "0027", "Aeronautical Telecommunications Network (ATN)", "1) /XXXX/ICAO/International Civil Aviation Organization, 2) No check digits"),
	_0028(null, "0028", "International Standard ISO 6523", "1) 14 characters identifying STYRIA FEDERN GmbH, 2) no check digits"),
	_0029(null, "0029", "The All-Union Classifier of Enterprises and Organisations", "1) 8 character in digits, The first 7 digits indicate the ordinal number of an organization, 2) From 0 to 9 including one check digit"),
	_0030(null, "0030", "AT&T/OSI Network", "1) ICD, 2) Organization Code, 1-14 characters, 3) Organization Name, upto 250 characters"),
	_0031(null, "0031", "EDI Partner Identification Code", "1) ICD Code...N4, District Number of Chamber of Commerce...N2, Company number according to Chamber of Commerce...N12, Sub-address...AN 6 (if required)"),
	_0032(null, "0032", "Telecom Australia", "1) Delimiter between ICD and Organisation code to be 3 spaces, 2) Delimiter between Organisation name and Organisation code to be 2 spaces, 3) Delimiter between names within the Organisation name to be 2 spaces, 4) No check digits"),
	_0033(null, "0033", "S G W OSI Internetwork", "1) S G W OSI, 2) S G Warburg Group Management Ltd"),
	_0034(null, "0034", "Reuter Open Address Standard", "1) According to ISO 8348 Addendum 2, 2) There are no check digits"),
	_0035(null, "0035", "ISO 6523 - ICD", "1), 2) Display Requirements : None Character Repertoire :"),
	_0036(null, "0036", "TeleTrust Object Identifiers", "1) Organization code: TeleTrust, 2) Organization name: TeleTrust-Deutschland-e.V."),
	_0037("FI:OVT", "0037", "LY-tunnus", "1) 8 digits, 1st-7th digit = number, 8th digit = check number, 2) digit"),
	_0038(null, "0038", "The Australian GOSIP Network", "1) NSAP address: maximum length: 20 codes including the ICD code, 2) No check digit"),
	_0039(null, "0039", "The OZ DOD OSI Network", "1) 0039/OZDOD DEFNET/Australian Department of Defence OSI Network, 2) No check digits needed as the whole message has a checking mechanism"),
	_0040(null, "0040", "Unilever Group Companies", "1) 4 digits 0-9, 2) No check digits"),
	_0041(null, "0041", "Citicorp Global Information Network", "1) ICD CGIN Citicorp Global Information Network, 2) None"),
	_0042(null, "0042", "DBP Telekom Object Identifiers", "1) Organisation code: four numeric digits (ICD), 2) Organisation name: Deutsche Bundespost Telekom"),
	_0043(null, "0043", "HydroNETT", "1) ICD code: 4 digit, Organization code: (up to 14 characters). Organization name: (up to 250 characters). 2) No check digits needed."),
	_0044(null, "0044", "Thai Industrial Standards Institute (TISI)", "1) Four Fields, First field = four digits, ICD code, Second field = three characters to represent organization group, Third field = between 1-11 characters, Fourth field = Organization Name, up to 250, characters, 2) None"),
	_0045(null, "0045", "ICI Company Identification System", "1) ICD org, Code, 1 4 5 8 9 n, xxxx/xxxx/organisation name//, 2) None"),
	_0046(null, "0046", "FUNLOC", "1) 6 Decimal digits,the first 3 denoting the country in a proprietary coding system. 2) None"),
	_0047(null, "0047", "BULL ODI/DSA/UNIX Network", "1) Four numeric digits, 2) None"),
	_0048(null, "0048", "OSINZ", "1) 8 Digits (1-4 organisation), (5-8 Subnet ID), 2) None"),
	_0049(null, "0049", "Auckland Area Health", "1) 8 Digits (1-4 organisation), (5-8 Subnet ID), 2) None"),
	_0050(null, "0050", "Firmenich", "1) XXXX/XXX XXXXXXX/FIRMENICH//, 2) XXXX/XXXXXXXXXX//"),
	_0051(null, "0051", "AGFA-DIS", "1) XXXX/AGFA-DIS/AGFA-DIS//, 2) None"),
	_0052(null, "0052", "Society of Motion Picture and Television Engineers (SMPTE)", "1) Three fields, First field = ICD, Second field = SMPTE, Third field = Society of Motion Picture and Television Engineers, 2) None, except that all fields are left"),
	_0053(null, "0053", "Migros_Network M_NETOPZ", "1) MIGROS, MGB, 2) None"),
	_0054(null, "0054", "ISO6523 - ICDPCR", "1) As per Addendum 2 ISO 8348, 2) None"),
	_0055(null, "0055", "Energy Net", "1) AFI, ICD, country code, routing domain, Area, ID, SEL, 2) None"),
	_0056(null, "0056", "Nokia Object Identifiers (NOI)", "1) ICD (fixed length 4 digits), Organization code (variable length up to 14 characters), Organization name (variable length up to 250 characters), 2) None"),
	_0057(null, "0057", "Saint Gobain", "1) ICD 4 digits, (AFI 47 followed by a 4 digit ICD), 2) None"),
	_0058(null, "0058", "Siemens Corporate Network", "1) cccc(ICD) SCN (Siemens Corporate Network), 2) No check digits as in general the whole message has a checking mechanism."),
	_0059(null, "0059", "DANZNET", "1) Between 1 - 4 characters (letters, digits and hyphens only). 2) Between 1 - 12 characters (letters, digits and hyphens only)."),
	_0061(null, "0061", "SOFFEX OSI", "1) 4 numeric digits, 2) None"),
	_0062(null, "0062", "KPN OVN", "1) ICD 4 digits, 2) None"),
	_0063(null, "0063", "ascomOSINet", "1) ICD 4 digits, 2) None Display Requirements : All fields are left justified Character Repertoire :"),
	_0064(null, "0064", "UTC: Uniforme Transport Code", "1) ICD Code: 4 digits, Organization code: minimum 1, maximum 8 digits, Sequence number: minimum 1, maximum 8 digits, 2) None"),
	_0065(null, "0065", "SOLVAY OSI CODING", "1) Two octets, fixed length. The particular values of 00-00 (all zeros) and FF-FF (allones) will be reserved, so that addresses are able to comply with ECMA-117, where the ISO 6523 organization code is mapped to ECMA-117 subnetwork. This structure also permits compliance with GOSIP (FIPS PUB 146), 2) No check digits will be used"),
	_0066(null, "0066", "Roche Corporate Network", "As per Addendum 2 ISO 8348"),
	_0067(null, "0067", "ZellwegerOSINet", "1) ICD 4 digits, 2) None Display Requirements : All fields are left justified Character Repertoire :"),
	_0068(null, "0068", "Intel Corporation OSI", "1) 4 Numeric digits, 2) None"),
	_0069(null, "0069", "SITA Object Identifier Tree", "1) ISO(1), identified organization(3), sita(00xx), 2) None"),
	_0070(null, "0070", "DaimlerChrysler Corporate Network", "1) cccc (ICD) DCCN (DaimlerChrysler Corporate Network), 2) No check digits as in general the whole message has a checking mechanism"),
	_0071(null, "0071", "LEGO /OSI NETWORK", "1) Three fields: First field = ICD, Second field = Organization Code, Third field = Organization Name, 2) None"),
	_0072(null, "0072", "NAVISTAR/OSI Network", "1) Three fields: First field = ICD, Second field = NAVISTAR, Third field = International Truck and Engine Corporation, 2) None"),
	_0073(null, "0073", "ICD Formatted ATM address", "1) Format: /XXXX/xxxxxxx/Organization Name//, Example: /XXXX/000000/Newbridge Network Corporation//, The xxxxxx field is a 6-digit BCD encoded number. 2) There are no check digits"),
	_0074(null, "0074", "ARINC", "1) ISO (1), identified organization (3), arinc (00xx), 2) None"),
	_0075(null, "0075", "Alcanet/Alcatel-Alsthom Corporate Network", "1) cccc (ICD) Alcanet, 2) No check digits as in general the whole message has a checking mechanism"),
	_0076(null, "0076", "Sistema Italiano di Identificazione di ogetti gestito da UNINFO", "1) Six digits. Organization name: variable length up to 250 characters, 2) No check digits"),
	_0077(null, "0077", "Sistema Italiano di Indirizzamento di Reti OSI Gestito da UNINFO", "1) To be defined, 2) Display Requirements : To be defined Character Repertoire :"),
	_0078(null, "0078", "Mitel terminal or switching equipment", "1) XXX/MITEL/Mitel Corporation//, 2) None"),
	_0079(null, "0079", "ATM Forum", "1) Format includes 2 fields, First field = ICD, Second field = Domains Specific Part,"),
	_0080(null, "0080", "UK National Health Service Scheme, (EDIRA compliant)", null),
	_0081(null, "0081", "International NSAP", "1) NSAP address (detailed document on structure can be supplied on request, 2) No check digit"),
	_0082(null, "0082", "Norwegian Telecommunications Authority's, NTA'S, EDI, identifier scheme (EDIRA compliant)", null),
	_0083(null, "0083", "Advanced Telecommunications Modules Limited, Corporate Network", "1) Format includes 2 fields : First field = ICD, Second field = Domain specific part, 2) None"),
	_0084(null, "0084", "Athens Chamber of Commerce & Industry Scheme (EDIRA compliant)", null),
	_0085(null, "0085", "Swiss Chambers of Commerce Scheme (EDIRA) compliant", "999-999999-999999-9-99; useage of 100999999-999999-9-99 is prohibited, 1)18 numerical characters, organization ID (mandatory): 9 characters (first 3 char. may, indicate a registration office), organization part, OPI (optional): 6 char. OPI source indicator, OPIS (optional): 1 char. 2) Check digits (optional): last 2 char. Calculated mod 97 on used characters"),
	_0086(null, "0086", "United States Council for International Business (USCIB) Scheme, (EDIRA compliant)", null),
	_0087(null, "0087", "National Federation of Chambers of Commerce & Industry of Belgium, Scheme (EDIRA compliant)", null),
	_0089(null, "0089", "The Association of British Chambers of Commerce Ltd. Scheme, (EDIRA compliant)", null),
	_0090(null, "0090", "Internet IP addressing - ISO 6523 ICD encoding", "1) ICD, Organization Code, 1-14 characters, Organization name, up to 250 characters, 2) None, as the whole message has a checking mechanism"),
	_0091(null, "0091", "Cisco Sysytems / OSI Network", "1) Three fields, First field = ICD, Second field = Organization Code, 1-14 characters, Third field = Organization Name, up to 150 characters, 2)"),
	_0093(null, "0093", "Revenue Canada Business Number Registration (EDIRA compliant)", null),
	_0094(null, "0094", "DEUTSCHER INDUSTRIE- UND HANDELSTAG (DIHT) Scheme (EDIRA compliant)", null),
	_0095(null, "0095", "Hewlett - Packard Company Internal AM Network", "1) Format includes 2 fields: First field = ICD, 2) Second field = Domain specific part"),
	_0096("DK:P", "0096", "DANISH CHAMBER OF COMMERCE Scheme (EDIRA compliant)", null),
	_0097("IT:FTI", "0097", "FTI - Ediforum Italia, (EDIRA compliant)", null),
	_0098(null, "0098", "CHAMBER OF COMMERCE TEL AVIV-JAFFA Scheme (EDIRA compliant)", null),
	_0099(null, "0099", "Siemens Supervisory Systems Network", "1) cccc (ICD), 2) No check digits as in general the whole message has a checking mechanism"),
	_0100(null, "0100", "PNG_ICD Scheme", null),
	_0101(null, "0101", "South African Code Allocation", null),
	_0102(null, "0102", "HEAG", "1) cccc(ICD), 2) no check digits"),
	_0104(null, "0104", "BT - ICD Coding System", "1) Format includes 2 fields: First field = ICD (4 decimal digits), Second field = Domain specific part, 2) None"),
	_0105(null, "0105", "Portuguese Chamber of Commerce and Industry Scheme (EDIRA compliant)", null),
	_0106("NL:KVK", "0106", "Vereniging van Kamers van Koophandel en Fabrieken in Nederland (Association of Chambers of Commerce and Industry in the Netherlands), Scheme (EDIRA compliant)", null),
	_0107(null, "0107", "Association of Swedish Chambers of Commerce and Industry Scheme (EDIRA compliant)", null),
	_0108(null, "0108", "Australian Chambers of Commerce and Industry Scheme (EDIRA compliant)", null),
	_0109(null, "0109", "BellSouth ICD AESA (ATM End System Address)", "Field, #bytes, Name, Notes: 1, 1, Authority and Format Identifier, =0x47; 2, 2, ICD; 3, 10, Higher Order Domain Specific Part, BellSouth administered; 4, 6, End System Identifier, End user field; 5, 1, Selector, End user field; This structure conforms to the ICD AESA specified in User-Network (UNI), Specification Version 3.1 and ATM User-Network Interface (UNI) Signalling, Specification Version 4.0 (af-sig-0061.000). Both by the ATM Forum."),
	_0110(null, "0110", "Bell Atlantic", "Format includes 2 fields : First field = ICD, Second field = Domain Specific Part"),
	_0111(null, "0111", "Object Identifiers", "SMPTE 298M Universal Labels for Unique Identification, of Digital Data an ISO/ITU based identifier hierarchy registration system."),
	_0112(null, "0112", "ISO register for Standards producing Organizations", "1) Numeric sequential, 2)"),
	_0113(null, "0113", "OriginNet", "Format includes 2 fields, First field: ICD, Second field: Domain Specific Part"),
	_0114(null, "0114", "Check Point Software Technologies", "1) Format includes 3 fields: First field: ICD, 4 decimal digits, Second field: Organization Code, 1-14 characters, Third field: Organization Name, up to 150 characters, 2) No check digits"),
	_0115(null, "0115", "Pacific Bell Data Communications Network", "An OSI network address, which consists of the IDP, with subfields AFI and ICD, followed by the Domain Specific Part. No check digits are used."),
	_0116(null, "0116", "PSS Object Identifiers", "1) As defined in ISO 6523, clause 3.1, 2) Check digits, none"),
	_0117(null, "0117", "STENTOR-ICD CODING SYSTEM", "1) Format includes 2 fields. First field - ICD (4 decimal digital), Second field - Domain Specific Part, 2) None"),
	_0118(null, "0118", "ATM-Network ZN'96", "ICD Format, (4 characters)"),
	_0119(null, "0119", "MCI / OSI Network", "1) Three fields, First Field = ICD, Second Field = Organization Code, 1-14 digits, Third Field = Organization Name, up to 250 digits"),
	_0120(null, "0120", "Advantis", "The format includes three fields: First field: ICD, 4 digits, Second field: Organization code, 1-14 digits, Third field: Organizational name up to 250 digits"),
	_0121(null, "0121", "Affable Software Data Interchange Codes", "1) format: XXXX/AFC, example: 0000/AFC, 2) none"),
	_0122(null, "0122", "BB-DATA GmbH", "cccc(ICD) Display Requirements : None Character Repertoire :"),
	_0123(null, "0123", "BASF Company ATM-Network", "1) ICD (International Code Designator), 2) Organization code, comprising 4 fields with a total of, 10 characters"),
	_0124(null, "0124", "IOTA Identifiers for Organizations for Telecommunications Addressing using the ICD system format defined in ISO/IEC 8348", null),
	_0125(null, "0125", "Henkel Corporate Network (H-Net)", "1) ICD (4 characters), 2) Organization code, comprising 4 fields with a total of 10 characters, No check digits are used in the code"),
	_0126(null, "0126", "GTE/OSI Network", "1) ICD, 2) Organization Code, 1-14 characters, 3) Organization name, up to 250 characters"),
	_0127(null, "0127", "Dresdner Bank Corporate Network", "ICD (4 characters)"),
	_0128(null, "0128", "BCNR (Swiss Clearing Bank Number)", "1) n..6, 2) Minimum of 4 numeric characters"),
	_0129(null, "0129", "BPI (Swiss Business Partner Identification) code", "1) an..6, 2) None"),
	_0130(null, "0130", "Directorates of the European Commission", "1) ICD 4 digits, 2) None"),
	_0131(null, "0131", "Code for the Identification of National Organizations", "1) ICD (International Code Designator), 2) Organization Code, comprising 2 fields with a total of 9 characters. 8 number or character body code and 1 number or character check code."),
	_0132(null, "0132", "Certicom Object Identifiers", "Two fields : First field - ICD, Second field - Sequence of digits"),
	_0133(null, "0133", "TC68 OID", "1) Three fields, First field = ICD, Second field = Member Country Code, 1-14 characters, Third field = Number of Standard"),
	_0134(null, "0134", "Infonet Services Corporation", "1) ICD Code- 4 digits, Organization code - up to 14 characters, Organization name - up to 250 characters, 2) No check digits needed"),
	_0135("IT:SIA", "0135", "SIA Object Identifiers", "First field: ICD: 4 digits, Second field: sequence of digits"),
	_0136(null, "0136", "Cable & Wireless Global ATM End-System Address Plan", "1) ICD 4 digits, 2)"),
	_0137(null, "0137", "Global AESA scheme", "1) Field, 1 Authority and Format Identifier, 2 ICD, 3 Higher Order Domain Specific Part Assigned by Global One"),
	_0138(null, "0138", "France Telecom ATM End System Address Plan", "Field, #bytes, Name; 1, 1, Authority and Format Identifier (0x47); 2, 2, ICD; 3, 10, Higher Order Domain Specific Part (administered by France Telecom; 4, 6, End System Identifier (End user field); 5, 1, Selector (End user field)"),
	_0139(null, "0139", "Savvis Communications AESA:.", "First Field = ICD (0x47), Second Field = Domain Specific Part (0x124), Third Field = Organisation ID (3 bytes), Fourth Field = Domain Specific Part (7 bytes), The Domain Specific will be used to for assigning ATM, Addresses according to ATM Forum UNI3.1/4.0 and PNNI 1.0, Specifications"),
	_0140(null, "0140", "Toshiba Organizations, Partners, And Suppliers' (TOPAS) Code", "1. ICD 4 digits, Organization Identifier, Organization Part Identifier, 4. OPIS -----1"),
	_0141(null, "0141", "NATO Commercial and Government Entity system", "1) This code consists of: Three alpha and/or numeric characters prefixed and suffixed by a numeral, for Canada and the United States, or: Three alpha and/or numeric characters either prefixed by one significant alpha character and suffixed by one numeral or suffixed by one significant alpha character and prefixed by one numeral for the other user countries/organizations. 2) None"),
	_0142("IT:SECETI", "0142", "SECETI Object Identifiers", "1) First field: ICD: 4 digits, Second field: sequence of digits"),
	_0143(null, "0143", "EINESTEINet AG", "2 digit authority and format identifier X'47', 2 digit authority and format identifier, 4 digit international code designator (ICD), 20 digit domain definition based upon geographic location, No check characters"),
	_0144(null, "0144", "DoDAAC (Department of Defense Activity Address Code)", "1) 6 alphanumeric character string. No significance is applied to any character in the string, 2) None"),
	_0145(null, "0145", "DGCP (Direction Générale de la Comptabilité Publique)administrative accounting identification scheme", "1) 10 characters, first 4 characters are 'DCGP' following by 6 digits to identify an administrative accounting unit, 2) None"),
	_0146(null, "0146", "DGI (Direction Générale des Impots) code", "Various structures, 1) Dependant on structure, 2) None"),
	_0147(null, "0147", "Standard Company Code", "1) 12 characters (fixed length), First 6 characters identify an organization, Last 6 characters identify an organization part, 2) None"),
	_0148(null, "0148", "ITU (International Telecommunications Union)Data Network Identification Codes (DNIC)", "1) 4 numeric digits, First three digits represent the country, Fourth digit represents the actual data network within the Country (for countries with many public networks multiple country codes exist). Up to 10 additional characters can be appended by the individual data networks to specify a network address within their network. 2)"),
	_0149(null, "0149", "Global Business Identifier", "9999-9999-9999, 1) 12 Characters; no significance, 2) There are no check characters"),
	_0150(null, "0150", "Madge Networks Ltd- ICD ATM Addressing Scheme", "40 digit ATM NSAP address, 1) Field Digits Purpose, 1 2 AFI (= 47), 2 4 ICD, 3 20"),
	_0151(null, "0151", "Australian Business Number (ABN) Scheme", null),
	_0152(null, "0152", "Edira Scheme Identifier Code", "99999; greater than 10000, 5 characters; no significance, no check characters"),
	_0153(null, "0153", "Concert Global Network Services ICD AESA", "Field, Name; 1, Authority and Format Identifier; 2, ICD; 3, Higher order domain specific part as assigned by Concert. This structure conforms to the ICD AESA specified in the User- Network (UNI) specification Version 3.1 and ATM User Network (UNI) Signalling Specification 4.0. It also conforms with PNNI 1.0 standard. All"),
	_0154(null, "0154", "Identification number of economic subjects: (ICO)", "Form of representation: nnnnnnn.n, nnnnnnn - serial number, - - - - - - - n code"),
	_0155(null, "0155", "Global Crossing AESA (ATM End System Address)", "1) AFI -Authority and Format Identifier, 2) ICD - International Code Designator, 3) HODSP - Higher Order Domain Specific Part. Structure conforms to ATM Forum UNI Signalling Specifications 3.1/4.0"),
	_0156(null, "0156", "AUNA", "1) CCCC (ICD), 2) Organization Code"),
	_0157(null, "0157", "ATM interconnection with the Dutch KPN Telecom", "1) ICD Code- 4 digits, 2) None"),
	_0158(null, "0158", "Identification number of economic subject (ICO) Act on State Statistics of 29 November 2'001, § 27", "1) 8 characters (fixed length), 2) Check character: 8th digit"),
	_0159(null, "0159", "ACTALIS Object Identifiers", "First field: ICD: 4 digits, Second field: sequence of digits"),
	_0160(null, "0160", "GTIN - Global Trade Item Number", "THE GTIN has four different formats of respectively 8, 12, 13 and 14 digits. When stored in a computer file, right justified with leading zeroes, GTIN's are unique against each other, Up to 14 digits, Last digit = modulo 10 check digit"),
	_0161(null, "0161", "ECCMA Open Technical Directory", "9.999999"),
	_0162(null, "0162", "CEN/ISSS Object Identifier Scheme", "First field: ICD: 4 digitsSecond field: sequence of digits"),
	_0163(null, "0163", "US-EPA Facility Identifier", "Alphanumeric (12)"),
	_0164(null, "0164", "TELUS Corporation", "All 10 characters of HODSP required"),
	_0165(null, "0165", "FIEIE Object identifiers", "The identifier consists of a sequence of digits"),
	_0166(null, "0166", "Swissguide Identifier Scheme", "999999"),
	_0167(null, "0167", "Priority Telecom ATM End System Address Plan", "Field 1 = AFI = 47 (1 byte)"),
	_0168(null, "0168", "Vodafone Ireland OSI Addressing", "1) AFI-Authority and Format Identifier"),
	_0169(null, "0169", "Swiss Federal Business Identification Number. Central Business names Index (zefix) Identification Number", "CH-RRR.X.XXX.XXX-P"),
	_0170(null, "0170", "Teikoku Company Code", "1) Eight identification digits and a check digit"),
	_0171(null, "0171", "Luxembourg CP & CPS (Certification Policy and Certification Practice Statement) Index", "xxx.yyy.zzz.nnnn"),
	_0172(null, "0172", "Project Group “Lists of Properties” (PROLIST®)", "1. ICD 4 digits, 2. Organisation Identifier. No check character required. 3. Organisation Part Identifier. 4. OPIS"),
	_0173(null, "0173", "eCI@ss", "1) ICD 4 digits, 2) Organization identifier, 3) Organization Part identifier, 4) OPISNo check character required"),
	_0174(null, "0174", "StepNexus", null),
	_0175(null, "0175", "Siemens AG", "1. ICD 4 digits, 2. Organisation Identifier, 3. Organisation Part Identifier, 4. OPISNo check character required"),
	_0176(null, "0176", "Paradine GmbH", "1. ICD 4 digits, 2. Organisation Identifier, 3. Organisation Part Identifier, 4. OPISNo check character required"),
	_0178(null, "0178", "Route1 MobiNET", "6 Fields:"),
	_0179(null, "0179", "Penango Object Identifiers", "The OID structure and the inclusion therein of the ICD is as follows: Level 1: iso(1) Level 2: identified-organization (3) Level 3: Penango(xxxx) Level 4 and higher: Defined by Penango"),
	_0180(null, "0180", "Lithuanian military PKI", "3 fields: 1) PKI code; 2) CP/CPS code; 3) doc-code"),
	_0183(null, "0183", "Numéro d'identification suisse des enterprises (IDE), Swiss Unique Business Identification Number (UIDB)", "CHEXXXXXXXXP, UID number, is composed by 9 digits and is random generated and has no internal means. 1) 12 characters CHE: Swiss Country Code following ISO 3166-1. XXXXXXXX: 8 digits for the number itselfP: check digit 2) CHEXXXXXXXXP, the last digit"),
	_0184("DK:DIGST", "0184", "DIGSTORG", "8 or 10 digits"),
	_0185(null, "0185", "Perceval Object Code", "The code is primarily intended for the registration of object identifiers in the International Object Identifier tree in accordance with ISO/IEC 8824 under the top arcs iso(1) identified- organization(3) perceval(International Code Designator value). The lower levels are defined by Perceval. Variable length encoding using dotted notation. No check characters."),
	_0186(null, "0186", "TrustPoint Object Identifiers", "1) ICD, 2) Object class, 3) Object number(s) Number of characters and their significance: Object class 1 or 2 digits, Object number(s) multiple levels of 1 or more digits"),
	_0187(null, "0187", "Amazon Unique Identification Scheme", "Each identifier may have a textual description assigned to describe the identifier. The identifier shall not begin with a zero nor shall the character immediately after a full stop character be a zero unless the zero is the last character in the identifier. 1) Between one and 35 characters each of which is a digit (0 to 9) or a full stop character (.) 2) There is no check character."),
	_0188(null, "0188", "Corporate Number of The Social Security and Tax Number System", "12-digit fundamental numbers, and a one-digit check numeral put ahead of them. 1) Figure of 13 digits. 2) Figures from 1 to 9 (Formula to calculate the test number) Formula 9- ((n = 1 (Sigma)12( Pn * Qn )) remainder obtained by dividing the 9) Pn : the numeral of the n-th digit of a fundamental number, when counted from the bottom digit. Qn : one when the 'n' is an odd number, two when the 'n' is an even one"),
	_0189(null, "0189", "European Business Identifier (EBID)", "XXXXXXXXXXXXC 1) XXXXXXXXXXXX: Twelve identification digits C: Check digit 2) 13th digit"),
	_0190("NL:OINO", "0190", "Organisatie Indentificatie Nummer (OIN)", null),
	_0191("EE:CC", "0191", "Company Code (Estonia)", "Always 8-digit number"),
	_0192("NO:ORG", "0192", "Organisasjonsnummer", "9 digits, The organization number consists of 9 digits where the last digit is a control digit calculated with standard weights, modulus 11. After this, weights 3, 2, 7, 6, 5, 4, 3 and 2 are calculated from the first digit."),
	_0193("UBLBE", "0193", "UBL.BE Party Identifier", "Maximum 50 characters, 4 Characters fixed length identifying the type , Maximum 46 characters for the identifier itself"),
	_0194(null, "0194", "KOIOS Open Technical Dictionary", null),
	_0195(null, "0195", "Singapore Nationwide E-lnvoice Framework", null),
	_0196(null, "0196", "Icelandic identifier - Íslensk kennitala", null),
	_0197(null, "0197", "APPLiA Pl Standard", null),
	_0198(null, "0198", "ERSTORG", null),
	_0199(null, "0199", "Legal Entity Identifier (LEI)", null),
	_0200(null, "0200", "Legal entity code (Lithuania)", null),
	_0201(null, "0201", "Codice Univoco Unità Organizzativa iPA", null),
	_0202(null, "0202", "Indirizzo di Posta Elettronica Certificata", null),
	_0203(null, "0203", "eDelivery Network Participant identifier", null),
	_0204(null, "0204", "Leitweg-ID", null),
	_0205(null, "0205", "CODDEST", null),
	_0206(null, "0206", "Registre du Commerce et de l’Industrie : RCI", null),
	_0207(null, "0207", "PiLog Ontology Codification Identifier (POCI)", null),
	_0208(null, "0208", "Numero d'entreprise / ondernemingsnummer / Unternehmensnummer", null),
	_0209(null, "0209", "GS1 identification keys", null),
	_0210(null, "0210", "CODICE FISCALE", null),
	_0211(null, "0211", "PARTITA IVA", null),
	_0212(null, "0212", "Finnish Organization Identifier", null),
	_0213(null, "0213", "Finnish Organization Value Add Tax Identifier", null),
	_0214(null, "0214", "Tradeplace TradePI Standard", null),
	_0215(null, "0215", "Net service ID", null),
	_0216(null, "0216", "OVTcode", null),
	_0217(null, "0217", "The Netherlands Chamber of Commerce and Industry establishment number", null),
	_0218(null, "0218", "Unified registration number (Latvia)", null),
	_0219(null, "0219", "Taxpayer registration code (Latvia)", null),
	_0220(null, "0220", "The Register of Natural Persons (Latvia)", null),
	_0221(null, "0221", "The registered number of the qualified invoice issuer", null),
	_0222(null, "0222", "Metadata Registry Support", null),
	_0223(null, "0223", "EU based company", "EU Based Company Intracommunity VAT ID up to 18 characters maximum, used in order to identify EU based company in e-invoices"),
	_0224(null, "0224", "FTCTC CODE ROUTAGE", "The identifier is alphanumeric with 50 characters maximumA-Z, a-z, 0-9 and special characters '-', '_', '/', '@'"),
	_0225(null, "0225", "FRCTC ELECTRONIC ADDRESS", "The identifier is alphanumeric with 50 characters maximumA-Z, a-z, 0-9 and special characters '-', '_', '/', '@'"),
	_0226(null, "0226", "FRCTC Particulier", "The identifier is alphanumeric with 80 characters maximum- 10 digits (the NIR) composed with- 1 digit = 1 or 2- 2 digits (0-9)- a 2-digit number between 01 and 12- 5 digits (0-9)- 70 characters maximum with + the first 35 characters of the last name + the first 35 characters of the first name (special characters allowed: '-', ''', ',', '.', '&')"),
	_0227(null, "0227", "NON - EU based company", "Non EU Based Company up to 18 characters maximum, used in order to identify a non EU based company in e-invoicesThis identifier is alphanumeric and composed with 18 characters maximum :- It starts with 2 characters which must be the Country code of country where the company is registered- Followed with the first 16 Characters of the company registered name (special characters allowed: '-', ''', ',', '.', '&')"),
	_0228(null, "0228", "Répertoire des Entreprises et des Etablissements (RIDET)", "This identifier is numeric, with 10 digits- 7 digits for the RID (company ID number)- 3 digits for establishment level"),
	_0229(null, "0229", "T.A.H.I.T.I (traitement automatique hiérarchisé des institutions de Tahiti et des îles)", "This identifier is alpha numeric with 9 charactersA-Z and 0-9 for 6 characters completed with 3 digits 0-9 (special characters allowed: '-', ''', ',', '.', '&')"),
	_0230(null, "0230", "National e-Invoicing Framework", "Identifier for  organizations. Issuing agency: Malaysia Digital Economy Corporation Sdn Bhd (MDEC)"),
}