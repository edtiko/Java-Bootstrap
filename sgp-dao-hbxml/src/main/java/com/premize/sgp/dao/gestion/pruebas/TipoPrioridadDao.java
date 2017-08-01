package com.premize.sgp.dao.gestion.pruebas;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoPrioridad;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class PaisDao
 * @since 27/03/2014
 *
 */
public interface TipoPrioridadDao extends Dao<SgpTipoPrioridad, Integer> {
	

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
	* @param tipoHallazgoDTO
	* @return
	* @throws AppBaseException
	 */
	boolean validarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO) throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @return
	* @throws AppBaseException
	 */
	public List<SgpTipoPrioridad> consultarTipoPrioridad()	throws AppBaseException;

	List<TipoPrioridadDTO> consultaPrioridadPorTipoHallazgo(Integer idTipoHallazgo, Paginator page)throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014
	 * @description 
	 * @param idTipoHallazgo
	 * @return
	 * @throws AppBaseException
	 */
	List<TipoPrioridadDTO> consultarPrioridadPorTipoHallazgo(Integer idTipoHallazgo) throws AppBaseException;
	
}
