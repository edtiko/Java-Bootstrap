package com.premize.sgp.modelo.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpSequence;

/**
 * SgpSequence entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_sequences", schema = "public")
public class SgpSequence extends AbstractSgpSequence implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2532061819159996994L;
	
	// Constructors
	
	/** default constructor */
	public SgpSequence() {
	}
	
	/** minimal constructor */
	public SgpSequence(String entityName, Integer sequence) {
		super(entityName, sequence);
	}
	
}
