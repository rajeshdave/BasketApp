package com.rbc.service;

import java.util.Map;

import com.rbc.model.Basket;
import com.rbc.model.Item;

/**
 * A builder class for building Basket and calculate total cost.
 * 
 * It contains static methods for adding and removing items in any basket. It
 * contains checkout method to calculate total price.
 * 
 * @author Rajesh
 *
 */
public class BasketBuilder {

	/**
	 * Add a given quantity of specific item in given Basket.
	 * 
	 * @param basket
	 *            represent unique basket of any user.
	 * @param item
	 *            represent Item to add in given basket
	 * @param quantity
	 *            represent quantity to add of given item in given basket.
	 * @throws Exception
	 *             in case of invalid inputs
	 */
	public static void addItem(Basket basket, Item item, int quantity)
			throws Exception {

		// Checking inputs of method. in real scenario, we can throw CUSTOM
		// exceptions with proper error codes.
		if (basket == null || item == null || quantity <= 0) {
			throw new Exception("Invalid inputs.");
		}

		// As every user will have own basket, we don't need to worry much about
		// multi threading. In case user create multiple threads for
		// adding/removing items, we are handling by taking lock on item's map.
		synchronized (basket.getItemsWithQuantity()) {
			basket.getItemsWithQuantity().put(item, quantity);
		}
	}

	/**
	 * Remove given quantity of specific item in given Basket.
	 * 
	 * @param basket
	 *            represent unique basket of any user.
	 * @param item
	 *            represent Item to remove from given basket
	 * @param quantity
	 *            represent quantity to remove of given item in given basket.
	 * @throws Exception
	 *             in case of invalid inputs
	 */
	public static void removeItem(Basket basket, Item item, int quantity)
			throws Exception {

		// Checking inputs of method. in real scenario, we can throw CUSTOM
		// exceptions with proper error codes.
		if (basket == null || item == null || quantity <= 0) {
			throw new Exception("Invalid inputs.");
		}

		// As every user will have own basket, we don't need to worry much about
		// multi threading. In case user create multiple threads for
		// adding/removing items, we are handling by taking lock on item's map.
		synchronized (basket.getItemsWithQuantity()) {
			// Checking whether map has items to remove
			int existingQuantity = 0;
			if (!basket.getItemsWithQuantity().containsKey(item)
					|| (existingQuantity = basket.getItemsWithQuantity().get(
							item)) < quantity) {
				throw new Exception("Insufficient quantity to remove.");
			}

			if (existingQuantity == quantity) {
				// remove item
				basket.getItemsWithQuantity().remove(item);
			} else {
				// decrease quantity of item
				basket.getItemsWithQuantity().put(item,
						(existingQuantity - quantity));
			}

		}
	}

	/**
	 * Calculate total cost of items in given basket.
	 * 
	 * @param basket
	 *            represent unique basket of any user.
	 * @return total cost of all items in basket
	 * @throws Exception
	 *             in case of invalid inputs
	 */
	public static float checkOut(Basket basket) throws Exception {
		// Checking inputs of method. in real scenario, we can throw CUSTOM
		// exceptions with proper error codes.
		if (basket == null) {
			throw new Exception("Invalid inputs.");
		}
		float totalCost = 0.0f;
		// As every user will have own basket, we don't need to worry much about
		// multi threading. In case user create multiple threads for
		// adding/removing items, we are handling by taking lock on item's map.
		synchronized (basket.getItemsWithQuantity()) {
			Map<Item, Integer> itemsWithQuantity = basket
					.getItemsWithQuantity();
			// return zero if basket is empty
			if (itemsWithQuantity.isEmpty()) {
				return totalCost;
			}

			// iterate map to calculate total cost based on it's quantity
			for (Item item : itemsWithQuantity.keySet()) {
				totalCost = totalCost
						+ (item.getPrice() * itemsWithQuantity.get(item));
			}

		}
		return totalCost;
	}

}
