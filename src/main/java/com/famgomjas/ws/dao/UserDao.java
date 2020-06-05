package com.famgomjas.ws.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.famgomjas.ws.entities.User;

public interface UserDao extends JpaRepository<User, Long> {
	public User getUser(String user, String password);
}
