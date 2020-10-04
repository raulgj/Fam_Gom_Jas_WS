package com.famgomjas.ws.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the t_media database table.
 * 
 */
@Entity
@Table(name="t_media")
public class TMedia {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_id", unique=true, nullable=false)
	private int mediaId;

	@Lob
	@Column(nullable=false)
	private byte[] file;

	@Column(name="file_name", nullable=false, length=100)
	private String fileName;

	@Column(name="is_active", nullable=false)
	private byte isActive;

	//bi-directional many-to-one association to TEvent
	@ManyToOne
	@JoinColumn(name="event_id", nullable=false)
	private TEvent TEvent;

	public TMedia() {
	}

	public int getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public TEvent getTEvent() {
		return this.TEvent;
	}

	public void setTEvent(TEvent TEvent) {
		this.TEvent = TEvent;
	}

}