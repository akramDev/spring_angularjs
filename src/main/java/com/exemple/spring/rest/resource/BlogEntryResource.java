package com.exemple.spring.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.exemple.spring.core.model.BlogEntry;

public class BlogEntryResource extends ResourceSupport {
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public BlogEntry toBlogEntry(){
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setTitle(title);
		return blogEntry;
	}

}
