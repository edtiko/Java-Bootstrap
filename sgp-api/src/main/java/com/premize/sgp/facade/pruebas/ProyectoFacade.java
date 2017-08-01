/**
 * 
 */
package com.premize.sgp.facade.pruebas;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroFacade
 * @since 17/01/2014
 */
public interface ProyectoFacade extends EntityFacade<SgpProyecto, Integer> {
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 20/01/2014
	 * @return
	 * @throws AppBaseException 
	 */
	List<ProyectoDTO> getListProyectos() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param proyecto
	 * @throws AppBaseException
	 */
	void guardarProyecto(ProyectoDTO proyecto) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 23/01/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<ProyectoDTO> getRecords(PagingCriteria criteria,Boolean mostraActivoInactivo) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/01/2014
	 * @param parametrodto
	 * @throws AppBaseException
	 */
	void editarProyecto(ProyectoDTO proyectoDTO) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 28/01/2014
	 * @param idParametro
	 * @return
	 * @throws AppBaseException
	 */
	ProyectoDTO getProyecto(Integer idProyecto) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idCiudad
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	List<ProyectoDTO> consultarProyectoPorEmpresa(Integer idEmpresa, Paginator page) throws AppBaseException;
	
	/**
	 * Procedimiento Encargado de actualizar los Usuarios por Proyecto
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param usuarios
	 * @param idProyecto
	 * @param login
	 * @throws Exception
	 */
	void actualizarUsuariosPorProyecto(List<String> usuarios, String idProyecto, String login) throws AppBaseException;
}
