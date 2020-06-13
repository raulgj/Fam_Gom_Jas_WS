package com.famgomjas.ws.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.famgomjas.ws.entities.User;

public interface UserSvc extends ISvc<User> {
	public User loadUserByUsernameAndPassword(String userName, String password);
	public User loadUserByUsername(String userName);
}
