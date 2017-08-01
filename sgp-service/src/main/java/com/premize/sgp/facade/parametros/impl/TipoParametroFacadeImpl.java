/**
 * 
 */
package com.premize.sgp.facade.parametros.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.facade.parametros.TipoParametroFacade;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;
import com.premize.sgp.service.TipoParametroService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class TipoParametroFacadeImpl
 * @since 28/01/2014
 */
@Service
public class TipoParametroFacadeImpl extends AbstractEntityFacade<SgpTipoParametro, Integer> implements
		TipoParametroFacade {
	
	@Autowired
	TipoParametroService tipoParametroService;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 28/01/2014
	 * @see com.premize.pmz.facade.AbstractEntityFacade#getEntityService()
	 */
	@Override
	public EntityService<SgpTipoParametro, Integer> getEntityService() {
		return tipoParametroService;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 28/01/2014
	 * @see com.premize.sgp.facade.parametros.TipoParametroFacade#getTipos()
	 */
	@Override
	public List<TipoParametroDTO> getTipos() throws AppBaseException {
		
		return tipoParametroService.getTipos();
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.comR">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @see com.premize.sgp.facade.parametros.TipoParametroFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return tipoParametroService.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 29/01/2014
	 * @see com.premize.sgp.facade.parametros.TipoParametroFacade#guardarParametro(com.premize.sgp.dto.parametros.TipoParametroDTO)
	 */
	@Override
	public void guardarTipoParametro(TipoParametroDTO tipoParametrodto) throws AppBaseException {
		
		tipoParametroService.guardarTipoParametro(tipoParametrodto);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @see com.premize.sgp.facade.parametros.TipoParametroFacade#editarParametro(com.premize.sgp.dto.parametros.TipoParametroDTO)
	 */
	@Override
	public void editarTipoParametro(TipoParametroDTO tipoParametrodto) throws AppBaseException {
		tipoParametroService.editarTipoParametro(tipoParametrodto);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @see com.premize.sgp.facade.parametros.TipoParametroFacade#getTipoParametro(java.lang.Integer)
	 */
	@Override
	public TipoParametroDTO getTipoParametro(Integer idTipoParametro) throws AppBaseException {
		return tipoParametroService.getTipoParametro(idTipoParametro);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.TipoParametroFacade#listarParametrosPorTipoHallazgos(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<TipoParametroDTO> listarParametrosPorTipoHallazgos(Integer idTipoParametro, Paginator page)
			throws AppBaseException {
		
		return tipoParametroService.listarParametrosPorTipoHallazgo(idTipoParametro, page);
	}
	
}
