package com.yash.tcvm.model;

import com.yash.tcvm.enums.IngredientsWithMaximumCapacity;

public class Container {

	private IngredientsWithMaximumCapacity ingredient;
	private double maximumCapacity;
	private double availableCapacity;

	public Container() {
	}

	public Container(IngredientsWithMaximumCapacity ingredient, double maxCapacity, double availableCapacity) {
		this.ingredient = ingredient;
		this.maximumCapacity = maxCapacity;
		this.availableCapacity = availableCapacity;
	}

	public IngredientsWithMaximumCapacity getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientsWithMaximumCapacity ingredient) {
		this.ingredient = ingredient;
	}

	public double getMaxCapacity() {
		return maximumCapacity;
	}

	public void setMaxCapacity(double maxCapacity) {
		this.maximumCapacity = maxCapacity;
	}

	public double getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(double availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	@Override
	public String toString() {
		return "Container [ingredient=" + ingredient + ", maxCapacity=" + maximumCapacity + ", availableCapacity="
				+ availableCapacity + "]";
	}

}
