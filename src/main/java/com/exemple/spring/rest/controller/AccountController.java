package com.exemple.spring.rest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exemple.spring.core.model.Account;
import com.exemple.spring.core.model.Blog;
import com.exemple.spring.core.service.AccountService;
import com.exemple.spring.core.service.exceptions.AccountDoesNotExistException;
import com.exemple.spring.core.service.exceptions.AccountExistsException;
import com.exemple.spring.core.service.exceptions.BlogExistsException;
import com.exemple.spring.core.service.util.AccountList;
import com.exemple.spring.core.service.util.BlogList;
import com.exemple.spring.rest.exceptions.ConflictException;
import com.exemple.spring.rest.exceptions.NotFoundException;
import com.exemple.spring.rest.resource.AccountListResource;
import com.exemple.spring.rest.resource.AccountResource;
import com.exemple.spring.rest.resource.BlogListResource;
import com.exemple.spring.rest.resource.BlogResource;
import com.exemple.spring.rest.resource.asm.AccountListResourceAsm;
import com.exemple.spring.rest.resource.asm.AccountResourceAsm;
import com.exemple.spring.rest.resource.asm.BlogListResourceAsm;
import com.exemple.spring.rest.resource.asm.BlogResourceAsm;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
 //   @PreAuthorize("permitAll")
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value="name", required = false) String name, @RequestParam(value="password", required = false) String password) {
        AccountList list = null;
        if(name == null) {
            list = accountService.findAllAccounts();
        } else {
            Account account = accountService.findByAccountName(name);
            list = new AccountList(new ArrayList<Account>());
            if(account != null) {
                if(password != null) {
                    if(account.getPassword().equals(password)) {
                        list = new AccountList(Arrays.asList(account));
                    }
                } else {
                    list = new AccountList(Arrays.asList(account));
                }
            }
        }
        AccountListResource res = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> createAccount(
            @RequestBody AccountResource sentAccount
    ) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
        } catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping( value="/{accountId}",
                method = RequestMethod.GET)
//    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> getAccount(
            @PathVariable Long accountId
    ) {
        Account account = accountService.findAccount(accountId);
        if(account != null)
        {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{accountId}/blogs",
        method = RequestMethod.POST)
//    @PreAuthorize("permitAll")
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable Long accountId,
            @RequestBody BlogResource res)
    {
   //     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //if(principal instanceof UserDetails) {
 //           UserDetails details = (UserDetails)principal;
  //          Account loggedIn = accountService.findByAccountName(details.getUsername());
 //           if(loggedIn.getId() == accountId) {
                try {
                    Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
                    BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
                    return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
                } catch(AccountDoesNotExistException exception)
                {
                    throw new NotFoundException(exception);
                } catch(BlogExistsException exception)
                {
                    throw new ConflictException(exception);
                }
//            } else {
//                throw new ForbiddenException();
//            }
//        } else {
//            throw new ForbiddenException();
//        }
    }

    @RequestMapping(value="/{accountId}/blogs",
            method = RequestMethod.GET)
//    @PreAuthorize("permitAll")
    public ResponseEntity<BlogListResource> findAllBlogs(
            @PathVariable Long accountId) {
        try {
            BlogList blogList = accountService.findBlogsByAccount(accountId);
            BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
        } catch(AccountDoesNotExistException exception)
        {
            throw new NotFoundException(exception);
        }
    }



}
