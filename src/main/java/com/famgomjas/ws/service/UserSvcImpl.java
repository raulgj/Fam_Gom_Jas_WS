package com.famgomjas.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.famgomjas.ws.entities.Action;
import com.famgomjas.ws.entities.Role;
import com.famgomjas.ws.entities.User;
import com.famgomjas.ws.repository.UserRepository;

@Service
public class UserSvcImpl implements UserSvc {
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Optional<User> get(long id) {
		return userRepo.findById(id);
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

	@Override
	public void save(User t) {
		userRepo.save(t);
	}
	
	public User loadUserByUsernameAndPassword(String userName, String password) {
		final User user = userRepo.findFirstByUserAndPassword(userName, password);
		return user;
	}
	
	public User loadUserByUsername(String userName) {
		final User user = userRepo.findFirstByUser(userName);
		return user;
		
	}
}
