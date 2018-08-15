package com.collegeportal.security.service;

import javax.transaction.Transactional;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.collegeportal.dao.UserDao;
import com.collegeportal.entity.User;

@Service
public class SocialConnectionSignupService implements ConnectionSignUp {

	private UserDao userDao;
	
	public SocialConnectionSignupService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	@Override
	public String execute(Connection<?> connection) {
		User user = userDao.createNewUser(connection);
		if(user == null) {
			return "";
		}
		return user.getUserId().toString();
	}
	
	
}