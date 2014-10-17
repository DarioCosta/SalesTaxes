package org.dario.salestaxes;

import org.dario.salestaxes.io.DataParseException;
import org.dario.salestaxes.io.ReceiptFormatter;
import org.dario.salestaxes.io.ShoppingBasketInputDataParser;
import org.dario.salestaxes.model.Receipt;
import org.dario.salestaxes.model.ShoppingBasket;
import org.dario.salestaxes.policies.SalesTaxesPolicy;

public class ReceiptBuilder {

	private SalesTaxesPolicy salesTaxesPolicy;
	private ReceiptFormatter formatter;
	private ShoppingBasketInputDataParser parser;

	public ReceiptBuilder(Configuration configuration)
			throws IllegalConfigurationException {
		
		if(configuration==null){
			throw new IllegalConfigurationException();
		}

		formatter = configuration.getReceiptFormatter();
		parser = configuration.getDataParser();
		salesTaxesPolicy = configuration.getSalesTaxesPolicy();

	}

	public String getFormattedReceipt(String in) throws DataParseException {
		ShoppingBasket shoppingBasket = parser.parse(in);
		Receipt receipt = new Receipt(shoppingBasket, salesTaxesPolicy);
		return formatter.format(receipt);
	}

}
