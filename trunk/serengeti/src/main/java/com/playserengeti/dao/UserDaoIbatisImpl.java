package com.playserengeti.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.playserengeti.domain.User;

public class UserDaoIbatisImpl extends SqlMapClientDaoSupport implements UserDao {

    @Override
	public User authenticateUserByEmailAndPassword(String email, String password) {
    	Map<String,String> parameterMap = new HashMap<String,String>();
    	parameterMap.put("email", email);
    	parameterMap.put("password", password);
		return (User)getSqlMapClientTemplate().queryForObject(
				"authenticateUserByEmailAndPassword",
				parameterMap);
	}

	@Override
	public boolean deleteUser(Integer id) {
		return getSqlMapClientTemplate().delete("deleteUser", id) > 0;
	}

	@Override
	public Collection<User> getAllUsers() {
		return (List<User>)getSqlMapClientTemplate().queryForList("getAllUsers");
	}

	@Override
	public User getUserByEmail(String email) {
		return (User)getSqlMapClientTemplate().queryForObject(
				"getUserByEmail",
				email);
	}

	@Override
	public User getUserById(Integer id) {
		return (User)getSqlMapClientTemplate().queryForObject(
				"getUserById",
				id);
	}

	@Override
	public Integer insertUserWithPassword(User user, String password) {
		Map<String,String> parameterMap = new HashMap<String,String>();
		parameterMap.put("email", user.getEmail());
		parameterMap.put("firstName", user.getFirstName());
		parameterMap.put("lastName", user.getLastName());
		parameterMap.put("password", password);
		return (Integer)getSqlMapClientTemplate().insert(
				"insertUserWithPassword",
				parameterMap);
	}

	@Override
	public boolean updateUser(User user) {
		return getSqlMapClientTemplate().update("updateUser", user) > 0;
	}

	@Override
	public boolean updateUserPassword(Integer id, String password) {
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("id", id);
		parameterMap.put("password", password);
		return false;
	}
}
