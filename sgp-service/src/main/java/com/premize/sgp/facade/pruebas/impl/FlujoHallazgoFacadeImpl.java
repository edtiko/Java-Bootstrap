/**
 * 
 */
package com.premize.sgp.facade.pruebas.impl;

import com.premize.sgp.dto.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.facade.pruebas.FlujoHallazgoFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.service.pruebas.FlujoHallazgoService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class FlujoHallazgoFacadeImpl
 * @since 3/03/2014
 */
@Service
public class FlujoHallazgoFacadeImpl extends AbstractEntityFacade<SgpFlujoHallazgo, Integer> implements
		FlujoHallazgoFacade {
	
	@Autowired
	private FlujoHallazgoService flujoHallazgoService;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @see com.premize.pmz.facade.AbstractEntityFacade#getEntityService()
	 */
	@Override
	public EntityService<SgpFlujoHallazgo, Integer> getEntityService() {
		
		return flujoHallazgoService;
	}

   /**
    * 
    * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
    * @since 25/03/2014
    * @see com.premize.sgp.facade.pruebas.FlujoHallazgoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
    */
	@Override
	public ResultSet<FlujoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return flujoHallazgoService.getRecords(criteria);
	}
	
    /**
     * 
     * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
     * @since 25/03/2014
     * @see com.premize.sgp.facade.pruebas.FlujoHallazgoFacade#getFlujoHallazgo(java.lang.Integer)
     */
	@Override
	public FlujoHallazgoDTO getFlujoHallazgo(Integer idFlujo)
			throws AppBaseException {
		return flujoHallazgoService.getFlujoHallazgo(idFlujo);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @see com.premize.sgp.facade.pruebas.FlujoHallazgoFacade#editarFlujoHallazgo(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO)
	 */
	@Override
	public void editarFlujoHallazgo(FlujoHallazgoDTO flujoHallazgoDTO)
			throws AppBaseException {
		flujoHallazgoService.editarFlujoHallazgo(flujoHallazgoDTO);
		
	}

}
