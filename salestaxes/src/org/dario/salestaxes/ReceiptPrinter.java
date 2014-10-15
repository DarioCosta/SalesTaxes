package org.dario.salestaxes;


public class ReceiptPrinter {

	private SalesTaxesStrategy strategy;

	public ReceiptPrinter(SalesTaxesStrategy strategy) {
		if (strategy == null) {
			strategy = new RoundedSalesTaxesStrategy();
		}
		this.strategy = strategy;
	}

	public ReceiptPrinter() {
		this(null);
	}
	
	public String getFormattedReceipt(String in) throws DataParseException{
		ShoppingBasket shoppingBasket=ShoppingBasketInputDataParser.parse(in);
		Receipt receipt=new Receipt(shoppingBasket, new RoundedSalesTaxesStrategy());
		return ReceiptFormatter.format(receipt);
	}

	public static void main(String[] args) {
		ReceiptPrinter printer = new ReceiptPrinter();
		String[] input = new String[3];
		input[0] = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
		input[1] = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50";
		input[2] = "1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25";
		try {
			for (String in : input) {
				System.out.println(printer.getFormattedReceipt(in));
				System.out.println();
			}
		} catch (DataParseException e) {
			e.printStackTrace();
		}

	}

}
