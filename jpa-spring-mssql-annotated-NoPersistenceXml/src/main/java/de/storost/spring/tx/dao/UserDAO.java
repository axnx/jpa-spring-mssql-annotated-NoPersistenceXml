package de.storost.spring.tx.dao;

import java.util.List;

import de.storost.spring.tx.model.User;
//
public interface UserDAO {

	void insertUser(User user);

	List<User> findAllUsers();
}
