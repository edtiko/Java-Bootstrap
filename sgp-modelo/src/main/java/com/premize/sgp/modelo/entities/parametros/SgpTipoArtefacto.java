package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpTipoArtefacto;

/**
 * SgpTipoArtefacto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_tipo_artefacto", schema = "public")
public class SgpTipoArtefacto extends AbstractSgpTipoArtefacto implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6204173556086071535L;
	
	// Constructors
	
	/** default constructor */
	public SgpTipoArtefacto() {
	}
	
	/** minimal constructor */
	public SgpTipoArtefacto(Integer idTipoArtefacto, String usuarioCrea, String nombre, Integer indActivo,
			Date fecCreacion) {
		super(idTipoArtefacto, usuarioCrea, nombre, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpTipoArtefacto(Integer idTipoArtefacto, String usuarioCrea, String usuarioEdita, String nombre,
			String descripcion, Integer indActivo, Date fecCreacion, Date fecEdita, Set<SgpArtefacto> sgpArtefactos) {
		super(idTipoArtefacto, usuarioCrea, usuarioEdita, nombre, descripcion, indActivo, fecCreacion, fecEdita,
				sgpArtefactos);
	}
	
	/** customized constructor for id */
	public SgpTipoArtefacto(Integer idTipoArtefacto) {
		super(idTipoArtefacto);
	}
}
