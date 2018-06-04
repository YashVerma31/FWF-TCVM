package com.yash.tcvm.configurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.enums.IngredientsWithMaximumCapacity;
import com.yash.tcvm.util.ConfigurationUtil;

public class BlackTeaConfiguration extends AbstractDrinkConfigurer {

	private static DrinkConfigurer drinkConfigurer;
	
	private static Properties properties;

	private BlackTeaConfiguration() {
	}

	static {
		properties=ConfigurationUtil.readPropertyFile();
		drinkConfigurer = new BlackTeaConfiguration();
	}
	
	public static DrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	public void configIngredientConsumption() {
		Map<IngredientsWithMaximumCapacity, Double> ingredientsConsumption = new HashMap<>();
		double waterConsumption=Double.parseDouble(properties.getProperty("BTEA_WATAR_COMSUMPTION"));
		double sugarConsumption=Double.parseDouble(properties.getProperty("BTEA_SUGAR_COMSUMPTION"));
		double teaConsumption=Double.parseDouble(properties.getProperty("BTEA_TEA_COMSUMPTION"));
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.TEA, teaConsumption);
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.WATER, waterConsumption);
		ingredientsConsumption.put(IngredientsWithMaximumCapacity.SUGAR, sugarConsumption);

		setIngredientConsumption(ingredientsConsumption);
	}

	public void configIngredientWastage() {
		Map<IngredientsWithMaximumCapacity, Double> ingredientsWastage = new HashMap<>();
		double watarWastage=Double.parseDouble(properties.getProperty("BTEA_WATER_WASTAGE"));
		double sugarWastage=Double.parseDouble(properties.getProperty("BTEA_SUGAR_WASTAGE"));
		double teaWastage=Double.parseDouble(properties.getProperty("BTEA_TEA_WASTAGE"));
		ingredientsWastage.put(IngredientsWithMaximumCapacity.TEA, teaWastage);
		ingredientsWastage.put(IngredientsWithMaximumCapacity.WATER, watarWastage);
		ingredientsWastage.put(IngredientsWithMaximumCapacity.SUGAR, sugarWastage);

		setIngredientWastage(ingredientsWastage);
	}

	public void configDrinkType() {
		setDrinkType(Drink.BLACK_TEA);
	}

	public void configDrinkRate() {
		double rate=Double.parseDouble(properties.getProperty("BTEA_RATE"));
		setDrinkRate(rate);
	}

}
