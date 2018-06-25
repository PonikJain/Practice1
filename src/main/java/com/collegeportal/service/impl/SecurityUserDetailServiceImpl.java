package com.collegeportal.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collegeportal.dao.UserDao;
import com.collegeportal.entity.UserRole;

@Service
@Qualifier("userDetailsService")
public class SecurityUserDetailServiceImpl implements UserDetailsService {
	
	private static final Logger logger = LogManager.getLogger(SecurityUserDetailServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	// UserServcieDetails implemented metod
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.collegeportal.entity.User user = userDao.findByUserName(email);
		
		if(user==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
		return buildUserForAuthentication(user, authorities);
	}

	// Converts com.collegeportal.entity.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.collegeportal.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<UserRole> list) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : list) {
			logger.info("UserRoles  : {}", userRole);
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}
