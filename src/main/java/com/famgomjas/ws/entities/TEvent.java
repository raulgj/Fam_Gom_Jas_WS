package com.famgomjas.ws.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the t_event database table.
 * 
 */
@Entity
@Table(name="t_event")
public class TEvent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_id", unique=true, nullable=false)
	private int eventId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on", nullable=false)
	private Date createdOn;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	@Column(length=45)
	private String description;

	@Column(nullable=false, length=45)
	private String title;

	//bi-directional many-to-one association to TComment
	@OneToMany(mappedBy="TEvent")
	private List<TComment> TComments;

	//bi-directional many-to-one association to TUser
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private TUser TUser;

	//bi-directional many-to-one association to TGroup
	@ManyToOne
	@JoinColumn(name="group_id", nullable=false)
	private TGroup TGroup;

	//bi-directional many-to-one association to TMedia
	@OneToMany(mappedBy="TEvent")
	private List<TMedia> TMedias;

	public TEvent() {
	}

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TComment> getTComments() {
		return this.TComments;
	}

	public void setTComments(List<TComment> TComments) {
		this.TComments = TComments;
	}

	public TComment addTComment(TComment TComment) {
		getTComments().add(TComment);
		TComment.setTEvent(this);

		return TComment;
	}

	public TComment removeTComment(TComment TComment) {
		getTComments().remove(TComment);
		TComment.setTEvent(null);

		return TComment;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public TGroup getTGroup() {
		return this.TGroup;
	}

	public void setTGroup(TGroup TGroup) {
		this.TGroup = TGroup;
	}

	public List<TMedia> getTMedias() {
		return this.TMedias;
	}

	public void setTMedias(List<TMedia> TMedias) {
		this.TMedias = TMedias;
	}

	public TMedia addTMedia(TMedia TMedia) {
		getTMedias().add(TMedia);
		TMedia.setTEvent(this);

		return TMedia;
	}

	public TMedia removeTMedia(TMedia TMedia) {
		getTMedias().remove(TMedia);
		TMedia.setTEvent(null);

		return TMedia;
	}

}