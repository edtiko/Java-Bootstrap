package com.premize.sgp.dao.gestion.pruebas;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ReporteMapaPruebaDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndReprocesoConstruccionPruebasDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;

public interface MapaPruebasDao extends Dao<SgpMapaPrueba, Integer> {
	
	// NQ = Named Query
	String NQ_REPORTE_EJECUCION_AVANCE_MAPAS_FULL = "reporteAvanceEjecucionMapasFull";
	String NQ_REPORTE_EJECUCION_AVANCE_MAPAS = "reporteAvanceEjecucionMapas";
	String NQ_REPORTE_EJECUCION_AVANCE_MAPAS_FECHA_CORTE = "reporteAvanceEjecucionMapasFechaCorte";
	String NQ_REPORTE_EJECUCION_AVANCE_MAPAS_GRILLA = "reporteAvanceEjecucionMapasGrilla";
	String NQ_SUMATORIA_REPORTE_EJECUCION_AVANCE_MAPAS = "sumatoriaReporteAvanceEjecucionMapas";
	String NQ_REPORTE_MAPA_PRUEBAS = "consultarCasosPruebaHallazgos";
	String NQ_REPORTE_MAPA_PRUEBAS_FILTROS = "consultarMapaPruebasReporte";
	String NQ_CONSULTA_INDI_REPROCESO_CONSTRUCCION_PRUEBAS = "consultarIndicadorReprocesoConstruccionPruebas";
	
	// Parametros query HQL,NamedQuery:
	String P_ID_MAPA_PRUEBA = "idMapaPrueba";
	String P_ID_PROYECTO = "idProyecto";
	String P_FECHA_FROM = "fechaFrom";
	String P_FECHA_TO = "fechaTo";
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idProyecto
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<MapaPruebasDTO> consultarMapaPruebasPorProyecto(Integer idProyecto, Paginator page)
			throws AppBaseException;
	
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
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/04/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<MapaPruebasDTO> getRecordsEjecucion(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 27/06/2014
	 * @description 
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	ResultSet<MapaPruebasDTO> getRecordsAvanceEjecucion(PagingCriteria criteria) throws AppBaseException, ParseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014
	 * @description 
	 * @param idMapaPrueba
	 * @return
	 * @throws AppBaseException
	 */
	List<ReporteMapaPruebaDTO> consultarCasosDePrueba(Integer idMapaPrueba) throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 14/04/2014
	 * @param idProyecto
	 * @param fechaTo 
	 * @param fechaFrom 
	 * @param tipoHallazgo 
	 * @return
	 * @throws ParseException 
	 */
	public MapaPruebasDTO getTotalesMapaPruebaProyecto(Integer idProyecto, String fechaFrom, String fechaTo, String tipoHallazgo)throws AppBaseException, ParseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014
	 * @description 
	 * @param idMapaPrueba
	 * @return
	 * @throws AppBaseException
	 */
	List<ReporteMapaPruebaDTO> consultarCasosPruebaHallazgos(Integer idMapaPrueba) throws AppBaseException;
	
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
	List<MapaPruebasDTO> consultarAvanceEjecucionMapas(Map<String,Object> parametros) 
			throws AppBaseException, ParseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description 
	 * @param filtros
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	List<ReporteMapaPruebaDTO> consultarMapaPruebasReporte(Map<String,Object> filtros) throws AppBaseException, ParseException;
	
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
	 */
	IndReprocesoConstruccionPruebasDTO consultarIndicadorReprocesoConstruccionPruebas(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException;
	
}
