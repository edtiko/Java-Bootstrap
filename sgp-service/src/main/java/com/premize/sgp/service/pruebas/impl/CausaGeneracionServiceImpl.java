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
import com.premize.sgp.dao.gestion.pruebas.CausaGeneracionDao;
import com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CausaGeneracionDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.service.pruebas.CausaGeneracionService;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisServiceImpl
 * @date 31/01/2014
 */
@Service
public class CausaGeneracionServiceImpl extends AbstractEntityService<SgpCausaGeneracion, Integer> implements CausaGeneracionService {
	
	@Autowired
	private CausaGeneracionDao causaGeneracionDao;
	
	@Autowired
	private TipoHallazgoDao  tipoHallazgoDao;
	
	@Override
	public CausaGeneracionDao getDao() {
		return causaGeneracionDao;
	}
	
	
	@Override
	public ResultSet<CausaGeneracionDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		return causaGeneracionDao.getRecords(criteria);
	}

	@Override
	public CausaGeneracionDTO getCausaGeneracion(Integer idCausaGeneracion)
			throws AppBaseException {
	SgpCausaGeneracion sgpCausaGeneracion=causaGeneracionDao.get(idCausaGeneracion);
	CausaGeneracionDTO causaGeneracionDTO=new CausaGeneracionDTO(sgpCausaGeneracion);
	return causaGeneracionDTO;
	}

	@Override
	public void editarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO)
			throws AppBaseException {

	
		SgpCausaGeneracion causaGeneracion=causaGeneracionDao.get(causaGeneracionDTO.getId());
		causaGeneracion.setDescripcion(causaGeneracionDTO.getDescripcion());
		causaGeneracion.setNombre(causaGeneracionDTO.getNombre().trim());
		causaGeneracion.setIndActivo(causaGeneracionDTO.getNumeroEstado());
		causaGeneracion.setUsuarioEdita(causaGeneracionDTO.getUsuarioEdita());
		causaGeneracion.setFecEdita(Calendar.getInstance().getTime());
		SgpTipoHallazgo sgpTipoHallazgo=tipoHallazgoDao.get(causaGeneracionDTO.getTipoHallazgo().getId());
		causaGeneracion.setSgpTipoHallazgo(sgpTipoHallazgo);
		
		causaGeneracionDao.update(causaGeneracion);
		}
	
	
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 4/08/2014
* @param causaGeneracionDTO
* @return
* @throws AppBaseException
 */
	private boolean validarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException {
		Boolean res = false;
		if (causaGeneracionDao.validarCausaGeneracion(causaGeneracionDTO)) {
			throw new AppBaseException("La causa de generación ingresada ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
	}

	private boolean validarCausaGeneracionEditar(CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException {
		Boolean res = false;
		
		CausaGeneracionDTO generacionDTO=getCausaGeneracion(causaGeneracionDTO.getId());
		
		if (!generacionDTO.getNombre().equals(causaGeneracionDTO.getNombre())) {
			throw new AppBaseException("La causa de generación ingresada ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
	}

	@Override
	public void guardarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO)
			throws AppBaseException {
		if (validarCausaGeneracion(causaGeneracionDTO)) {
			SgpCausaGeneracion causaGeneracion = new SgpCausaGeneracion();
			causaGeneracion.setDescripcion(causaGeneracionDTO.getDescripcion());
			causaGeneracion.setNombre(causaGeneracionDTO.getNombre().trim());
			causaGeneracion.setFecCreacion(Calendar.getInstance().getTime());
			causaGeneracion.setIndActivo(causaGeneracionDTO.getNumeroEstado());
			causaGeneracion.setUsuarioCrea(causaGeneracionDTO.getUsuarioCreacion());
			SgpTipoHallazgo sgpTipoHallazgo=tipoHallazgoDao.get(causaGeneracionDTO.getTipoHallazgo().getId());
			causaGeneracion.setSgpTipoHallazgo(sgpTipoHallazgo);
			causaGeneracionDao.save(causaGeneracion);
		}
		
	}

	@Override
	public List<CausaGeneracionDTO> getCausaGeneraciones() throws AppBaseException {
		List<SgpCausaGeneracion> listadoEntidad = causaGeneracionDao.consultarCausaGeneracion();
		List<CausaGeneracionDTO> listadoDTO = new ArrayList<CausaGeneracionDTO>();
		
		for (SgpCausaGeneracion causaGeneracion : listadoEntidad) {
			
			CausaGeneracionDTO causaGeneracionDTO = new CausaGeneracionDTO();
			causaGeneracionDTO.setId(causaGeneracion.getId());
			causaGeneracionDTO.setNombre(causaGeneracion.getNombre());
			causaGeneracionDTO.setDescripcion(causaGeneracion.getDescripcion());
			causaGeneracionDTO.setIndActivo(causaGeneracion.getIndActivo());
			
			listadoDTO.add(causaGeneracionDTO);
		}
		
		return listadoDTO;
	}


	@Override
	public List<CausaGeneracionDTO> consultaCausaPorTipoHallazgo(Integer idTipoHallazgo,Paginator page)throws AppBaseException {
		
		return getDao().consultaCausaPorTipoHallazgo(idTipoHallazgo, page);
		
	}
	
}
