package org.dario.salestaxes.io;

import org.dario.salestaxes.model.Product;
import org.dario.salestaxes.model.ProductCategory;
import org.dario.salestaxes.model.Purchase;
import org.dario.salestaxes.model.ShoppingBasket;

public class StandardShoppingBasketInputDataParser implements ShoppingBasketInputDataParser{

	public ShoppingBasket parse(String data) throws DataParseException {
		ShoppingBasket result = new ShoppingBasket();
		String[] items = data.split("\n");
		for (int i = 0; i < items.length; i++) {
			result.add(parseSinglePurchaseData(items[i]));
		}
		return result;
	}

	private Purchase parseSinglePurchaseData(String data)
			throws DataParseException {
		try {
			Purchase result = null;
			String tokens[] = data.split(" at ");
			double price = Double.parseDouble(tokens[1]);
			int quantity = Integer.parseInt(tokens[0].substring(0,
					tokens[0].indexOf(' ')));
			String descr = tokens[0].substring(tokens[0].indexOf(' '));
			boolean imported = inferImported(descr);
			descr = adjustDescription(descr, imported);
			ProductCategory category = inferCategory(descr);
			Product p = new Product(descr, category);
			result = new Purchase(quantity, p, price, imported);
			return result;
		} catch (Exception ex) {
			throw new DataParseException();
		}
	}

	private boolean inferImported(String descr) {
		return descr.contains(" imported ");
	}

	private String adjustDescription(String descr, boolean imported) {
		if (imported) {
			descr=descr.replace(" imported ", " ");
		}
		descr = descr.trim();
		return descr;
	}

	private ProductCategory inferCategory(String descr) {
		if (descr.contains("chocolate")) {
			return ProductCategory.FOOD;
		}
		if (descr.contains("book")) {
			return ProductCategory.BOOK;
		}
		if (descr.contains("pill")) {
			return ProductCategory.MEDICAL;
		}
		return ProductCategory.STANDARD;
	}
	
}
