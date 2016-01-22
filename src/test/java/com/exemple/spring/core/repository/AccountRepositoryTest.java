package com.exemple.spring.core.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.exemple.spring.core.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext.xml")
public class AccountRepositoryTest {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Before
	@Transactional
	@Rollback(false)
	public void setup(){
		Account account = new Account();
		account.setName("test");
		account.setPassword("test");
		accountRepository.save(account);
	}
	
	@Test
	public void test(){
		List<Account> accounts = accountRepository.findAll();
		assertEquals(1, accounts.size());
	}

}
