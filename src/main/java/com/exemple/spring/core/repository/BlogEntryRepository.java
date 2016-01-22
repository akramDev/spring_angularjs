package com.exemple.spring.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exemple.spring.core.model.Blog;
import com.exemple.spring.core.model.BlogEntry;

@Repository("blogEntryRepository")
public interface BlogEntryRepository extends JpaRepository<BlogEntry, Long> {
	
	@Query(name = "BlogEntry.findByBlog")
	public List<BlogEntry> findByBlog(Blog blog);

}
