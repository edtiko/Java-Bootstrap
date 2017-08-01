package com.premize.sgp.dao.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class TipoParametroDao
 * @since 27/03/2014
 *
 */
public interface TipoParametroDao extends Dao<SgpTipoParametro, Integer> {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<SgpTipoParametro> getTipos() throws AppBaseException;

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<TipoParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException;

}
