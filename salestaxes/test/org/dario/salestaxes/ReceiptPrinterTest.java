package org.dario.salestaxes;

import java.util.Locale;

import junit.framework.TestCase;

public class ReceiptPrinterTest extends TestCase {

	private ReceiptPrinter printer;

	protected void setUp() throws Exception {
		super.setUp();
		// Set English locale otherwise double values are presented with ,
		// (comma) instead of . (dot) separator
		Locale.setDefault(new Locale("en"));
		printer = new ReceiptPrinter(new RoundedSalesTaxesStrategy());
	}

	private boolean test(String input, String output) {
		String result = "";
		try {
			result = printer.getFormattedReceipt(input);
			System.out.println(result);
		} catch (DataParseException e) {
			e.printStackTrace();
			fail("Input 1 failed");
		}
		return output.equals(result);
	}

	public void testGetReceiptInput1() {
		assertTrue(test(
				"1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85",
				"1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83"));
	}

	public void testGetReceiptInput2() {
		String input = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50";
		String output = "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15";
		assertTrue(test(input, output));
	}

	public void testGetReceiptInput3() {
		String input = "1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25";
		String output = "1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 imported box of chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68";
		assertTrue(test(input, output));
	}

}
