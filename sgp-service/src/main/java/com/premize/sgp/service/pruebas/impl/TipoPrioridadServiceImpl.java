/**
 * 
 */
package com.premize.sgp.service.pruebas.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.gestion.pruebas.TipoPrioridadDao;
import com.premize.sgp.dao.gestion.pruebas.TipoSeveridadDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoPrioridad;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;
import com.premize.sgp.service.pruebas.TipoPrioridadService;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-service
 * @class CasoDePruebaServiceImpl
 * @date 11/02/2014
 */
@Service
public class TipoPrioridadServiceImpl extends AbstractEntityService<SgpTipoPrioridad, Integer> implements
		TipoPrioridadService {

	@Autowired
	private TipoPrioridadDao tipoPrioridadDao;
	
	@Autowired
	private TipoSeveridadDao  tipoSeveridadDao;
	
	@Override
	public TipoPrioridadDao getDao() {
		return tipoPrioridadDao;
	}

	@Override
	public ResultSet<TipoPrioridadDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		return tipoPrioridadDao.getRecords(criteria);
	}
	
	
	@Override
	public TipoPrioridadDTO getTipoPrioridad(Integer idTipoPrioridad)
			throws AppBaseException {
	SgpTipoPrioridad sgpTipoPrioridad=tipoPrioridadDao.get(idTipoPrioridad);
	TipoPrioridadDTO tipoPrioridadDTO=new TipoPrioridadDTO(sgpTipoPrioridad);
	return tipoPrioridadDTO;
	}

	@Override
	public void editarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO)
			throws AppBaseException {
		

			SgpTipoPrioridad tipoPrioridad=tipoPrioridadDao.get(tipoPrioridadDTO.getId());
			tipoPrioridad.setDescripcion(tipoPrioridadDTO.getDescripcion());
			tipoPrioridad.setNombre(tipoPrioridadDTO.getNombre().trim());
			tipoPrioridad.setIndActivo(tipoPrioridadDTO.getNumeroEstado());
			tipoPrioridad.setUsuarioEdita(tipoPrioridadDTO.getUsuarioEdita());
			tipoPrioridad.setFecEdita(Calendar.getInstance().getTime());
			SgpTipoSeveridad sgpTipoSeveridad=tipoSeveridadDao.get(tipoPrioridadDTO.getTipoSeveridad().getId());
			tipoPrioridad.setTipoSeveridad(sgpTipoSeveridad);
			if(null == tipoPrioridadDTO.getPuntaje()){
				tipoPrioridad.setPuntaje(0F);
			}else{
				tipoPrioridad.setPuntaje(tipoPrioridadDTO.getPuntaje());
			}
			tipoPrioridadDao.update(tipoPrioridad);
		
	}
	
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 4/08/2014
* @param tipoPrioridadDTO
* @return
* @throws AppBaseException
 */
	private boolean validarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO) throws AppBaseException {
		Boolean res = false;
		if (tipoPrioridadDao.validarTipoPrioridad(tipoPrioridadDTO)) {
			throw new AppBaseException("El tipo de prioridad ingresada ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
	}
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 4/08/2014
* @param tipoPrioridadDTO
* @return
* @throws AppBaseException
 */
	private boolean validarTipoPrioridadEditar(TipoPrioridadDTO tipoPrioridadDTO) throws AppBaseException {
		Boolean res = false;
		
		TipoPrioridadDTO prioridadDTO=getTipoPrioridad(tipoPrioridadDTO.getId());
		
		if (!prioridadDTO.getNombre().equals(tipoPrioridadDTO.getNombre())) {
			throw new AppBaseException("El tipo de prioridad ingresada ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
	}
	
	@Override
	public void guardarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO)
			throws AppBaseException {
		if (validarTipoPrioridad(tipoPrioridadDTO)) {
			SgpTipoPrioridad tipoPrioridad = new SgpTipoPrioridad();
			tipoPrioridad.setDescripcion(tipoPrioridadDTO.getDescripcion());
			tipoPrioridad.setNombre(tipoPrioridadDTO.getNombre().trim());
			tipoPrioridad.setFecCreacion(Calendar.getInstance().getTime());
			tipoPrioridad.setIndActivo(tipoPrioridadDTO.getNumeroEstado());
			tipoPrioridad.setUsuarioCrea(tipoPrioridadDTO.getUsuarioCreacion());
			SgpTipoSeveridad sgpTipoSeveridad=tipoSeveridadDao.get(tipoPrioridadDTO.getTipoSeveridad().getId());
			tipoPrioridad.setTipoSeveridad(sgpTipoSeveridad);
			
			if(null==tipoPrioridadDTO.getPuntaje()){
				tipoPrioridad.setPuntaje(0F);
			}else{
				tipoPrioridad.setPuntaje(tipoPrioridadDTO.getPuntaje());
			}
			tipoPrioridadDao.save(tipoPrioridad);
		}
		
	}

	@Override
	public List<TipoPrioridadDTO> getTipoPrioridades() throws AppBaseException {
		List<SgpTipoPrioridad> listadoEntidad = tipoPrioridadDao.consultarTipoPrioridad();
		List<TipoPrioridadDTO> listadoDTO = new ArrayList<TipoPrioridadDTO>();
		
		for (SgpTipoPrioridad tipoPrioridad : listadoEntidad) {
			
			TipoPrioridadDTO tipoPrioridadDTO = new TipoPrioridadDTO();
			tipoPrioridadDTO.setId(tipoPrioridad.getId());
			tipoPrioridadDTO.setNombre(tipoPrioridad.getNombre());
			tipoPrioridadDTO.setDescripcion(tipoPrioridad.getDescripcion());
			tipoPrioridadDTO.setIndActivo(tipoPrioridad.getIndActivo());
			
			listadoDTO.add(tipoPrioridadDTO);
		}
		
		return listadoDTO;
	}
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.service.pruebas.TipoPrioridadService#consultaPrioridadPorTipoHallazgo(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<TipoPrioridadDTO> consultaPrioridadPorTipoHallazgo(Integer idTipoSeveridad, Paginator page) throws AppBaseException {
		return getDao().consultaPrioridadPorTipoHallazgo(idTipoSeveridad, page);
	}



	

	
}
