package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.Action;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ActionTest {
	
	@Mock
	private Utils utils;
	
	@Test
	public void testGetDescription() {
		Action action = Action.UPDATE;
		
		assertEquals("UPDATE: To change an entity already in the database", action.getDescription());
	}
	
	@Test
	public void testPrintAction() {
		Action.printActions();
	}
	
	@Test
	public void testGetAction() {
		final String ACTION = "read";
		final Action got = Action.READ;
		
		Mockito.when(utils.getString()).thenReturn("notGood", ACTION);
		
		Action.getAction(utils);
		assertEquals(got, Action.getAction(utils));
	}
	
}
