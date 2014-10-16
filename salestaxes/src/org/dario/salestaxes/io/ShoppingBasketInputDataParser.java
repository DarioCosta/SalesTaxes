package org.dario.salestaxes.io;

import org.dario.salestaxes.model.ShoppingBasket;

public interface ShoppingBasketInputDataParser {
	public ShoppingBasket parse(String data) throws DataParseException;
}
