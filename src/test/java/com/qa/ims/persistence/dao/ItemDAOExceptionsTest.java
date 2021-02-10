package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOExceptionsTest {
	
	private final ItemDAO DAO = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema-exceptions.sql");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(2L, "Yamato", 20600000F);
		assertEquals(null, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> emptyList = new ArrayList<>();
		assertEquals(emptyList, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(null, DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(null, DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Yamato", 20600000F);
		assertEquals(null, DAO.update(updated));

	}

}
