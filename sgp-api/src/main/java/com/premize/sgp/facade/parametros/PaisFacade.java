/**
 * 
 */
package com.premize.sgp.facade.parametros;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.modelo.entities.parametros.SgpPais;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-api
 * @class PaisFacade
 * @since 20/01/2014
 */
public interface PaisFacade extends EntityFacade<SgpPais, Integer> {
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<PaisDTO> getPaises() throws AppBaseException;
	
	/**
	 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
	 * @date 30/01/2014
	 * @param pais
	 * @throws AppBaseException
	 */
	void guardarPais(PaisDTO pais) throws AppBaseException;
	
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
	 * @param paisdto
	 * @throws AppBaseException
	 */
	void editarPais(PaisDTO paisdto) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idPais
	 * @return
	 * @throws AppBaseException
	 */
	PaisDTO getPais(Integer idPais) throws AppBaseException;
	
}
