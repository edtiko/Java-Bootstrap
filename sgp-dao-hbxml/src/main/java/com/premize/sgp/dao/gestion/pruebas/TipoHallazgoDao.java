package com.premize.sgp.dao.gestion.pruebas;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class PaisDao
 * @since 27/03/2014
 *
 */
public interface TipoHallazgoDao extends Dao<SgpTipoHallazgo, Integer> {
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	ResultSet<TipoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 16/06/2014
* @param pais
* @return
* @throws AppBaseException
 */
	boolean validarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO) throws AppBaseException;
	
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 17/06/2014
* @return
* @throws AppBaseException
 */
	List<SgpTipoHallazgo> consultarTipoHallazgos(String[] listTiposHallazgos)throws AppBaseException;
	
	public List<SgpTipoHallazgo> tipoHallazgos() throws AppBaseException;
}
