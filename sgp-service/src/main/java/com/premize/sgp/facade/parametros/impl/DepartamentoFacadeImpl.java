package com.premize.sgp.facade.parametros.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.DepartamentoDTO;
import com.premize.sgp.facade.parametros.DepartamentoFacade;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;
import com.premize.sgp.service.parametros.DepartamentoService;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class DepartamentoFacadeImpl
 * @since 27/03/2014
 *
 */
@Service("departamentoFacade")
public class DepartamentoFacadeImpl extends AbstractEntityFacade<SgpDepartamento, Integer> implements
		DepartamentoFacade {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.DepartamentoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<DepartamentoDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return departamentoService.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.DepartamentoFacade#guardarDepartamento(com.premize.sgp.dto.parametros.DepartamentoDTO)
	 */
	@Override
	public void guardarDepartamento(DepartamentoDTO departamento) throws AppBaseException {
		
		departamentoService.guardarDepartamento(departamento);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.facade.parametros.DepartamentoFacade#editarDepartamento(com.premize.sgp.dto.parametros.DepartamentoDTO)
	 */
	@Override
	public void editarDepartamento(DepartamentoDTO departamento) throws AppBaseException {
		departamentoService.editarDepartamento(departamento);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.pmz.facade.AbstractEntityFacade#getEntityService()
	 */
	public EntityService<SgpDepartamento, Integer> getEntityService() {
		return null;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 31/01/2014
	 * @see com.premize.sgp.facade.parametros.PaisFacade#getPaises()
	 */
	
	@Override
	public List<DepartamentoDTO> getDepartamentos() throws AppBaseException {
		return departamentoService.getDepartamentos();
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.DepartamentoFacade#getDepartamento(java.lang.Integer)
	 */
	public DepartamentoDTO getDepartamento(Integer idDepartamento) throws AppBaseException {
		
		return departamentoService.getDepartamento(idDepartamento);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.DepartamentoFacade#consultarCiudadPorDepartamento(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<DepartamentoDTO> consultarCiudadPorDepartamento(Integer id_Pais, Paginator default1) {
		return null;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.DepartamentoFacade#consultarDepartamentosPorPais(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<DepartamentoDTO> consultarDepartamentosPorPais(Integer id_Pais, Paginator default1)
			throws AppBaseException {
		
		return departamentoService.consultarDepartamentosPorPais(id_Pais, default1);
	}
	
}