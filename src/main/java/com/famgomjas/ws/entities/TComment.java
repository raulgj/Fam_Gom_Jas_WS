package com.famgomjas.ws.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the t_comment database table.
 * 
 */
@Entity
@Table(name="t_comment")
public class TComment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id", unique=true, nullable=false)
	private int commentId;

	@Column(nullable=false, length=300)
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on", nullable=false)
	private Date createdOn;

	//bi-directional many-to-one association to TComment
	@ManyToOne
	@JoinColumn(name="related_comment_id")
	private TComment TComment;

	//bi-directional many-to-one association to TComment
	@OneToMany(mappedBy="TComment")
	private List<TComment> TComments;

	//bi-directional many-to-one association to TEvent
	@ManyToOne
	@JoinColumn(name="event_id", nullable=false)
	private TEvent TEvent;

	//bi-directional many-to-one association to TUser
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private TUser TUser;

	public TComment() {
	}

	public int getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public TComment getTComment() {
		return this.TComment;
	}

	public void setTComment(TComment TComment) {
		this.TComment = TComment;
	}

	public List<TComment> getTComments() {
		return this.TComments;
	}

	public void setTComments(List<TComment> TComments) {
		this.TComments = TComments;
	}

	public TComment addTComment(TComment TComment) {
		getTComments().add(TComment);
		TComment.setTComment(this);

		return TComment;
	}

	public TComment removeTComment(TComment TComment) {
		getTComments().remove(TComment);
		TComment.setTComment(null);

		return TComment;
	}

	public TEvent getTEvent() {
		return this.TEvent;
	}

	public void setTEvent(TEvent TEvent) {
		this.TEvent = TEvent;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

}