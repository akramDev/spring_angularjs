package com.exemple.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.exemple.spring.service.Calculator;

public class CalculatorTest {
	
	@Mock
	private Calculator calc;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAbs(){
		
		when(calc.abs(-20)).thenReturn(20);
		assertEquals(calc.abs(-20), 20);
	}

}
