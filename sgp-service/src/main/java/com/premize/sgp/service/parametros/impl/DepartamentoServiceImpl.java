package com.premize.sgp.service.parametros.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.DepartamentoDao;
import com.premize.sgp.dao.parametros.PaisDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.DepartamentoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;
import com.premize.sgp.modelo.entities.parametros.SgpPais;
import com.premize.sgp.service.parametros.DepartamentoService;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class DepartamentoServiceImpl
 * @date 30/01/2014
 */
@Service
public class DepartamentoServiceImpl extends AbstractEntityService<SgpDepartamento, Integer> implements
		DepartamentoService {
	
	@Autowired
	private DepartamentoDao departamentoDao;
	
	@Autowired
	private PaisDao paisDao;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.DepartamentoService#guardarDepartamento(com.premize.sgp.dto.parametros.DepartamentoDTO)
	 */
	public void guardarDepartamento(DepartamentoDTO departamento) throws AppBaseException {
		
		if (validarDepartamento(departamento)) {
			SgpDepartamento sgpDepartamento = new SgpDepartamento();
			SgpPais sgpPais = paisDao.get(departamento.getPais().getId());
			sgpDepartamento.setDescripcion(departamento.getDescripcion());
			sgpDepartamento.setNombre(departamento.getNombre());
			sgpDepartamento.setFecCreacion(Calendar.getInstance().getTime());
			sgpDepartamento.setIndActivo(departamento.getNumeroEstado());
			sgpDepartamento.setUsuarioCrea(departamento.getUsuarioCreacion());
			sgpDepartamento.setSgpPais(sgpPais);
			departamentoDao.save(sgpDepartamento);
			
		}
	}
	
	@Override
	public DepartamentoDao getDao() {
		return departamentoDao;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.DepartamentoService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<DepartamentoDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return departamentoDao.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.DepartamentoService#editarDepartamento(com.premize.sgp.dto.parametros.DepartamentoDTO)
	 */
	@Override
	public void editarDepartamento(DepartamentoDTO departamento) throws AppBaseException {
		
		SgpDepartamento sgpDepartamento = departamentoDao.get(departamento.getId());
		
		SgpPais sgpPais = paisDao.get(departamento.getPais().getId());
		sgpDepartamento.setSgpPais(sgpPais);
		sgpDepartamento.setDescripcion(departamento.getDescripcion());
		sgpDepartamento.setNombre(departamento.getNombre());
		sgpDepartamento.setIndActivo(departamento.getNumeroEstado());
		sgpDepartamento.setUsuarioEdita(departamento.getUsuarioEdita());
		sgpDepartamento.setFecEdita(Calendar.getInstance().getTime());
		
		departamentoDao.update(sgpDepartamento);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @see com.premize.sgp.service.parametros.PaisService#ObtenerPaises()
	 */
	@Override
	public List<DepartamentoDTO> getDepartamentos() throws AppBaseException {
		List<SgpDepartamento> listadoEntidad = departamentoDao.consultarDepartamentos();
		List<DepartamentoDTO> listadoDTO = new ArrayList<DepartamentoDTO>();
		
		for (SgpDepartamento departamento : listadoEntidad) {
			
			DepartamentoDTO departamentoDTO = new DepartamentoDTO();
			departamentoDTO.setId(departamento.getId());
			departamentoDTO.setNombre(departamento.getNombre());
			departamentoDTO.setDescripcion(departamento.getDescripcion());
			departamentoDTO.setIndActivo(departamento.getIndActivo());
			
			listadoDTO.add(departamentoDTO);
		}
		
		return listadoDTO;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.DepartamentoService#getDepartamento(java.lang.Integer)
	 */
	public DepartamentoDTO getDepartamento(Integer idDepartamento) throws AppBaseException {
		
		SgpDepartamento sgpDepartamento = departamentoDao.get(idDepartamento);
		DepartamentoDTO departamentoDTO = new DepartamentoDTO(sgpDepartamento);
		return departamentoDTO;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.DepartamentoService#consultarDepartamentosPorPais(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<DepartamentoDTO> consultarDepartamentosPorPais(Integer idPais, Paginator page) throws AppBaseException {
		return getDao().consultarDepartamentosPorPais(idPais, page);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param depto
	 * @return
	 * @throws AppBaseException
	 */
	public Boolean validarDepartamento(DepartamentoDTO depto) throws AppBaseException {
		
		Boolean res = false;
		if (departamentoDao.validarDepartamento(depto)) {
			throw new AppBaseException("El departamento ingresado ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
		
	}
}