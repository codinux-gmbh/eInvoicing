package net.codinux.invoicing.model.codes

enum class ChargeReasonCode(val code: String, val meaning: String, val description: String) {
	AA("AA", "Advertising", "The service of providing advertising."),
	AAA("AAA", "Telecommunication", "The service of providing telecommunication activities and/or faclities."),
	AAC("AAC", "Technical modification", "The service of making technical modifications to a product."),
	AAD("AAD", "Job-order production", "The service of producing to order."),
	AAE("AAE", "Outlays", "The service of providing money for outlays on behalf of a trading partner."),
	AAF("AAF", "Off-premises", "The service of providing services outside the premises of the provider."),
	AAH("AAH", "Additional processing", "The service of providing additional processing."),
	AAI("AAI", "Attesting", "The service of certifying validity."),
	AAS("AAS", "Acceptance", "The service of accepting goods or services."),
	AAT("AAT", "Rush delivery", "The service to provide a rush delivery."),
	AAV("AAV", "Special construction", "The service of providing special construction."),
	AAY("AAY", "Airport facilities", "The service of providing airport facilities."),
	AAZ("AAZ", "Concession", "The service allowing a party to use another party's facilities."),
	ABA("ABA", "Compulsory storage", "The service provided to hold a compulsory inventory."),
	ABB("ABB", "Fuel removal", "Remove or off-load fuel from vehicle, vessel or craft."),
	ABC("ABC", "Into plane", "Service of delivering goods to an aircraft from local storage."),
	ABD("ABD", "Overtime", "The service of providing labour beyond the established limit of working hours."),
	ABF("ABF", "Tooling", "The service of providing specific tooling."),
	ABK("ABK", "Miscellaneous", "Miscellaneous services."),
	ABL("ABL", "Additional packaging", "The service of providing additional packaging."),
	ABN("ABN", "Dunnage", "The service of providing additional padding materials required to secure and protect a cargo within a shipping container."),
	ABR("ABR", "Containerisation", "The service of packing items into a container."),
	ABS("ABS", "Carton packing", "The service of packing items into a carton."),
	ABT("ABT", "Hessian wrapped", "The service of hessian wrapping."),
	ABU("ABU", "Polyethylene wrap packing", "The service of packing in polyethylene wrapping."),
	ACF("ACF", "Miscellaneous treatment", "Miscellaneous treatment service."),
	ACG("ACG", "Enamelling treatment", "The service of providing enamelling treatment."),
	ACH("ACH", "Heat treatment", "The service of treating with heat."),
	ACI("ACI", "Plating treatment", "The service of providing plating treatment."),
	ACJ("ACJ", "Painting", "The service of painting."),
	ACK("ACK", "Polishing", "The service of polishing."),
	ACL("ACL", "Priming", "The service of priming."),
	ACM("ACM", "Preservation treatment", "The service of preservation treatment."),
	ACS("ACS", "Fitting", "Fitting service."),
	ADC("ADC", "Consolidation", "The service of consolidating multiple consignments into one shipment."),
	ADE("ADE", "Bill of lading", "The service of providing a bill of lading document."),
	ADJ("ADJ", "Airbag", "The service of surrounding a product with an air bag."),
	ADK("ADK", "Transfer", "The service of transferring."),
	ADL("ADL", "Slipsheet", "The service of securing a stack of products on a slipsheet."),
	ADM("ADM", "Binding", "Binding service."),
	ADN("ADN", "Repair or replacement of broken returnable package", "The service of repairing or replacing a broken returnable package."),
	ADO("ADO", "Efficient logistics", "A code indicating efficient logistics services."),
	ADP("ADP", "Merchandising", "A code indicating that merchandising services are in operation."),
	ADQ("ADQ", "Product mix", "A code indicating that product mixing services are in operation."),
	ADR("ADR", "Other services", "A code indicating that other non-specific services are in operation."),
	ADT("ADT", "Pick-up", "The service of picking up or collection of goods."),
	ADW("ADW", "Chronic illness", "The special services provided due to chronic illness."),
	ADY("ADY", "New product introduction", "A service provided by a buyer when introducing a new product from a suppliers range to the range traded by the buyer."),
	ADZ("ADZ", "Direct delivery", "Direct delivery service."),
	AEA("AEA", "Diversion", "The service of diverting deliverables."),
	AEB("AEB", "Disconnect", "The service is a disconnection."),
	AEC("AEC", "Distribution", "Distribution service."),
	AED("AED", "Handling of hazardous cargo", "A service for handling hazardous cargo."),
	AEF("AEF", "Rents and leases", "The service of renting and/or leasing."),
	AEH("AEH", "Location differential", "Delivery to a different location than previously contracted."),
	AEI("AEI", "Aircraft refueling", "Fuel being put into the aircraft."),
	AEJ("AEJ", "Fuel shipped into storage", "Fuel being shipped into a storage system."),
	AEK("AEK", "Cash on delivery", "The provision of a cash on delivery (COD) service."),
	AEL("AEL", "Small order processing service", "A service related to the processing of small orders."),
	AEM("AEM", "Clerical or administrative services", "The provision of clerical or administrative services."),
	AEN("AEN", "Guarantee", "The service of providing a guarantee."),
	AEO("AEO", "Collection and recycling", "The service of collection and recycling products."),
	AEP("AEP", "Copyright fee collection", "The service of collecting copyright fees."),
	AES("AES", "Veterinary inspection service", "The service of providing veterinary inspection."),
	AET("AET", "Pensioner service", "Special service when the subject is a pensioner."),
	AEU("AEU", "Medicine free pass holder", "Special service when the subject holds a medicine free pass."),
	AEV("AEV", "Environmental protection service", "The provision of an environmental protection service."),
	AEW("AEW", "Environmental clean-up service", "The provision of an environmental clean-up service."),
	AEX("AEX", "National cheque processing service outside account area", "Service of processing a national cheque outside the ordering customer's bank trading area."),
	AEY("AEY", "National payment service outside account area", "Service of processing a national payment to a beneficiary holding an account outside the trading area of the ordering customer's bank."),
	AEZ("AEZ", "National payment service within account area", "Service of processing a national payment to a beneficiary holding an account within the trading area of the ordering customer's bank."),
	AJ("AJ", "Adjustments", "The service of making adjustments."),
	AU("AU", "Authentication", "The service of authenticating."),
	CA("CA", "Cataloguing", "The provision of cataloguing services."),
	CAB("CAB", "Cartage", "Movement of goods by heavy duty cart or vehicle."),
	CAD("CAD", "Certification", "The service of certifying."),
	CAE("CAE", "Certificate of conformance", "The service of providing a certificate of conformance."),
	CAF("CAF", "Certificate of origin", "The service of providing a certificate of origin."),
	CAI("CAI", "Cutting", "The service of cutting."),
	CAJ("CAJ", "Consular service", "The service provided by consulates."),
	CAK("CAK", "Customer collection", "The service of collecting goods by the customer."),
	CAL("CAL", "Payroll payment service", "Provision of a payroll payment service."),
	CAM("CAM", "Cash transportation", "Provision of a cash transportation service."),
	CAN("CAN", "Home banking service", "Provision of a home banking service."),
	CAO("CAO", "Bilateral agreement service", "Provision of a service as specified in a bilateral special agreement."),
	CAP("CAP", "Insurance brokerage service", "Provision of an insurance brokerage service."),
	CAQ("CAQ", "Cheque generation", "Provision of a cheque generation service."),
	CAR("CAR", "Preferential merchandising location", "Service of assigning a preferential location for merchandising."),
	CAS("CAS", "Crane", "The service of providing a crane."),
	CAT("CAT", "Special colour service", "Providing a colour which is different from the default colour."),
	CAU("CAU", "Sorting", "The provision of sorting services."),
	CAV("CAV", "Battery collection and recycling", "The service of collecting and recycling batteries."),
	CAW("CAW", "Product take back fee", "The fee the consumer must pay the manufacturer to take back the product."),
	CAX("CAX", "Quality control released", "Informs the stockholder it is free to distribute the quality controlled passed goods."),
	CAY("CAY", "Quality control held", "Instructs the stockholder to withhold distribution of the goods until the manufacturer has completed a quality control assessment."),
	CAZ("CAZ", "Quality control embargo", "Instructs the stockholder to withhold distribution of goods which have failed quality control tests."),
	CD("CD", "Car loading", "Car loading service."),
	CG("CG", "Cleaning", "Cleaning service."),
	CS("CS", "Cigarette stamping", "The service of providing cigarette stamping."),
	CT("CT", "Count and recount", "The service of doing a count and recount."),
	DAB("DAB", "Layout/design", "The service of providing layout/design."),
	DAC("DAC", "Assortment allowance", "Allowance given when a specific part of a suppliers assortment is purchased by the buyer."),
	DAD("DAD", "Driver assigned unloading", "The service of unloading by the driver."),
	DAF("DAF", "Debtor bound", "A special allowance or charge applicable to a specific debtor."),
	DAG("DAG", "Dealer allowance", "An allowance offered by a party dealing a certain brand or brands of products."),
	DAH("DAH", "Allowance transferable to the consumer", "An allowance given by the manufacturer which should be transfered to the consumer."),
	DAI("DAI", "Growth of business", "An allowance or charge related to the growth of business over a pre-determined period of time."),
	DAJ("DAJ", "Introduction allowance", "An allowance related to the introduction of a new product to the range of products traded by a retailer."),
	DAK("DAK", "Multi-buy promotion", "A code indicating special conditions related to a multi- buy promotion."),
	DAL("DAL", "Partnership", "An allowance or charge related to the establishment and on-going maintenance of a partnership."),
	DAM("DAM", "Return handling", "An allowance or change related to the handling of returns."),
	DAN("DAN", "Minimum order not fulfilled charge", "Charge levied because the minimum order quantity could not be fulfilled."),
	DAO("DAO", "Point of sales threshold allowance", "Allowance for reaching or exceeding an agreed sales threshold at the point of sales."),
	DAP("DAP", "Wholesaling discount", "A special discount related to the purchase of products through a wholesaler."),
	DAQ("DAQ", "Documentary credits transfer commission", "Fee for the transfer of transferable documentary credits."),
	DL("DL", "Delivery", "The service of providing delivery."),
	EG("EG", "Engraving", "The service of providing engraving."),
	EP("EP", "Expediting", "The service of expediting."),
	ER("ER", "Exchange rate guarantee", "The service of guaranteeing exchange rate."),
	FAA("FAA", "Fabrication", "The service of providing fabrication."),
	FAB("FAB", "Freight equalization", "The service of load balancing."),
	FAC("FAC", "Freight extraordinary handling", "The service of providing freight's extraordinary handling."),
	FC("FC", "Freight service", "The service of moving goods, by whatever means, from one place to another."),
	FH("FH", "Filling/handling", "The service of providing filling/handling."),
	FI("FI", "Financing", "The service of providing financing."),
	GAA("GAA", "Grinding", "The service of grinding."),
	HAA("HAA", "Hose", "The service of providing a hose."),
	HD("HD", "Handling", "Handling service."),
	HH("HH", "Hoisting and hauling", "The service of hoisting and hauling."),
	IAA("IAA", "Installation", "The service of installing."),
	IAB("IAB", "Installation and warranty", "The service of installing and providing warranty."),
	ID("ID", "Inside delivery", "The service of providing delivery inside."),
	IF("IF", "Inspection", "The service of inspection."),
	IR("IR", "Installation and training", "The service of providing installation and training."),
	IS("IS", "Invoicing", "The service of providing an invoice."),
	KO("KO", "Koshering", "The service of preparing food in accordance with Jewish law."),
	L1("L1", "Carrier count", "The service of counting by the carrier."),
	LA("LA", "Labelling", "Labelling service."),
	LAA("LAA", "Labour", "The service to provide required labour."),
	LAB("LAB", "Repair and return", "The service of repairing and returning."),
	LF("LF", "Legalisation", "The service of legalising."),
	MAE("MAE", "Mounting", "The service of mounting."),
	MI("MI", "Mail invoice", "The service of mailing an invoice."),
	ML("ML", "Mail invoice to each location", "The service of mailing an invoice to each location."),
	NAA("NAA", "Non-returnable containers", "The service of providing non-returnable containers."),
	OA("OA", "Outside cable connectors", "The service of providing outside cable connectors."),
	PA("PA", "Invoice with shipment", "The service of including the invoice with the shipment."),
	PAA("PAA", "Phosphatizing (steel treatment)", "The service of phosphatizing the steel."),
	PC("PC", "Packing", "The service of packing."),
	PL("PL", "Palletizing", "The service of palletizing."),
	PRV("PRV", "Price variation", "Price variation related to energy and or raw materials cost variation."),
	RAB("RAB", "Repacking", "The service of repacking."),
	RAC("RAC", "Repair", "The service of repairing."),
	RAD("RAD", "Returnable container", "The service of providing returnable containers."),
	RAF("RAF", "Restocking", "The service of restocking."),
	RE("RE", "Re-delivery", "The service of re-delivering."),
	RF("RF", "Refurbishing", "The service of refurbishing."),
	RH("RH", "Rail wagon hire", "The service of providing rail wagons for hire."),
	RV("RV", "Loading", "The service of loading goods."),
	SA("SA", "Salvaging", "The service of salvaging."),
	SAA("SAA", "Shipping and handling", "The service of shipping and handling."),
	SAD("SAD", "Special packaging", "The service of special packaging."),
	SAE("SAE", "Stamping", "The service of stamping."),
	SAI("SAI", "Consignee unload", "The service of unloading by the consignee."),
	SG("SG", "Shrink-wrap", "The service of shrink-wrapping."),
	SH("SH", "Special handling", "The service of special handling."),
	SM("SM", "Special finish", "The service of providing a special finish."),
	SU("SU", "Set-up", "The service of setting-up."),
	TAB("TAB", "Tank renting", "The service of providing tanks for hire."),
	TAC("TAC", "Testing", "The service of testing."),
	TT("TT", "Transportation - third party billing", "The service of providing third party billing for transportation."),
	TV("TV", "Transportation by vendor", "The service of providing transportation by the vendor."),
	V1("V1", "Drop yard", "The service of delivering goods at the yard."),
	V2("V2", "Drop dock", "The service of delivering goods at the dock."),
	WH("WH", "Warehousing", "The service of storing and handling of goods in a warehouse."),
	XAA("XAA", "Combine all same day shipment", "The service of combining all shipments for the same day."),
	YY("YY", "Split pick-up", "The service of providing split pick-up."),
	ZZZ("ZZZ", "Mutually defined", "A code assigned within a code list to be used on an interim basis and as defined among trading partners until a precise code can be assigned to the code list."),
}