package com.famgomjas.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.Role;
import com.famgomjas.ws.entities.User;

public interface RoleDao extends JpaRepository<Role, Long> {
	public Role findFirstByName(String name);
}
