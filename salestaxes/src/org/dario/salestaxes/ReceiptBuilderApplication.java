package org.dario.salestaxes;

import java.util.Locale;

import org.dario.salestaxes.io.DataParseException;
import org.dario.salestaxes.io.StandardReceiptFormatter;
import org.dario.salestaxes.io.StandardShoppingBasketInputDataParser;
import org.dario.salestaxes.policies.strategies.BasicSalesTaxesStrategy;
import org.dario.salestaxes.policies.strategies.ImportDutyStrategy;
import org.dario.salestaxes.policies.strategies.RoundPolicyStrategy;
import org.dario.salestaxes.policies.strategies.RoundToHigher5PolicyStrategy;
import org.dario.salestaxes.policies.strategies.SalesTaxesPolicyStrategy;
import org.dario.salestaxes.policies.strategies.TotalTaxesStrategy;

public class ReceiptBuilderApplication {

	private static Configuration config;

	public static void setup() {
		// Set English locale otherwise double values might eventually be
		// presented with ,
		// (comma) instead of . (dot) separator
		Locale.setDefault(new Locale("en"));

		config = new Configuration();
		config.setDataParser(new StandardShoppingBasketInputDataParser());
		config.setReceiptFormatter(new StandardReceiptFormatter());

		try {
			SalesTaxesPolicyStrategy[] compositeTaxStrategy = new SalesTaxesPolicyStrategy[2];
			compositeTaxStrategy[0] = new BasicSalesTaxesStrategy();
			compositeTaxStrategy[1] = new ImportDutyStrategy();
			SalesTaxesPolicyStrategy salesTaxesStrategy = new TotalTaxesStrategy(
					compositeTaxStrategy);
			config.setSalesTaxesPolicyStrategy(salesTaxesStrategy);

			RoundPolicyStrategy roundPolicyStrategy = new RoundToHigher5PolicyStrategy();
			config.setRoundPolicyStrategy(roundPolicyStrategy);
		} catch (IllegalConfigurationException ex) {
			ex.printStackTrace();
		}
	}

	public static Configuration getConfiguration() {
		return config;
	}

	public static void main(String[] args) {
		String[] input = new String[3];
		input[0] = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
		input[1] = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50";
		input[2] = "1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25";
		try {
			setup();
			Configuration config = getConfiguration();
			ReceiptBuilder builder = new ReceiptBuilder(config);
			for (String in : input) {
				System.out.println(builder.getFormattedReceipt(in));
				System.out.println();
			}
		} catch (DataParseException e) {
			e.printStackTrace();
			System.out.println("Parsing error");
		} catch (IllegalConfigurationException e) {
			e.printStackTrace();
			System.out.println("Configuration error");
		}

	}

}
