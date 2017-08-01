package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpEstadoFlujo;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * SgpEstadoFlujo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_estado_flujo", schema = "public")
public class SgpEstadoFlujo extends AbstractSgpEstadoFlujo implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7665180381417337821L;
	
	/** default constructor */
	public SgpEstadoFlujo() {
	}
	
	/** minimal constructor */
	public SgpEstadoFlujo(Integer idEstadoFlujo, String usuarioCrea, SgpParametro flujoActual,
			SgpParametro flujoSiguiente, SgpParametro tipoHallazgo, Integer indActivo, Date fecCreacion) {
		super(idEstadoFlujo, usuarioCrea, flujoActual, flujoSiguiente, tipoHallazgo, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpEstadoFlujo(Integer idEstadoFlujo, String usuarioCrea, String usuarioEdita, SgpParametro flujoActual,
			SgpParametro flujoSiguiente, SgpParametro tipoHallazgo, Integer indActivo, Date fecCreacion, Date fecEdita) {
		super(idEstadoFlujo, usuarioCrea, usuarioEdita, flujoActual, flujoSiguiente, tipoHallazgo, indActivo,
				fecCreacion, fecEdita);
	}
	
}
