/**
 * 
 */
package com.premize.sgp.service.parametros;

import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.UsuarioProyectoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class UsuarioService
 * @since 20/01/2014
 */
public interface UsuarioService extends EntityService<SgpUsuario, Integer> {
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param pUsuario
	 */
	void guardar(UsuarioDTO pUsuario) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param pUsuario
	 */
	void actualizar(UsuarioDTO pUsuario) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<UsuarioDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @param idUsuario
	 * @return
	 */
	UsuarioDTO obtenerUsuario(Integer idUsuario) throws AppBaseException;
	
	/**
	 * Metodo que Obtiene todos Usuarios Asociados a determinado Proyecto.
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param idProyecto
	 * @return
	 * @throws Exception
	 */
	List<UsuarioDTO> obtenerUsuarioPorProyecto(Integer idProyecto) throws Exception;
	
	/**
	 * Metodo que Obtiene los Usuarios Disponibles para Asociar a un Proyecto
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param idProyecto
	 * @return
	 * @throws Exception
	 */
	List<UsuarioDTO> obtenerUsuariosParaAsociar(Integer idProyecto) throws Exception;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<UsuarioProyectoDTO> getUsuarioProyectoRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @param id
	 * @throws AppBaseException
	 */
	void eliminarUsuarioProyecto(Integer id) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 12/03/2014
	 * @param login
	 * @return
	 */
	UsuarioDTO obtenerUsuario(String login) throws AppBaseException;
	
}
