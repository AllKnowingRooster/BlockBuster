package com.blockbuster.authenticationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jwtProvider.impl.JwtServiceImpl;
import redisManager.impl.RedisServiceImpl;

@Configuration
public class ApplicationConfiguration {

	@Value(value="${jwt.secret}")
	private String jwtSecret;
	
	@Value(value="${jwt.accessExpiration}")
	private long accessExpiration;
	
	@Value(value="${jwt.refreshExpiration}")
	private long refreshExpiration;
	
	@Value(value="${redis.url}")
	private String redisUrl;
	
	@Value(value="${redis.expiration}")
	private long redisExpiration;
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	RedisServiceImpl redisService() {
		return new RedisServiceImpl(redisUrl,redisExpiration);
	}
	
	@Bean
	AuthenticationManager authentication(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	@Bean
	JwtServiceImpl jwtService() {
		return new JwtServiceImpl(jwtSecret,accessExpiration,refreshExpiration);
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http,CustomRequestAuthentication requestAuth,CustomLogger logger) {
		http.formLogin((fl)->{fl.disable();})
		.authorizeHttpRequests((auth)->{
			auth.requestMatchers("/api/users/signin","/api/users/signup").permitAll();
			auth.requestMatchers("/api/users/*").authenticated();
			auth.anyRequest().permitAll();
		})
		.csrf((csrf)->{
			csrf.disable();
		})
		.sessionManagement((ses)->{
			ses.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		})
		.headers((h)->{
			h.frameOptions((f)->{
				f.sameOrigin();
			});
		});
		http.addFilterBefore(requestAuth,UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(logger, CustomRequestAuthentication.class);
		return http.build();
	}
}
