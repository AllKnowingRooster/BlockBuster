package com.blockbuster.authenticationService;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogger extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path=request.getServletPath();
		String method=request.getMethod();
		long start=System.currentTimeMillis();
		System.out.println(String.format("Method : %s | Path : %s",method,path));
		filterChain.doFilter(request, response);
		int status=response.getStatus();
		long duration=System.currentTimeMillis()-start;
		System.out.println(String.format("Method : %s | Path : %s | Duration : %d | Status : %d",method,path,duration,status));;
	}
	
	
	
}
