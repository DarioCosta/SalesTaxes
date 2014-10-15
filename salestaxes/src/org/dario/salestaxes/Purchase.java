package org.dario.salestaxes;

public class Purchase {

	private int quantity;
	private Product product;
	private double price;
	private boolean imported;

	public Purchase(int quantity, Product product, double price,
			boolean imported) {
		this.quantity = quantity;
		this.price = price;
		this.imported = imported;
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public String getDescription(){
		String prefix="";
		if(isImported()){
			prefix="imported ";
		}
		return prefix+product.getDescription();
	}
	
	public ProductCategory getProductCategory(){
		return product.getProductCategory();
	}

	public double getPrice() {
		return price;
	}

	public boolean isImported() {
		return imported;
	}

}
