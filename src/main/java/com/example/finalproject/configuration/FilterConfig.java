package com.example.finalproject.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.finalproject.jwt.JwtFilters;


@Configuration
public class FilterConfig {
	
	
	@Bean
	public FilterRegistrationBean<JwtFilters> jwtFilter()
	{
		FilterRegistrationBean<JwtFilters> bean = new FilterRegistrationBean<>();
		
		bean.setFilter( new JwtFilters());
		bean.addUrlPatterns("/booking/*");
		bean.addUrlPatterns("/payment/*");
		bean.addUrlPatterns("/dashboard/*");

		
		
		return bean;
	}

}
