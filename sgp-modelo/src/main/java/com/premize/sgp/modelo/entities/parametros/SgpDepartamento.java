package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpDepartamento;

/**
 * SgpDepartamento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_departamento", schema = "public")
public class SgpDepartamento extends AbstractSgpDepartamento implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 751787690886739497L;
	
	/** default constructor */
	public SgpDepartamento() {
	}
	
	/** minimal constructor */
	public SgpDepartamento(Integer id, SgpPais sgpPais, String nombre, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		super(id, sgpPais, nombre, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpDepartamento(Integer id, SgpPais sgpPais, String nombre, String descripcion, Integer indActivo,
			Date fecCreacion, Date fecEdita, String usuarioCrea, String usuarioEdita, Set<SgpCiudad> sgpCiudads) {
		super(id, sgpPais, nombre, descripcion, indActivo, fecCreacion, fecEdita, usuarioCrea, usuarioEdita, sgpCiudads);
	}
	
}
