package org.dario.salestaxes;

public class BasicSalesTaxesStrategy implements SalesTaxesStrategy {

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
