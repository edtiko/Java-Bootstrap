/**
 * 
 */
package com.premize.sgp.service.parametros.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dao.usuarios.UsuarioProyectoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.UsuarioProyectoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;
import com.premize.sgp.service.parametros.UsuarioService;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class UsuarioServiceImpl
 * @since 20/01/2014
 */
@Service
public class UsuarioServiceImpl extends AbstractEntityService<SgpUsuario, Integer> implements UsuarioService {
	
	@Autowired
	private UsuarioDao sgpUsuarioDao;
	
	@Autowired
	private UsuarioProyectoDao usuarioProyectoDao;
	
	@Override
	public Dao<SgpUsuario, Integer> getDao() {
		return sgpUsuarioDao;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#guardar(com.premize.sgp.dto.parametros.UsuarioDTO)
	 */
	public void guardar(UsuarioDTO pUsuario) throws AppBaseException {
		
		try {
			
			validarLoginUsuario(pUsuario.getLogin());
			
			SgpUsuario usuario = new SgpUsuario();
			usuario.setNombre(pUsuario.getNombre());
			usuario.setEmpresa(pUsuario.getEmpresa());
			usuario.setCargo(pUsuario.getCargo());
			usuario.setLogin(pUsuario.getLogin());
			usuario.setCorreo(pUsuario.getCorreo());
			usuario.setTelefono(pUsuario.getTelefono());
			usuario.setIndActivo(pUsuario.getNumeroEstado());
			usuario.setFecCreacion(Calendar.getInstance().getTime());
			usuario.setUsuarioCrea(pUsuario.getUsuarioCreacion());
			
			sgpUsuarioDao.save(usuario);
		} catch (AppBaseException e) {
			throw e;
		}
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#actualizar(com.premize.sgp.modelo.entities.parametros.SgpUsuario)
	 */
	@Override
	public void actualizar(UsuarioDTO pUsuario) throws AppBaseException {
		
		try {
			
			if (pUsuario.getId() == null || StringUtils.isBlank(pUsuario.getId().toString())) {
				throw new AppBaseException("Usuario sin ingresar.", null);
			}
			
			SgpUsuario usuario = sgpUsuarioDao.get(pUsuario.getId());
			
			if (usuario == null || usuario.getId() == null) {
				throw new AppBaseException("Registro no existe.", null);
			}
			
			if (!usuario.getLogin().equals(pUsuario.getLogin())) {
				validarLoginUsuario(pUsuario.getLogin());
			}
			
			usuario.setNombre(pUsuario.getNombre());
			usuario.setEmpresa(pUsuario.getEmpresa());
			usuario.setCargo(pUsuario.getCargo());
			usuario.setLogin(pUsuario.getLogin());
			usuario.setCorreo(pUsuario.getCorreo());
			usuario.setTelefono(pUsuario.getTelefono());
			usuario.setIndActivo(pUsuario.getNumeroEstado());
			usuario.setFecEdita(Calendar.getInstance().getTime());
			usuario.setUsuarioEdita(pUsuario.getUsuarioEdita());
			
			sgpUsuarioDao.update(usuario);
			
		} catch (AppBaseException e) {
			throw e;
		}
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @param login
	 */
	private void validarLoginUsuario(String login) throws AppBaseException {
		
		SgpUsuario usuario = sgpUsuarioDao.findByProperty("login", login);
		
		if (usuario != null && usuario.getId() != null) {
			throw new AppBaseException("Login no disponible, por favor ingrese un login diferente", null);
		}
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<UsuarioDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		return sgpUsuarioDao.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#obtenerArtefacto(java.lang.Integer)
	 */
	@Override
	public UsuarioDTO obtenerUsuario(Integer idUsuario) throws AppBaseException {
		
		SgpUsuario sgpUsuario = sgpUsuarioDao.get(idUsuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO(sgpUsuario);
		
		return usuarioDTO;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#obtenerUsuarioPorProyecto(java.lang.Integer)
	 */
	@Override
	public List<UsuarioDTO> obtenerUsuarioPorProyecto(Integer idProyecto) throws Exception {
		return sgpUsuarioDao.obtenerUsuarioPorProyecto(idProyecto);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#obtenerUsuariosParaAsociar(java.lang.Integer)
	 */
	@Override
	public List<UsuarioDTO> obtenerUsuariosParaAsociar(Integer idProyecto) throws Exception {
		return sgpUsuarioDao.obtenerUsuariosParaAsociar(idProyecto);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 17/02/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#getUsuarioProyectoRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<UsuarioProyectoDTO> getUsuarioProyectoRecords(PagingCriteria criteria) throws AppBaseException {
		
		return usuarioProyectoDao.getUsuarioProyectoRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 17/02/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#eliminarUsuarioProyecto(java.lang.Integer)
	 */
	@Override
	public void eliminarUsuarioProyecto(Integer id) throws AppBaseException {
		
		usuarioProyectoDao.deleteById(id);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 12/03/2014
	 * @see com.premize.sgp.service.parametros.UsuarioService#obtenerUsuario(java.lang.String)
	 */
	@Override
	public UsuarioDTO obtenerUsuario(String login) throws AppBaseException {
		
		UsuarioDTO usuarioDto = null;
		SgpUsuario sgpUsuario = sgpUsuarioDao.findByProperty("login", login);
		
		if (sgpUsuario != null && sgpUsuario.getId() != null) {
			usuarioDto = new UsuarioDTO(sgpUsuario);
		}
		
		return usuarioDto;
	}
	
}
