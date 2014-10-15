package org.dario.salestaxes;

public class ImportDutyStrategy implements SalesTaxesStrategy {

	@Override
	public double getTaxes(Purchase p) {
		if (p.isImported()) {
			return p.getPrice() * 5 / 100;
		}
		return 0;
	}

}
