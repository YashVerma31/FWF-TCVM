package com.yash.tcvm.configurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.enums.IngredientsWithMaximumCapacity;
import com.yash.tcvm.util.ConfigurationUtil;

public class TeaConfiguration extends AbstractDrinkConfigurer {

	private static DrinkConfigurer drinkConfigurer;

	private static Properties properties;

	private TeaConfiguration() {
	}

	static {
		properties = ConfigurationUtil.readPropertyFile();
		drinkConfigurer = new TeaConfiguration();
	}

	public static DrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	public void configIngredientConsumption() {
		Map<IngredientsWithMaximumCapacity, Double> ingredientsConsumption = new HashMap<>();
		double waterConsumption = Double.parseDouble(properties.getProperty("TEA_WATAR_COMSUMPTION"));
		double sugarConsumption = Double.parseDouble(properties.getProperty("TEA_SUGAR_COMSUMPTION"));
		double milkConsumption = Double.parseDouble(properties.getProperty("TEA_MILK_COMSUMPTION"));
		double teaConsumption = Double.parseDouble(properties.getProperty("TEA_TEA_COMSUMPTION"));
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.TEA, teaConsumption);
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.MILK, milkConsumption);
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.WATER, waterConsumption);
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.SUGAR, sugarConsumption);
		setIngredientConsumption(ingredientsConsumption);
	}

	public void configIngredientWastage() {
		Map<IngredientsWithMaximumCapacity, Double> ingredientsWastage = new HashMap<>();
		double watarWastage = Double.parseDouble(properties.getProperty("TEA_WATER_WASTAGE"));
		double sugarWastage = Double.parseDouble(properties.getProperty("TEA_SUGAR_WASTAGE"));
		double milkWastage = Double.parseDouble(properties.getProperty("TEA_MILK_WASTAGE"));
		double teaWastage = Double.parseDouble(properties.getProperty("TEA_TEA_WASTAGE"));
		ingredientsWastage.put(IngredientsWithMaximumCapacity.TEA, teaWastage);
		ingredientsWastage.put(IngredientsWithMaximumCapacity.MILK, milkWastage);
		ingredientsWastage.put(IngredientsWithMaximumCapacity.WATER, watarWastage);
		ingredientsWastage.put(IngredientsWithMaximumCapacity.SUGAR, sugarWastage);
		setIngredientWastage(ingredientsWastage);
	}

	public void configDrinkType() {
		setDrinkType(Drink.TEA);
	}

	public void configDrinkRate() {
		double rate = Double.parseDouble(properties.getProperty("TEA_RATE"));
		setDrinkRate(rate);
	}
}