package com.famgomjas.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.TEvent;

public interface EventRepository extends JpaRepository<TEvent, Integer>{

}
