package com.premize.sgp.dao.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.CiudadDTO;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class CiudadDao
 * @since 27/03/2014
 *
 */
public interface CiudadDao extends Dao<SgpCiudad, Integer> {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException
	 */
	public List<SgpCiudad> consultarCiudades() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idCiudad
	 * @return
	 */
	public SgpDepartamento getDepartamentoByCiudad(Integer idCiudad);
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	public ResultSet<CiudadDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 12/02/2014
	 * @param idDepartamento
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<CiudadDTO> consultarCiudadPorDepartamento(Integer idDepartamento, Paginator page)
			throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param ciudad
	 * @throws AppBaseException 
	 */
	public Boolean validarCiudad(CiudadDTO ciudad) throws AppBaseException;
}