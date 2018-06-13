package com.collegeportal.dao.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.collegeportal.config.DataBaseConfig;
import com.collegeportal.dao.UserDao;
import com.collegeportal.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		TypedQuery<User> query = entityManager.createNamedQuery("User.findByUserName", User.class);
		query.setParameter("username", username);
		users = query.getResultList();

		
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}