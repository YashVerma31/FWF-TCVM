package com.yash.tcvm.service;

import java.util.List;

import com.yash.tcvm.enums.IngredientsWithMaximumCapacity;
import com.yash.tcvm.exception.ContainerOverflowException;
import com.yash.tcvm.model.Container;

public interface ContainerService {
	List<Container> getAllContainers();

	Container getContainerByIngredient(IngredientsWithMaximumCapacity ingredient);

	boolean updateAllContainers(List<Container> containerList);

	boolean refillContainer(Container containerToRefill, Double quantity) throws ContainerOverflowException;

	boolean resetAllContainers();

}
