package org.dario.salestaxes.io;

public class ReceiptFormatterFactory {
	public static ReceiptFormatter getReceiptFormatter(){
		return new StandardReceiptFormatter();
	}
}
