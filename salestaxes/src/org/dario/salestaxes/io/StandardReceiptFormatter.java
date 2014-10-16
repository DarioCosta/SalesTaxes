package org.dario.salestaxes.io;

import java.text.DecimalFormat;

import org.dario.salestaxes.model.Receipt;
import org.dario.salestaxes.model.ReceiptItem;

public class StandardReceiptFormatter implements ReceiptFormatter{
	private static DecimalFormat df = new DecimalFormat("####0.00");

	public String format(Receipt receipt) {
		StringBuffer result = new StringBuffer();
		for (ReceiptItem item : receipt.getReceiptItems()) {
			result.append(formatReceiptItem(item) + "\n");
		}
		result.append(formatTax(receipt.getTotalTaxes()) + "\n");
		result.append(formatTot(receipt.getTotal()));
		return result.toString();
	}

	private String formatReceiptItem(ReceiptItem item) {
		StringBuffer result = new StringBuffer();
		result.append(item.getQuantity());
		result.append(" ");
		result.append(item.getDescription());
		result.append(": ");
		result.append(df.format((item.getPrice())));
		return result.toString();
	}

	private String formatTax(double tax) {
		return formatMessageAndValue("Sales Taxes: ", tax);
	}

	private String formatTot(double tot) {
		return formatMessageAndValue("Total: ", tot);
	}

	private String formatMessageAndValue(String msg, double dbl) {
		return msg + df.format(dbl);
	}

}
