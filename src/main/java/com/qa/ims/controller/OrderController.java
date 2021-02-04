package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
//		LOGGER.info("Please enter a first name");
//		String firstName = utils.getString();
//		LOGGER.info("Please enter a surname");
//		String surname = utils.getString();
//		Customer customer = customerDAO.create(new Customer(firstName, surname));
//		LOGGER.info("Customer created");
		return null;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
//		LOGGER.info("Please enter the id of the customer you would like to update");
//		Long id = utils.getLong();
//		LOGGER.info("Please enter a first name");
//		String firstName = utils.getString();
//		LOGGER.info("Please enter a surname");
//		String surname = utils.getString();
//		Customer customer = customerDAO.update(new Customer(id, firstName, surname));
		LOGGER.info("Customer Updated");
		return null;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
//		LOGGER.info("Please enter the id of the customer you would like to delete");
//		Long id = utils.getLong();
//		return OrderDAO.delete(id);
		return 0;
	}

}
