package com.example.test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test.ddd.JobDdd;
import com.example.test.model.Job;

@Mapper(componentModel = "spring")
public interface JobEntityToDdd {

	JobEntityToDdd INSTANCE = Mappers.getMapper(JobEntityToDdd.class);

	JobDdd fromJobEntityToDdd(Job job);

}
