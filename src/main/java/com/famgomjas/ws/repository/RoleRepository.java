package com.famgomjas.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.TRole;

public interface RoleRepository extends JpaRepository<TRole, Integer>{
	public TRole findFirstByName(String name);
}
