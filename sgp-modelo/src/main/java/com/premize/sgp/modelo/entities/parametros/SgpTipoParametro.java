package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpTipoParametro;

/**
 * SgpTipoParametro entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_tipo_parametro", schema = "public")
public class SgpTipoParametro extends AbstractSgpTipoParametro implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1207910752613763993L;
	
	/** default constructor */
	public SgpTipoParametro() {
	}
	
	public SgpTipoParametro(Integer id) {
		super();
	}
	
	/** minimal constructor */
	public SgpTipoParametro(Integer idTipoParametro, String nombre, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		super(idTipoParametro, nombre, indActivo, fecCreacion, usuarioCrea);
	}
	
	/** full constructor */
	public SgpTipoParametro(Integer idTipoParametro, String nombre, String descripcion, Integer indActivo,
			Date fecCreacion, Date fecEdita, String usuarioCrea, String usuarioEdita, Set<SgpParametro> sgpParametros) {
		super(idTipoParametro, nombre, descripcion, indActivo, fecCreacion, fecEdita, usuarioCrea, usuarioEdita,
				sgpParametros);
	}
	
}
