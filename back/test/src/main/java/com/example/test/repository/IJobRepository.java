package com.example.test.repository;

import java.util.List;
import java.util.Optional;

import com.example.test.ddd.JobDdd;

public interface IJobRepository {

	Optional<JobDdd> getJob(Long id);

	Optional<List<JobDdd>> getJobs();

	Optional<JobDdd> createJob(JobDdd Job);

}