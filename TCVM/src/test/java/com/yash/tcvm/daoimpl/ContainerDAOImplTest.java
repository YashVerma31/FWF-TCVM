package com.yash.tcvm.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.enums.IngredientsWithMaximumCapacity;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.util.JSONUtil;

public class ContainerDAOImplTest {

	@Mock
	private JSONUtil jsonUtil;

	@InjectMocks
	private ContainerDAO containerDAO = new ContainerDAOImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void insert_WhenListOfContainerIsGiven_ShouldInsertAndReturnTrue() throws EmptyException {
		List<Container> containerList = new ArrayList<>();
		for (IngredientsWithMaximumCapacity ingredient : IngredientsWithMaximumCapacity.values()) {
			containerList.add(new Container(ingredient, ingredient.getMaxCapacity(), ingredient.getMaxCapacity()));
		}
		when(JSONUtil.writeObjectInJSONFile(containerList)).thenReturn(true);
		assertTrue(containerDAO.insert(containerList));
	}

	@Test
	public void insert_WhenListOfContainerIsGiven_ShouldNotInsertAndReturnFalse() throws EmptyException {
		List<Container> containerList = new ArrayList<>();
		for (IngredientsWithMaximumCapacity ingredient : IngredientsWithMaximumCapacity.values()) {
			containerList.add(new Container(ingredient, ingredient.getMaxCapacity(), ingredient.getMaxCapacity()));
		}
		when(JSONUtil.writeObjectInJSONFile(containerList)).thenReturn(false);
		assertFalse(containerDAO.insert(containerList));
	}

	@Test
	public void containerList_ShouldReturnListOfContainers() throws FileNotFoundException, EmptyException {
		when(JSONUtil.readObjectFromJSONFile()).thenReturn(Arrays.asList(new Container()));
		assertEquals(1, containerDAO.getContainerList().size());
	}

	@Test
	public void containerList_WhenJSONFileIsEmpty_ShouldReturnListOfContainersWithMaxCapacity()
			throws FileNotFoundException, EmptyException {
		List<Container> containerList = Arrays.asList(new Container(), new Container(), new Container(),
				new Container(), new Container());
		when(JSONUtil.readObjectFromJSONFile()).thenReturn(null, containerList);
		assertEquals(5, containerDAO.getContainerList().size());
	}

	@Test
	public void updateContainers_WhenListOfContainersIsPassed_ShouldReturnTrueWhenUpdated() {
		List<Container> containerList = Arrays.asList(new Container());
		when(JSONUtil.writeObjectInJSONFile(containerList)).thenReturn(true);
		assertTrue(JSONUtil.writeObjectInJSONFile(containerList));
	}

	@Test
	public void updateContainers_WhenListOfContainersIsPassed_ShouldReturnFalseWhenNotUpdated() {
		List<Container> containerList = Arrays.asList(new Container());
		when(JSONUtil.writeObjectInJSONFile(containerList)).thenReturn(false);
		assertFalse(JSONUtil.writeObjectInJSONFile(containerList));
	}
}
