package com.premize.sgp.service.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.DepartamentoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class DepartamentoService
 * @date 30/01/2014
 */

public interface DepartamentoService extends EntityService<SgpDepartamento, Integer> {
	
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
	 * @return
	 * @throws AppBaseException
	 */
	List<DepartamentoDTO> getDepartamentos() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param departamento
	 * @throws AppBaseException
	 */
	void editarDepartamento(DepartamentoDTO departamento) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idDepartamento
	 * @return
	 * @throws AppBaseException
	 */
	DepartamentoDTO getDepartamento(Integer idDepartamento) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param departamento
	 * @throws AppBaseException
	 */
	void guardarDepartamento(DepartamentoDTO departamento) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idPais
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	List<DepartamentoDTO> consultarDepartamentosPorPais(Integer idPais, Paginator page) throws AppBaseException;
	
}
