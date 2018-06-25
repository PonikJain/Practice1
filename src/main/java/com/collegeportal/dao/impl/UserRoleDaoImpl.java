package com.collegeportal.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.collegeportal.dao.UserRoleDao;
import com.collegeportal.entity.UserRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDao{
	
	Logger logger = LogManager.getLogger(UserRoleDaoImpl.class);
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void save(UserRole role) {
		entityManager.merge(role);
		entityManager.flush();
	}

	public void delete(UserRole role) {
		entityManager.remove(role);
		entityManager.flush();
	}

}
