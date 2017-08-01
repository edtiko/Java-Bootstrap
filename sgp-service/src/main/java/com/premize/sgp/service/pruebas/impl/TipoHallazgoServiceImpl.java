/**
 * 
 */
package com.premize.sgp.service.pruebas.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.service.pruebas.TipoHallazgoService;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisServiceImpl
 * @date 31/01/2014
 */
@Service
public class TipoHallazgoServiceImpl extends AbstractEntityService<SgpTipoHallazgo, Integer> implements TipoHallazgoService {
	
	@Autowired
	private TipoHallazgoDao tipoHallazgoDao;
	

	
	@Override
	public TipoHallazgoDao getDao() {
		return tipoHallazgoDao;
	}

	@Override
	public ResultSet<TipoHallazgoDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		return tipoHallazgoDao.getRecords(criteria);
	}

	@Override
	public TipoHallazgoDTO getTipoHallazgo(Integer idTipoHallazgo)throws AppBaseException {
	SgpTipoHallazgo sgpTipoHallazgo=tipoHallazgoDao.get(idTipoHallazgo);
	TipoHallazgoDTO tipoHallazgoDTO=new TipoHallazgoDTO(sgpTipoHallazgo);
	return tipoHallazgoDTO;
	}

	@Override
	public void editarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO)
			throws AppBaseException {

			SgpTipoHallazgo tipoHallazgo=tipoHallazgoDao.get(tipoHallazgoDTO.getId());
			tipoHallazgo.setDescripcion(tipoHallazgoDTO.getDescripcion());
			tipoHallazgo.setNombre(tipoHallazgoDTO.getNombre().trim());
			tipoHallazgo.setIndActivo(tipoHallazgoDTO.getNumeroEstado());
			tipoHallazgo.setUsuarioEdita(tipoHallazgoDTO.getUsuarioEdita());
			tipoHallazgo.setFecEdita(Calendar.getInstance().getTime());
			tipoHallazgo.setEsPuntuado(tipoHallazgoDTO.getNumeroEsPuntuado());
			tipoHallazgo.setTieneCausaGeneracion(tipoHallazgoDTO.getNumeroTieneCausaGeneracion());
			tipoHallazgoDao.update(tipoHallazgo);
		}
	
	

	private boolean validarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO) throws AppBaseException {
		Boolean res = false;
		if (tipoHallazgoDao.validarTipoHallazgo(tipoHallazgoDTO)) {
			throw new AppBaseException("El tipo de hallazgo ingresado ya existe.", null);
		} else {
			res = true;
		}
		
		return res;
	}

	private boolean validarTipoHallazgoEditar(TipoHallazgoDTO tipoHallazgoDTO) throws AppBaseException {
		Boolean res = false;
	
		TipoHallazgoDTO hallazgoDTO= getTipoHallazgo(tipoHallazgoDTO.getId());
		if (!hallazgoDTO.getNombre().equals(tipoHallazgoDTO.getNombre())){
			throw new AppBaseException("El tipo de hallazgo ingresado ya existe.", null);
		}else{
			res=true;
		}
		
		return res;
	}
	
	@Override
	public void guardarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO)
			throws AppBaseException {
		if (validarTipoHallazgo(tipoHallazgoDTO)) {
			SgpTipoHallazgo tipoHallazgo = new SgpTipoHallazgo();
			tipoHallazgo.setDescripcion(tipoHallazgoDTO.getDescripcion());
			tipoHallazgo.setNombre(tipoHallazgoDTO.getNombre().trim());
			tipoHallazgo.setFecCreacion(Calendar.getInstance().getTime());
			tipoHallazgo.setIndActivo(tipoHallazgoDTO.getNumeroEstado());
			tipoHallazgo.setUsuarioCrea(tipoHallazgoDTO.getUsuarioCreacion());
			tipoHallazgo.setEsPuntuado(tipoHallazgoDTO.getNumeroEsPuntuado());
			tipoHallazgo.setTieneCausaGeneracion(tipoHallazgoDTO.getNumeroTieneCausaGeneracion());
			tipoHallazgoDao.save(tipoHallazgo);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.service.pruebas.TipoHallazgoService#getTipoHallazgos(java.lang.String[])
	 */
	@Override
	public List<TipoHallazgoDTO> getTipoHallazgos(String[] tipoIngresoHallazgo) throws AppBaseException {
		

		
		List<SgpTipoHallazgo> listadoEntidad = tipoHallazgoDao.consultarTipoHallazgos(tipoIngresoHallazgo);
		List<TipoHallazgoDTO> listadoDTO = new ArrayList<TipoHallazgoDTO>();
		
		for (SgpTipoHallazgo tipoHallazgo : listadoEntidad) {
			
			TipoHallazgoDTO tipoHallazgoDTO = new TipoHallazgoDTO();
			tipoHallazgoDTO.setId(tipoHallazgo.getId());
			tipoHallazgoDTO.setNombre(tipoHallazgo.getNombre());
			tipoHallazgoDTO.setDescripcion(tipoHallazgo.getDescripcion());
			tipoHallazgoDTO.setIndActivo(tipoHallazgo.getIndActivo());
			tipoHallazgoDTO.setEsPuntuado(tipoHallazgo.getEsPuntuado());
			tipoHallazgoDTO.setTieneCausaGeneracion(tipoHallazgo.getTieneCausaGeneracion());
			listadoDTO.add(tipoHallazgoDTO);
		}
		
		return listadoDTO;
	}
	public List<TipoHallazgoDTO> tipoHallazgos() throws AppBaseException{
		List<SgpTipoHallazgo> listadoEntidad = tipoHallazgoDao.tipoHallazgos();
		List<TipoHallazgoDTO> listadoDTO = new ArrayList<TipoHallazgoDTO>();
		
		for (SgpTipoHallazgo tipoHallazgo : listadoEntidad) {
			
			TipoHallazgoDTO tipoHallazgoDTO = new TipoHallazgoDTO();
			tipoHallazgoDTO.setId(tipoHallazgo.getId());
			tipoHallazgoDTO.setNombre(tipoHallazgo.getNombre());
			tipoHallazgoDTO.setDescripcion(tipoHallazgo.getDescripcion());
			tipoHallazgoDTO.setIndActivo(tipoHallazgo.getIndActivo());
			tipoHallazgoDTO.setEsPuntuado(tipoHallazgo.getEsPuntuado());
			tipoHallazgoDTO.setTieneCausaGeneracion(tipoHallazgo.getTieneCausaGeneracion());
			listadoDTO.add(tipoHallazgoDTO);
		}
		
		return listadoDTO;
	}
}
