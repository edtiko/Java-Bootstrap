/**
 * 
 */
package com.premize.sgp.facade.pruebas.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.facade.pruebas.MapaArtefactoFacade;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;
import com.premize.sgp.service.pruebas.MapaArtefactoService;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class MapaPruebaFacade
 * @since 22/01/2014
 */
@Service("mapaArtefactoFacadeImp")
public class MapaArtefactoFacadeImp extends AbstractEntityFacade<SgpMapaArtefacto, Integer> implements
		MapaArtefactoFacade {
	@Autowired
	private MapaArtefactoService mapaArtefactoService;
	
	@Override
	public MapaArtefactoService getEntityService() {
		
		return mapaArtefactoService;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.MapaArtefactoFacade#consultarArtefactosPorMapaPruebas(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<MapaArtefactoDTO> consultarArtefactosPorMapaPruebas(Integer idMapaPruebas, Paginator page)
			throws AppBaseException {
		
		return getEntityService().consultarArtefactosPorMapaPruebas(idMapaPruebas, page);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @see com.premize.sgp.facade.pruebas.MapaArtefactoFacade#getArtefactosActivosMapaDePruebas(java.lang.Integer,
	 *      com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<MapaArtefactoDTO> getArtefactosActivosMapaDePruebas(Integer idMapaPruebas, Paginator page)
			throws AppBaseException {
		
		return getEntityService().getArtefactosActivosMapaDePruebas(idMapaPruebas, page);
	}
	
}
