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
import com.premize.sgp.dto.pruebas.TipoSeveridadDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisService
 * @date 31/01/2014
 */

public interface TipoSeveridadService extends EntityService<SgpTipoSeveridad, Integer> {
	
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
		* @param idCausaGeneracion
		* @return
		* @throws AppBaseException
		 */
		TipoSeveridadDTO getTipoSeveridad(Integer idTipoSeveridad) throws AppBaseException ;
		
		/**
		 * 
		* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		* @date 17/06/2014
		* @param causaGeneracionDTO
		* @throws AppBaseException
		 */
		 void guardarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO) throws AppBaseException;
		 
		 /**
		  * 
		 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		 * @date 17/06/2014
		 * @param causaGeneracionDTO
		 * @throws AppBaseException
		  */
		 void editarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO) throws AppBaseException;
		 
		 /**
		  * 
		 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		 * @date 17/06/2014
		 * @return
		 * @throws AppBaseException
		  */
		 public List<TipoSeveridadDTO> getTipoSeveridades() throws AppBaseException ;
		  
		 /**
		  * 
		 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
		 * @date 18/06/2014
		 * @param idTipoHallazgo
		 * @param page
		 * @return
		 * @throws AppBaseException 
		  */
		 List<TipoSeveridadDTO> consultaSeveridadPorTipoHallazgo(Integer idTipoHallazgo, Paginator page) throws AppBaseException;

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
		 List<TipoSeveridadDTO> tipoHallazgos() throws AppBaseException;

}
