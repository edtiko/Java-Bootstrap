package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpTipoPrioridad;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * SgpDepartamento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_tipo_prioridad", schema = "public")
public class SgpTipoPrioridad extends AbstractSgpTipoPrioridad implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 751787690886739497L;
	
	/** default constructor */
	public SgpTipoPrioridad() {
	}
	
	/** minimal constructor */
	public SgpTipoPrioridad(Integer id, SgpTipoSeveridad tipoSeveridad, String nombre, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		super(id, tipoSeveridad, nombre, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpTipoPrioridad(Integer id, SgpTipoSeveridad tipoSeveridad,
			String nombre, String descripcion, Integer indActivo,Float puntaje,
			Date fecCreacion, Date fecEdita, String usuarioCrea,
			String usuarioEdita, Set<SgpCiudad> sgpCiudads) {
		super(id, tipoSeveridad, nombre, descripcion, indActivo,puntaje, fecCreacion, fecEdita, usuarioCrea, usuarioEdita, sgpCiudads);
	}
	
}
