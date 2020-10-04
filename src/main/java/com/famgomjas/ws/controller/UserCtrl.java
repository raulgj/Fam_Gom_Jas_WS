package com.famgomjas.ws.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.famgomjas.ws.entities.TEmail;
import com.famgomjas.ws.entities.TGroup;
import com.famgomjas.ws.entities.TRole;
import com.famgomjas.ws.entities.TUser;
import com.famgomjas.ws.jwt.JwtUtils;
import com.famgomjas.ws.jwt.UserDetailsImpl;
import com.famgomjas.ws.payload.JwtResponse;
import com.famgomjas.ws.repository.EmailRepository;
import com.famgomjas.ws.repository.GroupRepository;
import com.famgomjas.ws.repository.PhoneRepository;
import com.famgomjas.ws.repository.RoleRepository;
import com.famgomjas.ws.repository.UserRepository;
import com.famgomjas.ws.requestBody.LoginRequest;
import com.famgomjas.ws.requestBody.RegisterRequest;
import com.famgomjas.ws.util.Constants;

@RestController
@RequestMapping("user")
public class UserCtrl {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest signUpRequest) {
		if (userRepository.findFirstByUser(signUpRequest.getUser()) != null) {
			return ResponseEntity.badRequest().body("Error: El usuario ya xiste");
		}

		// Get the default role for all users
		List<TRole> roles = new ArrayList<TRole>();
		roles.add(roleRepository.findFirstByName(Constants.DEFAULT_ROLE));
		
		// Get the default role for all users
		List<TGroup> groups = new ArrayList<TGroup>();
		groups.add(groupRepository.findFirstByName(Constants.DEFAULT_GROUP));

		// Create the new user
		TUser user = new TUser();
		user.setUser(signUpRequest.getUser());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setName(signUpRequest.getName());
		user.setLastName(signUpRequest.getLastName());
		user.setLastNameMother(signUpRequest.getLastNameMother());
		user.setBirthdate(signUpRequest.getBirthdate());
		user.setGender(signUpRequest.getGender());
		user.setTRoles(roles);
		user.setTGroups(groups);
		userRepository.save(user);

		return ResponseEntity.ok(Constants.HTTP_RESPONSE_OK_MESSAGE);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public Optional<TUser> getUser(@PathVariable("id") Integer id) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (userDetails.getId() == id) {
			return userRepository.findById(id);
		}
		else {
			if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Constants.ADMIN_ROLE))) {
				return userRepository.findById(id);
			}
			else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constants.HTTP_RESPONSE_UNAUTHORIZED_MESSAGE);
			}
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/all")
	public List<TUser> getAll() {
		List<TUser> users = userRepository.findAll();
		return users;
	}
	
	
	/*************************************************************************
	**************************************************************************
	***************************************************************************
	* ROLE
	***************************************************************************
	***************************************************************************
	**************************************************************************/
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}/roles")
	public List<TRole> getRoles(@PathVariable("id") Integer id) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (userDetails.getId() == id) {
			return userRepository.findById(id).get().getTRoles();
		}
		else {
			if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Constants.ADMIN_ROLE))) {
				Optional<TUser> user = userRepository.findById(id);
				
				if (user.isPresent()) {
					return user.get().getTRoles();
				}
				else {
					throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
				}
			}
			else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constants.HTTP_RESPONSE_UNAUTHORIZED_MESSAGE);
			}
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/{userId}/roles")
	public ResponseEntity<?> addRole(@RequestParam("roleId") Integer roleId, @PathVariable("userId") Integer userId) {
		Optional<TRole> role = roleRepository.findById(roleId);
		Optional<TUser> user = userRepository.findById(userId);
		
		if (role.isPresent() && user.isPresent()) {
			user.get().getTRoles().add(role.get());
			userRepository.save(user.get());
			return ResponseEntity.ok(Constants.HTTP_RESPONSE_OK_MESSAGE);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping ("/{userId}/roles")
	public ResponseEntity<?> deleteRole(@RequestParam("roleId") Integer roleId, @PathVariable("userId") Integer userId) {
		Optional<TRole> role = roleRepository.findById(roleId);
		Optional<TUser> user = userRepository.findById(userId);
		
		if (role.isPresent() && user.isPresent()) {
			user.get().getTRoles().remove(role.get());
			userRepository.save(user.get());
			return ResponseEntity.ok(Constants.HTTP_RESPONSE_OK_MESSAGE);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
		}
	}
	
	/*************************************************************************
	**************************************************************************
	***************************************************************************
	* EMAIL
	***************************************************************************
	***************************************************************************
	**************************************************************************/
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}/emails")
	public List<TEmail> getEmails(@PathVariable("id") Integer id) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (userDetails.getId() == id) {
			return userRepository.findById(id).get().getTEmails();
		}
		else {
			if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Constants.ADMIN_ROLE))) {
				Optional<TUser> user = userRepository.findById(id);
				
				if (user.isPresent()) {
					return user.get().getTEmails();
				}
				else {
					throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
				}
			}
			else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constants.HTTP_RESPONSE_UNAUTHORIZED_MESSAGE);
			}
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/{userId}/emails")
	public ResponseEntity<?> addEmail(@RequestParam("email") String email, @PathVariable("userId") Integer userId) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<TUser> user;
				
		if (userDetails.getId() == userId || userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Constants.ADMIN_ROLE))) {
			user = userRepository.findById(userId);
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constants.HTTP_RESPONSE_UNAUTHORIZED_MESSAGE);
		}
		
		if (user.isPresent()) {
			TEmail oEmail = new TEmail();
			oEmail.setEmail(email);
			oEmail.setTUser(user.get());
			emailRepository.save(oEmail);
			
			return ResponseEntity.ok(Constants.HTTP_RESPONSE_OK_MESSAGE);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/{userId}/emails/{emailId}")
	public ResponseEntity<?> editEmail(@RequestParam("email") String email, @PathVariable("userId") Integer userId, @PathVariable("emailId") Integer emailId) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<TUser> user;
		
		if (userDetails.getId() == userId || userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Constants.ADMIN_ROLE))) {
			user = userRepository.findById(userId);
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constants.HTTP_RESPONSE_UNAUTHORIZED_MESSAGE);
		}
		
		if (user.isPresent()) {
			Optional<TEmail> oEmail = emailRepository.findById(emailId);
			
			if (oEmail.isPresent()) {
				oEmail.get().setEmail(email);
				emailRepository.save(oEmail.get());
				
				return ResponseEntity.ok(Constants.HTTP_RESPONSE_OK_MESSAGE);
			}
			else {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping("/{userId}/emails/{emailId}")
	public ResponseEntity<?> deleteEmail(@PathVariable("userId") Integer userId, @PathVariable("emailId") Integer emailId) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<TUser> user;
		
		if (userDetails.getId() == userId || userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Constants.ADMIN_ROLE))) {
			user = userRepository.findById(userId);
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, Constants.HTTP_RESPONSE_UNAUTHORIZED_MESSAGE);
		}
		
		if (user.isPresent()) {
			Optional<TEmail> oEmail = emailRepository.findById(emailId);
			
			if (oEmail.isPresent()) {
				emailRepository.delete(oEmail.get());
				return ResponseEntity.ok(Constants.HTTP_RESPONSE_OK_MESSAGE);
			}
			else {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, Constants.HTTP_RESPONSE_NO_CONTENT_MESSAGE);
		}
	}
}
