package org.dario.salestaxes.policies.strategies;

import org.dario.salestaxes.model.Purchase;

public class TotalTaxesStrategy implements SalesTaxesPolicyStrategy {

	private SalesTaxesPolicyStrategy[] compositeTaxStrategy;

	public TotalTaxesStrategy(SalesTaxesPolicyStrategy[] compositeTaxStrategy) {
		if (compositeTaxStrategy == null) {
			compositeTaxStrategy = new SalesTaxesPolicyStrategy[0];
		}
		this.compositeTaxStrategy = compositeTaxStrategy;
	}

	@Override
	public double getTaxes(Purchase p) {
		double result = 0;
		for (SalesTaxesPolicyStrategy strategy : compositeTaxStrategy) {
			if (strategy != null) {
				result += strategy.getTaxes(p);
			}
		}
		return result;
	}

}
