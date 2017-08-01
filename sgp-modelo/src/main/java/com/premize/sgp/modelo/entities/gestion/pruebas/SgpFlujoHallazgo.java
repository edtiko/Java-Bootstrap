package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * SgpFlujoHallazgo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_flujo_hallazgo", schema = "public")
public class SgpFlujoHallazgo extends AbstractSgpFlujoHallazgo implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7518818129768774220L;
	
	/** default constructor */
	public SgpFlujoHallazgo() {
	}
	
	/** minimal constructor */
	public SgpFlujoHallazgo(Integer idFlujoHallazgo, SgpHallazgo sgpHallazgo, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		super(idFlujoHallazgo, sgpHallazgo, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpFlujoHallazgo(Integer idFlujoHallazgo, SgpUsuario sgpUsuario, SgpHallazgo sgpHallazgo,
			SgpUsuario idUsuarioAsignado, String observacion, Integer indActivo, Date fecCreacion, Date fecEdita,
			String usuarioCrea, String usuarioEdita, SgpParametro accion) {
		super(idFlujoHallazgo, sgpUsuario, sgpHallazgo, idUsuarioAsignado, observacion, indActivo, fecCreacion,
				fecEdita, usuarioCrea, usuarioEdita, accion);
	}
	
}
