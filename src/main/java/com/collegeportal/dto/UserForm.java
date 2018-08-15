package com.collegeportal.dto;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;

public class UserForm {

	private String id;
	private String email;
	private String userName;

	private String firstName;
	private String lastName;
	private String password;
	private String cpassword;
	private String dob;
	private String state;
	private String phoneNumber;
	private String role;
	private String signInProvider;
	private String providerUserId;

	public UserForm() {

	}

	public UserForm(Connection<?> connection) {
		UserProfile socialUserProfile = connection.fetchUserProfile();
		this.id = null;
		this.email = socialUserProfile.getEmail();
		this.userName = socialUserProfile.getUsername();
		this.firstName = socialUserProfile.getFirstName();
		this.lastName = socialUserProfile.getLastName();

		ConnectionKey key = connection.getKey();
		// google, facebook, twitter
		this.signInProvider = key.getProviderId();

		// ID of User on google, facebook, twitter.
		// ID của User trên google, facebook, twitter.
		this.providerUserId = key.getProviderUserId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSignInProvider() {
		return signInProvider;
	}

	public void setSignInProvider(String signInProvider) {
		this.signInProvider = signInProvider;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}