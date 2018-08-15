package com.collegeportal.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.collegeportal.dao.UserDao;
import com.collegeportal.entity.User;

@Service("socialUserDetailService")
public class SocialUserDetailService implements SocialUserDetailsService{

	@Autowired
	@Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDao userDao;

    @Override
    public SocialUserDetails loadUserByUserId(final String userId) throws UsernameNotFoundException, DataAccessException {
    	User user = (User) userDao.findById(Integer.parseInt(userId));
        if (user == null) {
            throw new SocialAuthenticationException("No local user mapped with social user " + userId);
        }
        
        com.collegeportal.dto.SocialUser socialUser = new com.collegeportal.dto.SocialUser(user);
        return socialUser;
    }
    
}
