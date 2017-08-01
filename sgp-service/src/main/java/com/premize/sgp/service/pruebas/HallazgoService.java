/**
 * 
 */
package com.premize.sgp.service.pruebas;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.exception.MailServiceException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.AnexoHallazgoDTO;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.dto.pruebas.HallazgoDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalesHallazgoDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadDocumentacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadFuncionalidadDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndEfectividadHallazgosReportadosDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndMejorasIdentificadasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndTiemposRespuestaGarantiaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.utils.FilePmz;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class HallazgoService
 * @since 24/02/2014
 * 
 */
public interface HallazgoService extends EntityService<SgpHallazgo, Integer> {

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException 
	 */
	ResultSet<ConsultaHallazgoDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException, ParseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @param flujoHallazgo
	 * @param sgpHallazgo
	 * @return
	 * @throws AppBaseException
	 * @throws Exception
	 */
	void guardarHallazgo(FlujoHallazgoDTO flujoHallazgo, SgpHallazgo sgpHallazgo)
			throws AppBaseException,ParseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @param idHallazgo
	 * @return
	 * @throws AppBaseException
	 */
	HallazgoDTO getHallazgo(Integer idHallazgo,String login) throws AppBaseException;

	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/02/2014
	 * @param idCasoPrueba
	 * @return
	 */
	List<HallazgoDTO> getHallazgosPorCaso(Integer idCasoPrueba)
			throws AppBaseException;

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 6/03/2014
	 * @param flujoHallazgo
	 * @param sgpHallazgo
	 * @param file
	 */
	void guardarAnexoHallazgo(SgpHallazgo sgpHallazgo, FilePmz file) throws AppBaseException, IOException;

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param sgpFlujoHallazgo
	 * @throws MailServiceException
	 * @throws AppBaseException
	 */
	void enviarEmailHallazgo(SgpFlujoHallazgo sgpFlujoHallazgo)
			throws MailServiceException, AppBaseException;

	/**
	 * 
	 * @param idHallazgo
	 * @return
	 * @throws AppBaseException 
	 */
	List<ParametroDTO> getEstadosHallazgo(Integer idHallazgo) throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param hallazgodto
	 * @param file
	 * @throws AppBaseException 
	 */
	void guardarAnexo(HallazgoDTO hallazgodto, FilePmz file) throws AppBaseException, IOException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param idAnexo
	 * @return
	 * @throws AppBaseException 
	 */
	AnexoHallazgoDTO getAnexo(Integer idAnexo) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/06/2014
	 * @description 
	 * @param idAnexo
	 * @return
	 * @throws AppBaseException
	 */
	File getArchivoAnexo(Integer idAnexo) throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/03/2014
	 * @param hallazgoDTO
	 * @throws AppBaseException
	 */
	void modificarDatosHallazgo(HallazgoDTO hallazgoDTO) throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 14/04/2014
	 * @param idProyecto
	 * @param fechaTo 
	 * @param fechaFrom 
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException 
	 */
		List<ReporteHallazgoDTO> getHallazgosPorSeveridad(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException;
	
		
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 14/04/2014
	 * @param idProyecto
	 * @param fechaTo 
	 * @param fechaFrom 
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException 
	 */
		List<ReporteHallazgoDTO> getHallazgosPorMapaPruebas(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException;


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
	 * @param idTipoHallazgo 
	* @return
	* @throws AppBaseException
	* @throws ParseException
	 */
	List<TotalHallazgoDTO> getUsuarios_Hallazgos(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo)throws AppBaseException, ParseException;
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/04/2014
	 * @param filtros
	 * @return
	 * @throws AppBaseException
	 * @throws IOException
	 */
	Workbook consultaHallazgosExcel(Map<String, Object> filtros)throws AppBaseException, IOException;
	
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
	 * @throws ParseException
	 */
	Map<Integer, IndCalidadDocumentacionDTO> consultarIndicadorCalidadDocumentacion(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException;
	
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
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	List<IndCalidadFuncionalidadDTO> getIndicadorCalidadFuncionalidad(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException;

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
	IndEfectividadHallazgosReportadosDTO getIndicadorEfectividadHallazgosReportados(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException;

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

	List<ConsultaHallazgoDTO> getSeveridadColorHallazgo(String usuario)
			throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	List<IndMejorasIdentificadasDTO> getMejorasIndentifcadas(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	IndTiemposRespuestaGarantiaDTO getIndicadorTiemposRespuestaGarantia(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException;

}
