package com.famgomjas.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findFirstByUserAndPassword(String user, String password);
	
	public User findFirstByUser(String user);
}
