package com.exemple.spring.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exemple.spring.core.model.Account;
import com.exemple.spring.core.model.Blog;

@Repository("blogRepository")
public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	@Query(name = "Blog.findByTitle")
	public Blog findByTitle(String title);
	
	@Query(name = "Blog.findByOwner")
	public List<Blog> findByOwner(Account owner);

}
