package com.famgomjas.ws.util;

public class Constants {
	// JWT
	public static final long   JTW_EXPIRATION_TIME = 18000000;
	public static final String JTW_SECRET = "j@$soG0m3z";
	public static final String JTW_CLAIM = "CLAIM_TOKEN";
	public static final String JTW_ISSUER = "ISSUER";
	public static final String JTW_HEADER_AUTHORIZATION_KEY = "Authorization";
	public static final String JTW_BEARER_PREFIX = "Bearer ";
	
	// URLs
	public static final String SECURITY_URL_LOGIN = "/user/login";
	public static final String SECURITY_URL_REGISTER = "/user/register";
	
	// Default role
	public static final String DEFAULT_ROLE = "ROLE_USER";
	
	// Default Genders
	public static enum GENDERS { Masculino, Femenino };
}
