package com.collegeportal.dao;

import com.collegeportal.entity.User;

public interface UserDao {

	User findByUserName(String username);
}
