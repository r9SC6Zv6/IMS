package com.qa.ims.persistence.domain;

import java.util.List;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;

public class Order {

	private Long id;
	private Long customerId;
	private List<Long> itemId;
	private CustomerDAO customerDAO = new CustomerDAO();
	private ItemDAO itemDAO = new ItemDAO();
	private String update;

	public Order(Long customerId, List<Long> itemId) {
		this.setCustomerId(customerId);
		this.setItemId(itemId);
	}

	public Order(Long id, Long customerId, List<Long> itemId) {
		this.setId(id);
		this.setCustomerId(customerId);
		this.setItemId(itemId);
	}
	
	public Order(String update, Long id, List<Long> itemId) {
		this.setId(id);
		this.setItemId(itemId);
		this.setUpdate(update);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Long> getItemId() {
		return itemId;
	}

	public void setItemId(List<Long> itemId) {
		this.itemId = itemId;
	}
	
	public String getUpdate() {
		return update;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}

	private String itemsToString(List<Long> itemId) {
		String itemString = "";
		for (Long id : itemId) {
			itemString = itemString + System.lineSeparator() + "  - " + itemDAO.read(id).getName();
		}
		return itemString;
	}

	@Override
	public String toString() {
		return "order id:" + id + " customer name:" + customerDAO.read(customerId).getFirstName() + " "
				+ customerDAO.read(customerId).getSurname() + " items:" + itemsToString(itemId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (getCustomerId() == null) {
			if (other.getCustomerId() != null)
				return false;
		} else if (!getCustomerId().equals(other.getCustomerId()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

}
