package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpCasoPrueba;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * SgpCasoPrueba entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_caso_prueba", schema = "public")
public class SgpCasoPrueba extends AbstractSgpCasoPrueba implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1816507922133368706L;
	
	/** default constructor */
	public SgpCasoPrueba() {
	}
	
	/** minimal constructor */
	public SgpCasoPrueba(Integer idCasoPrueba, String usuarioCrea, SgpArtefacto sgpArtefacto,
			SgpMapaPrueba sgpMapaPrueba, SgpParametro tipoPrueba, Date fecCreacion, Integer consecutivo) {
		super(idCasoPrueba, usuarioCrea, sgpArtefacto, sgpMapaPrueba, tipoPrueba, fecCreacion, consecutivo);
	}
	
	/** full constructor */
	public SgpCasoPrueba(Integer idCasoPrueba, String usuarioCrea, SgpArtefacto sgpArtefacto,
			SgpMapaPrueba sgpMapaPrueba, String usuarioEdita, String descripcion, String resultado,
			SgpParametro tipoPrueba, String cumple, Date fecCreacion, Date fecEdita, String usuarioEjecuta,
			Date fecEjecuta, Integer consecutivo, Set<SgpHallazgo> sgpHallazgos) {
		super(idCasoPrueba, usuarioCrea, sgpArtefacto, sgpMapaPrueba, usuarioEdita, descripcion, resultado, tipoPrueba,
				cumple, fecCreacion, fecEdita, usuarioEjecuta, fecEjecuta, consecutivo, sgpHallazgos);
	}
	
}
