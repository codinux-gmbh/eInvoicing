package net.codinux.invoicing.model.codes

enum class Mime(val code: String) {
	application_pdf("application/pdf"),
	image_png("image/png"),
	image_jpeg("image/jpeg"),
	text_csv("text/csv"),
	application_vnd_openxmlformats_officedocument_spreadsheetml_sheet("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	application_vnd_oasis_opendocument_spreadsheet("application/vnd.oasis.opendocument.spreadsheet"),
}