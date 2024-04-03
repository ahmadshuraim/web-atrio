package com.example.test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test.ddd.JobDdd;
import com.example.test.dto.JobDto;

@Mapper(componentModel = "spring")
public interface JobDddToDto {

	JobDddToDto INSTANCE = Mappers.getMapper(JobDddToDto.class);

	JobDto fromJobDddToDto(JobDdd job);

}
