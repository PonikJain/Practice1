package com.collegeportal.dao.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collegeportal.dao.UserDao;
import com.collegeportal.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager entityManager;	
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public User findByUserName(String email) {

		List<User> users = new ArrayList<User>();

		TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", email);
		users = query.getResultList();

		
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	
	@Transactional
	public boolean save(User user) {
		String password = user.getPassword();
		String encodePass = encoder.encode(password);
		user.setPassword(encodePass);
		
		User nUser = entityManager.merge(user);	
		entityManager.flush();
		if(nUser != null) {
			return true;
		}
		return false;
	}

}