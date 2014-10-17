package org.dario.salestaxes;

import org.dario.salestaxes.io.ReceiptFormatter;
import org.dario.salestaxes.io.ShoppingBasketInputDataParser;
import org.dario.salestaxes.policies.SalesTaxesPolicy;

public class Configuration {
	private ReceiptFormatter receiptFormatter;
	private ShoppingBasketInputDataParser dataParser;
	private SalesTaxesPolicy salesTaxesPolicy;
	
	private void checkNull(Object obj) throws IllegalConfigurationException{
		if(obj==null){
			throw new IllegalConfigurationException();
		}
	}
	
	public ReceiptFormatter getReceiptFormatter() throws IllegalConfigurationException{
		checkNull(receiptFormatter);
		return receiptFormatter;
	}
	public void setReceiptFormatter(ReceiptFormatter receiptFormatter) {
		this.receiptFormatter = receiptFormatter;
	}
	public ShoppingBasketInputDataParser getDataParser() throws IllegalConfigurationException{
		checkNull(dataParser);
		return dataParser;
	}
	public void setDataParser(ShoppingBasketInputDataParser dataParser) {
		this.dataParser = dataParser;
	}

	public SalesTaxesPolicy getSalesTaxesPolicy() throws IllegalConfigurationException{
		checkNull(salesTaxesPolicy);
		return salesTaxesPolicy;
	}

	public void setSalesTaxesPolicy(SalesTaxesPolicy salesTaxesPolicy) {
		this.salesTaxesPolicy = salesTaxesPolicy;
	}
}
