package net.codinux.invoicing.model

enum class InvoiceField {
    Currency,

    Supplier,
    SupplierAddress,
    SupplierCountry,

    Customer,
    CustomerAddress,
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