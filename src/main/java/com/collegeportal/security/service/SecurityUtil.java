package com.collegeportal.security.service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.collegeportal.dto.SocialUser;
import com.collegeportal.entity.User;
 
public class SecurityUtil {
 
   // Auto login.
   public static void logInUser(User user) {
 
       SocialUser userDetails = new SocialUser(user);
 
       Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
               userDetails.getAuthorities());
       SecurityContextHolder.getContext().setAuthentication(authentication);
   }
  
}