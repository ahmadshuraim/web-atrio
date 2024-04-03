package com.example.test.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test.ddd.UserDdd;
import com.example.test.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserDddToDto {

	UserDddToDto INSTANCE = Mappers.getMapper(UserDddToDto.class);

	UserDto fromDddToDto(UserDdd user);

}
