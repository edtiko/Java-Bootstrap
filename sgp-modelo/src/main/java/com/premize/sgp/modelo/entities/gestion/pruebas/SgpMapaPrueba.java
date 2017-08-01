package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpMapaPrueba;

/**
 * SgpMapaPrueba entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_mapa_prueba", schema = "public")
public class SgpMapaPrueba extends AbstractSgpMapaPrueba implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1715402676087084786L;
	
	/** default constructor */
	public SgpMapaPrueba() {
	}
	
	/** minimal constructor */
	public SgpMapaPrueba(Integer id, SgpProyecto sgpProyecto, String usuarioCrea, String nombre, Integer indActivo,
			Date fecCreacion) {
		super(id, sgpProyecto, usuarioCrea, nombre, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpMapaPrueba(Integer id, SgpProyecto sgpProyecto, String usuarioCrea, String usuarioEdita, String nombre,
			Integer indActivo, Date fecCreacion, Date fecEdita) {
		super(id, sgpProyecto, usuarioCrea, usuarioEdita, nombre, indActivo, fecCreacion, fecEdita);
	}
	
}
