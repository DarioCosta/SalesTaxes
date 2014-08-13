package org.dario.salestaxes;

/**
 * Calculates import duty texes: Import duty is an additional sales tax
 * applicable on all imported goods at a rate of 5%, with no exemptions.
 * 
 * @author CostaDS
 *
 */
public class ImportDutyStrategy implements SalesTaxesStrategy {

	@Override
	public double getTaxes(Product p) {
		if (p.isImported()) {
			return p.getQuantity() * p.getPrice() * 5 / 100;
		}
		return 0;
	}

}
