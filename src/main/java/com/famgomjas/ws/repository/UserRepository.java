package com.famgomjas.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.TUser;

public interface UserRepository extends JpaRepository<TUser, Integer>{

}
