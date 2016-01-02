package com.rbc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * It represent a basket to hold one or more items. Every user will have it's
 * own instance of basket.
 * 
 * @author Rajesh
 *
 */
public class Basket {
	
	// Map to hold item against it's quantity. As every user will have it's own
	// basket, we don't need to worry much about synchronization.
	private Map<Item, Integer> itemsWithQuantity = new HashMap<Item, Integer>();

	public Map<Item, Integer> getItemsWithQuantity() {
		return itemsWithQuantity;
	}

}
