package org.dario.salestaxes.io;

import org.dario.salestaxes.model.Receipt;

public interface ReceiptFormatter {
	public String format(Receipt receipt);
}
