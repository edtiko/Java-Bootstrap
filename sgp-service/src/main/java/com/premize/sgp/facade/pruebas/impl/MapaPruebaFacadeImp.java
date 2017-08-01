/**
 * 
 */
package com.premize.sgp.facade.pruebas.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndReprocesoConstruccionPruebasDTO;
import com.premize.sgp.facade.pruebas.MapaPruebaFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.service.parametros.ArtefactoService;
import com.premize.sgp.service.pruebas.MapaPruebaService;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class MapaPruebaFacade
 * @since 22/01/2014
 */
@Service
public class MapaPruebaFacadeImp extends AbstractEntityFacade<SgpMapaPrueba, Integer> implements MapaPruebaFacade {
	
	@Autowired
	private MapaPruebaService mapaPruebaService;
	
	@Autowired
	private ArtefactoService artefactoService;
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @see com.premize.pmz.facade.AbstractEntityFacade#getEntityService()
	 */
	@Override
	public MapaPruebaService getEntityService() {
		return mapaPruebaService;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @see com.premize.sgp.facade.pruebas.MapaPruebaFacade#guardarMapaPrueba(com.premize.sgp.dto.pruebas.MapaPruebasDTO)
	 */
	@Override
	public void guardarMapaPrueba(MapaPruebasDTO mapaPruebasDTO) throws AppBaseException {
		
		mapaPruebaService.guardarMapaPrueba(mapaPruebasDTO);
		
	}
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idEmpresa
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	
	@Override
	public List<MapaPruebasDTO> consultarMapaPruebasPorProyecto(Integer idProyecto, Paginator page)
			throws AppBaseException {
		
		return getEntityService().consultarMapaPruebasPorProyecto(idProyecto, page);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @see com.premize.sgp.facade.pruebas.MapaPruebaFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<MapaPruebasDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return mapaPruebaService.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/04/2014
	 * @see com.premize.sgp.facade.pruebas.MapaPruebaFacade#getRecordsEjecucion(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<MapaPruebasDTO> getRecordsEjecucion(PagingCriteria criteria) 
			throws AppBaseException, ParseException {
		return mapaPruebaService.getRecordsEjecucion(criteria);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @see com.premize.sgp.facade.pruebas.MapaPruebaFacade#getMapaDTO(java.lang.Integer)
	 */
	@Override
	public MapaPruebasDTO getMapaDTO(Integer idMapa) throws AppBaseException {
		return mapaPruebaService.getMapaDTO(idMapa);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @see com.premize.sgp.facade.pruebas.MapaPruebaFacade#actualizarArtefactosPorMapa(java.util.List,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void actualizarArtefactosPorMapa(List<String> artefactos, String idMapa, String login) throws Exception {
		
		mapaPruebaService.actualizarArtefactosPorMapa(artefactos, idMapa, login);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @see com.premize.sgp.facade.pruebas.MapaPruebaFacade#editarMapaPrueba(com.premize.sgp.dto.pruebas.MapaPruebasDTO)
	 */
	@Override
	public void editarMapaPrueba(MapaPruebasDTO mapaDTO) throws AppBaseException {
		mapaPruebaService.editarMapaPrueba(mapaDTO);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 8/03/2014
	 * @see com.premize.sgp.facade.pruebas.MapaPruebaFacade#eliminarArtefactoMapa(java.lang.Integer)
	 */
	@Override
	public void eliminarArtefactoMapa(Integer id) throws AppBaseException {
		mapaPruebaService.eliminarArtefactoMapa(id);
		
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @since 14/04/2014
	 * @see com.premize.sgp.facade.pruebas.MapaPruebaFacade#getTotalesMapaPruebaProyecto(java.lang.Integer)
	 */
	@Override
	public MapaPruebasDTO getTotalesMapaPruebaProyecto(Integer idProyecto, String fechaFrom, String fechaTo, String tipoHallazgo)
			throws AppBaseException, ParseException {
		return mapaPruebaService.getTotalesMapaPruebaProyecto(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}
	
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
	@Override
	public Map<String,Object> generarMatrizContruccionPruebasExcel(Integer idMapaPrueba) throws AppBaseException, IOException {
		return mapaPruebaService.generarMatrizContruccionPruebasExcel(idMapaPrueba);
	}
	
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
	@Override
	public Map<String,Object> generarReporteAvanceEjecucionMapas(Map<String,Object> parametros) 
			throws AppBaseException, ParseException {
		return mapaPruebaService.generarReporteAvanceEjecucionMapas(parametros);
	}
	
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
	@Override
	public Map<String, Object> generarMapasZip(Map<String, Object> filtros, String directorio) 
			throws AppBaseException, ParseException, IOException {
		return mapaPruebaService.generarMapasZip(filtros, directorio);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description 
	 * @param directory
	 * @throws AppBaseException
	 * @throws IOException
	 */
	@Override
	public void deleteZipFolder(String directory) throws AppBaseException, IOException {
		 mapaPruebaService.deleteZipFolder(directory);
	}
	
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
	@Override
	public IndReprocesoConstruccionPruebasDTO getIndicadorReprocesoConstruccionPruebas(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException {
		return mapaPruebaService.getIndicadorReprocesoConstruccionPruebas(idProyecto, fechaFrom, fechaTo);
	}
	
}
