/**
 * 
 */
package com.premize.sgp.facade.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.ParametroService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroFacadeImpl
 * @since 17/01/2014
 */
@Service("parametroFacade")
public class ParametroFacadeImpl extends AbstractEntityFacade<SgpParametro, Integer> implements ParametroFacade {
	
	@Autowired
	private ParametroService parametroService;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.pmz.facade.AbstractEntityFacade#getEntityService()
	 */
	@Override
	public EntityService<SgpParametro, Integer> getEntityService() {
		
		return parametroService;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.ParametroFacade#getListParametros()
	 */
	@Override
	public List<ParametroDTO> getListParametros() throws AppBaseException {
		
		return parametroService.getListParametros();

	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.ParametroFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return parametroService.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.ParametroFacade#guardarParametro(com.premize.sgp.dto.parametros.ParametroDTO)
	 */
	@Override
	public void guardarParametro(ParametroDTO parametro) throws Exception {
		
		parametroService.guardarParametro(parametro);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.ParametroFacade#editarParametro(com.premize.sgp.dto.parametros.ParametroDTO)
	 */
	@Override
	public void editarParametro(ParametroDTO parametro) throws AppBaseException {
		parametroService.editarParametro(parametro);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 28/01/2014
	 * @see com.premize.sgp.facade.ParametroFacade#getParametro(int)
	 */
	@Override
	public ParametroDTO getParametro(Integer idParametro) throws AppBaseException {
		
		return parametroService.getParametro(idParametro);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 6/06/2014
	 * @description 
	 * @param parametroKey
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public ParametroDTO getParametro(String parametroKey) throws AppBaseException {
		return parametroService.getParametro(parametroKey);
	}
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 * @param idTipoParametro
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public List<ParametroDTO> listarParametrosPorTipoParametro(Integer idTipoParametro, Paginator page)
			throws AppBaseException {
		
		return parametroService.listarParametrosPorTipoParametro(idTipoParametro, page);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 29/05/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public Map<String,Object> getMapRestriccionesAnexo() throws AppBaseException {
		return parametroService.getMapRestriccionesAnexo();
	}
/*
 * (non-Javadoc)
 * @see com.premize.sgp.facade.ParametroFacade#getEstadoNoSolicitaUsuario(java.lang.String)
 */
	@Override
	public boolean getEstadoNoSolicitaUsuario(String estado) throws AppBaseException {
		
		return parametroService.getEstadoNoSolicitaUsuario(estado);
	}
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.ParametroFacade#getMapRestriccionesLogo()
	 */
	@Override
	public Map<String,Object> getMapRestriccionesLogo() throws AppBaseException {
		return parametroService.getMapRestriccionesLogo();
	}
}
