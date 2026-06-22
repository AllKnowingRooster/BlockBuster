package jwtProvider.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jwtProvider.service.jwtService;

public class JwtServiceImpl implements jwtService{

	private String secret;
	private long accessExpiration;
	private long refreshExpiration;
	
	public JwtServiceImpl(String secret,long accessExpiration,long refreshExpiration) {
		this.secret=secret;
		this.accessExpiration=accessExpiration;
		this.refreshExpiration=refreshExpiration;
	}
	
	@Override
	public String createAccessToken(String sid,String jti,String subject) {
		Map<String,Object> claims=new HashMap<String,Object>();
		claims.put("sid",sid);
		claims.put("type","access");
		return Jwts.builder().issuer("BlockBuster Jwt Provider")
							 .id(jti)
							 .subject(subject)
							 .claims(claims)
							 .issuedAt(new Date(System.currentTimeMillis()))
							 .expiration(new Date(System.currentTimeMillis()+this.accessExpiration))
							 .signWith(getSigningKey())
							 .compact();
	}

	@Override
	public String createRefreshToken(String sid,String jti,String subject) {
		Map<String,Object> claims=new HashMap<String,Object>();
		claims.put("sid", sid);
		claims.put("type", "refresh");	
		return Jwts.builder().subject(subject)
							 .claims(claims)
							 .issuer("Blockbuster Jwt Provider")
							 .issuedAt(new Date(System.currentTimeMillis()))
							 .expiration(new Date(System.currentTimeMillis()+this.refreshExpiration))
							 .id(jti)
							 .signWith(getSigningKey())
							 .compact();
	}

	@Override
	public SecretKey getSigningKey() {
		// TODO Auto-generated method stub
		byte[] secretByte=this.secret.getBytes();
		return Keys.hmacShaKeyFor(secretByte);
	}

	@Override
	public Claims validateToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().verifyWith(getSigningKey())
							.build()
							.parseSignedClaims(token)
							.getPayload();
	}

}
