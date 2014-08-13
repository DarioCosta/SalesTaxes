package org.dario.salestaxes;

/**
 * A Decorator that delegates calculateTax to composite strategies
 * (BasicSalesTaxStrategy and ImportDutyStrategy for this exercise) then adds up
 * and rounds result according to specified round policy (RoundToHigher5Policy for this exercise)
 * 
 * @author CostaDS
 *
 */
public class RoundedSalesTaxesStrategy implements SalesTaxesStrategy {

	private RoundPolicyStrategy policy;
	SalesTaxesStrategy[] compositeTaxStrategy;

	/**
	 * get a RoundPolicyStrategy so that we can easily change round-policy if we
	 * want to.
	 */
	public RoundedSalesTaxesStrategy(SalesTaxesStrategy[] compositeTaxStrategy,
			RoundPolicyStrategy policy) {
		if (policy == null) {
			policy = new RoundToHigher5Policy();
		}
		this.policy = policy;
		if (compositeTaxStrategy == null) {
			compositeTaxStrategy = new SalesTaxesStrategy[0];
		}
		this.compositeTaxStrategy = compositeTaxStrategy;
	}

	public RoundedSalesTaxesStrategy(SalesTaxesStrategy[] compositeTaxStrategy) {
		this(compositeTaxStrategy, null);
	}

	public RoundedSalesTaxesStrategy() {
		this(null, null);
		compositeTaxStrategy = new SalesTaxesStrategy[2];
		compositeTaxStrategy[0] = new BasicSalesTaxesStrategy();
		compositeTaxStrategy[1] = new ImportDutyStrategy();
	}

	@Override
	public double getTaxes(Product p) {
		double result = policy.round(calculateTax(p));
		return result;
	}

	private double calculateTax(Product p) {
		double result = 0;
		for (SalesTaxesStrategy strategy : compositeTaxStrategy) {
			result += strategy.getTaxes(p);
		}
		return result;
	}

}
