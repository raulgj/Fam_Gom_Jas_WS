package com.famgomjas.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.TEmail;

public interface EmailRepository extends JpaRepository<TEmail, Integer>{

}
