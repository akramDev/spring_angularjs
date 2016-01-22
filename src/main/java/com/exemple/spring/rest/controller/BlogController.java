package com.exemple.spring.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exemple.spring.core.model.Blog;
import com.exemple.spring.core.model.BlogEntry;
import com.exemple.spring.core.service.BlogService;
import com.exemple.spring.core.service.exceptions.BlogNotFoundException;
import com.exemple.spring.core.service.util.BlogEntryList;
import com.exemple.spring.core.service.util.BlogList;
import com.exemple.spring.rest.exceptions.NotFoundException;
import com.exemple.spring.rest.resource.BlogEntryListResource;
import com.exemple.spring.rest.resource.BlogEntryResource;
import com.exemple.spring.rest.resource.BlogListResource;
import com.exemple.spring.rest.resource.BlogResource;
import com.exemple.spring.rest.resource.asm.BlogEntryListResourceAsm;
import com.exemple.spring.rest.resource.asm.BlogEntryResourceAsm;
import com.exemple.spring.rest.resource.asm.BlogListResourceAsm;
import com.exemple.spring.rest.resource.asm.BlogResourceAsm;

@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

	private BlogService blogService;

	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<BlogListResource> findAllBlogs() {
		BlogList blogList = blogService.findAllBlogs();
		BlogListResource blogListRes = new BlogListResourceAsm()
				.toResource(blogList);
		return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
	}

	@RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId) {
		Blog blog = blogService.findBlog(blogId);
		BlogResource res = new BlogResourceAsm().toResource(blog);
		return new ResponseEntity<BlogResource>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.POST)
	public ResponseEntity<BlogEntryResource> createBlogEntry(
			@PathVariable Long blogId,
			@RequestBody BlogEntryResource sentBlogEntry) {
		BlogEntry createdBlogEntry = null;
		try {
			createdBlogEntry = blogService.createBlogEntry(blogId,
					sentBlogEntry.toBlogEntry());
			BlogEntryResource createdResource = new BlogEntryResourceAsm()
					.toResource(createdBlogEntry);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdResource.getLink("self")
					.getHref()));
			return new ResponseEntity<BlogEntryResource>(createdResource,
					headers, HttpStatus.CREATED);
		} catch (BlogNotFoundException e) {
			throw new NotFoundException(e);
		}
	}

	@RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.GET)
	public ResponseEntity<BlogEntryListResource> findAllBlogEntries(
			@PathVariable Long blogId) {
		try {
			BlogEntryList list = blogService.findAllBlogEntries(blogId);
			BlogEntryListResource res = new BlogEntryListResourceAsm()
					.toResource(list);
			return new ResponseEntity<BlogEntryListResource>(res, HttpStatus.OK);
		} catch (BlogNotFoundException exception) {
			throw new NotFoundException(exception);
		}
	}

}
