/**
 * 
 */
package com.premize.sgp.service.parametros.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.TipoArtefactoDao;
import com.premize.sgp.dto.parametros.TipoArtefactoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;
import com.premize.sgp.service.parametros.TipoArtefactoService;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class TipoArtefactoServiceImpl
 * @since 24/01/2014
 */
@Service
public class TipoArtefactoServiceImpl extends AbstractEntityService<SgpTipoArtefacto, Integer> implements
		TipoArtefactoService {
	
	@Autowired
	private TipoArtefactoDao sgpTipoArtefactoDao;
	
	@Override
	public Dao<SgpTipoArtefacto, Integer> getDao() {
		return sgpTipoArtefactoDao;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @see com.premize.sgp.service.parametros.TipoArtefactoService#obtenerTiposArtefactos()
	 */
	@Override
	public List<TipoArtefactoDTO> obtenerTiposArtefactos() throws AppBaseException {
		
		List<SgpTipoArtefacto> listadoEntidad = sgpTipoArtefactoDao.findAllEntries(Paginator.getDefault());
		List<TipoArtefactoDTO> listadoDTO = new ArrayList<TipoArtefactoDTO>();
		
		for (SgpTipoArtefacto tipoArtefacto : listadoEntidad) {
			
			TipoArtefactoDTO tipoArtefactoDTO = new TipoArtefactoDTO(tipoArtefacto);
			listadoDTO.add(tipoArtefactoDTO);
			
		}
		return listadoDTO;
	}
	
}
