package com.premize.sgp.modelo.entities.artefacto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.artefacto.AbstractSgpMapaArtefacto;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;

/**
 * SgpMapaArtefacto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_mapa_artefacto", schema = "public")
public class SgpMapaArtefacto extends AbstractSgpMapaArtefacto implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6098833999609380080L;
	
	// Constructors
	
	/** default constructor */
	public SgpMapaArtefacto() {
	}
	
	/** minimal constructor */
	public SgpMapaArtefacto(Integer idMapaArtefacto, String usuarioCrea, SgpArtefacto sgpArtefacto,
			SgpMapaPrueba sgpMapaPrueba, Integer indActivo, Timestamp fecCreacion) {
		super(idMapaArtefacto, usuarioCrea, sgpArtefacto, sgpMapaPrueba, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpMapaArtefacto(Integer idMapaArtefacto, String usuarioCrea, SgpArtefacto sgpArtefacto,
			SgpMapaPrueba sgpMapaPrueba, String usuarioEdita, Integer indActivo, Date fecCreacion, Date fecEdita) {
		super(idMapaArtefacto, usuarioCrea, sgpArtefacto, sgpMapaPrueba, usuarioEdita, indActivo, fecCreacion, fecEdita);
	}
	
}
