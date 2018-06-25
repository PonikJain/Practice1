package com.collegeportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collegeportal.dao.UserDao;
import com.collegeportal.entity.User;
import com.collegeportal.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	public boolean saveAndUpdate(User user) {
		return userDao.save(user);
	}

}
