package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerId = resultSet.getLong("customer_id");
		List<Long> itemId = new ArrayList<>();
		itemId.add(resultSet.getLong("item_id"));
		return new Order(id, customerId, itemId);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT orders.id, orders.customer_id, order_item.item_id FROM orders JOIN order_item ON orders.id = order_item.order_id ORDER BY orders.id ASC");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			Long currentOrderId = orders.get(0).getId();
			int i = 1;
			while (i < orders.size()) {
				if (orders.get(i).getId() == currentOrderId) {
					orders.get(i - 1).getItemId().add(orders.get(i).getItemId().get(0));
					orders.remove(i);
				} else {
					currentOrderId = orders.get(i).getId();
					i++;
				}
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT orders.id, orders.customer_id, order_item.item_id FROM orders JOIN order_item ON orders.id = order_item.order_id WHERE orders.id = (SELECT id FROM orders ORDER BY id DESC LIMIT 1)");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			Long currentOrderId = orders.get(0).getId();
			int i = 1;
			while (i < orders.size()) {
				if (orders.get(i).getId() == currentOrderId) {
					orders.get(i - 1).getItemId().add(orders.get(i).getItemId().get(0));
					orders.remove(i);
				} else {
					currentOrderId = orders.get(i).getId();
					i++;
				}
			}
			for (int j = 1; j < orders.size(); j++) {
				orders.get(0).getItemId().add(orders.get(j).getItemId().get(0));
			}
			return orders.get(0);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection
//						.prepareStatement("INSERT INTO customers(first_name, surname) VALUES (?, ?)");) {
//			statement.setString(1, customer.getFirstName());
//			statement.setString(2, customer.getSurname());
//			statement.executeUpdate();
//			return readLatest();
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
		return null;
	}

	@Override
	public Order read(Long id) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE id = ?");) {
//			statement.setLong(1, id);
//			try (ResultSet resultSet = statement.executeQuery();) {
//				resultSet.next();
//				return modelFromResultSet(resultSet);
//			}
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection
//						.prepareStatement("UPDATE customers SET first_name = ?, surname = ? WHERE id = ?");) {
//			statement.setString(1, customer.getFirstName());
//			statement.setString(2, customer.getSurname());
//			statement.setLong(3, customer.getId());
//			statement.executeUpdate();
//			return read(customer.getId());
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long id) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE id = ?");) {
//			statement.setLong(1, id);
//			return statement.executeUpdate();
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
		return 0;
	}

}
