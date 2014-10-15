package org.dario.salestaxes;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	private List<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
	private double total;
	private double totalTaxes;

	public Receipt(ShoppingBasket shoppingBasket,
			SalesTaxesStrategy salesTaxesStrategy) {
		if (shoppingBasket == null) {
			shoppingBasket = new ShoppingBasket();
		}
		if (salesTaxesStrategy == null) {
			salesTaxesStrategy = new RoundedSalesTaxesStrategy();
		}

		fillIn(shoppingBasket, salesTaxesStrategy);
	}

	private void fillIn(ShoppingBasket shoppingBasket,
			SalesTaxesStrategy salesTaxesStrategy) {
		synchronized (shoppingBasket) {
			ReceiptItem current = null;
			for (Purchase p : shoppingBasket) {
				double tax = salesTaxesStrategy.getTaxes(p);
				current = new ReceiptItem(p, tax);
				receiptItems.add(current);
				totalTaxes += tax;
				total += (current.getPrice());
			}
		}
	}

	public List<ReceiptItem> getReceiptItems() {
		return receiptItems;
	}

	public double getTotal() {
		return total;
	}

	public double getTotalTaxes() {
		return totalTaxes;
	}
}
