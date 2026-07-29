package com.example.finalproject.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.model.User;
import com.example.finalproject.repository.UserRepo;
import com.example.finalproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo uRepo;


	@Override
	public User insertUser(User us) {
	User createnewUser= uRepo.save(us);
	return createnewUser;
	
	}

}
