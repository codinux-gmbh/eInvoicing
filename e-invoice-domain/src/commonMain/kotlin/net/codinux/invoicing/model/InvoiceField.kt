package net.codinux.invoicing.model

enum class InvoiceField {
    InvoiceDate,
    InvoiceNumber,

    Currency,

    Supplier,
    SupplierName,
    SupplierCountry,

    Customer,
    CustomerName,
    CustomerCountry,

    Items,
    ItemName,
    ItemQuantity,
    ItemUnit,
    ItemUnitPrice,

    TotalAmount,
    LineTotalAmount,
    TaxBasisTotalAmount,
    GrandTotalAmount,
    DuePayableAmount

}