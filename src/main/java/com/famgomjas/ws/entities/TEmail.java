package com.famgomjas.ws.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the t_email database table.
 * 
 */
@Entity
@Table(name="t_email")
public class TEmail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="email_id", unique=true, nullable=false)
	private int emailId;

	@Column(nullable=false, length=50)
	private String email;

	@Column(name="is_active", nullable=false)
	private byte isActive;

	//bi-directional many-to-one association to TUser
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	@JsonIgnore
	private TUser TUser;

	public TEmail() {
	}

	public int getEmailId() {
		return this.emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	public TUser getTUser() {
		return this.TUser;
	}

	@JsonIgnore
	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

}