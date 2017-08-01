package com.premize.sgp.modelo.entities.gestion.pruebas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.premize.sgp.modelo.abstracts.gestion.pruebas.AbstractSgpIndicadores;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-modelo
* @class SgpIndicadores
* @date 9/07/2014
*
 */
@Entity
@Table(name = "sgp_indicadores", schema = "public")
public class SgpIndicadores extends AbstractSgpIndicadores implements java.io.Serializable {
	
	// Constructors
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4798209554831064931L;

	/** default constructor 
	 * @return */
	public SgpIndicadores() {
	}

	/** minimal constructor */
	public SgpIndicadores(Integer idIndicadores, String nombre, Integer indActivo,
			Date fecCreacion, String usuarioCrea) {
		super(idIndicadores,nombre, indActivo, fecCreacion,usuarioCrea);
	}

	/** full constructor */
	public SgpIndicadores(Integer idIndicadores, String nombre,
			String fase,String formula,String periodicidad,
			Integer indActivo, Date fecCreacion, Date fecEdita,
			String usuarioCrea, String usuarioEdita) {
		super(  idIndicadores,  nombre,
				 fase, formula, periodicidad,
				 indActivo,  fecCreacion,  fecEdita,
				 usuarioCrea,  usuarioEdita);
	}


	






}
