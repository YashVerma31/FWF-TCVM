package com.yash.tcvm.serviceimpl;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.tcvm.dao.OrderDAO;
import com.yash.tcvm.daoimpl.OrderDaoImpl;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDao = new OrderDaoImpl();

	public OrderServiceImpl(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderServiceImpl() {
	}

	@Override
	public List<Order> getOrders() throws FileNotFoundException, EmptyException {
		List<Order> orderList = orderDao.getOrders();
		if (orderList == null) {
			throw new NullPointerException("Order's list is null");
		}

		if (orderList.isEmpty()) {
			throw new EmptyException("Order's list is empty");
		}
		return orderList;
	}

	@Override
	public List<Order> getOrdersByDrink(Drink drink) throws FileNotFoundException, EmptyException {
		List<Order> orderListByDrink = orderDao.getOrdersByDrink(drink);
		if (orderListByDrink == null) {
			throw new NullPointerException("Order's list for given drink is null");
		}

		if (orderListByDrink.isEmpty()) {
			throw new EmptyException("Order's list for given drink is empty");
		}
		return orderListByDrink;
	}

	@Override
	public int addOrder(Order order) throws FileNotFoundException, EmptyException {
		int rowsAffected = 0;
		if (order == null) {
			throw new NullPointerException("Order cannot be null");
		}
		rowsAffected = orderDao.insertOrder(order);
		return rowsAffected;
	}

}