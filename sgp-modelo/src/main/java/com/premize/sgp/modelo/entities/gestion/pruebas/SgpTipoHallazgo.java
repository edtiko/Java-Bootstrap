package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpTipoHallazgo;

/**
 * SgpPais entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_tipo_hallazgo", schema = "public")
public class SgpTipoHallazgo extends AbstractSgpTipoHallazgo implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8343661350047436128L;
	
	/** default constructor */
	public SgpTipoHallazgo() {
	}
	
	/** minimal constructor */
	public SgpTipoHallazgo(Integer id, String nombre, Integer indActivo, Date fecCreacion, String usuarioCrea) {
		super(id, nombre, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpTipoHallazgo(Integer id, String nombre, String descripcion, Integer indActivo, Date fecCreacion, Date fecEdita,
			String usuarioCrea, String usuarioEdita,Integer esPuntuado) {
		super(id, nombre, descripcion, indActivo, fecCreacion, fecEdita, usuarioCrea, usuarioEdita,esPuntuado);
	}
	
}
