/**
 * 
 */
package com.premize.sgp.service.parametros.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.PaisDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.modelo.entities.parametros.SgpPais;
import com.premize.sgp.service.parametros.PaisService;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisServiceImpl
 * @date 31/01/2014
 */
@Service
public class PaisServiceImpl extends AbstractEntityService<SgpPais, Integer> implements PaisService {
	
	@Autowired
	private PaisDao paisDao;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.PaisService#guardarPais(com.premize.sgp.dto.parametros.PaisDTO)
	 */
	public void guardarPais(PaisDTO pais) throws AppBaseException {
		
		if (validarPais(pais)) {
			SgpPais sgpPais = new SgpPais();
			sgpPais.setDescripcion(pais.getDescripcion());
			sgpPais.setNombre(pais.getNombre());
			sgpPais.setFecCreacion(Calendar.getInstance().getTime());
			sgpPais.setIndActivo(pais.getNumeroEstado());
			sgpPais.setUsuarioCrea(pais.getUsuarioCreacion());
			paisDao.save(sgpPais);
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param pais
	 * @return
	 * @throws AppBaseException
	 */
	private boolean validarPais(PaisDTO pais) throws AppBaseException {
		Boolean res = false;
		if (paisDao.validarPais(pais)) {
			throw new AppBaseException("El país ingresado ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
	}
	
	@Override
	public Dao<SgpPais, Integer> getDao() {
		return paisDao;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.PaisService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<PaisDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return paisDao.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.PaisService#editarPais(com.premize.sgp.dto.parametros.PaisDTO)
	 */
	@Override
	public void editarPais(PaisDTO pais) throws AppBaseException {
		
		SgpPais sgpPais = paisDao.get(pais.getId());
		sgpPais.setDescripcion(pais.getDescripcion());
		sgpPais.setNombre(pais.getNombre());
		sgpPais.setIndActivo(pais.getNumeroEstado());
		sgpPais.setUsuarioEdita(pais.getUsuarioEdita());
		sgpPais.setFecEdita(Calendar.getInstance().getTime());
		paisDao.update(sgpPais);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @see com.premize.sgp.service.parametros.PaisService#ObtenerPaises()
	 */
	@Override
	public List<PaisDTO> getPaises() throws AppBaseException {
		List<SgpPais> listadoEntidad = paisDao.consultarPaises();
		List<PaisDTO> listadoDTO = new ArrayList<PaisDTO>();
		
		for (SgpPais pais : listadoEntidad) {
			
			PaisDTO paisDTO = new PaisDTO();
			paisDTO.setId(pais.getId());
			paisDTO.setNombre(pais.getNombre());
			paisDTO.setDescripcion(pais.getDescripcion());
			paisDTO.setIndActivo(pais.getIndActivo());
			
			listadoDTO.add(paisDTO);
		}
		
		return listadoDTO;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.PaisService#getPais(java.lang.Integer)
	 */
	public PaisDTO getPais(Integer idPais) throws AppBaseException {
		
		SgpPais sgpPais = paisDao.get(idPais);
		PaisDTO paisDTO = new PaisDTO(sgpPais);
		return paisDTO;
	}
	
}
