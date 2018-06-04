package com.yash.tcvm.serviceimpl;

import java.util.List;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.daoimpl.ContainerDAOImpl;
import com.yash.tcvm.enums.IngredientsWithMaximumCapacity;
import com.yash.tcvm.exception.ContainerOverflowException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.service.ContainerService;

public class ContainerServiceImpl implements ContainerService {

	private ContainerDAO containerDAO = new ContainerDAOImpl();

	public Container getContainerByIngredient(IngredientsWithMaximumCapacity ingredient) {
		List<Container> containerList = containerDAO.getContainerList();
		Container selectedContainer = null;
		for (Container container : containerList) {
			if (container.getIngredient() == ingredient) {
				selectedContainer = container;
			}
		}
		return selectedContainer;
	}

	public List<Container> getAllContainers() {
		List<Container> containerList = containerDAO.getContainerList();
		return containerList;
	}

	public boolean updateAllContainers(List<Container> containerList) {
		boolean updated = false;
		updated = containerDAO.updateContainers(containerList);
		return updated;
	}

	public boolean refillContainer(Container containerToRefill, Double quantity) throws ContainerOverflowException {
		boolean refilled = false;
		List<Container> containerList = containerDAO.getContainerList();
		checkForInvalidRefillQuantity(quantity);
		for (Container container : containerList) {
			checkForContainerAlreadyFull(container);
			if (container.getIngredient() == containerToRefill.getIngredient()) {
				double quantityTofill = container.getAvailableCapacity() + quantity;
				if (container.getMaxCapacity() >= quantityTofill) {
					container.setAvailableCapacity(quantityTofill);
					refilled = true;
				} else {
					System.out.println("Required Quantity to fill is "
							+ (container.getMaxCapacity() - container.getAvailableCapacity()));
					throw new ContainerOverflowException("Refill quantity " + quantity + " is greater than required");
				}
			}
		}
		checkToUpdateContainers(refilled, containerList);
		return refilled;
	}

	private void checkToUpdateContainers(boolean refilled, List<Container> containerList) {
		if (refilled) {
			containerDAO.updateContainers(containerList);
		}
	}

	private void checkForInvalidRefillQuantity(Double quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Refill quantity should be a positive number in grams.");
		}
	}

	private void checkForContainerAlreadyFull(Container container) throws ContainerOverflowException {
		if (container.getAvailableCapacity() == container.getMaxCapacity()) {
			throw new ContainerOverflowException("Container is already full, can not refill");
		}
	}

	public boolean resetAllContainers() {
		List<Container> containers = null;
		return containerDAO.insert(containers);
	}
}