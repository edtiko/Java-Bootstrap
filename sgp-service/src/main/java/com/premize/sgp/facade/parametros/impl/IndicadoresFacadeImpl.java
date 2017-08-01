/**
 * 
 */
package com.premize.sgp.facade.parametros.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.indicadores.HallazgoPorGarantiaDTO;
import com.premize.sgp.facade.parametros.IndicadoresFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpIndicadores;
import com.premize.sgp.service.parametros.IndicadoresService;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class PaisFacadeImpl
 * @since 21/01/2014
 */
@Service("indicadoresFacade")
public class IndicadoresFacadeImpl extends AbstractEntityFacade<SgpIndicadores, Integer> implements IndicadoresFacade {
	
	@Autowired
	private IndicadoresService indicadoresService;


	public EntityService<SgpIndicadores, Integer> getEntityService() {
		return null;
	}

	@Override
	public List<IndicadoresDTO> getPaises() throws AppBaseException {
		
		return indicadoresService.getIndicadores();
	}
	
	@Override
	public ResultSet<IndicadoresDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return indicadoresService.getRecords(criteria);
	}

	@Override
	public IndicadoresDTO getIndicador(Integer idIndicador) throws AppBaseException {
		
		return indicadoresService.getIndicador(idIndicador);
	}
	@Override
	public void guardarIndicador(IndicadoresDTO indicadoresDTO) throws AppBaseException {
		
		indicadoresService.guardarIndicador(indicadoresDTO);
	}

	@Override
	public void editarIndicador(IndicadoresDTO indicadoresDTO) throws AppBaseException {
		indicadoresService.editarIndicador(indicadoresDTO);
		
	}

	@Override
	public HallazgoPorGarantiaDTO hallazgosPorGarantia(Integer idProyecto,String fechaFrom,String fechaTo,List<String> listEstados) throws AppBaseException , ParseException{
		
		return indicadoresService.hallazgosPorGarantia(idProyecto,fechaTo,fechaFrom, listEstados);
	}
}