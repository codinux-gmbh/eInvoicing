package net.codinux.invoicing.model.codes

enum class Mime(val code: String) {
	PDF("application/pdf"),
	PNG("image/png"),
	JPEG("image/jpeg"),
	CSV("text/csv"),
	ExcelSpreadsheet("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	OpenDocumentSpreadsheet("application/vnd.oasis.opendocument.spreadsheet"),
}