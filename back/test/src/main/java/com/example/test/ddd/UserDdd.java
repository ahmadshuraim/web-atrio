package com.example.test.ddd;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.test.model.Job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDdd {

	public Long id;
	public String lasteName;
	public String firstName;
	public Date dateOfBirth;
	public List<Job> job;

}


