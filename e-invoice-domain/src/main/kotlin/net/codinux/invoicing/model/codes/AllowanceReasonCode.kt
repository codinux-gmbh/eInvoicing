package net.codinux.invoicing.model.codes

enum class AllowanceReasonCode(val code: String, val meaning: String, val description: String) {
	_41("41", "Bonus for works ahead of schedule", "Bonus for completing work ahead of schedule."),
	_42("42", "Other bonus", "Bonus earned for other reasons."),
	_60("60", "Manufacturer's consumer discount", "A discount given by the manufacturer which should be passed on to the consumer."),
	_62("62", "Due to military status", "Allowance granted because of the military status."),
	_63("63", "Due to work accident", "Allowance granted to a victim of a work accident."),
	_64("64", "Special agreement", "An allowance or charge as specified in a special agreement."),
	_65("65", "Production error discount", "A discount given for the purchase of a product with a production error."),
	_66("66", "New outlet discount", "A discount given at the occasion of the opening of a new outlet."),
	_67("67", "Sample discount", "A discount given for the purchase of a sample of a product."),
	_68("68", "End-of-range discount", "A discount given for the purchase of an end-of-range product."),
	_70("70", "Incoterm discount", "A discount given for a specified Incoterm."),
	_71("71", "Point of sales threshold allowance", "Allowance for reaching or exceeding an agreed sales threshold at the point of sales."),
	_88("88", "Material surcharge/deduction", "Surcharge/deduction, calculated for higher/ lower material's consumption."),
	_95("95", "Discount", "A reduction from a usual or list price."),
	_100("100", "Special rebate", "A return of part of an amount paid for goods or services, serving as a reduction or discount."),
	_102("102", "Fixed long term", "A fixed long term allowance or charge."),
	_103("103", "Temporary", "A temporary allowance or charge."),
	_104("104", "Standard", "The standard available allowance or charge."),
	_105("105", "Yearly turnover", "An allowance or charge based on yearly turnover."),
}