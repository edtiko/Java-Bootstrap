package com.premize.sgp.dao.parametros;

import com.premize.sgp.dto.ResultSet;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpParametroDao
 * @since 15/01/2014
 */
public interface SgpParametroDao extends Dao<SgpParametro, Integer> {
	
	/**
	 * Ruta para acceder a la propiedad tipo parametro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 */
	public static final String TIPO_PARAMETRO_ID = "sgpTipoParametro.id";
	
	/**
	 * Ruta para acceder a la propiedad tipo de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 */
	public static final String TIPO_PARAMETRO = "sgpTipoParametro";
	public static final Integer ACTIVO = 1;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException 
	 */
	List<SgpParametro> getListParametros() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<ParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 * @param idTipoParametro
	 * @return
	 */
	public List<ParametroDTO> listarParametrosPorTipoParametro(Integer idTipoParametro, Paginator page)
			throws AppBaseException;
}
