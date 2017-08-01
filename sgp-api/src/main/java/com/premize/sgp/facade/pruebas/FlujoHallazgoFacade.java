/**
 * 
 */
package com.premize.sgp.facade.pruebas;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class FlujoHallazgoFacade
 * @since 3/03/2014
 */
public interface FlujoHallazgoFacade extends EntityFacade<SgpFlujoHallazgo, Integer> {
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/03/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<FlujoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @param idHallazgo
	 * @return
	 */
	FlujoHallazgoDTO getFlujoHallazgo(Integer idHallazgo)throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @param flujoHallazgoDTO
	 * @throws AppBaseException
	 */
	void editarFlujoHallazgo(FlujoHallazgoDTO flujoHallazgoDTO)throws AppBaseException;


}
