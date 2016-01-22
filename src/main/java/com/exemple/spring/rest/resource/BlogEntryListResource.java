package com.exemple.spring.rest.resource;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Chris on 6/28/14.
 */
public class BlogEntryListResource extends ResourceSupport {

    private List<BlogEntryResource> entries;

    public List<BlogEntryResource> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntryResource> entries) {
        this.entries = entries;
    }
}
