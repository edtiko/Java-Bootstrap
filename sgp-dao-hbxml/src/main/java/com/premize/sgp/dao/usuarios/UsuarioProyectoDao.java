package com.premize.sgp.dao.usuarios;

import com.premize.sgp.dto.ResultSet;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.pruebas.UsuarioProyectoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class UsuarioProyectoDao
 * @since 27/03/2014
 *
 */
public interface UsuarioProyectoDao extends Dao<SgpUsuarioProyecto, Integer> {
	
	/**
	 * Procedimiento encargado de asociar Usuarios a Proyectos
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param artefactos
	 * @throws AppBaseException
	 */
	void asociarArtefactosPorMapa(List<SgpUsuarioProyecto> usuarioProyecto) throws AppBaseException;
	
	/**
	 * Procedimiento encargado de Eliminar todos los Usuarios relacionados a un Proyecto
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param idProyecto
	 * @throws AppBaseException
	 */
	void desasociarUsuariosProyecto(Integer idProyecto, String login) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<UsuarioProyectoDTO> getUsuarioProyectoRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 19/06/2014
	 * @description 
	 * @param idProyecto
	 * @return
	 * @throws AppBaseException
	 */
	List<SgpUsuarioProyecto> consultarUsuariosAsociados(Integer idProyecto) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 20/06/2014
	 * @description 
	 * @param proyectoId
	 * @param idUsuarios
	 * @param login
	 * @throws AppBaseException
	 */
	void desasociarUsuariosProyecto(Integer proyectoId, List<Integer> idUsuarios, String login) throws AppBaseException;
}
