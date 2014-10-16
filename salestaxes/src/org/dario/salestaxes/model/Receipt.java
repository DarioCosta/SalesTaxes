package org.dario.salestaxes.model;

import java.util.ArrayList;
import java.util.List;

import org.dario.salestaxes.policies.SalesTaxesPolicy;

public class Receipt {
	private List<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
	private double total;
	private double totalTaxes;

	public Receipt(ShoppingBasket shoppingBasket,
			SalesTaxesPolicy salesTaxesPolicy) {
		if (shoppingBasket == null) {
			shoppingBasket = new ShoppingBasket();
		}
		if (salesTaxesPolicy == null) {
			salesTaxesPolicy = new SalesTaxesPolicy() ;
		}

		fillIn(shoppingBasket, salesTaxesPolicy);
	}

	private void fillIn(ShoppingBasket shoppingBasket,
			SalesTaxesPolicy salesTaxesPolicy) {
		synchronized (shoppingBasket) {
			ReceiptItem current = null;
			for (Purchase p : shoppingBasket) {
				double tax = salesTaxesPolicy.getTaxes(p);
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
