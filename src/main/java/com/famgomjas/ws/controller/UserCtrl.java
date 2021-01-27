package com.famgomjas.ws.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.famgomjas.ws.repository.EmailRepository;
import com.famgomjas.ws.repository.PhoneRepository;
import com.famgomjas.ws.repository.UserRepository;
import com.famgomjas.ws.entities.TEmail;
import com.famgomjas.ws.entities.TPhone;
import com.famgomjas.ws.entities.TUser;

@RestController
@RequestMapping("user")
public class UserCtrl {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	PhoneRepository phoneRepository;
	
	/*
	 * User Services
	 * */
	
	@GetMapping("/")
	public List<TUser> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/{userId}")
	public TUser getUser(@PathVariable Integer userId){
		return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
	}
	
	@PostMapping("/")
	public void saveUser(@RequestBody TUser tUser) {
		userRepository.save(tUser);
	}
	
	@PutMapping("/{userId}")
	public void updateUser(@RequestBody TUser tUser,  @PathVariable Integer userId) {
		if (userRepository.findById(userId).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		tUser.setUserId(userId);
		userRepository.save(tUser);
	}
	
	/*
	 * Email Services
	 */

	@GetMapping("/{userId}/email")
	public List<TEmail> getEmails(@PathVariable Integer userId){
		TUser tUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
		
		return tUser.getTEmails();
	}
	
	@PostMapping("/{userId}/email")
	public void saveEmail(@RequestBody TEmail tEmail, @PathVariable Integer userId) {
		TUser tUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
		tUser.addTEmail(tEmail);
		userRepository.save(tUser);
	}
	
	@PutMapping("/{userId}/email/{emailId}")
	public void updateEmail(@RequestBody TEmail tEmail, @PathVariable Integer userId, @PathVariable Integer emailId) {
		Optional<TEmail> email = emailRepository.findById(emailId);
		
		if (email.isEmpty() || email.get().getTUser().getUserId() != userId) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		tEmail.setEmailId(emailId);
		emailRepository.save(tEmail);
	}
	
	@DeleteMapping("/{userId}/email/{emailId}")
	public void deleteEmail(@PathVariable Integer userId, @PathVariable Integer emailId) {
		Optional<TEmail> email = emailRepository.findById(emailId);
		
		if (email.isEmpty() || email.get().getTUser().getUserId() != userId) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		emailRepository.deleteById(emailId);
	}
	
	/*
	 * Phone Services
	 */

	@GetMapping("/{userId}/phone")
	public List<TPhone> getPhones(@PathVariable Integer userId){
		TUser tUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
		
		return tUser.getTPhones();
	}
	
	@PostMapping("/{userId}/phone")
	public void savePhone(@RequestBody TPhone tPhone, @PathVariable Integer userId) {
		TUser tUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
		tUser.addTPhone(tPhone);
		userRepository.save(tUser);
	}
	
	@PutMapping("/{userId}/phone/{phoneId}")
	public void updatePhone(@RequestBody TPhone tPhone, @PathVariable Integer userId, @PathVariable Integer phoneId) {
		Optional<TPhone> phone = phoneRepository.findById(phoneId);
		
		if (phone.isEmpty() || phone.get().getTUser().getUserId() != userId) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		tPhone.setPhoneId(phoneId);
		phoneRepository.save(tPhone);
	}
	
	@DeleteMapping("/{userId}/phone/{phoneId}")
	public void deletePhone(@PathVariable Integer userId, @PathVariable Integer phoneId) {
		Optional<TPhone> phone = phoneRepository.findById(phoneId);
		
		if (phone.isEmpty() || phone.get().getTUser().getUserId() != userId) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		
		emailRepository.deleteById(phoneId);
	}

}
