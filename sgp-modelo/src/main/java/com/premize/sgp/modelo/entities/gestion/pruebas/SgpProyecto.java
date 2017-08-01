package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;

/**
 * SgpProyecto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_proyecto", schema = "public")
public class SgpProyecto extends AbstractSgpProyecto implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4414701935746757623L;
	
	/** default constructor */
	public SgpProyecto() {
	}
	
	/** minimal constructor */
	public SgpProyecto(Integer id, SgpEmpresa sgpEmpresa, String usuarioCrea, String nombre, Integer indActivo,
			Date fecCreacion) {
		super(id, sgpEmpresa, usuarioCrea, nombre, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpProyecto(Integer id, SgpEmpresa sgpEmpresa, String usuarioCrea, String usuarioEdita, String nombre,
			String descripcion, Integer indActivo, Date fecCreacion, Date fecEdita, Set<SgpMapaPrueba> sgpMapaPruebas,
			Set<SgpUsuarioProyecto> sgpUsuarioProyectos) {
		super(id, sgpEmpresa, usuarioCrea, usuarioEdita, nombre, descripcion, indActivo, fecCreacion, fecEdita,
				sgpMapaPruebas, sgpUsuarioProyectos);
	}
	
}
