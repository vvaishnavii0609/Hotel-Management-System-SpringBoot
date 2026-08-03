package com.example.finalproject.controller;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.dtos.DashboardResponse;
import com.example.finalproject.service.DashboardService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	DashboardService service;
	
	@GetMapping
	public ResponseEntity<DashboardResponse>getDashboard(HttpServletRequest request)
	{
		String role = (String)request.getAttribute("role");
		
		return ResponseEntity.ok(service.getDashboard(role));
		
		

	}

}
