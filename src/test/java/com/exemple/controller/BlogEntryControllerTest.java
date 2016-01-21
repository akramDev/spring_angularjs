package com.exemple.controller;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.exemple.spring.model.BlogEntry;
import com.exemple.spring.rest.controller.BlogEntryController;
import com.exemple.spring.service.BlogEntryService;

public class BlogEntryControllerTest {
	
	@InjectMocks
	private BlogEntryController controller;
	
	@Mock
	private BlogEntryService blogEntryService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void test() throws Exception{
		mockMvc.perform(post("/test").content("{\"title\":\"title\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.title", is("Test blog entry"))).andDo(print());
	}
	
	@Test
	public void getExistingBlogEntry() throws Exception{
		
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setId(1l);
		blogEntry.setTitle("First Title");
		
		when(blogEntryService.find(1l)).thenReturn(blogEntry);
		
		mockMvc.perform(get("/rest/blog-entries/1")).andDo(print())
			.andExpect(jsonPath("$.title", equalTo("First Title")))
			.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
			.andExpect(status().isOk());
		
	}

}
