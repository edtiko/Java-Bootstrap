package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpEmpresa;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;

/**
 * SgpEmpresa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_empresa", schema = "public")
public class SgpEmpresa extends AbstractSgpEmpresa implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3948172519707207199L;
	
	/** default constructor */
	public SgpEmpresa() {
	}
	
	/** minimal constructor */
	public SgpEmpresa(Integer id, String usuarioCrea, SgpCiudad sgpCiudad, String nombre, String rutaLogo,
			Integer indActivo, Date fecCreacion) {
		super(id, usuarioCrea, sgpCiudad, nombre, rutaLogo, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpEmpresa(Integer id, String usuarioCrea, SgpCiudad sgpCiudad, String usuarioEdita, String nombre,
			String descripcion, String rutaLogo, String direccion, String telefono, String nit, Integer indActivo,
			Date fecCreacion, Date fecEdita, Set<SgpProyecto> sgpProyectos) {
		super(id, usuarioCrea, sgpCiudad, usuarioEdita, nombre, descripcion, rutaLogo, direccion, telefono, nit,
				indActivo, fecCreacion, fecEdita, sgpProyectos);
	}
	
}
