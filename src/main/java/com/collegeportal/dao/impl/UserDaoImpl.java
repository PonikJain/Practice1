package com.collegeportal.dao.impl;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collegeportal.dao.UserDao;
import com.collegeportal.entity.User;
import com.collegeportal.entity.UserRole;
import com.collegeportal.enums.UserRoleType;

@Repository
public class UserDaoImpl implements UserDao {

	Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager entityManager;	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private static final String DEFAULT_DOB = "1990-01-01";

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
	
	@Transactional(readOnly=false)
	public User save(User user) {
		String password = user.getPassword();
		String encodePass = encoder.encode(password);
		user.setPassword(encodePass);
		
		User nUser = entityManager.merge(user);	
		entityManager.flush();
		return nUser;
	}

	@Override
	@Transactional(readOnly=false)
	public User createNewUser(Connection<?> connection) {
		
		User user  = null;
		try {
		ConnectionKey key = connection.getKey();
		UserProfile userProfile = connection.fetchUserProfile();

		String email = userProfile.getEmail();
	    user = this.findByUserName(email);
	     
	    if (user != null) {
	       return user;
	    }
	    
	    
	    User newUser = new User();
	    newUser.setFirstname(userProfile.getFirstName());
	    newUser.setEmail(userProfile.getEmail());
	    newUser.setLastname(userProfile.getLastName());
	    
	    DateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = s.parse(DEFAULT_DOB);
		
	    newUser.setDob(birthDate);
	    newUser.setPassword("SOCIAL_PASSWORD");
	    newUser.setPhonenumber(new BigInteger("9988776655"));
	    newUser.setState("N/A");
	    
	    UserRole userRole = new UserRole(UserRoleType.USER.getValue(), newUser);
	    List<UserRole>  roles = new ArrayList<>();
	    roles.add(userRole);
	    
	    newUser.setUserRoles(roles);
	    
	    user = entityManager.merge(newUser);	
		entityManager.flush();
	    
		}catch(Exception e) {
			logger.error("Error while saving new Social User");
		}
		
		return user;
	}

	@Override
	public User findById(Integer id) {

		List<User> users = new ArrayList<User>();

		TypedQuery<User> query = entityManager.createNamedQuery("User.findById", User.class);
		query.setParameter("userId", id);
		users = query.getResultList();

		
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	
	}

}