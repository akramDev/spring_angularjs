package com.exemple.spring.rest.resource.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.exemple.spring.core.service.util.BlogList;
import com.exemple.spring.rest.controller.BlogController;
import com.exemple.spring.rest.resource.BlogListResource;

/**
 * Created by Chris on 7/1/14.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm()
    {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource res = new BlogListResource();
        res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return res;
    }
}
