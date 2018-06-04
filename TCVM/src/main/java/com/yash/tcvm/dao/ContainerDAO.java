package com.yash.tcvm.dao;

import java.util.List;

import com.yash.tcvm.model.Container;

public interface ContainerDAO {

	boolean insert(List<Container> containers);

	List<Container> getContainerList();

	boolean updateContainers(List<Container> containerList);
}
