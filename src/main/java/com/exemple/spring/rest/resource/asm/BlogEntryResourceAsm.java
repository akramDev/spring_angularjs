package com.exemple.spring.rest.resource.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.exemple.spring.core.model.BlogEntry;
import com.exemple.spring.rest.controller.BlogEntryController;
import com.exemple.spring.rest.resource.BlogEntryResource;

public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {

	public BlogEntryResourceAsm() {
		super(BlogEntryController.class, BlogEntryResource.class);
	}

	@Override
	public BlogEntryResource toResource(BlogEntry blogEntry) {
		BlogEntryResource blogEntryResource = new BlogEntryResource();
		blogEntryResource.setTitle(blogEntry.getTitle());
		Link link = linkTo(methodOn(BlogEntryController.class).getBlogEntry(blogEntry.getId())).withSelfRel();
		blogEntryResource.add(link);
		return blogEntryResource;
	}

}
