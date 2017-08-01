/**
 * 
 */
package com.premize.sgp.service.pruebas;

import java.text.ParseException;
import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisService
 * @date 31/01/2014
 */

public interface TipoHallazgoService extends EntityService<SgpTipoHallazgo, Integer> {
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	* @throws ParseException
	 */
	ResultSet<TipoHallazgoDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException;
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 16/06/2014
* @param idPais
* @return
* @throws AppBaseException
 */
	TipoHallazgoDTO getTipoHallazgo(Integer idTipoHallazgo) throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param tipoHallazgoDTO
	* @throws AppBaseException
	 */
	void editarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO)throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param pais
	* @throws AppBaseException
	 */
	void guardarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO) throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @return
	 */
	List<TipoHallazgoDTO> getTipoHallazgos(String[] tipoIngresoHallazgo )throws AppBaseException;

	
	public List<TipoHallazgoDTO> tipoHallazgos() throws AppBaseException;
}
