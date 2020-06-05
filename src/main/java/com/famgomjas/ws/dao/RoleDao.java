package com.famgomjas.ws.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
	
}
