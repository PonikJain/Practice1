package com.collegeportal.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
		@NamedQuery(name="User.findByEmail", query="select u from User u where u.email=:email"),
		@NamedQuery(name="User.findById", query="select u from User u where u.userId=:userId"),
		@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	private Integer userId;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(nullable=false, length=40)
	private String email;

	@Column(nullable=false, length=20)
	private String firstname;

	@Column(nullable=false, length=20)
	private String lastname;

	@Column(nullable=false, length=60)
	private String password;

	@Column(nullable=false)
	private BigInteger phonenumber;

	@Column(nullable=false, length=30)
	private String state;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<UserRole> userRoles;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		
		this.password = password;
	}

	public BigInteger getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(BigInteger phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}