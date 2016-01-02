package com.rbc.test;

import com.rbc.model.Basket;
import com.rbc.model.Item;
import com.rbc.service.BasketBuilder;

/**
 * Test class for testing basket system.
 * 
 * We are creating new instance of Basket and then adding, removing items from it.
 * Finally, we are calling checkout method to get total cost of all items.
 * 
 * @author Rajesh
 *
 */
public class MainTest {

	public static void main(String[] args) {
		try {
			// Create new Basket
			Basket basket = new Basket();

			// Add and remove items in basket with specifying quantity
			BasketBuilder.addItem(basket, Item.APPLE, 9);
			BasketBuilder.addItem(basket, Item.MANGO, 25);
			BasketBuilder.addItem(basket, Item.ORANGE, 31);
			BasketBuilder.removeItem(basket, Item.ORANGE, 5);

			// Checkout to get total price of basket
			float totalCost = BasketBuilder.checkOut(basket);

			System.out.println("Total items: " + basket.getItemsWithQuantity());
			System.out.println("Total Cost: " + totalCost);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
