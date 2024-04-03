package com.example.test.service;

import java.util.List;
import java.util.Optional;

import com.example.test.ddd.UserDdd;

public interface IUserService {

	Optional<UserDdd> getUser(Long id);

	Optional<List<UserDdd>> getUsers();

	Optional<UserDdd> createUser(UserDdd user);

	Optional<UserDdd> updateUser(Long id, UserDdd user);

	List<UserDdd> getUsersByCompanyName(String companyName);
	
	List<UserDdd> findAllOrderedByLasteName();

}
