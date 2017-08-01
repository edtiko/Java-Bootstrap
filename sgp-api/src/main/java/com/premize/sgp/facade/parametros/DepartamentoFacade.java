package com.premize.sgp.facade.parametros;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.DepartamentoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class DepartamentoFacade
 * @since 27/03/2014
 *
 */
public interface DepartamentoFacade extends EntityFacade<SgpDepartamento, Integer> {
	
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
	void guardarDepartamento(DepartamentoDTO departamento) throws AppBaseException;
	
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
	 * @param departamentodto
	 * @throws AppBaseException
	 */
	void editarDepartamento(DepartamentoDTO departamentodto) throws AppBaseException;
	
	/**
	 * @param idDepartamento
	 * @return
	 * @throws AppBaseException
	 */
	DepartamentoDTO getDepartamento(Integer idDepartamento) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param id_Pais
	 * @param default1
	 * @return
	 */
	List<DepartamentoDTO> consultarCiudadPorDepartamento(Integer id_Pais, Paginator default1);
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param id_Pais
	 * @param default1
	 * @return
	 * @throws AppBaseException
	 */
	List<DepartamentoDTO> consultarDepartamentosPorPais(Integer id_Pais, Paginator default1) throws AppBaseException;
	
}
