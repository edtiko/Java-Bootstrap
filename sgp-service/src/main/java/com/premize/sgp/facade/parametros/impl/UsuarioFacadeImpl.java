/**
 * 
 */
package com.premize.sgp.facade.parametros.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.UsuarioProyectoDTO;
import com.premize.sgp.facade.parametros.UsuarioFacade;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;
import com.premize.sgp.service.parametros.UsuarioService;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class UsuarioFacadeImpl
 * @since 21/01/2014
 */
@Service("usuarioFacade")
public class UsuarioFacadeImpl extends AbstractEntityFacade<SgpUsuario, Integer> implements UsuarioFacade, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4649567182270013495L;
	/**
	 * 
	 */
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public EntityService<SgpUsuario, Integer> getEntityService() {
		return usuarioService;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#guardar(com.premize.sgp.modelo.entities.parametros.SgpUsuario)
	 */
	@Override
	public void guardar(UsuarioDTO pUsuario) throws AppBaseException {
		
		usuarioService.guardar(pUsuario);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#actualizar(com.premize.sgp.modelo.entities.parametros.SgpUsuario)
	 */
	@Override
	public void actualizar(UsuarioDTO pUsuario) throws AppBaseException {
		
		usuarioService.actualizar(pUsuario);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<UsuarioDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		return usuarioService.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#obtenerArtefacto(java.lang.Integer)
	 */
	@Override
	public UsuarioDTO obtenerUsuario(Integer idUsuario) throws AppBaseException {
		return usuarioService.obtenerUsuario(idUsuario);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#obtenerUsuarioPorProyecto(java.lang.Integer)
	 */
	@Override
	public List<UsuarioDTO> obtenerUsuarioPorProyecto(Integer idProyecto) throws Exception {
		return usuarioService.obtenerUsuarioPorProyecto(idProyecto);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#obtenerUsuariosParaAsociar(java.lang.Integer)
	 */
	@Override
	public List<UsuarioDTO> obtenerUsuariosParaAsociar(Integer idProyecto) throws Exception {
		return usuarioService.obtenerUsuariosParaAsociar(idProyecto);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 17/02/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#getUsuarioProyectoRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<UsuarioProyectoDTO> getUsuarioProyectoRecords(PagingCriteria criteria) throws AppBaseException {
		
		return usuarioService.getUsuarioProyectoRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 17/02/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#eliminarUsuarioProyecto(java.lang.Integer)
	 */
	@Override
	public void eliminarUsuarioProyecto(Integer id) throws AppBaseException {
		
		usuarioService.eliminarUsuarioProyecto(id);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 12/03/2014
	 * @see com.premize.sgp.facade.parametros.UsuarioFacade#obtenerUsuario(java.lang.String)
	 */
	@Override
	public UsuarioDTO obtenerUsuario(String login) throws AppBaseException {
		
		return usuarioService.obtenerUsuario(login);
	}
}
