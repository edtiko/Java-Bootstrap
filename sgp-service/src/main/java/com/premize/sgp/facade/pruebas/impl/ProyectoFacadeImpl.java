/**
 * 
 */
package com.premize.sgp.facade.pruebas.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.facade.pruebas.ProyectoFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.service.pruebas.ProyectoService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroFacadeImpl
 * @since 17/01/2014
 */
@Service("proyectoFacadeImpl")
public class ProyectoFacadeImpl extends AbstractEntityFacade<SgpProyecto, Integer> implements ProyectoFacade {
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Override
	public ProyectoService getEntityService() {
		
		return proyectoService;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.ProyectoFacade#getListProyectos()
	 */
	@Override
	public List<ProyectoDTO> getListProyectos() throws AppBaseException {
		
		return proyectoService.getListProyectos();
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.ProyectoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ProyectoDTO> getRecords(PagingCriteria criteria,Boolean mostraActivoInactivo) throws AppBaseException {
		
		return proyectoService.getRecords(criteria,mostraActivoInactivo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.ProyectoFacade#guardarProyecto(com.premize.sgp.dto.pruebas.ProyectoDTO)
	 */
	@Override
	public void guardarProyecto(ProyectoDTO proyecto) throws AppBaseException {
		
		proyectoService.guardarProyecto(proyecto);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.ProyectoFacade#editarProyecto(com.premize.sgp.dto.pruebas.ProyectoDTO)
	 */
	@Override
	public void editarProyecto(ProyectoDTO proyecto) throws AppBaseException {
		proyectoService.editarProyecto(proyecto);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 28/01/2014
	 * @see com.premize.sgp.facade.ParametroFacade#getParametro(int)
	 */
	@Override
	public ProyectoDTO getProyecto(Integer idProyecto) throws AppBaseException {
		
		return proyectoService.getProyecto(idProyecto);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.ProyectoFacade#consultarProyectoPorEmpresa(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<ProyectoDTO> consultarProyectoPorEmpresa(Integer idEmpresa, Paginator page) throws AppBaseException {
		return getEntityService().consultarProyectoPorEmpresa(idEmpresa, page);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @see com.premize.sgp.facade.pruebas.ProyectoFacade#actualizarUsuariosPorProyecto(java.util.List,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void actualizarUsuariosPorProyecto(List<String> usuarios, String idProyecto, String login) throws AppBaseException {
		proyectoService.actualizarUsuariosPorProyecto(usuarios, idProyecto, login);
		
	}
	
}
