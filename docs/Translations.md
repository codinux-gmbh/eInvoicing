
- **Rechnungsaussteller / Rechnungsersteller / Leistungserbringer** – Invoice issuer / Invoice creator / Service provider
- **Empfänger der Rechnung / Auftraggeber / Leistungsempfänger** – Invoice recipient / Client / Service recipient
- **Rechnungsnummer** – Invoice number
- **Rechnungsdatum** – Invoice date
- **Fälligkeitsdatum** – Due date


- **Auftragsnummer** – Order number
- **Auftragsdatum** – Order date
- **Kundennummer** – Customer number
- **Kontaktperson / Kontaktadresse** – Contact person / Contact address
- **Lieferschein** – Delivery note
- **Lieferdatum** – Delivery date


JetBrains Rechnung:

```text
Invoice Details:
Reference number: INVCZ7424912
Order reference: R20162736
Tax point date: 8.3.2024
Issue date: 8.3.2024
Due date: 8.3.2024
Paid via: Credit Card
Payment Date: 8.3.2024
Transaction: VZ6HHRG7V2KJFXB2


Bill To:
Christian Dankl
Kürnbergstr. 36
81369 München
Germany
VAT ID: DE306521719


Row 1:

Customer Id
1983447

Order Date
8.3.2024

Shipped Electronically To
mammon@dankito.de


Row 2:

Part Number
P-S.ALL-Y-40C

<Invoice item name>
All Products Pack

Product Description
Personal annual subscription with
40% continuity discount
Valid from 15.3.2024 through
14.3.2025


Price
173.00

Qty
1

Extended Price
173.00 EUR

Subtotal:       173.00 EUR
VAT Rate*:          0.00%
VAT Amount:         0.00 EUR
Total:          173.00 EUR
PAID:           173.00 EUR
```


Rechnungsaussteller / Leistungserbringer:

- **CII**: Seller / Supplier
- **UBL**: Supplier

Rechnungsempfänger / Auftraggeber / Leistungsempfänger:

- **CII**: Buyer / Customer
- **UBL**: Buyer / Customer


1. Rechnungsaussteller (die Partei, die die Rechnung stellt):

   Seller – Das ist der häufigste Begriff und bezieht sich auf die Partei, die die Waren oder Produkte verkauft und die Rechnung ausstellt.
   Supplier – Wird ebenfalls oft verwendet, besonders wenn der Fokus auf der Lieferung von Waren liegt.
   Vendor – Ein weiterer sehr gebräuchlicher Begriff, vor allem im Einzelhandel und im E-Commerce.

2. Rechnungsempfänger (die Partei, die die Rechnung erhält und bezahlt):

   Buyer – Der Käufer, die Partei, die die Ware oder Dienstleistung erworben hat und die Rechnung erhält.
   Customer – Ein sehr allgemeiner Begriff, der häufig für alle Arten von Käufern verwendet wird, sowohl in B2B als auch in B2C.
   Purchaser – Wird ebenfalls verwendet, besonders in formelleren oder vertraglichen Kontexten.


Die am häufigsten verwendeten Begriffe in Rechnungs- und Geschäftsprozessen sind:

### Für denjenigen, der die Rechnung ausstellt bzw. die Leistung erbracht hat:
- **Service provider** – Dies ist der am häufigsten verwendete Begriff, insbesondere in Dienstleistungsbranchen. Der „Service provider“ ist derjenige, der eine Dienstleistung erbringt und die Rechnung stellt.
- **Invoice issuer** oder **Invoice creator** sind zwar korrekt, aber weniger gebräuchlich und klingen formeller oder spezifischer, insbesondere im Kontext von Rechnungsstellung und Buchhaltung. **Service provider** ist der allgemeinere und häufigste Begriff, wenn man von der Leistungserbringung spricht.

### Für denjenigen, der die Rechnung erhält:
- **Client** – Dies ist der am häufigsten verwendete Begriff, besonders in geschäftlichen und dienstleistungsorientierten Kontexten. Der „Client“ ist die Person oder Organisation, die die Leistung in Anspruch nimmt und die Rechnung bezahlt.
- **Invoice recipient** ist ebenfalls korrekt, wird aber weniger häufig verwendet, da es eine sehr formale Ausdrucksweise ist.
- **Service recipient** ist auch korrekt, wird aber eher in formelleren oder spezialisierten Kontexten verwendet, z.B. in Verträgen, bei denen die Erbringung einer spezifischen Dienstleistung betont wird.

