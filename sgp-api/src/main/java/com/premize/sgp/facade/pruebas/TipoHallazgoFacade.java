/**
 * 
 */
package com.premize.sgp.facade.pruebas;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-api
* @class TipoHallazgoFacade
* @date 16/06/2014
*
 */
public interface TipoHallazgoFacade extends EntityFacade<SgpTipoHallazgo, Integer> {
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param criteria
	* @return
	 */
	ResultSet<TipoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param paisdto
	 */
	void guardarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO) throws AppBaseException ;
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param paisdto
	* @throws AppBaseException
	 */
	void editarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO) throws AppBaseException;
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param idTipoHallazgo
	* @return
	* @throws AppBaseException
	 */
	TipoHallazgoDTO getTipoHallazgo(Integer idTipoHallazgo) throws AppBaseException;
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @return
	* @throws AppBaseException
	 */
	List<TipoHallazgoDTO> getTipoHallazgos(byte tipoIngresoHallazgo) throws AppBaseException;

	
	public List<TipoHallazgoDTO> tipoHallazgos() throws AppBaseException;
}
