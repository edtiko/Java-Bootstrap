/**
 * 
 */
package com.premize.sgp.service.parametros.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.gestion.pruebas.IndicadoresDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.indicadores.HallazgoPorGarantiaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpIndicadores;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.parametros.IndicadoresService;
import com.premize.sgp.utils.DateUtils;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisServiceImpl
 * @date 31/01/2014
 */
@Service
public class IndicadoresServiceImpl extends AbstractEntityService<SgpIndicadores, Integer> implements IndicadoresService {
	
	@Autowired
	private IndicadoresDao indicadoresDao;
	@Autowired
	private SgpParametroDao parametroDao;
	
	
	
	@Override
	public Dao<SgpIndicadores, Integer> getDao() {
		return indicadoresDao;
	}

	@Override
	public List<IndicadoresDTO> getIndicadores() throws AppBaseException  {
		List <SgpIndicadores> indicadores=indicadoresDao.consultarIndicadores();
		
List<IndicadoresDTO> listadoDTO = new ArrayList<IndicadoresDTO>();
		
		for (SgpIndicadores sgpIndicadores : indicadores) {
			
			IndicadoresDTO indicadoresDTO=new IndicadoresDTO();
			indicadoresDTO.setId(sgpIndicadores.getId());
			indicadoresDTO.setNombre(sgpIndicadores.getNombre());
			listadoDTO.add(indicadoresDTO);
		}
		
		return listadoDTO;
	}
	

	@Override
	public ResultSet<IndicadoresDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return indicadoresDao.getRecords(criteria);
	}

	@Override
	public IndicadoresDTO getIndicador(Integer idIndicador) throws AppBaseException {
		
		SgpIndicadores sgpIndicadores=indicadoresDao.get(idIndicador);
		IndicadoresDTO indicadoresDTO=new IndicadoresDTO(sgpIndicadores);
		return indicadoresDTO;
		
	}

	@Override
	public void guardarIndicador(IndicadoresDTO indicadoresDTO) throws AppBaseException {
		if (validarIndicador(indicadoresDTO)) {
			SgpIndicadores sgpIndicadores = new SgpIndicadores();
			
			sgpIndicadores.setFase(indicadoresDTO.getFase());
			sgpIndicadores.setFormula(indicadoresDTO.getFormula());
			sgpIndicadores.setPeriodicidad(indicadoresDTO.getPeriodicidad());
			sgpIndicadores.setNombre(indicadoresDTO.getNombre().trim());
			sgpIndicadores.setValorMinimo(indicadoresDTO.getValorMinimo());

			sgpIndicadores.setValorMaximo(indicadoresDTO.getValorMaximo());
			
			sgpIndicadores.setFecCreacion(Calendar.getInstance().getTime());
			sgpIndicadores.setIndActivo(indicadoresDTO.getNumeroEstado());
			sgpIndicadores.setUsuarioCrea(indicadoresDTO.getUsuarioCreacion());
			indicadoresDao.save(sgpIndicadores);
		}
		
	}
		private boolean validarIndicador(IndicadoresDTO indicadoresDTO) throws AppBaseException {
			Boolean res = false;
			if (indicadoresDao.validarIndicador(indicadoresDTO)) {
				throw new AppBaseException("El Indicador ingresado ya existe.", null);
			} else {
				res = true;
			}
			
			return res;
		}

		@Override
		public void editarIndicador(IndicadoresDTO indicadoresDTO) throws AppBaseException {

			
			SgpIndicadores sgpIndicadores = indicadoresDao.get(indicadoresDTO.getId());
			
//			sgpIndicadores.setDescripcion(indicadoresDTO.getDescripcion());
			
			sgpIndicadores.setNombre(indicadoresDTO.getNombre().trim());
			sgpIndicadores.setFase(indicadoresDTO.getFase());
			sgpIndicadores.setFormula(indicadoresDTO.getFormula());
			sgpIndicadores.setPeriodicidad(indicadoresDTO.getPeriodicidad());
			sgpIndicadores.setValorMinimo(indicadoresDTO.getValorMinimo());
	
			sgpIndicadores.setValorMaximo(indicadoresDTO.getValorMaximo());
			
			sgpIndicadores.setIndActivo(indicadoresDTO.getNumeroEstado());
			sgpIndicadores.setUsuarioEdita(indicadoresDTO.getUsuarioEdita());
			sgpIndicadores.setFecEdita(Calendar.getInstance().getTime());
			
			indicadoresDao.update(sgpIndicadores);
			
		}

		@Override
		public HallazgoPorGarantiaDTO  hallazgosPorGarantia(Integer idProyecto,String fechaTo,String fechaFrom,List<String> listEstados) throws AppBaseException, ParseException {

			Date fechaIn=null;
			Date fechaFin=null;
			List<Integer> listCodigoGarantias=new ArrayList<Integer>();
			
			SgpParametro codigoTipoHallazgo=parametroDao.findByProperty(PARAMETRO_FIELD_CLAVE, CODIGO_HALLAZGOS_POR_GARANTIA);
			
			String[] listCodigos=codigoTipoHallazgo.getValor().split(",");
					
			for (int i = 0; i < listCodigos.length; i++) {
				listCodigoGarantias.add(Integer.parseInt(listCodigos[i]));
			}
			if(validarCampo(fechaFrom)) 
				fechaIn = DateUtils.getDateFromString("yyyy/MM/dd",fechaFrom,true);
			if(validarCampo(fechaTo))
				fechaFin = DateUtils.getDateFromString("yyyy/MM/dd",fechaTo,false);
			HallazgoPorGarantiaDTO  hallazgoPorGarantiaDTO=indicadoresDao.hallazgosPorGarantia(idProyecto, fechaIn, fechaFin,listEstados,listCodigoGarantias);
		
			return hallazgoPorGarantiaDTO;
		}
	

		private Boolean validarCampo(Object object){
			 Boolean res = false;
			 
			 if(object != null && !object.toString().isEmpty())
			   res = true;
			 
			return res;
			
		}
	
	
	
}
