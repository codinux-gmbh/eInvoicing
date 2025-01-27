package net.codinux.invoicing.format

enum class FacturXProfile(val profileId: String) {
    Minimum("urn:factur-x.eu:1p0:minimum"),
    BasicWL("urn:factur-x.eu:1p0:basicwl"),
    Basic("urn:cen.eu:en16931:2017#compliant#urn:factur-x.eu:1p0:basic"),
    EN16931("urn:cen.eu:en16931:2017"),
    Extended("urn:cen.eu:en16931:2017#conformant#urn:factur-x.eu:1p0:extended"),
    XRechnung("urn:cen.eu:en16931:2017#compliant#urn:xeinkauf.de:kosit:xrechnung_3.0") // not fully correct, there are multiple different XRechnung profile IDs
    ;


    companion object {
        val FacturXProfile?.isMinimum: Boolean
            get() = this == FacturXProfile.Minimum

        val FacturXProfile?.isNotMinimum: Boolean
            get() = this != FacturXProfile.Minimum

        val FacturXProfile?.isMinimumOrBasicWL: Boolean
            get() = this.isMinimum || this == FacturXProfile.BasicWL

        val FacturXProfile?.isNotMinimumOrBasicWL: Boolean
            get() = this.isMinimumOrBasicWL == false
    }

}