package org.dario.salestaxes;

/**
 * Calculates basic sales texes: Basic sales tax is applicable at a rate of 10%
 * on all goods, except books, food, and medical products that are exempt.
 * 
 * @author CostaDS
 *
 */
public class BasicSalesTaxesStrategy implements SalesTaxesStrategy {

	@Override
	public double getTaxes(Product p) {
		if (isExempt(p)) {
			return 0;
		} else {
			return p.getQuantity() * p.getPrice() * 10 / 100;
		}
	}

	/**
	 * This is a very basic implementation that just matches provided test
	 * cases. A robust implementation clearly requires a different approach
	 * 
	 * @param p
	 * @return
	 */
	private boolean isExempt(Product p) {
		return ((p.getDescription().contains("chocolate"))
				|| (p.getDescription().contains("book")) || (p.getDescription()
				.contains("pill")));
	}

}
