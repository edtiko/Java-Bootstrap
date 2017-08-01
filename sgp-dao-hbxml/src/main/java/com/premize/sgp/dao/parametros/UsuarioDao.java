package com.premize.sgp.dao.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class UsuarioDao
 * @since 27/03/2014
 *
 */
public interface UsuarioDao extends Dao<SgpUsuario, Integer> {
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<UsuarioDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * Metodo que Obtiene todos Usuarios Asociados a determinado Proyecto.
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @param idProyecto
	 * @return
	 * @throws Exception
	 */
	List<UsuarioDTO> obtenerUsuarioPorProyecto(Integer idProyecto) throws Exception;
	
	/**
	 * Metodo que Obtiene los Usuarios Disponibles para Asociar a un Proyecto
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @param idProyecto
	 * @return
	 * @throws Exception
	 */
	List<UsuarioDTO> obtenerUsuariosParaAsociar(Integer idProyecto) throws Exception;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @param usuarioCreacion
	 * @return
	 * @throws AppBaseException
	 * @throws Exception
	 */
	SgpUsuario getUsuarioByLogin(String usuarioCreacion) throws AppBaseException;
	
}
