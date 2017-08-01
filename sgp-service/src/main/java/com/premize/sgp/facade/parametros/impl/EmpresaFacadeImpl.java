/**
 * 
 */
package com.premize.sgp.facade.parametros.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.facade.parametros.EmpresaFacade;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;
import com.premize.sgp.service.parametros.EmpresaService;
import com.premize.sgp.utils.FilePmz;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class EmpresaFacadeImpl
 * @since 21/01/2014
 */
@Service("empresaFacade")
public class EmpresaFacadeImpl extends AbstractEntityFacade<SgpEmpresa, Integer> implements EmpresaFacade {
	
	@Autowired
	private EmpresaService empresaService;
	
	@Override
	public EmpresaService getEntityService() {
		return empresaService;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.facade.parametros.EmpresaFacade#guardar(com.premize.sgp.modelo.entities.parametros.SgpEmpresa)
	 */
	@Override
	public void guardar(EmpresaDTO pEmpresa, FilePmz logo) throws Exception {
		
		empresaService.guardar(pEmpresa, logo);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.facade.parametros.EmpresaFacade#acualizar(com.premize.sgp.modelo.entities.parametros.SgpEmpresa)
	 */
	@Override
	public void actualizar(EmpresaDTO pEmpresa, FilePmz logo) throws AppBaseException {
		
		empresaService.actualizar(pEmpresa, logo);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 31/01/2014
	 * @see com.premize.sgp.facade.parametros.EmpresaFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<EmpresaDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return empresaService.getRecords(criteria);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 31/01/2014
	 * @see com.premize.sgp.facade.parametros.EmpresaFacade#obtenerEmpresa(java.lang.Integer)
	 */
	@Override
	public EmpresaDTO obtenerEmpresa(Integer idEmpresa) throws AppBaseException {
		return empresaService.obtenerEmpresa(idEmpresa);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.EmpresaFacade#consultarEmpresasPorCiudad(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<EmpresaDTO> consultarEmpresasPorCiudad(Integer idCiudad, Paginator page) throws AppBaseException {
		
		return getEntityService().consultarEmpresasPorCiudad(idCiudad, page);
	}
	
}
