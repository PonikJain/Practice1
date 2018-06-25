package com.collegeportal.dao;

import com.collegeportal.entity.User;

public interface UserDao {

	public User findByUserName(String username);
	
	public boolean save(User user);
}
