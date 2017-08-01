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
import com.premize.sgp.dto.pruebas.TipoSeveridadDTO;
import com.premize.sgp.facade.pruebas.TipoSeveridadFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;
import com.premize.sgp.service.pruebas.TipoSeveridadService;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class PaisFacadeImpl
 * @since 21/01/2014
 */
@Service("tipoSeveridadFacade")
public class TipoSeveridadFacadeImpl extends AbstractEntityFacade<SgpTipoSeveridad, Integer> 
					implements TipoSeveridadFacade {

	@Autowired
	private TipoSeveridadService tipoSeveridadService;
	
	
	@Override
	public EntityService<SgpTipoSeveridad, Integer> getEntityService() {
		return tipoSeveridadService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.TipoHallazgoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoSeveridadDTO> getRecords(PagingCriteria criteria) throws AppBaseException {

		return tipoSeveridadService.getRecords(criteria);
	}

	@Override
	public void guardarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO)
			throws AppBaseException {
		tipoSeveridadService.guardarTipoSeveridad(tipoSeveridadDTO);
		
	}

	@Override
	public TipoSeveridadDTO getTipoSeveridad(Integer idTipoSeveridad) throws AppBaseException {
		return tipoSeveridadService.getTipoSeveridad(idTipoSeveridad);
	}

	@Override
	public void editarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO) throws AppBaseException{

		tipoSeveridadService.editarTipoSeveridad(tipoSeveridadDTO);
		
	}

	@Override
	public List<TipoSeveridadDTO> consultaSeveridadPorTipoHallazgo(Integer idTipoHallazgo,Paginator page)throws AppBaseException {
		return tipoSeveridadService.consultaSeveridadPorTipoHallazgo(idTipoHallazgo,page);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public List<String> getNombreTiposSeveridad() throws AppBaseException {
		return tipoSeveridadService.getNombreTiposSeveridad();
	}

	@Override
	public List<TipoSeveridadDTO> tipoHallazgos() throws AppBaseException {
		return tipoSeveridadService.tipoHallazgos();
	}

}