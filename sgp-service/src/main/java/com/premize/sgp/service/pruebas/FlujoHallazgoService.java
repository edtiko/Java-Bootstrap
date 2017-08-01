/**
 * 
 */
package com.premize.sgp.service.pruebas;

import com.premize.sgp.dto.ResultSet;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class FlujoHallazgoService
 * @since 3/03/2014
 *
 */
public interface FlujoHallazgoService extends EntityService<SgpFlujoHallazgo, Integer>{

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @param flujoHallazgodto
	 * @return 
	 * @throws AppBaseException
	 * @throws Exception 
	 */
	void guardarFlujoHallazgo(FlujoHallazgoDTO flujoHallazgodto, SgpHallazgo hallazgo, SgpFlujoHallazgo sgpFlujoHallazgo)throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/03/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<FlujoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException ;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/03/2014
	 * @param flujoHallazgo
	 * @param sgpFlujoHallazgo 
	 * @throws AppBaseException
	 */
	void editarHallazgo(FlujoHallazgoDTO flujoHallazgo, SgpFlujoHallazgo sgpFlujoHallazgo)  throws Exception ;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @param idHallazgo
	 * @return
	 * @throws AppBaseException
	 */
	FlujoHallazgoDTO getFlujoHallazgo(Integer idFlujo)throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @param flujoHallazgoDTO
	 * @throws AppBaseException
	 */
	void editarFlujoHallazgo(FlujoHallazgoDTO flujoHallazgoDTO)throws AppBaseException;
}
