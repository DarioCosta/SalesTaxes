package org.dario.salestaxes.policies.strategies;

import org.dario.salestaxes.model.Purchase;

public interface SalesTaxesPolicyStrategy {
	public double getTaxes(Purchase p);
}
