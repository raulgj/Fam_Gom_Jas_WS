package com.famgomjas.ws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famgomjas.ws.dao.RoleDao;
import com.famgomjas.ws.dao.UserDao;
import com.famgomjas.ws.entities.Role;
import com.famgomjas.ws.entities.User;

@Service
public class RoleSvcImpl implements RoleSvc {
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Optional<Role> get(long id) {
		return roleDao.findById(id);
	}

	@Override
	public List<Role> getAll() {
		return roleDao.findAll();
	}

	@Override
	public void save(Role t) {
		roleDao.save(t);
	}
}