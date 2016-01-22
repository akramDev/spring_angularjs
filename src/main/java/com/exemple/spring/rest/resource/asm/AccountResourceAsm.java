package com.exemple.spring.rest.resource.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.exemple.spring.core.model.Account;
import com.exemple.spring.rest.controller.AccountController;
import com.exemple.spring.rest.resource.AccountResource;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {

	public AccountResourceAsm() {
		super(Account.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(Account account) {
		AccountResource accountResource = new AccountResource();
		accountResource.setName(account.getName());
		accountResource.setPassword(account.getPassword());
		accountResource.add(linkTo(methodOn(AccountController.class)).withSelfRel());
		return accountResource;
	}

}
