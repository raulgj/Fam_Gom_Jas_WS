package com.famgomjas.ws.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.famgomjas.ws.service.UserDetailsImpl;

@RestController
@RequestMapping("feed")
public class FeedCtrl {
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/hello")
	public String helloWorld() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "Hello "+userDetails.getUsername()+"!!";
	}
}
