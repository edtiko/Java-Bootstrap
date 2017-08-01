package com.premize.sgp.modelo.entities.parametros;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.parametros.AbstractSgpArtefacto;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;

/**
 * SgpArtefacto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_artefacto", schema = "public")
public class SgpArtefacto extends AbstractSgpArtefacto implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8034734936441649369L;
	
	/** default constructor */
	public SgpArtefacto() {
	}
	
	/** minimal constructor */
	public SgpArtefacto(Integer id, String usuarioCrea, SgpTipoArtefacto sgpTipoArtefacto, String nombre,
			Integer indActivo, Date fecCreacion, SgpProyecto proyecto) {
		super(id, usuarioCrea, sgpTipoArtefacto, nombre, indActivo, fecCreacion, proyecto);
	}
	
	/** full constructor */
	public SgpArtefacto(Integer id, String usuarioCrea, SgpTipoArtefacto sgpTipoArtefacto, String usuarioEdita,
			String nombre, String descripcion, Integer indActivo, Date fecCreacion, Date fecEdita,
			SgpProyecto proyecto, SgpUsuario usuario, Set<SgpCasoPrueba> sgpCasoPruebas, Set<SgpMapaArtefacto> sgpMapaArtefactos) {
		super(id, usuarioCrea, sgpTipoArtefacto, usuarioEdita, nombre, descripcion, indActivo, fecCreacion, fecEdita,
				proyecto, usuario, sgpCasoPruebas, sgpMapaArtefactos);
	}
	
	/** customized constructor */
	public SgpArtefacto(Integer id) {
		super(id);
	}
	
}
