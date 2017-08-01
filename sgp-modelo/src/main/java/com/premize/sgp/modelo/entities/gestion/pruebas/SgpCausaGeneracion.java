package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpCausaGeneracion;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * SgpDepartamento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_causa_de_generacion", schema = "public")
public class SgpCausaGeneracion extends AbstractSgpCausaGeneracion implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 751787690886739497L;
	
	/** default constructor */
	public SgpCausaGeneracion() {
	}
	
	/** minimal constructor */
	public SgpCausaGeneracion(Integer id, SgpTipoHallazgo sgpTipoHallazgo, String nombre, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		super(id, sgpTipoHallazgo, nombre, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpCausaGeneracion(Integer id, SgpTipoHallazgo sgpTipoHallazgo, String nombre, String descripcion, Integer indActivo,
			Date fecCreacion, Date fecEdita, String usuarioCrea, String usuarioEdita, Set<SgpCiudad> sgpCiudads) {
		super(id, sgpTipoHallazgo, nombre, descripcion, indActivo, fecCreacion, fecEdita, usuarioCrea, usuarioEdita, sgpCiudads);
	}
	
}
