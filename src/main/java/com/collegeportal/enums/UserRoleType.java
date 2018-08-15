package com.collegeportal.enums;

public enum UserRoleType {

	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	String role;
	
	UserRoleType(String role){
		this.role = role;
	}
	
	public String getValue() {
		return this.role;
	}
	
}
