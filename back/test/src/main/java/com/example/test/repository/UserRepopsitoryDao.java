package com.example.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.test.model.User;

public interface UserRepopsitoryDao extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u ORDER BY u.lasteName ASC")
    List<User> findAllOrderedByLasteName();

}
