package org.dario.salestaxes;

public class ShoppingBasketInputDataParser {

	public static ShoppingBasket parse(String data) throws DataParseException {
		ShoppingBasket result = new ShoppingBasket();
		String[] items = data.split("\n");
		for (int i = 0; i < items.length; i++) {
			result.add(parseSinglePurchaseData(items[i]));
		}
		return result;
	}

	private static Purchase parseSinglePurchaseData(String data)
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

	private static boolean inferImported(String descr) {
		return descr.contains(" imported ");
	}

	private static String adjustDescription(String descr, boolean imported) {
		if (imported) {
			descr=descr.replace(" imported ", " ");
		}
		descr = descr.trim();
		return descr;
	}

	private static ProductCategory inferCategory(String descr) {
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
