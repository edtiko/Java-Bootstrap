package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpParametro;

/**
 * SgpParametro entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_parametro", schema = "public")
public class SgpParametro extends AbstractSgpParametro implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9049834786496166954L;
	
	/** default constructor */
	public SgpParametro() {
	}
	
	/** minimal constructor */
	public SgpParametro(Integer idParametro, SgpTipoParametro sgpTipoParametro, String usuarioCrea, String valor,
			Integer indActivo, Date fecCreacion) {
		super(idParametro, sgpTipoParametro, usuarioCrea, valor, indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpParametro(Integer idParametro, SgpTipoParametro sgpTipoParametro, String usuarioCrea,
			String usuarioEdita, String nombre, String descripcion, String valor, Integer indActivo, Date fecCreacion,
			Date fecEdita) {
		super(idParametro, sgpTipoParametro, usuarioCrea, usuarioEdita, nombre, descripcion, valor, indActivo,
				fecCreacion, fecEdita);
	}
	
}
