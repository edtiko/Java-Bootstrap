package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpPais;

/**
 * SgpPais entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_pais", schema = "public")
public class SgpPais extends AbstractSgpPais implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8343661350047436128L;
	
	/** default constructor */
	public SgpPais() {
	}
	
	/** minimal constructor */
	public SgpPais(Integer id, String nombre, Integer indActivo, Date fecCreacion, String usuarioCrea) {
		super(id, nombre, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpPais(Integer id, String nombre, String descripcion, Integer indActivo, Date fecCreacion, Date fecEdita,
			String usuarioCrea, String usuarioEdita) {
		super(id, nombre, descripcion, indActivo, fecCreacion, fecEdita, usuarioCrea, usuarioEdita);
	}
	
}
