/**
 * 
 */
package com.premize.sgp.service.pruebas.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;
import com.premize.sgp.service.pruebas.MapaArtefactoService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroServiceImpl
 * @since 15/01/2014
 */
@Service
public class MapaArtefactoServiceImpl extends AbstractEntityService<SgpMapaArtefacto, Integer> implements
		MapaArtefactoService {

	
	@Autowired
	private MapaArtefactoDao mapaArtefactoDao;
	
	@Override
	public MapaArtefactoDao getDao() {
		return mapaArtefactoDao;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.pruebas.MapaArtefactoService#consultarArtefactosPorMapaPruebas(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<MapaArtefactoDTO> consultarArtefactosPorMapaPruebas(Integer idMapaPruebas, Paginator page)
			throws AppBaseException {
		
		return getDao().consultarMapaPruebasPorProyecto(idMapaPruebas, page);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 11/03/2014
	 * @see com.premize.sgp.service.pruebas.MapaArtefactoService#getArtefactosActivosMapaDePruebas(java.lang.Integer,
	 *      com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<MapaArtefactoDTO> getArtefactosActivosMapaDePruebas(Integer idMapaPruebas, Paginator page)
			throws AppBaseException {
		
		return mapaArtefactoDao.getArtefactosActivosMapaDePruebas(idMapaPruebas, page);
	}
	
}
