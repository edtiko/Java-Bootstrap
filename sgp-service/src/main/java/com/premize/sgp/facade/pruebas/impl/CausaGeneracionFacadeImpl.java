/**
 * 
 */
package com.premize.sgp.facade.pruebas.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CausaGeneracionDTO;
import com.premize.sgp.facade.pruebas.CausaGeneracionFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;
import com.premize.sgp.service.pruebas.CausaGeneracionService;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class PaisFacadeImpl
 * @since 21/01/2014
 */
@Service("causaGeneracionFacade")
public class CausaGeneracionFacadeImpl extends AbstractEntityFacade<SgpCausaGeneracion, Integer> implements CausaGeneracionFacade {

	@Autowired
	private CausaGeneracionService causaGeneracionService;
	
	public EntityService<SgpCausaGeneracion, Integer> getEntityService() {
		return causaGeneracionService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.TipoHallazgoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<CausaGeneracionDTO> getRecords(PagingCriteria criteria) throws AppBaseException {

		return causaGeneracionService.getRecords(criteria);
	}

	@Override
	public void guardarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO)
			throws AppBaseException {
		causaGeneracionService.guardarCausaGeneracion(causaGeneracionDTO);
		
	}

	@Override
	public CausaGeneracionDTO getCausaGeneracion(Integer idCausaGeneracion) throws AppBaseException {
		return causaGeneracionService.getCausaGeneracion(idCausaGeneracion);
	}

	@Override
	public void editarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException{

		causaGeneracionService.editarCausaGeneracion(causaGeneracionDTO);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.CausaGeneracionFacade#consultaCausaPorTipoHallazgo(java.lang.Integer)
	 */
	@Override
	public List<CausaGeneracionDTO> consultaCausaPorTipoHallazgo(Integer idTipoHallazgo,Paginator page)throws AppBaseException {
		
		return causaGeneracionService.consultaCausaPorTipoHallazgo(idTipoHallazgo,page);
	}


}