package com.yash.tcvm.enums;

public enum IngredientsWithMaximumCapacity {

	TEA(2000), COFFEE(2000), SUGAR(8000), MILK(10000), WATER(15000);

	private double maximumCapacity;

	IngredientsWithMaximumCapacity(double maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public double getMaxCapacity() {
		return maximumCapacity;
	}

}
