package com.example.test.dto;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.test.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDto {

	public Long id;
	public String entrepriseName;
	public String positionHeld;
	public Date jobStarded;
	public Date jobEnded;
	public boolean onGoing;


}
