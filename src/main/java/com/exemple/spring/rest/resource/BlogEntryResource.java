package com.exemple.spring.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.exemple.spring.core.model.BlogEntry;

public class BlogEntryResource extends ResourceSupport {
	
	private String title;
	
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BlogEntry toBlogEntry(){
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setTitle(title);
		blogEntry.setContent(content);
		return blogEntry;
	}

}
