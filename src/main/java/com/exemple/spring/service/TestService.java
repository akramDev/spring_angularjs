package com.exemple.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.spring.model.TestModel;
import com.exemple.spring.repository.TestRepository;

@Service("testService")
public class TestService {
	
	@Autowired
	private TestRepository testRepository;
	
	public TestModel getTest() {
		return testRepository.save(new TestModel("akram"));
	}
}
