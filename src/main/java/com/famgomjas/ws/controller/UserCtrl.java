package com.famgomjas.ws.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.famgomjas.ws.entities.Role;
import com.famgomjas.ws.entities.User;
import com.famgomjas.ws.jwt.JwtUtils;
import com.famgomjas.ws.payload.JwtResponse;
import com.famgomjas.ws.payload.MessageResponse;
import com.famgomjas.ws.requestBody.LoginRequest;
import com.famgomjas.ws.requestBody.RegisterRequest;
import com.famgomjas.ws.service.RoleSvc;
import com.famgomjas.ws.service.UserDetailsImpl;
import com.famgomjas.ws.service.UserSvc;
import com.famgomjas.ws.util.Constants;

@RestController
@RequestMapping("user")
public class UserCtrl {
	@Autowired
	private UserSvc userSvc;
	
	@Autowired
	private RoleSvc roleSvc;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest signUpRequest) {
		if (userSvc.loadUserByUsername(signUpRequest.getUser()) != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		Set<Role> roles = new HashSet<>();
		roles.add(roleSvc.findFirstByName(Constants.DEFAULT_ROLE));		
		User user = new User(signUpRequest.getUser(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getName(), signUpRequest.getLastName(), signUpRequest.getLastNameMother(), signUpRequest.getBirthdate(), signUpRequest.getGender(), roles);

		userSvc.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
		
	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
	@GetMapping("hello")
	public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "Hello "+userDetails.getUsername()+"!!";
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
