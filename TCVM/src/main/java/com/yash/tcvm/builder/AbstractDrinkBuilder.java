package com.yash.tcvm.builder;

import java.util.List;
import java.util.Map;

import com.yash.tcvm.configurer.AbstractDrinkConfigurer;
import com.yash.tcvm.configurer.DrinkConfigurer;
import com.yash.tcvm.enums.IngredientsWithMaximumCapacity;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.serviceimpl.ContainerServiceImpl;

public abstract class AbstractDrinkBuilder implements DrinkBuilder {

	DrinkConfigurer drinkConfigurer;
	ContainerService containerService = new ContainerServiceImpl();

	public void setDrinkConfigurer(DrinkConfigurer drinkConfigurer) {
		this.drinkConfigurer = drinkConfigurer;
	}

	public void setContainerService(ContainerServiceImpl containerServiceImpl) {
		this.containerService = containerServiceImpl;
	}

	public void prepareDrink(Order order) throws ContainerUnderflowException {
		checkUnderFlow(order);
		updateContainers(order);
		order.setStatus(true);
	}

	private void checkUnderFlow(Order order) throws ContainerUnderflowException {
		AbstractDrinkConfigurer abstractDrinkConfigurer = (AbstractDrinkConfigurer) drinkConfigurer;
		Map<IngredientsWithMaximumCapacity, Double> consumption = abstractDrinkConfigurer.getIngredientConsumption();
		Map<IngredientsWithMaximumCapacity, Double> wastage = abstractDrinkConfigurer.getIngredientWastage();
		for (Map.Entry<IngredientsWithMaximumCapacity, Double> entry : consumption.entrySet()) {
			double quantityWasted = wastage.get(entry.getKey());
			double quantityConsumed = entry.getValue();
			double quantityAvailableInContainer = containerService.getContainerByIngredient(entry.getKey())
					.getAvailableCapacity();
			int numberOfCups = order.getQuantity();
			checkForUnderFlowCondition(entry, quantityWasted, quantityConsumed, quantityAvailableInContainer,
					numberOfCups);
		}
	}

	private void checkForUnderFlowCondition(Map.Entry<IngredientsWithMaximumCapacity, Double> entry, double quantityWasted,
			double quantityConsumed, double quantityAvailableInContainer, int numberOfCups)
			throws ContainerUnderflowException {
		if (isUnderFlowCondition(quantityWasted, quantityConsumed, quantityAvailableInContainer, numberOfCups)) {
			throw new ContainerUnderflowException(entry.getKey() + "Insufficient");
		}
	}

	private boolean isUnderFlowCondition(double quantityWasted, double quantityConsumed,
			double quantityAvailableInContainer, int numberOfCups) {
		return (numberOfCups * (quantityConsumed + quantityWasted)) > quantityAvailableInContainer;
	}

	public void updateContainers(Order order) {

		List<Container> containers = containerService.getAllContainers();
		AbstractDrinkConfigurer abstractDrinkConfigurer = (AbstractDrinkConfigurer) drinkConfigurer;
		Map<IngredientsWithMaximumCapacity, Double> consumption = abstractDrinkConfigurer.getIngredientConsumption();
		Map<IngredientsWithMaximumCapacity, Double> wastage = abstractDrinkConfigurer.getIngredientWastage();
		for (Map.Entry<IngredientsWithMaximumCapacity, Double> entry : consumption.entrySet()) {
			for (Container container : containers) {
				if (container.getIngredient().toString().equalsIgnoreCase(entry.getKey().toString())) {
					double quantityWasted = wastage.get(entry.getKey());
					double quantityConsumed = entry.getValue();
					double quantityAvailableInContainer = container.getAvailableCapacity();
					int numberOfCups = order.getQuantity();
					container.setAvailableCapacity(
							quantityAvailableInContainer - (numberOfCups * (quantityConsumed + quantityWasted)));
					break;
				}
			}
		}
		if (containerService.updateAllContainers(containers)) {
			System.out.println("Your " + order.getDrink().toString() + " is Ready");
			System.out.println("Total Amount = " + (order.getQuantity() * abstractDrinkConfigurer.getDrinkRate()));
		}
	}

}
