package com.exemple.spring.core.service;

import com.exemple.spring.core.model.Account;
import com.exemple.spring.core.model.Blog;
import com.exemple.spring.core.service.util.AccountList;
import com.exemple.spring.core.service.util.BlogList;

public interface AccountService {
    
	public Account findAccount(Long id);
    
	public Account createAccount(Account data);
    
	public Blog createBlog(Long accountId, Blog data);

	public BlogList findBlogsByAccount(Long accountId);

	public AccountList findAllAccounts();

	public Account findByAccountName(String name);
}
