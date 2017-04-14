package de.storost.spring.tx.user;

import java.util.List;

import de.storost.spring.tx.model.User;

public interface UserManager {

	void insertUser(User user);

	List<User> findAllUsers();
}
