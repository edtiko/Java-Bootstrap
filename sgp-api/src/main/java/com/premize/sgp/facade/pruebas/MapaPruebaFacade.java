/**
 * 
 */
package com.premize.sgp.facade.pruebas;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndReprocesoConstruccionPruebasDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-api
 * @class MapaArtefactoFacade
 * @since 22/01/2014
 */
public interface MapaPruebaFacade extends EntityFacade<SgpMapaPrueba, Integer> {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param mapaPruebasDTO
	 * @throws AppBaseException
	 */
	void guardarMapaPrueba(MapaPruebasDTO mapaPruebasDTO) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idEmpresa
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	List<MapaPruebasDTO> consultarMapaPruebasPorProyecto(Integer idProyecto, Paginator page) throws AppBaseException;
	
	/**
	 * Procedimiento Encargado de actualizar los artefactos relacionados a un Mapa
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @param artefactos
	 * @param idMapa
	 * @param login
	 * @throws Exception
	 */
	void actualizarArtefactosPorMapa(List<String> artefactos, String idMapa, String login) throws Exception;
	
	/**
	 * Metodo encargado de Obtener listado de Mapa de Pruebas
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<MapaPruebasDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * Obtiene un Mapa de Prueba DTO
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @param idMapa
	 * @return
	 * @throws AppBaseException
	 */
	MapaPruebasDTO getMapaDTO(Integer idMapa) throws AppBaseException;
	
	/**
	 * Procedimiento encargado de Editar un mapa de Prueba
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @throws AppBaseException
	 */
	void editarMapaPrueba(MapaPruebasDTO mapaDTO) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 8/03/2014
	 * @param integer
	 */
	void eliminarArtefactoMapa(Integer integer) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/04/2014
	 * @description 
	 * @param idMapaPrueba
	 * @return
	 * @throws AppBaseException
	 * @throws IOException
	 */
	Map<String,Object> generarMatrizContruccionPruebasExcel(Integer idMapaPrueba) throws AppBaseException, IOException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 14/04/2014
	 * @param idProyecto
	 * @param fechaTo 
	 * @param fechaFrom 
	 * @param tipoHallazgo 
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException 
	 */
	MapaPruebasDTO getTotalesMapaPruebaProyecto(Integer idProyecto, String fechaFrom, String fechaTo, String tipoHallazgo) throws AppBaseException, ParseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description 
	 * @param parametros
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	Map<String,Object> generarReporteAvanceEjecucionMapas(Map<String,Object> parametros) 
			throws AppBaseException, ParseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/04/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	ResultSet<MapaPruebasDTO> getRecordsEjecucion(PagingCriteria criteria)
			throws AppBaseException, ParseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description 
	 * @param filtros
	 * @param directorio
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws IOException
	 */
	Map<String, Object> generarMapasZip(Map<String, Object> filtros, String directorio) 
			throws AppBaseException, ParseException, IOException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description 
	 * @param directory
	 * @throws AppBaseException
	 * @throws IOException
	 */
	void deleteZipFolder(String directory) throws AppBaseException, IOException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	IndReprocesoConstruccionPruebasDTO getIndicadorReprocesoConstruccionPruebas(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException;
}
