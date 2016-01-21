package com.exemple.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.exemple.spring.controller.BlogEntryController;

public class BlogEntryControllerTest {
	
	@InjectMocks
	private BlogEntryController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void test() throws Exception{
		mockMvc.perform(post("/test").content("{\"title\":\"title\"}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print());
	}

}
