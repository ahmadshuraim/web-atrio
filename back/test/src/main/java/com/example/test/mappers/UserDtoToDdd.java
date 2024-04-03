package com.example.test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test.ddd.UserDdd;
import com.example.test.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserDtoToDdd {

	UserDtoToDdd INSTANCE = Mappers.getMapper(UserDtoToDdd.class);

	UserDdd fromUserDtoToDdd(UserDto user);

}
