package com.example.test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test.ddd.JobDdd;
import com.example.test.model.Job;

@Mapper(componentModel = "spring")
public interface JobDddToEntity {

	JobDddToEntity INSTANCE = Mappers.getMapper(JobDddToEntity.class);

	Job fromJobDddToEntity(JobDdd job);

}
