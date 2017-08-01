package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpUsuarioProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * SgpUsuarioProyecto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_usuario_proyecto", schema = "public")
public class SgpUsuarioProyecto extends AbstractSgpUsuarioProyecto implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2789396812702514662L;
	
	// Constructors
	
	/** default constructor */
	public SgpUsuarioProyecto() {
	}
	
	/** minimal constructor */
	public SgpUsuarioProyecto(Integer idUsuarioProyecto, SgpProyecto sgpProyecto, String usuarioCrea,
			SgpUsuario sgpUsuario, Integer indActivo, Date fecCreacion) {
		super(idUsuarioProyecto, sgpProyecto, usuarioCrea, sgpUsuario, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpUsuarioProyecto(Integer idUsuarioProyecto, SgpProyecto sgpProyecto, String usuarioCrea,
			SgpUsuario sgpUsuario, String usuarioEdita, Integer indActivo, Date fecCreacion, Date fecEdita) {
		super(idUsuarioProyecto, sgpProyecto, usuarioCrea, sgpUsuario, usuarioEdita, indActivo, fecCreacion, fecEdita);
	}
	
}
