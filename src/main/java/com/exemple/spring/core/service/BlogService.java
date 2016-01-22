package com.exemple.spring.core.service;

import com.exemple.spring.core.model.Blog;
import com.exemple.spring.core.model.BlogEntry;
import com.exemple.spring.core.service.util.BlogEntryList;
import com.exemple.spring.core.service.util.BlogList;

/**
 * Created by Chris on 6/28/14.
 */
public interface BlogService {
    /**
     * @param blogId the id of the blog to add this BlogEntry to
     * @param data the BlogEntry containing the data to be used for creating the new entity
     * @return the created BlogEntry with a generated ID
     * @throws tutorial.core.services.exceptions.BlogNotFoundException if the blog to add to cannot be found
     */
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data);

    public BlogList findAllBlogs(); 

    public BlogEntryList findAllBlogEntries(Long blogId); // findBlog all associated blog entries

    public Blog findBlog(Long eq);
}
