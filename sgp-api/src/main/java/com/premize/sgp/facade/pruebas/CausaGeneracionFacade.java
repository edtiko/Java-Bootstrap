/**
 * 
 */
package com.premize.sgp.facade.pruebas;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CausaGeneracionDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-api
 * @class PaisFacade
 * @since 20/01/2014
 */
public interface CausaGeneracionFacade extends EntityFacade<SgpCausaGeneracion, Integer> {
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	ResultSet<CausaGeneracionDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
    /**
     * 
    * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
    * @date 17/06/2014
    * @param tipoHallazgoDTO
    * @throws AppBaseException
     */
	void guardarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException ;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param idCausaGeneracion
	* @return
	 */
	CausaGeneracionDTO getCausaGeneracion(Integer idCausaGeneracion) throws AppBaseException;

	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param causaGeneracionDTO
	 */
	void editarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param idTipoHallazgo
	* @return
	 */
	List<CausaGeneracionDTO> consultaCausaPorTipoHallazgo(Integer idTipoHallazgo,Paginator page)throws AppBaseException;
	
}
