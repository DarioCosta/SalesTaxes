package org.dario.salestaxes;

public class ReceiptItem {
	
	private Purchase purchase;
	private double tax;

	public ReceiptItem(Purchase purchase, double tax) {
		this.purchase=purchase;
		this.tax=tax;
	}
	
	public int getQuantity(){
		return purchase.getQuantity();
	}
	
	public String getDescription(){
		return purchase.getDescription();
	}
	
	public double getPrice() {
		return purchase.getPrice()+tax;
	}
	
}
