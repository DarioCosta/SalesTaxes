package org.dario.salestaxes;

public class Product {

	private int quantity;
	private double price;
	private boolean imported;
	private String description;

	public Product(int quantity, double price, boolean imported,
			String description) {
		this.quantity = quantity;
		this.price = price;
		this.imported = imported;
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public boolean isImported() {
		return imported;
	}

	public String getDescription() {
		return description;
	}

}
