package com.famgomjas.ws.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.famgomjas.ws.entities.Role;
import com.famgomjas.ws.entities.User;
import com.famgomjas.ws.service.UserSvc;

@RestController
@RequestMapping("user")
public class UserCtrl {
	@Autowired
	private UserSvc userSvc;

	@GetMapping("hello")
	public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) {
		return "Hello "+name+"!!";
	}
	
	@GetMapping("{id}")
	public Optional<User> getUser(@PathVariable("id") Integer id) {
		Optional<User> user = userSvc.get(id);
		return user;
	}
	
	@GetMapping("{id}/roles")
	public Collection<Role> getRolesByUser(@PathVariable("id") Integer id) {
		Collection<Role> roles = userSvc.get(id).get().getRoles();; 
		return roles;
	}
}
