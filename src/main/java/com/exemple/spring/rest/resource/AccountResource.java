package com.exemple.spring.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.exemple.spring.core.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AccountResource extends ResourceSupport {
	
	private Long rid;
    
	private String name;

    private String password;

    public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        return account;
    }
}
