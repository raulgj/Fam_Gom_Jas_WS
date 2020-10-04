package com.famgomjas.ws.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the t_phone database table.
 * 
 */
@Entity
@Table(name="t_phone")
public class TPhone {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="phone_id", unique=true, nullable=false)
	private int phoneId;

	@Column(name="is_active", nullable=false)
	private byte isActive;

	@Column(nullable=false, length=20)
	private String label;

	@Column(nullable=false, length=20)
	private String phone;

	//bi-directional many-to-one association to TUser
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private TUser TUser;

	public TPhone() {
	}

	public int getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

}