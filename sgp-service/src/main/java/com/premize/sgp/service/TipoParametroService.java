/**
 * 
 */
package com.premize.sgp.service;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class TipoParametroService
 * @since 16/01/2014
 */
public interface TipoParametroService extends EntityService<SgpTipoParametro, Integer> {
	
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
	 * @since 30/01/2014
	 */
	void guardarTipoParametro(TipoParametroDTO tipoParametro) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 */
	void editarTipoParametro(TipoParametroDTO tipoParametro) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @param idTipoParametro
	 * @return
	 * @throws AppBaseException
	 */
	TipoParametroDTO getTipoParametro(Integer idTipoParametro) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idTipoHallazgo
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	List<TipoParametroDTO> listarParametrosPorTipoHallazgo(Integer idTipoHallazgo, Paginator page)
			throws AppBaseException;
	
}
