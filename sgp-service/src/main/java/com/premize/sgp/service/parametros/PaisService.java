/**
 * 
 */
package com.premize.sgp.service.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.modelo.entities.parametros.SgpPais;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisService
 * @date 31/01/2014
 */

public interface PaisService extends EntityService<SgpPais, Integer> {
	
	ResultSet<PaisDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 31/01/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<PaisDTO> getPaises() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param pais
	 * @throws AppBaseException
	 */
	void editarPais(PaisDTO pais) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idPais
	 * @return
	 * @throws AppBaseException
	 */
	PaisDTO getPais(Integer idPais) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param pais
	 * @throws AppBaseException
	 */
	void guardarPais(PaisDTO pais) throws AppBaseException;
	
}
