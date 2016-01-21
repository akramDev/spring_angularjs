package com.exemple.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.test.model.TestModel;
import com.exemple.test.repository.TestRepository;

@Service("testService")
public class TestService {
	
	@Autowired
	private TestRepository testRepository;
	
	public TestModel getTest() {
		return testRepository.save(new TestModel("akram"));
	}
}
