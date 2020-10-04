package com.famgomjas.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famgomjas.ws.entities.TComment;

public interface CommentRepository extends JpaRepository<TComment, Integer>{

}
