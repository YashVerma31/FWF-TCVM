package com.yash.tcvm.enums;

public enum Drink {

	TEA(10), BLACK_TEA(5), COFFEE(15), BLACK_COFFEE(10);

	private double price;

	Drink(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
}
