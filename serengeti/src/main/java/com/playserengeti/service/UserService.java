package com.playserengeti.service;

import java.util.Collection;

import com.playserengeti.dao.UserDao;
import com.playserengeti.domain.User;

/**
 * Service for operations related to users.
 */
public class UserService {

	private UserDao userDao;
	
	public UserService(UserDao userDao) {
	    this.userDao = userDao;	
	}
	
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}
	
	/**
	 * Writes the given user to persistent storage.  If the given
	 * user has a null id, a user is created in persistent storage.
	 * If the id is not null, and a user with that id exists, the
	 * user in persistent storage will be updated with the fields in
	 * the argument.  If the id is not null, but no such user with that
	 * id exists, then throws some kind of exception.  (TODO) 
	 */
	public void saveUser(User user) {
		if (user.getId() == null) {
			userDao.insertUser(user);
		} else {
			userDao.updateUser(user);
		}
	}
	
	/**
	 * Deletes the user with the given id from persistent storage.  If no
	 * such user exists, throws an IllegalArgumentException.
	 */
	public void deleteUser(Integer id) {
	    userDao.deleteUser(id);	
	}
	
	/**
	 * Fetches all the users from persistent storage.
	 */
	public Collection<User> getAllUsers() {
		return userDao.getAllUsers();
	}
}