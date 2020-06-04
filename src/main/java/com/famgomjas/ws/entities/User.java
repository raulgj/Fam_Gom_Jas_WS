package com.famgomjas.ws.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
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
    private String birthdate;
	
	@Column(name="gender")
    private String gender;
	
	@Column(name="is_active", nullable=false, columnDefinition="BIT", length=1)
    private boolean isActive;
	
	

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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
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
	
	
	
}
