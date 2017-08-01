package com.premize.sgp.dao.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.DepartamentoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class DepartamentoDao
 * @since 27/03/2014
 *
 */
public interface DepartamentoDao extends Dao<SgpDepartamento, Integer> {
	/**
	 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
	 * @date 03/02/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<SgpDepartamento> consultarDepartamentos() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<DepartamentoDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idPais
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<DepartamentoDTO> consultarDepartamentosPorPais(Integer idPais, Paginator page) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param depto
	 * @return
	 * @throws AppBaseException 
	 */
	public Boolean validarDepartamento(DepartamentoDTO depto) throws AppBaseException;
	
}
