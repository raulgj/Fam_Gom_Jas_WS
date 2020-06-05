package com.famgomjas.ws.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="actions")
public class Action {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="action_id")
    private Long actionId;
	
	@Column(name="name", nullable=false)
    private String name;
	
	@Column(name="is_active", nullable=false, columnDefinition="BIT", length=1)
    private boolean isActive;
	
	@ManyToMany(mappedBy = "actions")
	@JsonBackReference
    private Collection<Role> roles;

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
}
