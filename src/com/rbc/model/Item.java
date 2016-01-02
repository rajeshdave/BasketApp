package com.rbc.model;

/**
 * Enum class to represent any item and it's price.
 * 
 * Note: In real world scenario, items can be come from database and there
 * 'class'(instead of enum) would be right choice with proper hashcode and equal
 * implementation.
 * 
 * @author Rajesh
 *
 */
public enum Item {

	ORANGE("Orange", 10.5f), APPLE("Apple", 20.0f), MANGO("Mango", 30.5f);

	private Item(String name, float price) {
		this.name = name;
		this.price = price;
	}

	private String name;
	private float price;

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

}
