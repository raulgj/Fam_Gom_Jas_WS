package com.famgomjas.ws.entities;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="users")
public class User {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
    private Long userId;
	
	@Column(name="user", nullable=false)
    private String user;
	
	@Column(name="password", nullable=false)
    private String password;
	
	@Column(name="name")
    private String name;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="last_name_mother")
    private String lastNameMother;
	
	@Column(name="birthdate")
    private Calendar birthdate;
	
	@Column(name="gender")
    private String gender;
	
	@Column(name="is_active", nullable=false, columnDefinition="BIT", length=1)
    private boolean isActive;
	
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonManagedReference
	private Collection<Role> roles;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNameMother() {
		return lastNameMother;
	}

	public void setLastNameMother(String lastNameMother) {
		this.lastNameMother = lastNameMother;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public User() {
		
	}

	public User(Long userId, String user, String password, String name, String lastName, String lastNameMother, Calendar birthdate, String gender, boolean isActive, Collection<Role> roles) {
		this.userId = userId;
		this.user = user;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.lastNameMother = lastNameMother;
		this.birthdate = birthdate;
		this.gender = gender;
		this.isActive = isActive;
		this.roles = roles;
	}
	
	public User(String user, String password, String name, String lastName, String lastNameMother, Calendar birthdate, String gender, Collection<Role> roles) {
		this.user = user;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.lastNameMother = lastNameMother;
		this.birthdate = birthdate;
		this.gender = gender;
		this.roles = roles;
	}
	
	
}
