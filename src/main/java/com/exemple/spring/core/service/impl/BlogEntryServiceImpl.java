package com.exemple.spring.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exemple.spring.core.model.BlogEntry;
import com.exemple.spring.core.repository.BlogEntryRepository;
import com.exemple.spring.core.service.BlogEntryService;

@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {

	@Autowired
    private BlogEntryRepository blogEntryRepository;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return blogEntryRepository.findOne(id);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
    	BlogEntry blogEntry = blogEntryRepository.findOne(id);
    	blogEntryRepository.delete(id);
        return blogEntry;
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
    	BlogEntry blogEntry = blogEntryRepository.findOne(id);
    	blogEntry.setTitle(data.getTitle());
    	blogEntry.setContent(data.getContent());
    			
        return blogEntryRepository.save(blogEntry);
    }
}
