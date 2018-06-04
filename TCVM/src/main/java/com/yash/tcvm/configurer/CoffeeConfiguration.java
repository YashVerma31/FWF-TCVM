package com.yash.tcvm.configurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.enums.IngredientsWithMaximumCapacity;
import com.yash.tcvm.util.ConfigurationUtil;

public class CoffeeConfiguration extends AbstractDrinkConfigurer {

	private static DrinkConfigurer drinkConfigurer;
	
	private static Properties properties;

	private CoffeeConfiguration() {
	}

	static {
		properties=ConfigurationUtil.readPropertyFile();
		drinkConfigurer = new CoffeeConfiguration();
	}
	
	public static DrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	public void configIngredientConsumption() {
		Map<IngredientsWithMaximumCapacity, Double> ingredientsConsumption = new HashMap<>();
		double waterConsumption=Double.parseDouble(properties.getProperty("COFFEE_WATAR_COMSUMPTION"));
		double sugarConsumption=Double.parseDouble(properties.getProperty("COFFEE_SUGAR_COMSUMPTION"));
		double milkConsumption=Double.parseDouble(properties.getProperty("COFFEE_MILK_COMSUMPTION"));
		double coffeeConsumption=Double.parseDouble(properties.getProperty("COFFEE_COFFEE_COMSUMPTION"));
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.COFFEE, coffeeConsumption);
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.MILK, milkConsumption);
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.WATER, waterConsumption);
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.SUGAR, sugarConsumption);

		setIngredientConsumption(ingredientsConsumption);
	}

	public void configIngredientWastage() {
		Map<IngredientsWithMaximumCapacity, Double> ingredientsWastage = new HashMap<>();
		double watarWastage=Double.parseDouble(properties.getProperty("COFFEE_WATER_WASTAGE"));
		double sugarWastage=Double.parseDouble(properties.getProperty("COFFEE_SUGAR_WASTAGE"));
		double milkWastage=Double.parseDouble(properties.getProperty("COFFEE_MILK_WASTAGE"));
		double coffeeWastage=Double.parseDouble(properties.getProperty("COFFEE_COFFEE_WASTAGE"));
		ingredientsWastage.put(IngredientsWithMaximumCapacity.COFFEE, coffeeWastage);
		ingredientsWastage.put(IngredientsWithMaximumCapacity.MILK, milkWastage);
		ingredientsWastage.put(IngredientsWithMaximumCapacity.WATER, watarWastage);
		ingredientsWastage.put(IngredientsWithMaximumCapacity.SUGAR, sugarWastage);

		setIngredientWastage(ingredientsWastage);
	}

	public void configDrinkType() {
		setDrinkType(Drink.COFFEE);
	}

	public void configDrinkRate() {
		double rate=Double.parseDouble(properties.getProperty("COFFEE_RATE"));
		setDrinkRate(rate);
	}

}
