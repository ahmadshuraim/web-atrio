package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.model.Job;

public interface JobRepopsitoryDao extends JpaRepository<Job, Long> {

}
