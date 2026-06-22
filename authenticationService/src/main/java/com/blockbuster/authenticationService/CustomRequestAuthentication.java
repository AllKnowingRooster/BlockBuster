package com.blockbuster.authenticationService;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blockbuster.dto.ErrorResponse;
import com.blockbuster.dto.SessionData;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwtProvider.impl.JwtServiceImpl;
import redisManager.impl.RedisServiceImpl;
import tools.jackson.databind.ObjectMapper;

@Component
public class CustomRequestAuthentication extends OncePerRequestFilter{

	@Autowired
	RedisServiceImpl redisService;
	
	@Autowired
	JwtServiceImpl jwtService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path=request.getRequestURI();
		 return path.startsWith("/api/users/signin")
			        || path.startsWith("/api/users/signup");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorization=request.getHeader("Authorization");
		if(authorization==null || !authorization.startsWith("Bearer ")) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(),objectMapper.writeValueAsString(new ErrorResponse("Header Not Valid")));
			return;
		}
	
		String token=authorization.substring(7);
		try {
			Claims claim=jwtService.validateToken(token);
			String sid=claim.get("sid",String.class);
			String jti=claim.get("jti",String.class);
			String key=String.format("session:%s",sid);
			String blacklistKey=String.format("blacklist:%s",jti);
			
			if(redisService.exists(blacklistKey)) {
				response.sendError(HttpStatus.UNAUTHORIZED.value(),objectMapper.writeValueAsString(new ErrorResponse("JWT Blacklisted")));
				return;
			}
			
	
			if(!redisService.exists(key)) {
				response.sendError(HttpStatus.UNAUTHORIZED.value(),objectMapper.writeValueAsString(new ErrorResponse("Session Expired")));
				return;
			}
			
			String subject=claim.getSubject();
			if(subject!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				SessionData data=objectMapper.readValue(redisService.get(key),SessionData.class);
				Set<SimpleGrantedAuthority> listAuthorities=data.getRoles().stream().map((v)->{
					return new SimpleGrantedAuthority(v);
				}).collect(Collectors.toSet());
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(subject,null,listAuthorities);	
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}

			filterChain.doFilter(request, response);
			return;
		}catch(Exception e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(),objectMapper.writeValueAsString(new ErrorResponse(e.getMessage())));
			return;
		}
		
	}

	
	
}
