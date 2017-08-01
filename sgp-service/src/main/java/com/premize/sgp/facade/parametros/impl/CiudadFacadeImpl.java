package com.premize.sgp.facade.parametros.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.CiudadDTO;
import com.premize.sgp.facade.parametros.CiudadFacade;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;
import com.premize.sgp.service.parametros.CiudadService;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class CiudadFacadeImpl
 * @since 24/02/2014
 */

@Service("ciudadFacade")
public class CiudadFacadeImpl extends AbstractEntityFacade<SgpCiudad, Integer> implements CiudadFacade {
	
	@Autowired
	private CiudadService ciudadService;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.CiudadFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<CiudadDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return ciudadService.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.CiudadFacade#guardarCiudad(com.premize.sgp.dto.parametros.CiudadDTO)
	 */
	@Override
	public void guardarCiudad(CiudadDTO ciudad) throws AppBaseException {
		
		ciudadService.guardarCiudad(ciudad);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.CiudadFacade#editarCiudad(com.premize.sgp.dto.parametros.CiudadDTO)
	 */
	@Override
	public void editarCiudad(CiudadDTO ciudad) throws AppBaseException {
		ciudadService.editarCiudad(ciudad);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.pmz.facade.AbstractEntityFacade#getEntityService()
	 */
	@Override
	public CiudadService getEntityService() {
		
		return ciudadService;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 31/01/2014
	 * @see com.premize.sgp.facade.parametros.PaisFacade#getPaises()
	 */
	
	@Override
	public CiudadDTO getCiudad(Integer idCiudad) throws AppBaseException {
		
		return ciudadService.getCiudad(idCiudad);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.CiudadFacade#consultarCiudadPorDepartamento(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<CiudadDTO> consultarCiudadPorDepartamento(Integer id_Departamento, Paginator default1)
			throws AppBaseException {
		return getEntityService().consultarCiudadPorDepartamento(id_Departamento, default1);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.CiudadFacade#getListCiudades()
	 */
	@Override
	public List<CiudadDTO> getListCiudades() throws AppBaseException {
		return null;
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.CiudadFacade#consultarCiudadDTOs(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<CiudadDTO> consultarCiudadDTOs(Integer id_Ciudad, Paginator default1) {
		return null;
	}
	
}
