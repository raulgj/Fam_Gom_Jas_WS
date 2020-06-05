package com.famgomjas.ws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famgomjas.ws.dao.ActionDao;
import com.famgomjas.ws.dao.UserDao;
import com.famgomjas.ws.entities.Action;
import com.famgomjas.ws.entities.User;

@Service
public class ActionSvcImpl implements ActionSvc {
	@Autowired
	private ActionDao actionDao;
	
	@Override
	public Optional<Action> get(long id) {
		return actionDao.findById(id);
	}

	@Override
	public List<Action> getAll() {
		return actionDao.findAll();
	}

	@Override
	public void save(Action t) {
		actionDao.save(t);
	}
}
