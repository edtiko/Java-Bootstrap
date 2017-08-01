package com.premize.sgp.facade.pruebas.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.facade.pruebas.AnexoHallazgoFacade;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;
import com.premize.sgp.service.pruebas.AnexoHallazgoService;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class AnexoHallazgoFacadeImpl
 * @since 27/03/2014
 *
 */
@Service
public class AnexoHallazgoFacadeImpl extends AbstractEntityFacade<SgpAnexoHallazgo, Integer> implements
		AnexoHallazgoFacade {
	
	@Autowired
	AnexoHallazgoService anexoHallazgoService;
	
	@Override
	public EntityService<SgpAnexoHallazgo, Integer> getEntityService() {
		return anexoHallazgoService;
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws IOException 
	 * @since 7/05/2014
	 * @see com.premize.sgp.facade.pruebas.AnexoHallazgoFacade#eliminarAnexo(java.lang.Integer)
	 */
	@Override
	public void eliminarAnexo(Integer id) throws AppBaseException, IOException {
		
		anexoHallazgoService.eliminarAnexo(id);
		
	}
	
}
