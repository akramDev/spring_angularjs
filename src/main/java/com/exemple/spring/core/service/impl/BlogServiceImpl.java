package com.exemple.spring.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exemple.spring.core.model.Blog;
import com.exemple.spring.core.model.BlogEntry;
import com.exemple.spring.core.repository.BlogEntryRepository;
import com.exemple.spring.core.repository.BlogRepository;
import com.exemple.spring.core.service.BlogService;
import com.exemple.spring.core.service.exceptions.BlogNotFoundException;
import com.exemple.spring.core.service.util.BlogEntryList;
import com.exemple.spring.core.service.util.BlogList;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
    private BlogRepository blogRepository;

	@Autowired
    private BlogEntryRepository blogEntryRepository;

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
        Blog blog = blogRepository.findOne(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        BlogEntry blogEntry = blogEntryRepository.save(data);
        blogEntry.setBlog(blog);
        return blogEntry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogRepository.findAll());
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogRepository.findOne(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId, blogEntryRepository.findByBlog(blog));
    }

    @Override
    public Blog findBlog(Long id) {
        return blogRepository.findOne(id);
    }
}
