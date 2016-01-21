package com.exemple.spring.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exemple.spring.model.BlogEntry;
import com.exemple.spring.rest.resource.BlogEntryResource;
import com.exemple.spring.rest.resource.asm.BlogEntryResourceAsm;
import com.exemple.spring.service.BlogEntryService;

@Controller
public class BlogEntryController {
	
	public BlogEntryService blogEntryService;
	
	public BlogEntryController(BlogEntryService service) {
		this.blogEntryService = service;
	}
	
	@RequestMapping(value="/test", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BlogEntry test(@RequestBody BlogEntry entry){
		
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setTitle("Test blog entry");
		
		return blogEntry;
	}
	
	@RequestMapping(value = "/rest/blog-entries/{blogEntryId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BlogEntryResource getBlogEntry(@PathVariable Long blogEntryId){
		BlogEntry blogEntry = blogEntryService.find(blogEntryId);
		BlogEntryResource res = new BlogEntryResourceAsm().toResource(blogEntry);
		return res;
	}

}
