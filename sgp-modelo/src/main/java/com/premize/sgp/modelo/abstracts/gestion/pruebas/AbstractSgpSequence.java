package com.premize.sgp.modelo.abstracts.gestion.pruebas;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractSgpSequence entity provides the base persistence definition of the
 * SgpSequence entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpSequence implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571466847979240283L;
	private String entityName;
	private Integer  sequence;

	// Constructors

	/** default constructor */
	public AbstractSgpSequence() {
	}

	/** minimal constructor */
	public AbstractSgpSequence(String entityName, Integer sequence) {
		this.entityName = entityName;
		this.sequence = sequence;
	}

	@Id
	@Column(name = "entity_name", unique= true, nullable = false)
	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Column(name = "entity_sq", nullable = false)
	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	
}