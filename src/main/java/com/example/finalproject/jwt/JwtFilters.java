package com.example.finalproject.jwt;

import java.io.IOException;


import io.jsonwebtoken.Claims;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilters implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		
	
		String authHeader = req.getHeader("Authorization");
		
		if(authHeader!= null &&authHeader.startsWith("Bearer "))
		{
			String token = authHeader.substring(7);
			Claims claims = JwtUtils.getClaims(token);
			
			req.setAttribute("id", claims.get("id" , Integer.class));
			req.setAttribute("name", claims.get("name" , String.class));
			
		}
		
		if(authHeader == null || !authHeader.startsWith("Bearer "))
		{
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED ,"Missing Token");
			return;
		}
		
		String token = authHeader.substring(7);
		if(!JwtUtils.validate(token))
		{
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED ,"Invalid Token");
			return;
		}
		
		chain.doFilter(request, response);
	}

}
