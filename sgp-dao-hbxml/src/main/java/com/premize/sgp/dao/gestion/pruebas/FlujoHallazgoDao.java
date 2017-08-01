package com.premize.sgp.dao.gestion.pruebas;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class FlujoHallazgoDao
 * @since 27/03/2014
 *
 */
public interface FlujoHallazgoDao extends Dao<SgpFlujoHallazgo, Integer> {
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param id
	 * @return
	 * @throws AppBaseException 
	 */
	SgpFlujoHallazgo obtenerFlujoPorHallazgo(Integer id) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param id
	 * @return
	 * @throws AppBaseException 
	 */
	List<SgpFlujoHallazgo> getFlujosByHallazgo(Integer id) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<FlujoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
}
