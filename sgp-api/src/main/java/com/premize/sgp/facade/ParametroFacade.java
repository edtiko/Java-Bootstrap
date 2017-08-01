/**
 * 
 */
package com.premize.sgp.facade;

import java.util.List;
import java.util.Map;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroFacade
 * @since 17/01/2014
 */
public interface ParametroFacade extends EntityFacade<SgpParametro, Integer> {
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 20/01/2014
	 * @return
	 * @throws AppBaseException 
	 */
	List<ParametroDTO> getListParametros() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param parametro
	 * @throws Exception
	 */
	void guardarParametro(ParametroDTO parametro) throws Exception;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 23/01/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<ParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/01/2014
	 * @param parametrodto
	 * @throws AppBaseException
	 */
	void editarParametro(ParametroDTO parametrodto) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 28/01/2014
	 * @param idParametro
	 * @return
	 * @throws AppBaseException
	 */
	ParametroDTO getParametro(Integer idParametro) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 6/06/2014
	 * @description 
	 * @param parametroKey
	 * @return
	 * @throws AppBaseException
	 */
	ParametroDTO getParametro(String parametroKey) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 * @param idTipoParametro
	 * @return
	 */
	List<ParametroDTO> listarParametrosPorTipoParametro(Integer idTipoParametro, Paginator page)
			throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 29/05/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
	Map<String,Object> getMapRestriccionesAnexo() throws AppBaseException;
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 11/06/2014
	* @return
	* @throws AppBaseException
	 */
	Map<String,Object> getMapRestriccionesLogo() throws AppBaseException;
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 11/06/2014
* @param estado
* @return
* @throws AppBaseException
 */
	boolean getEstadoNoSolicitaUsuario(String estado) throws AppBaseException;
	
}
