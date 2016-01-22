package com.exemple.spring.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exemple.spring.core.model.Account;
import com.exemple.spring.core.model.Blog;
import com.exemple.spring.core.repository.AccountRepository;
import com.exemple.spring.core.repository.BlogRepository;
import com.exemple.spring.core.service.AccountService;
import com.exemple.spring.core.service.exceptions.AccountDoesNotExistException;
import com.exemple.spring.core.service.exceptions.AccountExistsException;
import com.exemple.spring.core.service.exceptions.BlogExistsException;
import com.exemple.spring.core.service.util.AccountList;
import com.exemple.spring.core.service.util.BlogList;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
    private AccountRepository accountRepository;
	
	@Autowired
    private BlogRepository blogRepository;

    @Override
    public Account findAccount(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepository.findByName(data.getName());
        if(account != null)
        {
            throw new AccountExistsException();
        }
        return accountRepository.save(data);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        Blog blogSameTitle = blogRepository.findByTitle(data.getTitle());

        if(blogSameTitle != null)
        {
            throw new BlogExistsException();
        }

        Account account = accountRepository.findOne(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }

        Blog createdBlog = blogRepository.save(data);

        createdBlog.setOwner(account);

        return createdBlog;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountRepository.findOne(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }
        return new BlogList(blogRepository.findByOwner(account));
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepository.findAll());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepository.findByName(name);
    }
}
