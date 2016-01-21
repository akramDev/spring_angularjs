package com.exemple.spring.service;

import com.exemple.spring.model.BlogEntry;

public interface BlogEntryService {
	
	public BlogEntry find(Long id);
	
	public BlogEntry delete(Long id);
	
	public BlogEntry update(Long id, BlogEntry blogEntry);

}
