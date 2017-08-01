package com.premize.sgp.dao.gestion.pruebas;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-dao-hbxml
 * @class SgpMapaArtefactoDao
 * @date 5/02/2014
 */

public interface MapaArtefactoDao extends Dao<SgpMapaArtefacto, Integer> {
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idProyecto
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<MapaArtefactoDTO> consultarMapaPruebasPorProyecto(Integer idMapaPruebas, Paginator page)
			throws AppBaseException;
	
	/**
	 * Procedimiento encargado de Eliminar todos los artefactos relacionados a un mapa
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @param idMapa
	 * @throws Exception
	 */
	void desasociarArtefactosMapa(Integer idMapa, String login) throws AppBaseException;
	
	/**
	 * Procedimiento encargado de asociar Artefactos a mapa de Pruebas
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @param artefactos
	 * @throws AppBaseException
	 */
	void asociarArtefactosPorMapa(List<SgpMapaArtefacto> artefactos) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<MapaArtefactoDTO> getMapaArtefactos(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * Retorna una lista de artefactos activos por id de MapaPruebas
	 * 
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @param idMapaPruebas
	 * @param page
	 */
	public List<MapaArtefactoDTO> getArtefactosActivosMapaDePruebas(Integer idMapaPruebas, Paginator page)
			throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 13/03/2014
	 * @param idArtefacto
	 * @return
	 * @throws AppBaseException
	 */
	public List<SgpMapaArtefacto> getMapaArtefactoByArtefacto(Integer idArtefacto) throws AppBaseException;
}
