package com.premize.sgp.dao.gestion.pruebas;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoSeveridadDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class PaisDao
 * @since 27/03/2014
 *
 */
public interface TipoSeveridadDao extends Dao<SgpTipoSeveridad, Integer> {
	
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
	* @return
	* @throws AppBaseException
	 */
	boolean validarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO) throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @return
	* @throws AppBaseException
	 */
	public List<SgpTipoSeveridad> consultarTipoSeveridad()	throws AppBaseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param idTipoHallazgo
	* @param page
	* @return
	 */
	List<TipoSeveridadDTO> consultaSeveridadPorTipoHallazgo(Integer idTipoHallazgo, Paginator page) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014
	 * @description 
	 * @param tipoHallazgoId
	 * @return
	 * @throws AppBaseException
	 */
	List<TipoSeveridadDTO> consultarSeveridadPorTipoHallazgo(Integer tipoHallazgoId) throws AppBaseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 11/07/2014
	* @return
	 */
	List<TipoSeveridadDTO> tipoHallazgos() throws AppBaseException;
}
