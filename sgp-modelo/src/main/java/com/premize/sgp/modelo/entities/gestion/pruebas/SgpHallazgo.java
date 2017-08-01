package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpHallazgo;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * SgpHallazgo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sgp_hallazgo", schema = "public")
public class SgpHallazgo extends AbstractSgpHallazgo implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7913071720630353236L;
	
	/** default constructor */
	public SgpHallazgo() {
	}
	
	/** minimal constructor */
	public SgpHallazgo(Integer idHallazgo, String usuarioCrea, SgpCasoPrueba sgpCasoPrueba, SgpTipoHallazgo tipoHallazgo,
			SgpCausaGeneracion causaGeneracion, SgpTipoSeveridad severidad, SgpTipoPrioridad prioridad, String titulo,
			Integer indActivo, Date fecCreacion) {
		super(idHallazgo, usuarioCrea, sgpCasoPrueba, tipoHallazgo, causaGeneracion, severidad, prioridad, titulo,
				indActivo, fecCreacion);
	}
	
	/** full constructor */
	public SgpHallazgo(Integer idHallazgo,
			String usuarioCrea, SgpCasoPrueba sgpCasoPrueba,
			String usuarioEdita, SgpTipoHallazgo tipoHallazgo,
			SgpCausaGeneracion causaGeneracion, SgpTipoSeveridad severidad, SgpTipoPrioridad prioridad,
			SgpParametro causaAnulacion, String titulo, String descripcion,
			Integer indActivo, Date fecCreacion, Date fecEdita,SgpArtefacto artefacto,
			Set<SgpAnexoHallazgo> sgpAnexoHallazgos,
			Set<SgpFlujoHallazgo> sgpFlujoHallazgos) {
		super(idHallazgo, usuarioCrea, sgpCasoPrueba, usuarioEdita, tipoHallazgo, causaGeneracion, severidad,
				prioridad, causaAnulacion, titulo, descripcion, indActivo, fecCreacion, fecEdita,artefacto, sgpAnexoHallazgos,
				sgpFlujoHallazgos);
	}
	 
}
