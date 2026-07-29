package com.example.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finalproject.model.User;

@Repository
public interface UserRepo  extends JpaRepository<User, Integer>{

	User findByEmail(String email);
}
