package com.exemple.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.test.model.TestModel;

@Repository("testRepository")
public interface TestRepository extends JpaRepository<TestModel, Long> {

}
