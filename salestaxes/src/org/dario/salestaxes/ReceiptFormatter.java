package org.dario.salestaxes;

import java.text.DecimalFormat;

public class ReceiptFormatter {
	private static DecimalFormat df = new DecimalFormat("####0.00");

	public static String format(Receipt receipt) {
		String result = "";
		for (ReceiptItem item : receipt.getReceiptItems()) {
			result += formatReceiptItem(item) + "\n";
		}
		result += printTax(receipt.getTotalTaxes()) + "\n";
		result += printTot(receipt.getTotal());
		return result;
	}

	private static String formatReceiptItem(ReceiptItem item) {
		String result = "";
		result += item.getQuantity();
		result += " ";
		result += item.getDescription();
		result += ": ";
		result += df.format((item.getPrice()));
		return result;
	}

	private static String printTax(double tax) {
		return printMessageAndValue("Sales Taxes: ", tax);
	}

	private static String printTot(double tot) {
		return printMessageAndValue("Total: ", tot);
	}

	private static String printMessageAndValue(String msg, double dbl) {
		return msg + df.format(dbl);
	}

}
