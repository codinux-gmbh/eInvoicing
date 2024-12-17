package net.codinux.invoicing.model.codes

enum class PaymentMeansCode(val code: String, val meaning: String, val description: String, val isFrequentlyUsedValue: Boolean) {
	_1("1", "Instrument not defined", "Not defined legally enforceable agreement between two or more parties (expressing a contractual right or a right to the payment of money).", false),
	_2("2", "Automated clearing house credit", "A credit transaction made through the automated clearing house system.", false),
	_3("3", "Automated clearing house debit", "A debit transaction made through the automated clearing house system.", false),
	_4("4", "ACH demand debit reversal", "A request to reverse an ACH debit transaction to a demand deposit account.", false),
	_5("5", "ACH demand credit reversal", "A request to reverse a credit transaction to a demand deposit account.", false),
	_6("6", "ACH demand credit", "A credit transaction made through the ACH system to a demand deposit account.", false),
	_7("7", "ACH demand debit", "A debit transaction made through the ACH system to a demand deposit account.", false),
	_8("8", "Hold", "Indicates that the bank should hold the payment for collection by the beneficiary or other instructions.", false),
	_9("9", "National or regional clearing", "Indicates that the payment should be made using the national or regional clearing.", false),
	_10("10", "In cash", "Payment by currency (including bills and coins) in circulation, including checking account deposits.", true),
	_11("11", "ACH savings credit reversal", "A request to reverse an ACH credit transaction to a savings account.", false),
	_12("12", "ACH savings debit reversal", "A request to reverse an ACH debit transaction to a savings account.", false),
	_13("13", "ACH savings credit", "A credit transaction made through the ACH system to a savings account.", false),
	_14("14", "ACH savings debit", "A debit transaction made through the ACH system to a savings account.", false),
	_15("15", "Bookentry credit", "A credit entry between two accounts at the same bank branch. Synonym: house credit.", false),
	_16("16", "Bookentry debit", "A debit entry between two accounts at the same bank branch. Synonym: house debit.", false),
	_17("17", "ACH demand cash concentration/disbursement (CCD) credit", "A credit transaction made through the ACH system to a demand deposit account using the CCD payment format.", false),
	_18("18", "ACH demand cash concentration/disbursement (CCD) debit", "A debit transaction made through the ACH system to a demand deposit account using the CCD payment format.", false),
	_19("19", "ACH demand corporate trade payment (CTP) credit", "A credit transaction made through the ACH system to a demand deposit account using the CTP payment format.", false),
	_20("20", "Cheque", "Payment by a pre-printed form on which instructions are given to an account holder (a bank or building society) to pay a stated sum to a named recipient.", true),
	_21("21", "Banker's draft", "Issue of a banker's draft in payment of the funds.", false),
	_22("22", "Certified banker's draft", "Cheque drawn by a bank on itself or its agent. A person who owes money to another buys the draft from a bank for cash and hands it to the creditor who need have no fear that it might be dishonoured.", false),
	_23("23", "Bank cheque (issued by a banking or similar establishment)", "Payment by a pre-printed form, which has been completed by a financial institution, on which instructions are given to an account holder (a bank or building society) to pay a stated sum to a named recipient.", false),
	_24("24", "Bill of exchange awaiting acceptance", "Bill drawn by the creditor on the debtor but not yet accepted by the debtor.", false),
	_25("25", "Certified cheque", "Payment by a pre-printed form stamped with the paying bank's certification on which instructions are given to an account holder (a bank or building society) to pay a stated sum to a named recipient .", false),
	_26("26", "Local cheque", "Indicates that the cheque is given local to the recipient.", false),
	_27("27", "ACH demand corporate trade payment (CTP) debit", "A debit transaction made through the ACH system to a demand deposit account using the CTP payment format.", false),
	_28("28", "ACH demand corporate trade exchange (CTX) credit", "A credit transaction made through the ACH system to a demand deposit account using the CTX payment format.", false),
	_29("29", "ACH demand corporate trade exchange (CTX) debit", "A debit transaction made through the ACH system to a demand account using the CTX payment format.", false),
	_30("30", "Credit transfer", "Payment by credit movement of funds from one account to another.", true),
	_31("31", "Debit transfer", "Payment by debit movement of funds from one account to another.", false),
	_32("32", "ACH demand cash concentration/disbursement plus (CCD+)", "credit A credit transaction made through the ACH system to a demand deposit account using the CCD+ payment format.", false),
	_33("33", "ACH demand cash concentration/disbursement plus (CCD+)", "debit A debit transaction made through the ACH system to a demand deposit account using the CCD+ payment format.", false),
	_34("34", "ACH prearranged payment and deposit (PPD)", "A consumer credit transaction made through the ACH system to a demand deposit or savings account.", false),
	_35("35", "ACH savings cash concentration/disbursement (CCD) credit", "A credit transaction made through the ACH system to a demand deposit or savings account.", false),
	_36("36", "ACH savings cash concentration/disbursement (CCD) debit", "A debit transaction made through the ACH system to a savings account using the CCD payment format.", false),
	_37("37", "ACH savings corporate trade payment (CTP) credit", "A credit transaction made through the ACH system to a savings account using the CTP payment format.", false),
	_38("38", "ACH savings corporate trade payment (CTP) debit", "A debit transaction made through the ACH system to a savings account using the CTP payment format.", false),
	_39("39", "ACH savings corporate trade exchange (CTX) credit", "A credit transaction made through the ACH system to a savings account using the CTX payment format.", false),
	_40("40", "ACH savings corporate trade exchange (CTX) debit", "A debit transaction made through the ACH system to a savings account using the CTX payment format.", false),
	_41("41", "ACH savings cash concentration/disbursement plus (CCD+)", "credit A credit transaction made through the ACH system to a savings account using the CCD+ payment format.", false),
	_42("42", "Payment to bank account", "Payment by an arrangement for settling debts that is operated by the Post Office.", true),
	_43("43", "ACH savings cash concentration/disbursement plus (CCD+)", "debit A debit transaction made through the ACH system to a savings account using the CCD+ payment format.", false),
	_44("44", "Accepted bill of exchange", "Bill drawn by the creditor on the debtor and accepted by the debtor.", false),
	_45("45", "Referenced home-banking credit transfer", "A referenced credit transfer initiated through home- banking.", false),
	_46("46", "Interbank debit transfer", "A debit transfer via interbank means.", false),
	_47("47", "Home-banking debit transfer", "A debit transfer initiated through home-banking.", false),
	_48("48", "Bank card", "Payment by means of a card issued by a bank or other financial institution.", true),
	_49("49", "Direct debit", "The amount is to be, or has been, directly debited to the customer's bank account.", true),
	_50("50", "Payment by postgiro", "A method for the transmission of funds through the postal system rather than through the banking system.", false),
	_51("51", "FR, norme 6 97-Telereglement CFONB (French Organisation for", "Banking Standards) - Option A A French standard procedure that allows a debtor to pay an amount due to a creditor. The creditor will forward it to its bank, which will collect the money on the bank account of the debtor.", false),
	_52("52", "Urgent commercial payment", "Payment order which requires guaranteed processing by the most appropriate means to ensure it occurs on the requested execution date, provided that it is issued to the ordered bank before the agreed cut-off time.", false),
	_53("53", "Urgent Treasury Payment", "Payment order or transfer which must be executed, by the most appropriate means, as urgently as possible and before urgent commercial payments.", false),
	_54("54", "Credit card", "Payment made by means of credit card.", false),
	_55("55", "Debit card", "Payment made by means of debit card.", false),
	_56("56", "Bankgiro", "Payment will be, or has been, made by bankgiro.", false),
	_57("57", "Standing agreement", "The payment means have been previously agreed between seller and buyer and thus are not stated again.", true),
	_58("58", "SEPA credit transfer", "Credit transfer inside the Single Euro Payment Area (SEPA) system.", true),
	_59("59", "SEPA direct debit", "Direct debit inside the Single Euro Payment Area (SEPA) system.", true),
	_60("60", "Promissory note", "Payment by an unconditional promise in writing made by one person to another, signed by the maker, engaging to pay on demand or at a fixed or determinable future time a sum certain in money, to order or to bearer.", false),
	_61("61", "Promissory note signed by the debtor", "Payment by an unconditional promise in writing made by the debtor to another person, signed by the debtor, engaging to pay on demand or at a fixed or determinable future time a sum certain in money, to order or to bearer.", false),
	_62("62", "Promissory note signed by the debtor and endorsed by a bank", "Payment by an unconditional promise in writing made by the debtor to another person, signed by the debtor and endorsed by a bank, engaging to pay on demand or at a fixed or determinable future time a sum certain in money, to order or to bearer.", false),
	_63("63", "Promissory note signed by the debtor and endorsed by a", "third party Payment by an unconditional promise in writing made by the debtor to another person, signed by the debtor and endorsed by a third party, engaging to pay on demand or at a fixed or determinable future time a sum certain in money, to order or to bearer.", false),
	_64("64", "Promissory note signed by a bank", "Payment by an unconditional promise in writing made by the bank to another person, signed by the bank, engaging to pay on demand or at a fixed or determinable future time a sum certain in money, to order or to bearer.", false),
	_65("65", "Promissory note signed by a bank and endorsed by another", "bank Payment by an unconditional promise in writing made by the bank to another person, signed by the bank and endorsed by another bank, engaging to pay on demand or at a fixed or determinable future time a sum certain in money, to order or to bearer.", false),
	_66("66", "Promissory note signed by a third party", "Payment by an unconditional promise in writing made by a third party to another person, signed by the third party, engaging to pay on demand or at a fixed or determinable future time a sum certain in money, to order or to bearer.", false),
	_67("67", "Promissory note signed by a third party and endorsed by a", "bank Payment by an unconditional promise in writing made by a third party to another person, signed by the third party and endorsed by a bank, engaging to pay on demand or at a fixed or determinable future time a sum certain in money, to order or to bearer.", false),
	_68("68", "Online payment service", "Payment will be made or has been made by an online payment service.", false),
	_69("69", "Transfer Advice", "Transfer of an amount of money in the books of the account servicer. An advice should be sent back to the account owner.", false),
	_70("70", "Bill drawn by the creditor on the debtor", "Bill drawn by the creditor on the debtor.", false),
	_74("74", "Bill drawn by the creditor on a bank", "Bill drawn by the creditor on a bank.", false),
	_75("75", "Bill drawn by the creditor, endorsed by another bank", "Bill drawn by the creditor, endorsed by another bank.", false),
	_76("76", "Bill drawn by the creditor on a bank and endorsed by a", "third party Bill drawn by the creditor on a bank and endorsed by a third party.", false),
	_77("77", "Bill drawn by the creditor on a third party", "Bill drawn by the creditor on a third party.", false),
	_78("78", "Bill drawn by creditor on third party, accepted and", "endorsed by bank Bill drawn by creditor on third party, accepted and endorsed by bank.", false),
	_91("91", "Not transferable banker's draft", "Issue a bankers draft not endorsable.", false),
	_92("92", "Not transferable local cheque", "Issue a cheque not endorsable in payment of the funds.", false),
	_93("93", "Reference giro", "Ordering customer tells the bank to use the payment system 'Reference giro'. Used in the Finnish national banking system.", false),
	_94("94", "Urgent giro", "Ordering customer tells the bank to use the bank service 'Urgent Giro' when transferring the payment. Used in Finnish national banking system.", false),
	_95("95", "Free format giro", "Ordering customer tells the ordering bank to use the bank service 'Free Format Giro' when transferring the payment. Used in Finnish national banking system.", false),
	_96("96", "Requested method for payment was not used", "If the requested method for payment was or could not be used, this code indicates that.", false),
	_97("97", "Clearing between partners", "Amounts which two partners owe to each other to be compensated in order to avoid useless payments.", true),
	_98("98", "JP, Electronically Recorded Monetary Claims", "An electronically recorded monetary claim is a claim that is separate from the underlying debt that gave rise to its accrual.Therefore, even if an electronically recorded monetary claim is accrued as a means of payment of the underlying debt, the underlying debt will not be extinguished as a matter of course.", false),
	ZZZ("ZZZ", "Mutually defined", "A code assigned within a code list to be used on an interim basis and as defined among trading partners until a precise code can be assigned to the code list.", true),
}