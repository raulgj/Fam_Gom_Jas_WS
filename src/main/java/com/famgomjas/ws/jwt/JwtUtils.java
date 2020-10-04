package com.famgomjas.ws.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.famgomjas.ws.util.Constants;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + Constants.JTW_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, Constants.JTW_SECRET)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(Constants.JTW_SECRET).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(Constants.JTW_SECRET).parseClaimsJws(authToken);
			return true;
		} 
		catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e);
		} 
		catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e);
		} 
		catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e);
		} 
		catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e);
		} 
		catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e);
		}
		catch (Exception e) {
			logger.error("JWT validateJwtToken: {}", e);
		}

		return false;
	}
}
