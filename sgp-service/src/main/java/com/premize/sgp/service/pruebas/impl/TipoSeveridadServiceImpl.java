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
import com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.TipoSeveridadDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoSeveridadDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;
import com.premize.sgp.service.pruebas.TipoSeveridadService;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-service
 * @class CasoDePruebaServiceImpl
 * @date 11/02/2014
 */
@Service
public class TipoSeveridadServiceImpl extends AbstractEntityService<SgpTipoSeveridad, Integer> implements
		TipoSeveridadService {

	@Autowired
	private TipoSeveridadDao tipoSeveridadDao;
	
	@Autowired
	private TipoHallazgoDao  tipoHallazgoDao;
	
	@Override
	public TipoSeveridadDao getDao() {
		return tipoSeveridadDao;
	}

	@Override
	public ResultSet<TipoSeveridadDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		return tipoSeveridadDao.getRecords(criteria);
	}
	
	
	@Override
	public TipoSeveridadDTO getTipoSeveridad(Integer idTipoSeveridad)
			throws AppBaseException {
	SgpTipoSeveridad sgpTipoSeveridad=tipoSeveridadDao.get(idTipoSeveridad);
	TipoSeveridadDTO tipoHallazgoDTO=new TipoSeveridadDTO(sgpTipoSeveridad);
	return tipoHallazgoDTO;
	}

	@Override
	public void editarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO)
			throws AppBaseException {

	
			SgpTipoSeveridad tipoSeveridad=tipoSeveridadDao.get(tipoSeveridadDTO.getId());
			tipoSeveridad.setDescripcion(tipoSeveridadDTO.getDescripcion());
			tipoSeveridad.setNombre(tipoSeveridadDTO.getNombre().trim());
			tipoSeveridad.setIndActivo(tipoSeveridadDTO.getNumeroEstado());
			tipoSeveridad.setUsuarioEdita(tipoSeveridadDTO.getUsuarioEdita());
			tipoSeveridad.setFecEdita(Calendar.getInstance().getTime());
			tipoSeveridad.setColor(tipoSeveridadDTO.getColor());
			SgpTipoHallazgo sgpTipoHallazgo=tipoHallazgoDao.get(tipoSeveridadDTO.getTipoHallazgo().getId());
			tipoSeveridad.setTipoHallazgo(sgpTipoHallazgo);
		
		tipoSeveridadDao.update(tipoSeveridad);

	}
	
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 4/08/2014
* @param tipoSeveridadDTO
* @return
* @throws AppBaseException
 */
	private boolean validarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO) throws AppBaseException {
		Boolean res = false;
		if (tipoSeveridadDao.validarTipoSeveridad(tipoSeveridadDTO)) {
			throw new AppBaseException("El tipo de severidad ingresada ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 4/08/2014
	* @param tipoSeveridadDTO
	* @return
	* @throws AppBaseException
	 */
	private boolean validarTipoSeveridadEditar(TipoSeveridadDTO tipoSeveridadDTO) throws AppBaseException {
		Boolean res = false;
		
		TipoSeveridadDTO severidadDTO=getTipoSeveridad(tipoSeveridadDTO.getId());
		
		if (!severidadDTO.getNombre().equals(tipoSeveridadDTO.getNombre())) {
			throw new AppBaseException("El tipo de severidad ingresada ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
	}
/**
 * 
 */
	@Override
	public void guardarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO)
			throws AppBaseException {
		if (validarTipoSeveridad(tipoSeveridadDTO)) {
			SgpTipoSeveridad tipoSeveridad = new SgpTipoSeveridad();
			tipoSeveridad.setDescripcion(tipoSeveridadDTO.getDescripcion());
			tipoSeveridad.setNombre(tipoSeveridadDTO.getNombre().trim());
			tipoSeveridad.setFecCreacion(Calendar.getInstance().getTime());
			tipoSeveridad.setIndActivo(tipoSeveridadDTO.getNumeroEstado());
			tipoSeveridad.setUsuarioCrea(tipoSeveridadDTO.getUsuarioCreacion());
			tipoSeveridad.setColor(tipoSeveridadDTO.getColor());
			SgpTipoHallazgo sgpTipoHallazgo=tipoHallazgoDao.get(tipoSeveridadDTO.getTipoHallazgo().getId());
			tipoSeveridad.setTipoHallazgo(sgpTipoHallazgo);
			
			tipoSeveridadDao.save(tipoSeveridad);
		}
		
	}

	@Override
	public List<TipoSeveridadDTO> getTipoSeveridades() throws AppBaseException {
		List<SgpTipoSeveridad> listadoEntidad = tipoSeveridadDao.consultarTipoSeveridad();
		List<TipoSeveridadDTO> listadoDTO = new ArrayList<TipoSeveridadDTO>();
		
		for (SgpTipoSeveridad tipoSeveridad : listadoEntidad) {
			
			TipoSeveridadDTO tipoSeveridadDTO = new TipoSeveridadDTO();
			tipoSeveridadDTO.setId(tipoSeveridad.getId());
			tipoSeveridadDTO.setNombre(tipoSeveridad.getNombre());
			tipoSeveridadDTO.setDescripcion(tipoSeveridad.getDescripcion());
			tipoSeveridadDTO.setIndActivo(tipoSeveridad.getIndActivo());
			tipoSeveridadDTO.setColor(tipoSeveridad.getColor());
			listadoDTO.add(tipoSeveridadDTO);
		}
		
		return listadoDTO;
	}

	@Override
	public List<TipoSeveridadDTO> consultaSeveridadPorTipoHallazgo(
			Integer idTipoHallazgo, Paginator page)throws AppBaseException {
		return getDao().consultaSeveridadPorTipoHallazgo(idTipoHallazgo, page);
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
		List<TipoSeveridadDTO> listSeveridad = tipoSeveridadDao.consultarSeveridadPorTipoHallazgo(1);
		List<String> listSeveridadNombre = new ArrayList<String>();
		for(TipoSeveridadDTO tipoSeveridad : listSeveridad) {
			listSeveridadNombre.add(tipoSeveridad.getNombre());
		}
		return listSeveridadNombre;
	}

	@Override
	public List<TipoSeveridadDTO> tipoHallazgos() throws AppBaseException {
		List<TipoSeveridadDTO> listSeveridad=tipoSeveridadDao.tipoHallazgos(); 
		return listSeveridad;
	}
	
}
