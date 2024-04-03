package com.example.test.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.ddd.JobDdd;
import com.example.test.ddd.UserDdd;
import com.example.test.mappers.JobEntityToDdd;
import com.example.test.mappers.UserEntityToDdd;
import com.example.test.model.Job;
import com.example.test.model.User;
import com.example.test.repository.JobRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class JobService implements IJobService {

	@Autowired
	JobRepository jobRepository;
	
	private final EntityManager entityManager;

	public JobService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<JobDdd> getJob(Long id) {

		return this.jobRepository.getJob(id);
	}

	@Override
	public Optional<List<JobDdd>> getJobs() {

		return this.jobRepository.getJobs();
	}

	@Override
	public Optional<JobDdd> createJob(JobDdd job) {

		return this.jobRepository.createJob(job);
	}

	@Override
	public Optional<JobDdd> updateJob(Long id, JobDdd job) {

		Optional<JobDdd> existingJob = this.jobRepository.getJob(null);
		if (!existingJob.isPresent()) {
			throw new EntityNotFoundException("Job not found with id: " + id);
		}

		JobDdd existingJobDdd = existingJob.get();
		existingJobDdd.setEntrepriseName(job.getEntrepriseName());
		existingJobDdd.setPositionHeld(job.getPositionHeld());
		/*existingJobDdd.setJobStarded(job.getJobStarded());
		existingJobDdd.setJobEnded(job.getJobEnded());
		existingJobDdd.setOnGoing(job.isOnGoing());
		existingJobDdd.setUser(job.getUser());*/

		return this.jobRepository.createJob(existingJobDdd);

	}
	
	@Override
	public List<JobDdd> getJobsByDateRange(Long userId, Date startDate, Date endDate) {
		String jpql = "SELECT j FROM User u " +
                "JOIN u.job j " +
                "WHERE u.id = :userId " +
                "AND j.jobStarded BETWEEN :startDate AND :endDate";
		  TypedQuery<Job> query = entityManager.createQuery(jpql, Job.class);
		  query.setParameter("userId", userId);
		  query.setParameter("startDate", startDate);
		  query.setParameter("endDate", endDate);
		  List<Job> jobs = query.getResultList();
		    List<JobDdd> jobsDdd = new ArrayList<>();
			for (Job job : jobs) {
				jobsDdd.add(JobEntityToDdd.INSTANCE.fromJobEntityToDdd(job));
			}
			return jobsDdd;
		   
	    }


}
