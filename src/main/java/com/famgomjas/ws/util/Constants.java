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
	
	// Default group
	public static final String DEFAULT_GROUP = "Usuario";
	
	// Default role
	public static final String DEFAULT_ROLE = "ROLE_USER";
	
	// Admin role
	public static final String ADMIN_ROLE = "ROLE_ADMIN";
	
	// Default Genders
	public static enum GENDERS { Masculino, Femenino };
	
	// HTTP Response, OK
	public static final String HTTP_RESPONSE_OK_MESSAGE = "La informacion se actualizo/inserto correctamente";
	
	// HTTP Response, Error UNAUTHORIZED
	public static final String HTTP_RESPONSE_UNAUTHORIZED_MESSAGE = "No tienes permisos para ver esa informacion";
		
	// HTTP Response, Error NO_CONTENT
	public static final String HTTP_RESPONSE_NO_CONTENT_MESSAGE = "No hay informacion con esos parametros";
}
