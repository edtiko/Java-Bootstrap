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
import com.premize.sgp.dto.pruebas.TipoSeveridadDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-api
* @class TipoSeveridadFacade
* @date 13/06/2014
*
 */
public interface TipoSeveridadFacade extends EntityFacade<SgpTipoSeveridad, Integer> {
	

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	ResultSet<TipoSeveridadDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
    /**
     * 
    * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
    * @date 17/06/2014
    * @param tipoHallazgoDTO
    * @throws AppBaseException
     */
	void guardarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO) throws AppBaseException ;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param idCausaGeneracion
	* @return
	 */
	TipoSeveridadDTO getTipoSeveridad(Integer idCausaGeneracion) throws AppBaseException;

	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param causaGeneracionDTO
	 */
	void editarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO) throws AppBaseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param parseInt
	* @param default1
	* @return
	 * @throws AppBaseException 
	 */
	public List<TipoSeveridadDTO> consultaSeveridadPorTipoHallazgo(Integer idTipoHallazgo ,Paginator default1) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
	List<String> getNombreTiposSeveridad() throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 11/07/2014
	* @return
	* @throws AppBaseException
	 */
	public List<TipoSeveridadDTO> tipoHallazgos() throws AppBaseException;
}
