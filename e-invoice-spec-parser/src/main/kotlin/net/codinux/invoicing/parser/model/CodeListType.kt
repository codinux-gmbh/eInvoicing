package net.codinux.invoicing.parser.model

enum class CodeListType(val className: String, val usesFullList: Boolean, val usedInFields: List<String>) {
    IsoCountryCodes("Country", true, listOf("BT-40", "BT-48", "BT-55", "BT-63", "BT-69", "BT-80", "BT-159")), // actually it's not only the full list, it's "extended"
    IsoCurrencyCodes("Currency", true, listOf("BT-5", "BT-6")),
    Iso_6523_IdentificationSchemeIdentifier("SchemeIdentifier", true, listOf("BT-29-1", "BT-30-1", "BT-46-1", "BT-47-1", "BT-60-1", "BT-61-1", "BT-71-1", "BT-157-1")), // = ICD

    UN_1001_InvoiceType("InvoiceType", false, listOf("BT-3")), // original name: Document type, but in specification mostly called Invoice type
    UN_1153_ReferenceCode("ReferenceType", true, listOf("BT-18-1", "BT-128-1")), // ReferenceCode, ReferenceCodeQualifier, ReferenceTypeCode, ReferenceCodeType
    UN_2005_2475_EventTimeCode("TimeReferenceCode", false, listOf("BT-8")), // code list only in EN Excel file

    UN_4451_TextSubjectCodeQualifier("InvoiceNoteSubjectCode", true, listOf("BT-21")), // tab Text
    UN_4461_PaymentCodes("PaymentMeansCode", true, listOf("BT-81")), // tab Payment
    UN_5189_AllowanceIdentificationCode("AllowanceReasonCode", false, listOf("BT-98", "BT-140")), // tab Allowance
    UN_5305_DutyOrTaxOrFeeCategory("VatCategoryCode", false, listOf("BT-95", "BT-102", "BT-118", "BT-151")),
    UN_7143_ItemTypeIdentificationCode("ItemTypeIdentificationCode", true, listOf("BT-158-1")), // tab Item
    UN_7161_SpecialServiceDescriptionCodes("ChargeReasonCode", true, listOf("BT-105", "BT-145")), // tab Charge

    Units("UnitOfMeasure", true, listOf("BT-130", "BT-150")), // UN/ECE Recommendation N°20 and UN/ECE Recommendation N°21 — Unit codes

    EAS("ElectronicAddressSchemeIdentifier", true, listOf("BT-34-1", "BT-49-1")),
    VATEX("VatExemptionReasonCode", true, listOf("BT-121")),

    VatIdentifier("VatIdentifier", false, listOf("BT-31", "BT-48", "BT-63")), // code list only in EN Excel file
    VatCategoryCode("VatCategoryCode", false, listOf("BT-95", "BT-102", "BT-118", "BT-151")), // code list only in EN Excel file
    Mime("Mime", false, listOf("BT-125-1")),
}