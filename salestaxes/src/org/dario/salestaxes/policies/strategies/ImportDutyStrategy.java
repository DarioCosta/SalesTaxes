package org.dario.salestaxes.policies.strategies;

import org.dario.salestaxes.model.Purchase;

public class ImportDutyStrategy implements SalesTaxesPolicyStrategy {

	@Override
	public double getTaxes(Purchase p) {
		if (p.isImported()) {
			return p.getPrice() * 5 / 100;
		}
		return 0;
	}

}
