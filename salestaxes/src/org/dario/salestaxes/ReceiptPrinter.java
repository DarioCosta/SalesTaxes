package org.dario.salestaxes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

public class ReceiptPrinter {

	private SalesTaxesStrategy strategy;
	private DecimalFormat df = new DecimalFormat("####0.00");

	public ReceiptPrinter(SalesTaxesStrategy strategy) {
		if (strategy == null) {
			strategy = new RoundedSalesTaxesStrategy();
		}
		this.strategy = strategy;
	}

	public ReceiptPrinter() {
		this(null);
	}

	public String getReceipt(Collection<Product> list) {
		double tot = 0;
		double tax = 0;
		String result = "";
		double itemTax = 0;
		for (Product item : list) {
			itemTax = strategy.getTaxes(item);
			result += printItem(item, itemTax) + "\n";
			tax += itemTax;
			tot += (item.getPrice() * item.getQuantity());
		}
		result += printTax(tax) + "\n";
		result += printTot(tot + tax);
		return result;
	}

	private String printItem(Product item, double tax) {
		String result = "";
		result += item.getQuantity();
		result += " ";
		if (item.isImported()) {
			result += "imported ";
		}
		result += item.getDescription();
		result += ": ";
		result += (df.format((item.getPrice() * item.getQuantity()) + tax));
		return result;
	}

	private String printDouble(String msg, double dbl) {
		return msg + df.format(dbl);
	}

	private String printTax(double tax) {
		return printDouble("Sales Taxes: ", tax);
	}

	private String printTot(double tot) {
		return printDouble("Total: ", tot);
	}

	public static Collection<Product> listDataParse(String data)
			throws DataParseException {
		Collection<Product> result = new ArrayList<Product>();
		String[] items = data.split("\n");
		for (int i = 0; i < items.length; i++) {
			result.add(productDataParse(items[i]));
		}
		return result;
	}

	public static Product productDataParse(String data)
			throws DataParseException {
		Product result = null;
		try {
			String tokens[] = data.split(" at ");
			double price = Double.parseDouble(tokens[1]);
			int quantity = Integer.parseInt(tokens[0].substring(0,
					tokens[0].indexOf(' ')));
			String descr = tokens[0].substring(tokens[0].indexOf(' '));
			boolean imported = false;
			if (descr.contains(" imported ")) {
				imported = true;
				descr = descr.replace(" imported ", " ");
			}
			descr = descr.trim();
			result = new Product(quantity, price, imported, descr);
		} catch (Exception ex) {
			throw new DataParseException();
		}
		return result;
	}

	public static void main(String[] args) {
		ReceiptPrinter printer = new ReceiptPrinter();
		String[] input = new String[3];
		input[0] = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
		input[1] = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50";
		input[2] = "1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25";
		try {
			for (String in : input) {
				System.out.println(printer.getReceipt(ReceiptPrinter
						.listDataParse(in)));
				System.out.println();
			}
		} catch (DataParseException e) {
			e.printStackTrace();
		}

	}

}
