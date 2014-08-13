SalesTaxes
==========

"Basic sales tax is applicable at a rate of 10% on all goods, except books,     food, and medical products that are exempt. 
Import duty is an additional sales     tax applicable on all imported goods at a rate of 5%, with no exemptions.
When I purchase items I receive a receipt which lists the name of all the     items and their price (including tax), finishing with the total cost of the     items, and the total amounts of sales taxes paid. The rounding rules for sales     tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded     up to the nearest 0.05) amount of sales tax.
Write an application that prints out the receipt details for these shopping     baskets."

The proposed solution provides a ReceiptPrinter that supports specified input format parsing and outputs results that match the provided test cases.

ReceiptPrinter features a basic main method for testing and ReceiptPrinterTest provides JUnit test case implementations for the given scenarios.

ReceiptPrinter uses a SalesTaxesStrategy to define texes policy. Using strategy pattern the tax policy is decoupled from ReceiptPrinter so that we can easily change strategy if necessary.

RoundedSalesTaxesStrategy is the composite policy implementation we use for taxes. It applies decorator pattern so that it delegates tax calculation to different specific Strategy implementations for BasicSalesTaxes and ImportDuty and then adds up results and applies the defined Round policy (Round policy is defined as a strategy too, so we can easily decide to change it if necessary. Current implementation uses RoundToHigher5Policy)

In summary solution code is made up of a ReceiptPrinter class that provides the requested functionality. Tax calculation has been decoupled used startegy pattern and RoundedSalesTaxesStrategy is the provided implementation that supports specified requirements. RoundedSalesTaxesStrategy uses decorator pattern to add up results of specific tax calculations (provided by specific SalesTaxesStrategy implementations for Basic Sales Taxes and Import Duty) and then applies the required RoundPolicy. RoundPolicy is a strategy too so that it can be replaced at any time with minimum impact.

Code analysis can start from ReceiptPrinter.java
