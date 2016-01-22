package com.exemple.spring.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exemple.spring.core.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query(name = "Account.findByName")
	public Account findByName(String name);

}
