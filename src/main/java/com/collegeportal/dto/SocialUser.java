package com.collegeportal.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import com.collegeportal.entity.User;
import com.collegeportal.entity.UserRole;
import com.collegeportal.enums.UserRoleType;

public class SocialUser implements SocialUserDetails {
 
    private static final long serialVersionUID = 1L;
 
    private List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    private User user;
 
    public SocialUser(User user) {
        this.user = user;
        List<UserRole> roles = user.getUserRoles();
        
        String role = UserRoleType.USER.getValue();
        if(roles != null && roles.size() > 0) {
        	role = roles.get(0).getRole();
        }
 
        GrantedAuthority grant = new SimpleGrantedAuthority(role);
        this.list.add(grant);
    }
 
    @Override
    public String getUserId() {
        return this.user.getEmail();
    }
 
    @Override
    public String getUsername() {
        return user.getFirstname();
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return list;
    }
 
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
}