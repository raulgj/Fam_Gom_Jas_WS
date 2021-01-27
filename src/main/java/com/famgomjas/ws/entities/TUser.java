package com.famgomjas.ws.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;


/**
 * The persistent class for the t_user database table.
 * 
 */
@Entity
@Table(name="t_user")
public class TUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id", unique=true, nullable=false)
	private int userId;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date birthdate;

	@Column(nullable=false, length=15)
	private String gender;

	@Column(name="is_active", nullable=false)
	private byte isActive;

	@Column(name="last_name", nullable=false, length=20)
	private String lastName;

	@Column(name="last_name_mother", length=20)
	private String lastNameMother;

	@Column(nullable=false, length=30)
	private String name;

	@Column(nullable=false, length=200)
	private String password;

	@Column(nullable=false, length=50)
	private String user;

	@OneToMany(mappedBy="TUser")
	private List<TComment> TComments;

	@OneToMany(mappedBy="TUser")
	private List<TEmail> TEmails;

	@OneToMany(mappedBy="TUser")
	private List<TEvent> TEvents;

	@OneToMany(mappedBy="TUser")
	private List<TPhone> TPhones;

	@ManyToMany
	@JoinTable(name="t_user_group", joinColumns={@JoinColumn(name="user_id", nullable=false)}, inverseJoinColumns={@JoinColumn(name="group_id", nullable=false)})
	private List<TGroup> TGroups;

	@ManyToMany
	@JoinTable(name="t_user_role", joinColumns={@JoinColumn(name="user_id", nullable=false)}, inverseJoinColumns={@JoinColumn(name="role_id", nullable=false)})
	private List<TRole> TRoles;

	public TUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNameMother() {
		return this.lastNameMother;
	}

	public void setLastNameMother(String lastNameMother) {
		this.lastNameMother = lastNameMother;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<TComment> getTComments() {
		return this.TComments;
	}

	public void setTComments(List<TComment> TComments) {
		this.TComments = TComments;
	}

	public TComment addTComment(TComment TComment) {
		getTComments().add(TComment);
		TComment.setTUser(this);

		return TComment;
	}

	public TComment removeTComment(TComment TComment) {
		getTComments().remove(TComment);
		TComment.setTUser(null);

		return TComment;
	}

	public List<TEmail> getTEmails() {
		return this.TEmails;
	}

	public void setTEmails(List<TEmail> TEmails) {
		this.TEmails = TEmails;
	}

	public TEmail addTEmail(TEmail TEmail) {
		getTEmails().add(TEmail);
		TEmail.setTUser(this);

		return TEmail;
	}

	public TEmail removeTEmail(TEmail TEmail) {
		getTEmails().remove(TEmail);
		TEmail.setTUser(null);

		return TEmail;
	}

	public List<TEvent> getTEvents() {
		return this.TEvents;
	}

	public void setTEvents(List<TEvent> TEvents) {
		this.TEvents = TEvents;
	}

	public TEvent addTEvent(TEvent TEvent) {
		getTEvents().add(TEvent);
		TEvent.setTUser(this);

		return TEvent;
	}

	public TEvent removeTEvent(TEvent TEvent) {
		getTEvents().remove(TEvent);
		TEvent.setTUser(null);

		return TEvent;
	}

	public List<TPhone> getTPhones() {
		return this.TPhones;
	}

	public void setTPhones(List<TPhone> TPhones) {
		this.TPhones = TPhones;
	}

	public TPhone addTPhone(TPhone TPhone) {
		getTPhones().add(TPhone);
		TPhone.setTUser(this);

		return TPhone;
	}

	public TPhone removeTPhone(TPhone TPhone) {
		getTPhones().remove(TPhone);
		TPhone.setTUser(null);

		return TPhone;
	}

	public List<TGroup> getTGroups() {
		return this.TGroups;
	}

	public void setTGroups(List<TGroup> TGroups) {
		this.TGroups = TGroups;
	}

	public List<TRole> getTRoles() {
		return this.TRoles;
	}

	public void setTRoles(List<TRole> TRoles) {
		this.TRoles = TRoles;
	}

}