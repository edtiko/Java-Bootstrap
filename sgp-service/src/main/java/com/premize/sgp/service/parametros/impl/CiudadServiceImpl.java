package com.premize.sgp.service.parametros.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.Filter;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.CiudadDao;
import com.premize.sgp.dao.parametros.DepartamentoDao;
import com.premize.sgp.dao.parametros.PaisDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.CiudadDTO;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;
import com.premize.sgp.service.parametros.CiudadService;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class CiudadServiceImpl
 * @date 31/01/2014
 */
@Service
public class CiudadServiceImpl extends AbstractEntityService<SgpCiudad, Integer> implements CiudadService {
	
	@Autowired
	private CiudadDao ciudadDao;
	
	@Autowired
	private DepartamentoDao departamentoDao;
	
	@Autowired
	private PaisDao paisDao;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.CiudadService#getListCiudades()
	 */
	@Override
	public List<CiudadDTO> getListCiudades() throws AppBaseException {
		List<CiudadDTO> resultList = new ArrayList<CiudadDTO>();
		List<SgpCiudad> ciudades = ciudadDao.consultarCiudades();
		for (SgpCiudad sgpCiudad : ciudades) {
			CiudadDTO ciudadDTO = new CiudadDTO(sgpCiudad);
			
			resultList.add(ciudadDTO);
			
		}
		return resultList;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.CiudadService#guardarCiudad(com.premize.sgp.dto.parametros.CiudadDTO)
	 */
	public void guardarCiudad(CiudadDTO ciudad) throws AppBaseException {
		
		if (validarCiudad(ciudad)) {
			SgpCiudad sgpCiudad = new SgpCiudad();
			SgpDepartamento sgpDepartamento = departamentoDao.get(ciudad.getDepartamento().getId());
			sgpCiudad.setDescripcion(ciudad.getDescripcion());
			sgpCiudad.setNombre(ciudad.getNombre());
			sgpCiudad.setFecCreacion(Calendar.getInstance().getTime());
			sgpCiudad.setIndActivo(ciudad.getNumeroEstado());
			sgpCiudad.setUsuarioCrea(ciudad.getUsuarioCreacion());
			sgpCiudad.setSgpDepartamento(sgpDepartamento);
			ciudadDao.save(sgpCiudad);
		}
		
	}
	
	@Override
	public CiudadDao getDao() {
		return ciudadDao;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.CiudadService#findAllParametrosByFilter(com.premize.pmz.api.util.Filter[], com.premize.pmz.api.dto.Paginator)
	 */
	public List<SgpCiudad> findAllParametrosByFilter(Filter[] filters, Paginator paginator) {
		return null;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.CiudadService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<CiudadDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return ciudadDao.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.CiudadService#editarCiudad(com.premize.sgp.dto.parametros.CiudadDTO)
	 */
	@Override
	public void editarCiudad(CiudadDTO ciudad) throws AppBaseException {
		
		SgpCiudad sgpCiudad = ciudadDao.get(ciudad.getId());
		sgpCiudad.setDescripcion(ciudad.getDescripcion());
		sgpCiudad.setNombre(ciudad.getNombre());
		sgpCiudad.setFecEdita(Calendar.getInstance().getTime());
		sgpCiudad.setIndActivo(ciudad.getNumeroEstado());
		sgpCiudad.setUsuarioEdita(ciudad.getUsuarioEdita());
		ciudadDao.update(sgpCiudad);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.CiudadService#getCiudad(java.lang.Integer)
	 */
	@Override
	public CiudadDTO getCiudad(Integer idCiudad) throws AppBaseException {
		
		SgpCiudad sgpCiudad = ciudadDao.get(idCiudad);
		CiudadDTO ciudadDTO = new CiudadDTO(sgpCiudad);
		
		return ciudadDTO;
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param ciudad
	 * @return
	 * @throws AppBaseException
	 */
	public Boolean validarCiudad(CiudadDTO ciudad) throws AppBaseException {
		
		Boolean res = false;
		if (ciudadDao.validarCiudad(ciudad)) {
			throw new AppBaseException("La ciudad ingresada ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
		
	}
	
	@Override
	public List<CiudadDTO> consultarCiudadPorDepartamento(Integer idDepartamento, Paginator page)
			throws AppBaseException {
		return getDao().consultarCiudadPorDepartamento(idDepartamento, page);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.CiudadService#obtenerCiudades()
	 */
	@Override
	public List<SgpCiudad> obtenerCiudades() {
		return null;
	}
	
}
