package com.premize.sgp.service.pruebas;

import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CausaGeneracionDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-service
* @class CausaGeneracionService
* @date 17/06/2014
*
 */
public interface CausaGeneracionService extends EntityService<SgpCausaGeneracion, Integer>{
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
	* @param idCausaGeneracion
	* @return
	* @throws AppBaseException
	 */
	 CausaGeneracionDTO getCausaGeneracion(Integer idCausaGeneracion) throws AppBaseException ;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param causaGeneracionDTO
	* @throws AppBaseException
	 */
	 void guardarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException;
	 
	 /**
	  * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 17/06/2014
	 * @param causaGeneracionDTO
	 * @throws AppBaseException
	  */
	 void editarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException;
	 
	 /**
	  * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 17/06/2014
	 * @return
	 * @throws AppBaseException
	  */
	 public List<CausaGeneracionDTO> getCausaGeneraciones() throws AppBaseException ;
	
    /**
     * 
    * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
    * @date 18/06/2014
    * @param idTipoHallazgo
    * @param page
    * @return
    * @throws AppBaseException
     */
    public List<CausaGeneracionDTO> consultaCausaPorTipoHallazgo(Integer idTipoHallazgo,Paginator page)throws AppBaseException;
	 
	 
}
