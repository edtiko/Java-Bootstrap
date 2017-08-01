package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpCiudad;

/**
 * SgpCiudad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_ciudad", schema = "public")
public class SgpCiudad extends AbstractSgpCiudad implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -792714409879944688L;
	public Integer getIdPais;
	
	/** default constructor */
	public SgpCiudad() {
	}
	
	/** minimal constructor */
	public SgpCiudad(Integer id, SgpDepartamento sgpDepartamento, String nombre, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		super(id, sgpDepartamento, nombre, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpCiudad(Integer id, SgpDepartamento sgpDepartamento, String nombre, String descripcion, Integer indActivo,
			Date fecCreacion, Date fecEdita, String usuarioCrea, String usuarioEdita, Set<SgpEmpresa> sgpEmpresas) {
		super(id, sgpDepartamento, nombre, descripcion, indActivo, fecCreacion, fecEdita, usuarioCrea, usuarioEdita,
				sgpEmpresas);
	}
	
	public SgpCiudad(Integer id) {
		super(id);
	}
	
}
