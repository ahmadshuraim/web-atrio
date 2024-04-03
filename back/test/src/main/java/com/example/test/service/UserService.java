package com.example.test.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.ddd.UserDdd;
import com.example.test.dto.UserDto;
import com.example.test.mappers.UserDddToDto;
import com.example.test.mappers.UserEntityToDdd;
import com.example.test.model.Job;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	private final EntityManager entityManager;

	public UserService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<UserDdd> getUser(Long id) {

		return this.userRepository.getUser(id);
	}

	@Override
	public Optional<List<UserDdd>> getUsers() {

		return this.userRepository.getUsers();
	}

	@Override
	public Optional<UserDdd> createUser(UserDdd user) {

		return this.userRepository.createUser(user);
	}
	
	
    

	@Override
	public Optional<UserDdd> updateUser(Long id, UserDdd user) {
		Optional<UserDdd> existingUser = this.userRepository.getUser(id);
		if (!existingUser.isPresent()) {
			throw new EntityNotFoundException("User not found with id: " + id);
		}

		UserDdd existingUserDdd = existingUser.get();
		existingUserDdd.setLasteName(user.getLasteName());
		existingUserDdd.setFirstName(user.getFirstName());
		existingUserDdd.setDateOfBirth(user.getDateOfBirth());
		existingUserDdd.setJob(user.getJob());

		return this.userRepository.createUser(existingUserDdd);
	}

	@Override
	public List<UserDdd> getUsersByCompanyName(String companyName) {
	    
		String jpql = "SELECT DISTINCT u FROM User u JOIN u.job j WHERE j.entrepriseName = :companyName";
	    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
	    query.setParameter("companyName", companyName);
	    List<User> users = query.getResultList();
	    List<UserDdd> usersDdd = new ArrayList<>();
		for (User user : users) {
			usersDdd.add(UserEntityToDdd.INSTANCE.fromEntityToDdd(user));
		}
		return usersDdd;
	}

	@Override
	public List<UserDdd> findAllOrderedByLasteName() {
		return this.userRepository.findAllOrderedByLasteName();
	}

	
}
