package org.dario.salestaxes;

import org.dario.salestaxes.io.ReceiptFormatter;
import org.dario.salestaxes.io.ShoppingBasketInputDataParser;
import org.dario.salestaxes.policies.RoundPolicy;
import org.dario.salestaxes.policies.SalesTaxesPolicy;
import org.dario.salestaxes.policies.strategies.RoundPolicyStrategy;
import org.dario.salestaxes.policies.strategies.SalesTaxesPolicyStrategy;

public class Configuration {
	private ReceiptFormatter receiptFormatter;
	private ShoppingBasketInputDataParser dataParser;
	private SalesTaxesPolicyStrategy salesTaxesStrategy;
	private RoundPolicyStrategy roundPolicyStrategy;
	private SalesTaxesPolicy salesTaxesPolicy;

	private void checkNull(Object obj) throws IllegalConfigurationException {
		if (obj == null) {
			throw new IllegalConfigurationException();
		}
	}

	public ReceiptFormatter getReceiptFormatter()
			throws IllegalConfigurationException {
		checkNull(receiptFormatter);
		return receiptFormatter;
	}

	public void setReceiptFormatter(ReceiptFormatter receiptFormatter) {
		this.receiptFormatter = receiptFormatter;
	}

	public ShoppingBasketInputDataParser getDataParser()
			throws IllegalConfigurationException {
		checkNull(dataParser);
		return dataParser;
	}

	public void setDataParser(ShoppingBasketInputDataParser dataParser) {
		this.dataParser = dataParser;
	}

	private SalesTaxesPolicyStrategy getSalesTaxesStrategy()
			throws IllegalConfigurationException {
		checkNull(salesTaxesStrategy);
		return salesTaxesStrategy;
	}

	public void setSalesTaxesPolicyStrategy(
			SalesTaxesPolicyStrategy salesTaxesStrategy)
			throws IllegalConfigurationException {
		checkNull(salesTaxesStrategy);
		this.salesTaxesStrategy = salesTaxesStrategy;
	}

	private RoundPolicyStrategy getRoundPolicyStrategy()
			throws IllegalConfigurationException {
		checkNull(roundPolicyStrategy);
		return roundPolicyStrategy;
	}

	public void setRoundPolicyStrategy(RoundPolicyStrategy roundPolicyStrategy) throws IllegalConfigurationException {
		checkNull(roundPolicyStrategy);
		this.roundPolicyStrategy = roundPolicyStrategy;

	}
	
	public SalesTaxesPolicy getSalesTaxesPolicy() throws IllegalConfigurationException {
		if(salesTaxesPolicy==null){
			RoundPolicy roundPolicy = new RoundPolicy(getRoundPolicyStrategy());
			salesTaxesPolicy = new SalesTaxesPolicy(getSalesTaxesStrategy(), roundPolicy);
		}
		return salesTaxesPolicy;
	}
	
	
}
