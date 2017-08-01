/**
 * 
 */
package com.premize.sgp.facade.pruebas;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-api
 * @class MapaArtefactoFacade
 * @since 22/01/2014
 */
public interface MapaArtefactoFacade extends EntityFacade<SgpMapaArtefacto, Integer> {
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idEmpresa
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	List<MapaArtefactoDTO> consultarArtefactosPorMapaPruebas(Integer idMapaPruebas, Paginator page)
			throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @param id_MapaArtefacto
	 * @param default1
	 * @return
	 */
	List<MapaArtefactoDTO> getArtefactosActivosMapaDePruebas(Integer idMapaPruebas, Paginator page)
			throws AppBaseException;
	
}
