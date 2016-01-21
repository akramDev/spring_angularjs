package com.exemple.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.exemple.spring.model.Car;
import com.exemple.spring.model.Engine;

public class CarTest {
	
	@Mock
	private Engine engine;
	
	@InjectMocks
	private Car car;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testWarn(){
		when(engine.getRpm()).thenReturn(6000);
		
		car.accelerate();
		
		assertEquals("slow down!", car.getWarnMessage());
		
	}

}
