package org.dario.salestaxes.io;

public class ShoppingBasketInputDataParserFactory {
	public static ShoppingBasketInputDataParser getShoppingBasketInputDataParser(){
		return new StandardShoppingBasketInputDataParser();
	}
}
