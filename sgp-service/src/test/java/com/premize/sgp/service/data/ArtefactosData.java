/**
 * 
 */
package com.premize.sgp.service.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class ArtefactosData
 * @since 14/02/2014
 */
public class ArtefactosData {
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @return
	 */
	public static List<ArtefactoDTO> getListaArtefactosDTO() {
		List<ArtefactoDTO> list = new ArrayList<ArtefactoDTO>();
		list.add(new ArtefactoDTO());
		list.add(new ArtefactoDTO());
		
		return list;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 */
	public static List<SgpArtefacto> getListaArtefacto() {
		List<SgpArtefacto> listArtefactos = new ArrayList<SgpArtefacto>();
		listArtefactos.add(new SgpArtefacto(1, "admin", new SgpTipoArtefacto(), "daniel", 1, Calendar.getInstance()
				.getTime(), new SgpProyecto()));
		listArtefactos.add(new SgpArtefacto(2, "syso", new SgpTipoArtefacto(), "Orlando", 1, Calendar.getInstance()
				.getTime(), new SgpProyecto()));
		
		return listArtefactos;
	}
}
