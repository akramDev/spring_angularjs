package com.exemple.spring.rest.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exemple.spring.core.model.Account;
import com.exemple.spring.core.model.Blog;
import com.exemple.spring.core.service.AccountService;
import com.exemple.spring.core.service.exceptions.AccountDoesNotExistException;
import com.exemple.spring.core.service.exceptions.AccountExistsException;
import com.exemple.spring.core.service.exceptions.BlogExistsException;
import com.exemple.spring.rest.exceptions.BadRequestException;
import com.exemple.spring.rest.exceptions.ConflictException;
import com.exemple.spring.rest.resource.AccountResource;
import com.exemple.spring.rest.resource.BlogResource;
import com.exemple.spring.rest.resource.asm.AccountResourceAsm;
import com.exemple.spring.rest.resource.asm.BlogResourceAsm;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccountResource> createAccount(
			@RequestBody AccountResource sentAccount) {
		try {
			Account createdAccount = accountService.createAccount(sentAccount
					.toAccount());
			AccountResource res = new AccountResourceAsm()
					.toResource(createdAccount);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(res.getLink("self").getHref()));
			return new ResponseEntity<AccountResource>(res, headers,
					HttpStatus.CREATED);
		} catch (AccountExistsException exception) {
			throw new ConflictException(exception);
		}
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(
			@PathVariable Long accountId) {
		Account account = accountService.findAccount(accountId);
		if (account != null) {
			AccountResource res = new AccountResourceAsm().toResource(account);
			return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
	public ResponseEntity<BlogResource> createBlog(
			@PathVariable Long accountId, @RequestBody BlogResource res) {
		try {
			Blog createdBlog = accountService.createBlog(accountId,
					res.toBlog());
			BlogResource createdBlogRes = new BlogResourceAsm()
					.toResource(createdBlog);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdBlogRes.getLink("self")
					.getHref()));
			return new ResponseEntity<BlogResource>(createdBlogRes, headers,
					HttpStatus.CREATED);
		} catch (AccountDoesNotExistException exception) {
			throw new BadRequestException(exception);
		} catch (BlogExistsException exception) {
			throw new ConflictException(exception);
		}
	}

}
