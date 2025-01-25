package net.codinux.invoicing.model

enum class InvoiceField {
    Currency,

    Supplier,
    SupplierCountry,

    Customer,
    CustomerCountry,

    Items,
    ItemName,
    ItemQuantity,
    ItemUnit,
    ItemUnitPrice,

    TotalAmount,
    TaxBasisTotalAmount,
    GrandTotalAmount,
    DuePayableAmount

}