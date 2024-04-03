package com.example.test.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.ddd.JobDdd;
import com.example.test.mappers.JobDddToEntity;
import com.example.test.mappers.JobEntityToDdd;
import com.example.test.model.Job;

@Repository
public class JobRepository implements IJobRepository {

	@Autowired
	JobRepopsitoryDao JobRepopsitoryDao;

	@Override
	public Optional<JobDdd> getJob(Long id) {
		Optional<Job> Job = this.JobRepopsitoryDao.findById(id);
		if (Job.isPresent()) {
			return Optional.of(JobEntityToDdd.INSTANCE.fromJobEntityToDdd(Job.get()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<JobDdd>> getJobs() {
		List<Job> jobs = this.JobRepopsitoryDao.findAll();
		List<JobDdd> jobsDdd = new ArrayList<>();
		for (Job job : jobs) {
			jobsDdd.add(JobEntityToDdd.INSTANCE.fromJobEntityToDdd(job));
		}

		return Optional.of(jobsDdd);
	}

	@Override
	public Optional<JobDdd> createJob(JobDdd job) {
		if (job != null) {
			Job jobEntity = JobDddToEntity.INSTANCE.fromJobDddToEntity(job);

			return Optional.of(
					JobEntityToDdd.INSTANCE.fromJobEntityToDdd(this.JobRepopsitoryDao.save(jobEntity)));
		}
		return Optional.empty();
	}

}
