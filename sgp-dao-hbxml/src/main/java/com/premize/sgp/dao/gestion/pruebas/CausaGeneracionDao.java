package com.premize.sgp.dao.gestion.pruebas;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CausaGeneracionDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class PaisDao
 * @since 27/03/2014
 *
 */
public interface CausaGeneracionDao extends Dao<SgpCausaGeneracion, Integer> {
	
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
	* @return
	* @throws AppBaseException
	 */
	boolean validarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @return
	* @throws AppBaseException
	 */
	public List<SgpCausaGeneracion> consultarCausaGeneracion()	throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param idTipoHallazgo
	* @param page
	* @return
	 */
	public List<CausaGeneracionDTO> consultaCausaPorTipoHallazgo(Integer idTipoHallazgo,Paginator page)throws AppBaseException;
}
