package com.example.test.service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.test.ddd.JobDdd;

public interface IJobService {

	Optional<JobDdd> getJob(Long id);

	Optional<List<JobDdd>> getJobs();

	Optional<JobDdd> createJob(JobDdd job);

	Optional<JobDdd> updateJob(Long id, JobDdd job);
	
	List<JobDdd> getJobsByDateRange(Long userId, Date startDate, Date endDate);

}
