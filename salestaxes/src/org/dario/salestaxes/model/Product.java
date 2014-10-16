package org.dario.salestaxes.model;

public class Product {

	private String description;
	private ProductCategory productCategory = ProductCategory.STANDARD;

	public Product(String description, ProductCategory productCategory) {
		if(description==null){
			description="";
		}
		this.description = description;
		if (productCategory != null) {
			this.productCategory = productCategory;
		}
	}

	public String getDescription() {
		return description;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}
}
