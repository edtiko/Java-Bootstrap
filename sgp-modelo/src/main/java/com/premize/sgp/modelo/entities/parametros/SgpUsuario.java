package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpUsuario;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto;

/**
 * SgpUsuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_usuario", schema = "public")
public class SgpUsuario extends AbstractSgpUsuario implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7231375651421862363L;
	
	/** default constructor */
	public SgpUsuario() {
	}
	
	/** minimal constructor */
	public SgpUsuario(Integer id, String nombre, String empresa, String cargo, String login, String correo,
			Integer indActivo, Date fecCreacion, String usuarioCrea) {
		super(id, nombre, empresa, cargo, login, correo, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpUsuario(Integer id, String nombre, String empresa, String cargo, String login, String correo,
			String telefono, Integer indActivo, Date fecCreacion, Date fecEdita, String usuarioCrea,
			String usuarioEdita, Set<SgpFlujoHallazgo> sgpFlujoHallazgos, Set<SgpUsuarioProyecto> sgpUsuario) {
		super(id, nombre, empresa, cargo, login, correo, telefono, indActivo, fecCreacion, fecEdita, usuarioCrea,
				usuarioEdita, sgpFlujoHallazgos, sgpUsuario);
	}
	
	/** customized constructor for id */
	public SgpUsuario(Integer idUsuario) {
		super(idUsuario);
	}
	
}
