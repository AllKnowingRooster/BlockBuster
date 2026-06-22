package jwtProvider.service;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;

public interface jwtService {

	String createAccessToken(String sid,String jti,String subject);
	String createRefreshToken(String sid,String jti,String subject);
	SecretKey getSigningKey();
	Claims validateToken(String token);
}
