package com.premize.sgp.modelo.entities.anexo;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.anexo.AbstractSgpAnexoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;

/**
 * SgpAnexoHallazgo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_anexo_hallazgo", schema = "public")
public class SgpAnexoHallazgo extends AbstractSgpAnexoHallazgo implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5181649356659779147L;
	
	/** default constructor */
	public SgpAnexoHallazgo() {
	}
	
	/** minimal constructor */
	public SgpAnexoHallazgo(Integer idAnexoHallazgo, String usuarioCrea, SgpHallazgo sgpHallazgo, String ruta,
			Integer indActivo, Date fecCreacion) {
		super(idAnexoHallazgo, usuarioCrea, sgpHallazgo, ruta, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpAnexoHallazgo(Integer idAnexoHallazgo, String usuarioCrea, SgpHallazgo sgpHallazgo, String usuarioEdita,
			String ruta, Integer indActivo, Date fecCreacion, Timestamp fecEdita) {
		super(idAnexoHallazgo, usuarioCrea, sgpHallazgo, usuarioEdita, ruta, indActivo, fecCreacion, fecEdita);
	}
	
}
