package com.premize.sgp.facade.parametros.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.parametros.TipoArtefactoDTO;
import com.premize.sgp.facade.parametros.TipoArtefactoFacade;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;
import com.premize.sgp.service.parametros.TipoArtefactoService;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class TipoArtefactoFacadeImpl
 * @since 24/01/2014
 */
@Service("TipoArtefactoFacade")
public class TipoArtefactoFacadeImpl extends AbstractEntityFacade<SgpTipoArtefacto, Integer> implements
		TipoArtefactoFacade {
	
	@Autowired
	private TipoArtefactoService tipoArtefactoService;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @see com.premize.pmz.facade.AbstractEntityFacade#getEntityService()
	 */
	@Override
	public EntityService<SgpTipoArtefacto, Integer> getEntityService() {
		return tipoArtefactoService;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @see com.premize.sgp.facade.parametros.TipoArtefactoFacade#obtenerTiposArtefactos()
	 */
	@Override
	public List<TipoArtefactoDTO> obtenerTiposArtefactos() throws AppBaseException {
		
		return tipoArtefactoService.obtenerTiposArtefactos();
	}
	
}
