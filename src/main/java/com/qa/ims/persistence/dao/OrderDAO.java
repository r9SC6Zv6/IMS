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

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	@SuppressWarnings("unused")
	private Long nullCheck;

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerId = resultSet.getLong("customer_id");
		List<Long> itemId = new ArrayList<>();
		nullCheck = resultSet.getLong("item_id");
		if (!resultSet.wasNull()) {
			itemId.add(resultSet.getLong("item_id"));
		}
		return new Order(id, customerId, itemId);
	}

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT orders.id, orders.customer_id, order_item.item_id FROM orders LEFT JOIN order_item ON orders.id = order_item.order_id ORDER BY orders.id ASC");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			Long currentOrderId = orders.get(0).getId();
			int i = 1;
			while (i < orders.size()) {
				if (orders.get(i).getId().equals(currentOrderId)) {
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
						"SELECT orders.id, orders.customer_id, order_item.item_id FROM orders LEFT JOIN order_item ON orders.id = order_item.order_id WHERE orders.id = (SELECT id FROM orders ORDER BY id DESC LIMIT 1)");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			Long currentOrderId = orders.get(0).getId();
			int i = 1;
			while (i < orders.size()) {
				if (orders.get(i).getId().equals(currentOrderId)) {
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
	 * Creates a order in the database
	 * 
	 * @param order - takes in an order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomerId());
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		Long newOrderId = readLatest().getId();
		LOGGER.info(newOrderId);
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_item(order_id, item_id) VALUES (?, ?)");) {
			connection.setAutoCommit(false);
			for (Long l : order.getItemId()) {
				statement.setLong(1, newOrderId);
				statement.setLong(2, l);
				statement.addBatch();
			}
			statement.executeBatch();
			connection.commit();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT orders.id, orders.customer_id, order_item.item_id FROM orders LEFT JOIN order_item ON orders.id = order_item.order_id WHERE orders.id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				List<Order> orders = new ArrayList<>();
				while (resultSet.next()) {
					orders.add(modelFromResultSet(resultSet));
				}
				Long currentOrderId = orders.get(0).getId();
				int i = 1;
				while (i < orders.size()) {
					if (orders.get(i).getId().equals(currentOrderId)) {
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
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public List<Long> readByCustomer(Long customerId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT id FROM orders WHERE customer_id = ?");) {
			statement.setLong(1, customerId);
			try (ResultSet resultSet = statement.executeQuery();) {
				List<Long> orderId = new ArrayList<>();
				while (resultSet.next()) {
					orderId.add(resultSet.getLong(1));
				}
				return orderId;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public List<Long> readByItem(Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"SELECT order_id FROM order_item WHERE item_id = ?");) {
			statement.setLong(1, itemId);
			try (ResultSet resultSet = statement.executeQuery();) {
				List<Long> orderId = new ArrayList<>();
				while (resultSet.next()) {
					orderId.add(resultSet.getLong(1));
				}
				return orderId;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	/**
	 * Updates an order in the database
	 * 
	 * @param order - takes in an order object, the id field will be used to update
	 *              that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		if (order.getUpdate().equals("ADD")) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO order_item(order_id, item_id) VALUES (?, ?)");) {
				connection.setAutoCommit(false);
				for (Long l : order.getItemId()) {
					statement.setLong(1, order.getId());
					statement.setLong(2, l);
					statement.addBatch();
				}
				statement.executeBatch();
				connection.commit();
				return read(order.getId());
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
		} else if (order.getUpdate().equals("REMOVE")) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection
							.prepareStatement("DELETE FROM order_item WHERE order_id = ? AND item_id = ? LIMIT 1");) {
				connection.setAutoCommit(false);
				for (Long l : order.getItemId()) {
					statement.setLong(1, order.getId());
					statement.setLong(2, l);
					statement.addBatch();
				}
				statement.executeBatch();
				connection.commit();
				return read(order.getId());
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
		} else {
			LOGGER.info("Wrong update action, please try again");
		}
		return null;
	}

	/**
	 * Deletes an order in the database
	 * 
	 * @param id - id of the order
	 */
	@Override
	public int delete(long id) {
		int deleteCount = 0;
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM order_item WHERE order_id = ?");) {
			statement.setLong(1, id);
			deleteCount += statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			deleteCount += statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return deleteCount;
	}

	public float calculateCost(long id) {
		Float cost = 0F;
		Order order = read(id);
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT value FROM items WHERE id = ?");) {
			for (Long itemId : order.getItemId()) {
				statement.setLong(1, itemId);
				try (ResultSet resultSet = statement.executeQuery();) {
					while (resultSet.next()) {
						cost = cost + (resultSet.getFloat(1));
					}
				}
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}

		return cost;
	}

}
