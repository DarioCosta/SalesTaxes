package org.dario.salestaxes;

import java.util.Locale;

import org.dario.salestaxes.io.DataParseException;
import org.dario.salestaxes.io.ReceiptFormatter;
import org.dario.salestaxes.io.ReceiptFormatterFactory;
import org.dario.salestaxes.io.ShoppingBasketInputDataParser;
import org.dario.salestaxes.io.ShoppingBasketInputDataParserFactory;
import org.dario.salestaxes.model.Receipt;
import org.dario.salestaxes.model.ShoppingBasket;
import org.dario.salestaxes.policies.RoundPolicy;
import org.dario.salestaxes.policies.SalesTaxesPolicy;
import org.dario.salestaxes.policies.strategies.TotalTaxesStrategy;

public class ReceiptBuilder {

	private SalesTaxesPolicy salesTaxesPolicy;
	private ReceiptFormatter formatter;
	private ShoppingBasketInputDataParser parser;

	public ReceiptBuilder() {
		this(null);
	}

	public ReceiptBuilder(SalesTaxesPolicy salesTaxesPolicy) {
		// Set English locale otherwise double values might eventually be
		// presented with ,
		// (comma) instead of . (dot) separator
		Locale.setDefault(new Locale("en"));

		if (salesTaxesPolicy == null) {
			salesTaxesPolicy = new SalesTaxesPolicy(new TotalTaxesStrategy(),
					new RoundPolicy());
		}
		this.salesTaxesPolicy = salesTaxesPolicy;
		parser = ShoppingBasketInputDataParserFactory
				.getShoppingBasketInputDataParser();
		formatter = ReceiptFormatterFactory.getReceiptFormatter();
	}

	public String getFormattedReceipt(String in) throws DataParseException {
		ShoppingBasket shoppingBasket = parser.parse(in);
		Receipt receipt = new Receipt(shoppingBasket, salesTaxesPolicy);
		return formatter.format(receipt);
	}

	public static void main(String[] args) {
		String[] input = new String[3];
		input[0] = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
		input[1] = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50";
		input[2] = "1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25";
		try {
			ReceiptBuilder printer = new ReceiptBuilder();
			for (String in : input) {
				System.out.println(printer.getFormattedReceipt(in));
				System.out.println();
			}
		} catch (DataParseException e) {
			e.printStackTrace();
		}

	}

}
