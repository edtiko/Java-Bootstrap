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
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.facade.pruebas.TipoPrioridadFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoPrioridad;
import com.premize.sgp.service.pruebas.TipoPrioridadService;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class PaisFacadeImpl
 * @since 21/01/2014
 */
@Service("tipoPrioridadFacade")
public class TipoPrioridadFacadeImpl extends AbstractEntityFacade<SgpTipoPrioridad, Integer> 
					implements TipoPrioridadFacade {

	@Autowired
	private TipoPrioridadService tipoPrioridadService;
	
	
	@Override
	public EntityService<SgpTipoPrioridad, Integer> getEntityService() {
		return tipoPrioridadService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.TipoHallazgoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoPrioridadDTO> getRecords(PagingCriteria criteria) throws AppBaseException {

		return tipoPrioridadService.getRecords(criteria);
	}

	@Override
	public void guardarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO)
			throws AppBaseException {
		tipoPrioridadService.guardarTipoPrioridad(tipoPrioridadDTO);
		
	}

	@Override
	public TipoPrioridadDTO getTipoPrioridad(Integer idTipoPrioridad) throws AppBaseException {
		return tipoPrioridadService.getTipoPrioridad(idTipoPrioridad);
	}

	@Override
	public void editarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO) throws AppBaseException{

		tipoPrioridadService.editarTipoPrioridad(tipoPrioridadDTO);
		
	}

	@Override
	public List<TipoPrioridadDTO> consultaPrioridadPorTipoHallazgo(Integer idTipoSeveridad,Paginator page) throws AppBaseException {
		return tipoPrioridadService.consultaPrioridadPorTipoHallazgo(idTipoSeveridad,page);
	}

}