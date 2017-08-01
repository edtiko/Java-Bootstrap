package com.premize.sgp.dao.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.modelo.entities.parametros.SgpPais;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class PaisDao
 * @since 27/03/2014
 *
 */
public interface PaisDao extends Dao<SgpPais, Integer> {
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 21/01/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<SgpPais> consultarPaises() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idCiudad
	 * @return
	 * @throws AppBaseException
	 */
	SgpPais getPaisByCiudad(Integer idCiudad) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<PaisDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<PaisDTO> getRecordsByHSQL() throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param pais
	 * @return
	 * @throws AppBaseException 
	 */
	boolean validarPais(PaisDTO pais) throws AppBaseException;
}
