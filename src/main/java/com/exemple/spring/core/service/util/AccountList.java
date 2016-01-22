package com.exemple.spring.core.service.util;

import java.util.ArrayList;
import java.util.List;

import com.exemple.spring.core.model.Account;

public class AccountList {

    private List<Account> accounts = new ArrayList<Account>();

    public AccountList(List<Account> list) {
        this.accounts = list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
