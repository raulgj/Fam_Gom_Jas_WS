package com.famgomjas.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.Action;

public interface ActionDao extends JpaRepository<Action, Long> {
	
}
