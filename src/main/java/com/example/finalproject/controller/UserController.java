package com.example.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.finalproject.dtos.LoginRequest;
import com.example.finalproject.dtos.LoginResponse;
import com.example.finalproject.jwt.JwtUtils;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.UserRepo;
import com.example.finalproject.service.UserService;


@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = {
//		"http://127.0.0.1:5500",
//		"http://localhost:5500"
//})
public class UserController {
	
	@Autowired
	UserRepo ur;
	
	@Autowired
	UserService userService;
	
	
    @PostMapping("/signup")
    public User signup(@RequestBody User newUser) {
    	
    	return this.userService.insertUser(newUser);
    	
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
    {
    	User foundUser = this.ur.findByEmail(loginRequest.getEmail());
    	if(foundUser!=null && foundUser.getPassword().equals(loginRequest.getPassword()))
    	{
    		//Generate Token and return it 
    		String token = JwtUtils.generateToken(foundUser.getId(),foundUser.getName(),foundUser.getRole());
    		
    		LoginResponse response  = new LoginResponse(token,foundUser.getName(),foundUser.getRole().toString());
    		
    		return ResponseEntity.ok(response);
    		
    	}
    	
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
