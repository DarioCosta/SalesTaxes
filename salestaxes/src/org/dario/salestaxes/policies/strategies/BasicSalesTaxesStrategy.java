package org.dario.salestaxes.policies.strategies;

import org.dario.salestaxes.model.ProductCategory;
import org.dario.salestaxes.model.Purchase;

public class BasicSalesTaxesStrategy implements SalesTaxesPolicyStrategy {

	@Override
	public double getTaxes(Purchase p) {
		if (isExempt(p)) {
			return 0;
		} else {
			return p.getPrice() * 10 / 100;
		}
	}

	private boolean isExempt(Purchase p) {
		return ((p.getProductCategory().equals(ProductCategory.FOOD))
				|| (p.getProductCategory().equals(ProductCategory.BOOK)) || (p
					.getProductCategory().equals(ProductCategory.MEDICAL)));
	}

}
