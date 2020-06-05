package com.famgomjas.ws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.famgomjas.ws.dao.UserDao;
import com.famgomjas.ws.entities.User;

@Service
public class UserSvcImpl implements UserSvc {
	@Autowired
	private UserDao userDao;
	
	@Override
	public Optional<User> get(long id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> getAll() {
		return userDao.findAll();
	}

	@Override
	public void save(User t) {
		userDao.save(t);
	}
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final User retrievedUser = userDao.getUser(user, password)
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return UserDetailsMapper.build(retrievedUser);
	}
}
