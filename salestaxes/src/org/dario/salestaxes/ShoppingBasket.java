package org.dario.salestaxes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingBasket implements Iterable<Purchase> {

	private List<Purchase> purchases = new ArrayList<Purchase>();
	
	public synchronized void add(Purchase purchase){
		purchases.add(purchase);
	}
	
	@Override
	public Iterator<Purchase> iterator() {
		return purchases.iterator();
	}

}
