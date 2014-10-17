package org.dario.salestaxes.policies;

import org.dario.salestaxes.model.Purchase;
import org.dario.salestaxes.policies.strategies.SalesTaxesPolicyStrategy;

public class SalesTaxesPolicy {
	private SalesTaxesPolicyStrategy salesTaxesStrategy;
	private RoundPolicy roundPolicy;
	
	public SalesTaxesPolicy(){
		this(null, null);
	}
	
	public SalesTaxesPolicy(SalesTaxesPolicyStrategy salesTaxesStrategy, RoundPolicy roundPolicy){
		this.salesTaxesStrategy=salesTaxesStrategy;
		this.roundPolicy=roundPolicy;
	}
	
	public double getTaxes(Purchase p){
		double rawTax=salesTaxesStrategy.getTaxes(p);
		double roundedTax=roundPolicy.round(rawTax);
		return roundedTax;
	}
}
