package com.exemple.spring.core.service;

import com.exemple.spring.core.model.Account;
import com.exemple.spring.core.model.Blog;

public interface AccountService {
    
	public Account findAccount(Long id);
    
	public Account createAccount(Account data);
    
	public Blog createBlog(Long accountId, Blog data);
}
