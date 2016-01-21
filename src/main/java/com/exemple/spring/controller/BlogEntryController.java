package com.exemple.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exemple.spring.model.BlogEntry;

@Controller
public class BlogEntryController {
	
	@RequestMapping(value="/test", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BlogEntry test(@RequestBody BlogEntry entry){
		
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setTitle("Test blog entry");
		
		return blogEntry;
	}

}
