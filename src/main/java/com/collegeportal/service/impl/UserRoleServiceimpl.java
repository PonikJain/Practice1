package com.collegeportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collegeportal.dao.UserRoleDao;
import com.collegeportal.entity.UserRole;
import com.collegeportal.service.UserRoleService;

@Service
public class UserRoleServiceimpl implements UserRoleService{

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Transactional
	public void save(UserRole role) {
		userRoleDao.save(role);
	}

	@Transactional
	public void delete(UserRole role) {
		
	}

}