### Zusammengefasst:
- **Für denjenigen, der die Rechnung ausstellt:** **Service provider**
- **Für denjenigen, der die Rechnung erhält:** **Client**


## Client vs. Customer

Der Unterschied zwischen **Customer** und **Client** im Kontext von CII/UBL und allgemeiner Geschäftsbeziehung ist subtil, aber dennoch wichtig. Die Begriffe haben unterschiedliche Konnotationen, abhängig von der Art der Transaktion und dem zugrunde liegenden Geschäftsmodell.

### **1. Customer (CII / UBL)**
- **Kontext**: Der Begriff **Customer** ist weit verbreitet und bezieht sich auf eine Person oder Organisation, die **Waren** oder **Dienstleistungen** kauft. In den **CII** und **UBL** XML-Formaten wird der Begriff "Customer" verwendet, um den Empfänger einer Rechnung zu bezeichnen – also die Partei, die das Produkt oder die Dienstleistung **erwirbt** und **bezahlt**.

- **Verwendung**:
    - **B2C (Business-to-Consumer)**: Der Begriff **Customer** wird insbesondere in B2C-Transaktionen verwendet, also bei der Interaktion zwischen einem Unternehmen und einem Endverbraucher.
    - **B2B (Business-to-Business)**: Auch im B2B-Kontext wird der Begriff oft verwendet, wenn eine Firma Waren oder Dienstleistungen an eine andere Firma verkauft.

- **Beispiel**: Ein Online-Shop verkauft ein Produkt an einen Endverbraucher. Der Endverbraucher ist der **Customer** des Shops.

### **2. Client (Allgemein)**
- **Kontext**: Der Begriff **Client** ist etwas **formeller** und wird häufiger in bestimmten **Dienstleistungsbranchen** oder in längerfristigen **geschäftlichen Beziehungen** verwendet, wo eine kontinuierliche Betreuung oder ein fortlaufender Service erforderlich ist. Er bezeichnet häufig eine Person oder Organisation, die **eine Dienstleistung in Anspruch nimmt** und häufig eine engere, individuellere Geschäftsbeziehung pflegt.

- **Verwendung**:
    - **Dienstleistungsbranche**: **Client** wird vor allem in Bereichen wie **Beratung, Finanzdienstleistungen, Recht, IT-Dienstleistungen** und anderen beratungsintensiven Branchen verwendet.
    - **Längerfristige Beziehungen**: Der Begriff wird auch verwendet, wenn es eine **langfristige Geschäftsbeziehung** gibt, wie z.B. bei regelmäßigen Verträgen oder individuellen Dienstleistungen.

- **Beispiel**: Ein Beratungsunternehmen stellt einem Unternehmen eine Rechnung für eine langfristige Dienstleistung. Das Unternehmen ist der **Client** des Beratungsunternehmens.

### **Wichtige Unterschiede**:

- **Customer** ist eher allgemein und bezieht sich auf **jeden Käufer** von Waren oder Dienstleistungen, egal ob in einem **einmaligen Kauf** oder einer **langfristigen Beziehung**. Der Begriff ist besonders in **B2C** und **Einzelhandelskontexten** verbreitet.

- **Client** wird eher in **dienstleistungsorientierten** und **langfristigen** Geschäftsbeziehungen verwendet und suggeriert eine **individuellere, oft persönlichere Beziehung**. Es geht um eine **Dienstleistung** oder **Beratung**, die über einen längeren Zeitraum erfolgt und typischerweise mit einem **Vertrag** oder einer regelmäßigen Interaktion verbunden ist.

### Fazit:
- **Customer** (CII/UBL) ist der häufigere und allgemeinere Begriff für den Empfänger einer Rechnung in den meisten **Warenkauf**-Szenarien und auch in **B2C**-Transaktionen.
- **Client** wird häufig in einem **Dienstleistungszusammenhang** verwendet, besonders wenn es um langfristige Beziehungen oder maßgeschneiderte Dienstleistungen geht.

Im Kontext von **CII** und **UBL**, die häufig für **Produktkäufe** und **Warenlieferungen** verwendet werden, ist **Customer** also der passendere Begriff, während **Client** in einem Geschäfts- oder Beratungsdienstleistungsumfeld gebräuchlicher wäre.