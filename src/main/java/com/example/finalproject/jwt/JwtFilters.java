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
		HttpServletResponse res = (HttpServletResponse) response;

		// Allow CORS preflight requests
		if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
			return;
		}
		
		
	
		String authHeader = req.getHeader("Authorization");
		
		if(authHeader == null || !authHeader.startsWith("Bearer "))
		{
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED ,"Missing Token");
			return;
		}
		
		
		String token = authHeader.substring(7);
		if(!JwtUtils.validate(token))
		{
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED ,"Invalid Token");
			return;
		}
		
		
		if(authHeader!= null &&authHeader.startsWith("Bearer "))
		{
			 token = authHeader.substring(7);
			Claims claims = JwtUtils.getClaims(token);
			
			req.setAttribute("id", claims.get("id" , Integer.class));
			req.setAttribute("name", claims.get("name" , String.class));
			req.setAttribute("role", claims.get("role" , String.class));

			
		}
		


		
		chain.doFilter(request, response);
	}

}
