package com.qa.ims.utils;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

public class UtilsTest {
	
	@Test
	public void testConstructor() {
		final String SCANNED = "abc\n";
		final String RETURNED = "abc";
		
		ByteArrayInputStream in = new ByteArrayInputStream(SCANNED.getBytes());
		System.setIn(in);
		
		final Scanner scan = new Scanner(System.in);

		Utils utils = new Utils(scan);
		
		assertEquals(RETURNED, utils.getString());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetLongException() throws Exception {
		final String SCANNED = "abc\n";

		ByteArrayInputStream in = new ByteArrayInputStream(SCANNED.getBytes());
		System.setIn(in);

		Utils utils = new Utils();

		utils.getLong();

	}

	@Test
	public void testGetLong() {
		final String SCANNED = "1234\n";
		final Long RETURNED = 1234L;

		ByteArrayInputStream in = new ByteArrayInputStream(SCANNED.getBytes());
		System.setIn(in);

		Utils utils = new Utils();

		assertEquals(RETURNED, utils.getLong());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetDoubleException() {
		final String SCANNED = "abc\n";

		ByteArrayInputStream in = new ByteArrayInputStream(SCANNED.getBytes());
		System.setIn(in);

		Utils utils = new Utils();

		utils.getDouble();
	}
	
	@Test
	public void testGetDouble() {
		final String SCANNED = "1234\n";
		final Double RETURNED = 1234D;

		ByteArrayInputStream in = new ByteArrayInputStream(SCANNED.getBytes());
		System.setIn(in);

		Utils utils = new Utils();

		assertEquals(RETURNED, utils.getDouble());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetLongListException() {
		final String SCANNED = "abc\n";

		ByteArrayInputStream in = new ByteArrayInputStream(SCANNED.getBytes());
		System.setIn(in);

		Utils utils = new Utils();

		utils.getLongList();
	}
	
	@Test
	public void testGetLongList() {
		final String SCANNED = "1 2 3\n";
		final List<Long> RETURNED = new ArrayList<>();
		RETURNED.add(1L);
		RETURNED.add(2L);
		RETURNED.add(3L);

		ByteArrayInputStream in = new ByteArrayInputStream(SCANNED.getBytes());
		System.setIn(in);

		Utils utils = new Utils();

		assertEquals(RETURNED, utils.getLongList());
	}
}
