package br.com.emtest.persistence.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Language generated by hbm2java
 */
@Entity
@Table(name = "language")
public class Language implements java.io.Serializable {

	private static final long serialVersionUID = 5949521556806624327L;
	
	private Byte languageId;
	private String name;
	private Date lastUpdate;

	public Language() {
	}

	public Language(String name, Date lastUpdate) {
		this.name = name;
		this.lastUpdate = lastUpdate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "language_id", unique = true, nullable = false)
	public Byte getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Byte languageId) {
		this.languageId = languageId;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update", nullable = false, length = 19)
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
