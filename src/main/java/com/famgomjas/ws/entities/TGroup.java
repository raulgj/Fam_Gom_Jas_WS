package com.famgomjas.ws.entities;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the t_group database table.
 * 
 */
@Entity
@Table(name="t_group")
public class TGroup {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="group_id", unique=true, nullable=false)
	private int groupId;

	@Column(length=45)
	private String description;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to TEvent
	@OneToMany(mappedBy="TGroup")
	private List<TEvent> TEvents;

	@ManyToMany(mappedBy="TGroups")
	private List<TUser> TUsers;

	public TGroup() {
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TEvent> getTEvents() {
		return this.TEvents;
	}

	public void setTEvents(List<TEvent> TEvents) {
		this.TEvents = TEvents;
	}

	public TEvent addTEvent(TEvent TEvent) {
		getTEvents().add(TEvent);
		TEvent.setTGroup(this);

		return TEvent;
	}

	public TEvent removeTEvent(TEvent TEvent) {
		getTEvents().remove(TEvent);
		TEvent.setTGroup(null);

		return TEvent;
	}

	public List<TUser> getTUsers() {
		return this.TUsers;
	}

	public void setTUsers(List<TUser> TUsers) {
		this.TUsers = TUsers;
	}

}