package org.dario.salestaxes.policies;

import org.dario.salestaxes.model.Purchase;
import org.dario.salestaxes.policies.strategies.RoundToHigher5PolicyStrategy;
import org.dario.salestaxes.policies.strategies.SalesTaxesPolicyStrategy;
import org.dario.salestaxes.policies.strategies.TotalTaxesStrategy;

public class SalesTaxesPolicy {
	private SalesTaxesPolicyStrategy salesTaxesStrategy;
	private RoundPolicy roundPolicy;
	
	public SalesTaxesPolicy(SalesTaxesPolicyStrategy salesTaxesStrategy, RoundPolicy roundPolicy){
		if(salesTaxesStrategy==null){
			salesTaxesStrategy=new TotalTaxesStrategy();
		}
		this.salesTaxesStrategy=salesTaxesStrategy;
		if(roundPolicy==null){
			roundPolicy=new RoundPolicy(new RoundToHigher5PolicyStrategy());
		}
		this.roundPolicy=roundPolicy;
	}
	
	public double getTaxes(Purchase p){
		double rawTax=salesTaxesStrategy.getTaxes(p);
		double roundedTax=roundPolicy.round(rawTax);
		return roundedTax;
	}
}
