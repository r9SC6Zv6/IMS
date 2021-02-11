package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class DomainTest {
	
	@Mock
	private Utils utils;

	@Test
	public void testGetDescription() {
		Domain domain = Domain.CUSTOMER;
		
		assertEquals("    CUSTOMER:  Information about customers", domain.getDescription());
	}
	
	@Test
	public void testGetDomain() {
		final String DOMAIN = "customer";
		final Domain got = Domain.CUSTOMER;
		
		Mockito.when(utils.getString()).thenReturn("notGood", DOMAIN);
		
		Domain.getDomain(utils);
		assertEquals(got, Domain.getDomain(utils));
	}
	
}
