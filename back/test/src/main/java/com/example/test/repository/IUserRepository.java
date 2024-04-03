package com.example.test.repository;

import java.util.List;
import java.util.Optional;

import com.example.test.ddd.UserDdd;

public interface IUserRepository {

	Optional<UserDdd> getUser(Long id);

	Optional<List<UserDdd>> getUsers();

	Optional<UserDdd> createUser(UserDdd user);
	
	List<UserDdd> findAllOrderedByLasteName();

}
