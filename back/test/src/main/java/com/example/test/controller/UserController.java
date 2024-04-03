package com.example.test.controller;

import java.util.ArrayList;
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

import com.example.test.ddd.UserDdd;
import com.example.test.dto.UserDto;
import com.example.test.mappers.UserDddToDto;
import com.example.test.mappers.UserDtoToDdd;
import com.example.test.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService iUserService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		UserDdd userDdd = UserDtoToDdd.INSTANCE.fromUserDtoToDdd(user);
		Optional<UserDdd> createdUser = this.iUserService.createUser(userDdd);
		System.out.println("////////////////////////////////////////////////////////////////////////////////");
		System.out.println(user.getDateOfBirth());
		
		System.out.println(createdUser.get().getFirstName());
		
		
		System.out.println("////////////////////////////////////////////////////////////////////////////////");
		if (createdUser.isPresent()) {
			return new ResponseEntity<>(UserDddToDto.INSTANCE.fromDddToDto(createdUser.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {

		Optional<List<UserDdd>> usersDdd = this.iUserService.getUsers();
		if (!usersDdd.isEmpty()) {
			List<UserDto> usersDto = new ArrayList<>();
			for (UserDdd userDdd : usersDdd.get()) {
				usersDto.add(UserDddToDto.INSTANCE.fromDddToDto(userDdd));
			}
			return new ResponseEntity<>(usersDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {

		Optional<UserDdd> userDdd = this.iUserService.getUser(id);
		if (userDdd.isPresent()) {
			return new ResponseEntity<>(UserDddToDto.INSTANCE.fromDddToDto(userDdd.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping("/message")
	public ResponseEntity<String> hello() {

		return new ResponseEntity<>("Heelo", HttpStatus.OK);

	}
	
	@RequestMapping("/byCompanyName")
	public ResponseEntity<List<UserDto>> getUsersByCompanyName(@RequestParam("companyName") String companyName) {

		List<UserDdd> usersDdd = this.iUserService.getUsersByCompanyName(companyName);
		if (!usersDdd.isEmpty()) {
			List<UserDto> usersDto = new ArrayList<>();
			for (UserDdd userDdd : usersDdd) {
				usersDto.add(UserDddToDto.INSTANCE.fromDddToDto(userDdd));
			}
			return new ResponseEntity<>(usersDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@RequestMapping("/byNameAsc")
    public ResponseEntity<List<UserDto>> findAllOrderedByLasteName() {
		
		List<UserDdd> usersDdd = this.iUserService.findAllOrderedByLasteName();
		if (!usersDdd.isEmpty()) {
			List<UserDto> usersDto = new ArrayList<>();
			for (UserDdd userDdd : usersDdd) {
				usersDto.add(UserDddToDto.INSTANCE.fromDddToDto(userDdd));
			}
			return new ResponseEntity<>(usersDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

    }

}
