package com.example.test.dto;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	public Long id;
	public String lasteName;
	public String firstName;
	public Date dateOfBirth;
	public List<Job> job;

}
