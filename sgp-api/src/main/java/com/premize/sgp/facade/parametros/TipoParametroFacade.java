/**
 * 
 */
package com.premize.sgp.facade.parametros;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class TipoParametroFacade
 * @since 28/01/2014
 */
public interface TipoParametroFacade extends EntityFacade<SgpTipoParametro, Integer> {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException 
	 */
	List<TipoParametroDTO> getTipos() throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<TipoParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @param tipoParametrodto
	 */
	void guardarTipoParametro(TipoParametroDTO tipoParametrodto) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @param tipoParametrodto
	 */
	void editarTipoParametro(TipoParametroDTO tipoParametrodto) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @param idTipoParametro
	 * @return
	 * @throws AppBaseException
	 */
	TipoParametroDTO getTipoParametro(Integer idTipoParametro) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idTipoParametro
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	List<TipoParametroDTO> listarParametrosPorTipoHallazgos(Integer idTipoParametro, Paginator page)
			throws AppBaseException;
}
