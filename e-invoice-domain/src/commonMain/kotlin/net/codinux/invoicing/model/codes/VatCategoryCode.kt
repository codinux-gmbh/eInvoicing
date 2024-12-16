package net.codinux.invoicing.model.codes

enum class VatCategoryCode(val code: String, val meaning: String, val optionalRemarkForTheUsageOfTheCode: String) {
	S("S", "Standard rate", "Standard rate"),
	Z("Z", "Zero rated goods", "Zero rated goods"),
	E("E", "Exempt from tax", "Exempt from tax"),
	AE("AE", "VAT Reverse charge", "VAT reverse charge"),
	K("K", "VAT exempt for EEA intra-community supply of goods and services", "VAT exempt for intra community supply of goods"),
	G("G", "Free export item, tax not charged", "Free export item, tax not charged"),
	O("O", "Service outside scope of tax", "Services outside scope of tax"),
	L("L", "Canary Islands general indirect tax", "Canary Islands General Indirect Tax"),
	M("M", "Tax for production, services and importation in Ceuta and Melilla", "Liable for IPSI"),
}