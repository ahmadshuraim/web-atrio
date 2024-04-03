package com.example.test.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.ddd.UserDdd;
import com.example.test.mappers.UserDddToEntity;
import com.example.test.mappers.UserEntityToDdd;
import com.example.test.model.User;

@Repository
public class UserRepository implements IUserRepository {

	@Autowired
	UserRepopsitoryDao userRepopsitoryDao;

	@Override
	public Optional<UserDdd> getUser(Long id) {
		Optional<User> user = this.userRepopsitoryDao.findById(id);
		if (user.isPresent()) {
			return Optional.of(UserEntityToDdd.INSTANCE.fromEntityToDdd(user.get()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<UserDdd>> getUsers() {

		List<User> users = this.userRepopsitoryDao.findAll();
		List<UserDdd> usersDdd = new ArrayList<>();
		for (User user : users) {
			usersDdd.add(UserEntityToDdd.INSTANCE.fromEntityToDdd(user));
		}

		return Optional.of(usersDdd);
	}

	@Override
	public Optional<UserDdd> createUser(UserDdd user) {
		if (user != null) {
			User userEntity = UserDddToEntity.INSTANCE.fromUserDddToEntity(user);

			return Optional.of(UserEntityToDdd.INSTANCE.fromEntityToDdd(this.userRepopsitoryDao.save(userEntity)));
		}
		return Optional.empty();
	}

	public List<UserDdd> findAllOrderedByLasteName() {
		List<User> users = this.userRepopsitoryDao.findAllOrderedByLasteName();
		List<UserDdd> usersDdd = new ArrayList<>();
		for (User user : users) {
			usersDdd.add(UserEntityToDdd.INSTANCE.fromEntityToDdd(user));
		}
		return usersDdd;
	}

}
