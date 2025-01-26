package net.codinux.invoicing.format

enum class FacturXProfile {
    Minimum,
    BasicWL,
    Basic,
    EN16931,
    Extended,
    XRechnung
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