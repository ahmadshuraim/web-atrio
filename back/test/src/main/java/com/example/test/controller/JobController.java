package com.example.test.controller;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.ddd.JobDdd;
import com.example.test.dto.JobDto;
import com.example.test.mappers.JobDddToDto;
import com.example.test.mappers.JobDtoToDdd;
import com.example.test.service.IJobService;
import java.text.ParseException;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	IJobService iJobService;

	@RequestMapping("/{id}")
	public ResponseEntity<JobDto> getJob(@PathVariable Long id) {

		Optional<JobDdd> jobDdd = iJobService.getJob(id);
		if (jobDdd.isPresent()) {
			JobDto jobDto = JobDddToDto.INSTANCE.fromJobDddToDto(jobDdd.get());
			return new ResponseEntity<>(jobDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping
	public ResponseEntity<List<JobDto>> getJobs() {

		Optional<List<JobDdd>> jobsDdd = iJobService.getJobs();
		if (jobsDdd.isEmpty()) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
		}
		List<JobDto> jobsDto = new ArrayList<>();
		for (JobDdd JobDdd : jobsDdd.get()) {
			jobsDto.add(JobDddToDto.INSTANCE.fromJobDddToDto(JobDdd));
		}

		return new ResponseEntity<>(jobsDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<JobDto> createJob(@RequestBody JobDto job) {

		JobDdd jobDdd = JobDtoToDdd.INSTANCE.fromJobDtoToDdd(job);

		Optional<JobDdd> createdUserDdd = iJobService.createJob(jobDdd);
		if (createdUserDdd.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		JobDto jobDto = JobDddToDto.INSTANCE.fromJobDddToDto(createdUserDdd.get());
		return new ResponseEntity<>(jobDto, HttpStatus.OK);

	}
	
	@RequestMapping("/byDateRange")
	public ResponseEntity<List<JobDto>> getJobsByDateRange(
			@RequestParam("userId") Long userId,
			@RequestParam("startDate") String startDateChaine,
			@RequestParam("endDate") String endDateChaine) throws ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDateAsDate = formatter.parse(startDateChaine);
		Date endDateAsDate = formatter.parse(endDateChaine);
		
		
		List<JobDdd> jobsDdd = iJobService.getJobsByDateRange(userId,startDateAsDate,endDateAsDate);
		if (jobsDdd.isEmpty()) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
		}
		
		List<JobDto> jobsDto = new ArrayList<>();
		for (JobDdd JobDdd : jobsDdd) {
			jobsDto.add(JobDddToDto.INSTANCE.fromJobDddToDto(JobDdd));
		}

		return new ResponseEntity<>(jobsDto, HttpStatus.OK);
		
	}

}
