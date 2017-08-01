package com.premize.sgp.dao.gestion.pruebas;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalesHallazgoDTO;
import com.premize.sgp.dto.pruebas.indicadores.CalidadDocumentacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadFuncionalidadDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndEfectividadHallazgosReportadosDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndMejorasIdentificadasDTO;
import com.premize.sgp.dto.pruebas.indicadores.TiemposRespuestaGarantiaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class HallazgoDao
 * @since 24/02/2014
 */
public interface HallazgoDao extends Dao<SgpHallazgo, Integer> {
	
	public final String NO_PINTE_GRAFICO=null;
	
	/**
	 * Metodo encargado de listar los hallazgos por caso de Prueba
	 * 
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 22/02/2014
	 * @param idCaso
	 * @return
	 * @throws AppBaseException
	 */
	List<SgpHallazgo> getHallazgosPorCasoPrueba(Integer idCaso) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException 
	 */
	ResultSet<ConsultaHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException, ParseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idHallazgo
	 * @return
	 * @throws AppBaseException 
	 */
	List<ParametroDTO> getEstadosHallazgo(Integer idHallazgo) throws AppBaseException;

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 14/04/2014
 * @param idHallazgo
 * @param fechaTo 
 * @param fechaFrom 
 * @return
 * @throws AppBaseException
 * @throws ParseException 
 */
	List<ReporteHallazgoDTO> getHallazgosPorSeveridad(Integer idHallazgo, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException;
	
/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 14/04/2014
 * @param idHallazgo
 * @param fechaTo 
 * @param fechaFrom 
 * @return
 * @throws AppBaseException
 * @throws ParseException 
 */
	List<ReporteHallazgoDTO> getHallazgosPorMapaPruebas(Integer idHallazgo, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException;
	

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 14/04/2014
	 * @param idProyecto
	 * @param fechaTo 
	 * @param fechaFrom 
	 * @return
	 * @throws AppBaseException 
	 * @throws ParseException 
	 */
	List<TotalesHallazgoDTO> getTotalesHallazgoProyecto(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 26/06/2014
	* @param idProyecto
	* @param fechaFrom
	* @param fechaTo
	* @return
	* @throws AppBaseException
	* @throws ParseException
	 */
	List<TotalHallazgoDTO>getUsuarios_Hallazgos(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo)throws AppBaseException, ParseException;
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/04/2014
	 * @param filtros
	 * @return
	 * @throws AppBaseException
	 */
	List<ConsultaHallazgoDTO> consultarHallazgos(
			Map<String, Object> filtros)throws AppBaseException, NumberFormatException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 */
	List<CalidadDocumentacionDTO> consultarIndicadorCalidadDocumentacion(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 */
	List<IndCalidadFuncionalidadDTO> consultarIndicadorFuncionalidad(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 8/07/2014
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @param tipoHallazgo
	 * @return
	 * @throws ParseException 
	 * @throws AppBaseException 
	 */
	List<ReporteHallazgoDTO> getHallazgosPorArtefacto(Integer idProyecto,
			String fechaFrom, String fechaTo, String tipoHallazgo) throws AppBaseException, ParseException;


	/**
	 * consulta para pintar la tabla de colores de la bandeja 
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 11/07/2014
	* @param usuario
	* @return
	* @throws AppBaseException
	 */
	List<ConsultaHallazgoDTO> getSeveridadColorHallazgo(String usuario)
			throws AppBaseException;
	
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
	IndEfectividadHallazgosReportadosDTO consultarIndicadorEfectividadHallazgosReportados(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 */
	List<IndMejorasIdentificadasDTO> consultarMejorasIndentificadas(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 */
	List<TiemposRespuestaGarantiaDTO> consultarTiemposRespuestaGarantia(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/07/2014
	 * @param proyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @param listEstados
	 * @param listCausas
	 * @return
	 * @throws ParseException 
	 * @throws AppBaseException 
	 */
	List<ReporteHallazgoDTO> getHallazgosPorRecurso(String proyecto,
			String fechaFrom, String fechaTo, List<Integer> listEstados,
			List<Integer> listCausas) throws AppBaseException, ParseException;
}
