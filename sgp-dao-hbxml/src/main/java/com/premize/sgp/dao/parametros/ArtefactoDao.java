package com.premize.sgp.dao.parametros;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class ArtefactoDao
 * @since 27/03/2014
 *
 */
public interface ArtefactoDao extends Dao<SgpArtefacto, Integer> {
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<ArtefactoDTO> getRecords(PagingCriteria criteria) throws Exception;
	
	/**
	 * Metodo que Obtiene todos los Artefactos Por mapa de Prueba
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 8/02/2014
	 * @param idMapaPrueba
	 * @return
	 * @throws Exception
	 */
	List<ArtefactoDTO> obtenerArtefactosPorMapaPrueba(Integer mapaPrueba) throws Exception;
	
	/**
	 * Metodo que se encarga de Obtener los Artefactos que no estan asociados a determinado Mapa de Prueba En caso que
	 * no lleve el mapa de prueba trae todos los Artefactos asociados al proyecto
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @param idProyecto
	 * @param idMapaPrueba
	 * @return List<ArtefactoDTO>
	 * @throws Exception
	 */
	List<ArtefactoDTO> artefactosSinAsociarAMapa(Integer idProyecto, Integer idMapaPrueba) throws Exception;
	
	/**
	 *  Metodo que Obtiene todos los Artefactos Por proyecto
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 3/07/2014
	* @param mapaPrueba
	* @return
	* @throws Exception
	 */
	List<SgpArtefacto> obtenerArtefactosPorProyecto(Integer idProyecto) throws AppBaseException;
}
