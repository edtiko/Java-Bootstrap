/**
 * 
 */
package com.premize.sgp.service.pruebas;

import com.premize.sgp.dto.ResultSet;

import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.Filter;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroService
 * @since 15/01/2014
 *
 */
public interface ProyectoService extends EntityService<SgpProyecto, Integer> {
	
	int SIZE_PROYECTO_PREFIJO = 3;
	
    /**
     * 
     * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
     * @since 15/01/2014
     * @return
     * @throws AppBaseException 
     */
	List<ProyectoDTO> getListProyectos() throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param proyecto
	 * @throws AppBaseException
	 */
	void guardarProyecto(ProyectoDTO proyecto) throws AppBaseException;

	List<SgpProyecto> findAllProyectoByFilter(Filter[] filters,
			Paginator paginator);
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<ProyectoDTO> getRecords(PagingCriteria criteria,Boolean mostraActivoInactivo) throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param proyecto
	 * @throws AppBaseException
	 */
	void editarProyecto(ProyectoDTO proyecto) throws AppBaseException;

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 28/01/2014
	 * @param idParametro
	 * @return
	 * @throws AppBaseException 
	 */
	ProyectoDTO getProyecto(Integer idProyecto) throws AppBaseException;

	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 4/02/2014
	* @param idPais
	* @param page
	* @return
	* @throws AppBaseException
	 */
	public List<ProyectoDTO> consultarProyectoPorEmpresa(Integer idEmpresa,
			Paginator page) throws AppBaseException;
	
	/**
	 * Procedimiento Encargado de actualizar los Usuarios por Proyecto
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param usuarios
	 * @param idProyecto
	 * @param login
	 * @throws Exception
	 */
	void actualizarUsuariosPorProyecto(List<String> usuarios,String idProyecto, String login) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description 
	 * @param proyectoNombre
	 * @return
	 * @throws AppBaseException
	 */
	String construirPrefijoProyecto(String proyectoNombre) throws AppBaseException;
}
