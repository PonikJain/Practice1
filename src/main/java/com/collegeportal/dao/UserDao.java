package com.collegeportal.dao;

import org.springframework.social.connect.Connection;

import com.collegeportal.entity.User;

public interface UserDao {

	public User findByUserName(String username);
	
	public User findById(Integer id);
	
	public User save(User user);
	
	public User createNewUser(Connection<?> connection);
	
}
