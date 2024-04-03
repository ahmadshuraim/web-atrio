package com.example.test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test.ddd.UserDdd;
import com.example.test.model.User;

@Mapper(componentModel = "spring")
public interface UserEntityToDdd {

	UserEntityToDdd INSTANCE = Mappers.getMapper(UserEntityToDdd.class);

	UserDdd fromEntityToDdd(User user);

}
