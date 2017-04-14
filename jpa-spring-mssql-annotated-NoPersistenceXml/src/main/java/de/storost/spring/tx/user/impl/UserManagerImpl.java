package de.storost.spring.tx.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.storost.spring.tx.dao.UserDAO;
import de.storost.spring.tx.model.User;
import de.storost.spring.tx.user.UserManager;

@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void insertUser(User user) {
		userDAO.insertUser(user);
	}

	@Override
	public List<User> findAllUsers() {
		return userDAO.findAllUsers();
	}

	
}
