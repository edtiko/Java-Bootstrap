/**
 * 
 */
package com.premize.sgp.service.pruebas;

import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoPrioridad;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisService
 * @date 31/01/2014
 */

public interface TipoPrioridadService extends EntityService<SgpTipoPrioridad, Integer> {
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
		ResultSet<TipoPrioridadDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
		/**
		 * 
		* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		* @date 17/06/2014
		* @param idCausaGeneracion
		* @return
		* @throws AppBaseException
		 */
		TipoPrioridadDTO getTipoPrioridad(Integer idTipoPrioridad) throws AppBaseException ;
		
		/**
		 * 
		* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		* @date 17/06/2014
		* @param causaGeneracionDTO
		* @throws AppBaseException
		 */
		 void guardarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO) throws AppBaseException;
		 
		 /**
		  * 
		 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		 * @date 17/06/2014
		 * @param causaGeneracionDTO
		 * @throws AppBaseException
		  */
		 void editarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO) throws AppBaseException;
		 
		 /**
		  * 
		 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		 * @date 17/06/2014
		 * @return
		 * @throws AppBaseException
		  */
		 public List<TipoPrioridadDTO> getTipoPrioridades() throws AppBaseException ;
		 
		 /**
		  * 
		 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		 * @date 18/06/2014
		 * @param idTipoHallazgo
		 * @param page
		 * @return
		  */
		 List<TipoPrioridadDTO> consultaPrioridadPorTipoHallazgo(Integer idTipoHallazgo, Paginator page)throws AppBaseException;
		 
		 
	
}
