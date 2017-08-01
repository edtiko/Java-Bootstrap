package com.premize.sgp.web.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.constantes.ConstantesWeb;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.TableParam;
import com.premize.sgp.dto.WebResultSet;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.indicadores.HallazgoPorGarantiaDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadDocumentacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadFuncionalidadDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndEfectividadHallazgosReportadosDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndMejorasIdentificadasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndReprocesoConstruccionPruebasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndTiemposRespuestaGarantiaDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndicadoresProyectoDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.parametros.IndicadoresFacade;
import com.premize.sgp.facade.pruebas.HallazgoFacade;
import com.premize.sgp.facade.pruebas.MapaPruebaFacade;
import com.premize.sgp.facade.pruebas.TipoSeveridadFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.utils.IndicadoresUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-web
 * @class IndicadoresController
 * @description
 * @date 7/07/2014
 */
@Controller
@RequestMapping(value = "/indicadores")
public class IndicadoresController extends AbstractBaseMessageUI {
	
	private static final Logger LOG = LoggerFactory.getLogger(IndicadoresController.class);

	private static final Integer INDICADOR_HALLAZGO_GARANTIA = 9;
	
	@Autowired
	private UtilFacade utilFacade;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private IndicadoresFacade indicadoresFacade;
	
	@Autowired
	private HallazgoFacade hallazgoFacade;
	
	@Autowired
	private TipoSeveridadFacade tipoSeveridadFacade;
	
	@Autowired
	private MapaPruebaFacade mapaPruebaFacade;

	
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 10/07/2014
* @param request
* @param response
* @return
* @throws ServletException
* @throws IOException
 */
	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return new ModelAndView("/pages/forms/admin/indicadores.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 16/07/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/indicadores_calidad")
	public ModelAndView viewIndicadores(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return new ModelAndView("/pages/forms/pruebas/indicadoresCalidad.jsp");
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "/getIndicadores", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<IndicadoresDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<IndicadoresDTO> resultset = indicadoresFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	@RequestMapping(value = "getIndicador", method = RequestMethod.GET)
	public @ResponseBody

	String getIndicador(@RequestParam String id) throws AppBaseException {
		
		Integer idIndicador= Integer.parseInt(id);
		
		IndicadoresDTO indicadoresDTO = indicadoresFacade.getIndicador(idIndicador);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(indicadoresDTO);
		
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param indicadoresDTO
	* @return
	* @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarIndicador(@RequestBody IndicadoresDTO indicadoresDTO) throws Exception {
		
		Set<ConstraintViolation<IndicadoresDTO>> errors = getValidator().validate(indicadoresDTO);
		List<String> validationMessages = new ArrayList<String>();
		
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				indicadoresDTO.setUsuarioCreacion(getLogin());
				indicadoresFacade.guardarIndicador(indicadoresDTO);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			}
		} catch (Exception e) {
			LOG.error("error",e);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param indicadoresDTO
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarIndicador(@RequestBody IndicadoresDTO indicadoresDTO) throws AppBaseException {
		Set<ConstraintViolation<IndicadoresDTO>> errors = getValidator().validate(indicadoresDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				
				indicadoresDTO.setUsuarioEdita(getLogin());

				indicadoresFacade.editarIndicador(indicadoresDTO);
				res.setResult(ConstantesWeb.MSG_EDITAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				
			}
		} catch (Exception e) {
			LOG.error("error",e);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
		
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param failures
	* @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<IndicadoresDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<IndicadoresDTO> failure : failures) {
			
			failureMessages
					.add("<strong>" + failure.getPropertyPath().toString() + "</strong>:" + failure.getMessage());
		}
		return failureMessages;
	}
	
	public Validator getValidator() {
		return validator;
	}
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 7/07/2014
	 * @description 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "getIndicadoresCalidad", method = RequestMethod.POST)
	public ModelAndView cuerpoIndicadoresCalidadProyectos(HttpServletRequest request) throws ServletException {
		Map<String, Object> model = new HashMap<String,Object>();
		DecimalFormat df = new DecimalFormat("0.00");
		String titulo = "Error al Generar Indicadores";
		String message = "El sistema ha generado el siguiente error: ";
		try {
			String idProyecto = request.getParameter("idProyecto");
			String fechaFrom = request.getParameter("fechaFrom");
			String fechaTo = request.getParameter("fechaTo");
			String estado=request.getParameter("estados");
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			if(null==estado){
				estado="[Ninguno]";
			}
			List<String> listEstados = prettyGson.fromJson(estado, new TypeToken<List<String>>() {
			}.getType());
			
			
			String estadosTotal="[Ninguno]";
			List<String> listEstadosTotal = prettyGson.fromJson(estadosTotal,new TypeToken<List<String>>() {
			}.getType());
			
			HallazgoPorGarantiaDTO hallazgoGarantia = indicadoresFacade
					.hallazgosPorGarantia(Integer.parseInt(idProyecto),fechaFrom,fechaTo,listEstadosTotal);

			HallazgoPorGarantiaDTO hallazgoGarantiaTotal = indicadoresFacade
					.hallazgosPorGarantia(Integer.parseInt(idProyecto),fechaFrom,fechaTo,listEstados);
			
			
			IndicadoresProyectoDTO indicadores = new IndicadoresProyectoDTO();
			Map<Integer, IndCalidadDocumentacionDTO> indiCalidadDoc = hallazgoFacade
					.consultarIndicadorCalidadDocumentacion(idProyecto, fechaFrom, fechaTo);
			indicadores.setIndicadorCalidadDocumentacion(indiCalidadDoc);
			
			List<IndCalidadFuncionalidadDTO> indicadorCalidadFuncionalidad = hallazgoFacade
					.getIndicadorCalidadFuncionalidad(idProyecto, fechaFrom, fechaTo);
			indicadores.setIndicadorCalidadFuncionalidad(indicadorCalidadFuncionalidad);
			
			IndReprocesoConstruccionPruebasDTO indiReprocesoConstruccionPruebas = mapaPruebaFacade
					.getIndicadorReprocesoConstruccionPruebas(idProyecto, fechaFrom, fechaTo);
			indicadores.setIndicadorReprocesoConstruccionPruebas(indiReprocesoConstruccionPruebas);
			
			IndEfectividadHallazgosReportadosDTO indiEfectividadHallazgosReportados = hallazgoFacade
					.getIndicadorEfectividadHallazgosReportados(idProyecto, fechaFrom, fechaTo);
			indicadores.setIndicadorEfectividadHallazgosReportados(indiEfectividadHallazgosReportados);
			
			List<IndMejorasIdentificadasDTO> indiMejorasIdentificadas = hallazgoFacade
					.getMejorasIndentifcadas(idProyecto, fechaFrom, fechaTo);
			indicadores.setIndicadorMejorasIdentificadas(indiMejorasIdentificadas);
			
			IndTiemposRespuestaGarantiaDTO indiTiemposRespuestaGarantia = hallazgoFacade
					.getIndicadorTiemposRespuestaGarantia(idProyecto, fechaFrom, fechaTo);
			indicadores.setIndicadorTiemposRespuestaGarantia(indiTiemposRespuestaGarantia);
			
			
			List<String> listTipoSeveridadAnalisis = tipoSeveridadFacade.getNombreTiposSeveridad();
		
			IndicadoresDTO indicadoresDTO=indicadoresFacade.getIndicador(INDICADOR_HALLAZGO_GARANTIA);
			hallazgoGarantia.setFormula(indicadoresDTO.getFormula());
			hallazgoGarantia.setValorMaximo(indicadoresDTO.getValorMaximo());
			hallazgoGarantia.setValorMinimo(indicadoresDTO.getValorMinimo());
			
			Float valorIndicador=(hallazgoGarantia.getHallazgosReportados().floatValue() / hallazgoGarantia.getTotalArtefacto().floatValue());

			hallazgoGarantia.setIndicadorString(df.format(valorIndicador) + "%");
			
			String semaforo=IndicadoresUtils.getSemaforoImageComparisonBetween(indicadoresDTO, valorIndicador.doubleValue());
	
			hallazgoGarantia.setSemaforo(semaforo);
			
			
		
			hallazgoGarantia.setIndicador(valorIndicador);
			model.put("indicadores", indicadores);
			model.put("tipoSeveridad", listTipoSeveridadAnalisis);
			model.put("hallazgoGarantia", hallazgoGarantia);
			
			return new ModelAndView("/pages/forms/pruebas/indicadoresCalidadCuerpo.jsp", model);
		} catch (AppBaseException ex) {//Excepcion controlada
			LOG.error(ex.getMessage(), ex);
			String customMessage = getAppBaseExceptionMessage(ex);
			return getExceptionPageContent(titulo, titulo, customMessage, false);
		} catch (ParseException ex) {
			LOG.error(ex.getMessage(), ex);
			return getExceptionPageContent(titulo, titulo, message + ex.getMessage(), false);
		} catch (NumberFormatException ex) {
			LOG.error(ex.getMessage(), ex);
			return getExceptionPageContent(titulo, titulo, message + ex.getMessage(), false);
		}
	}
	

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/07/2014
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@RequestMapping(value = "getHallazgoPorRecurso", method = RequestMethod.POST)
	public ModelAndView getHallazgoPorRecurso(HttpServletRequest request) throws ServletException, AppBaseException, ParseException {
		Map<String, Object> model = new HashMap<String,Object>();
		Double total=0D;
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		String totalPorcentaje;
		String titulo = "Error al Generar el reporte";
		String message = "El sistema ha generado el siguiente error: ";
		try{
		String proyecto = request.getParameter("idProyecto");
		String fechaFrom = request.getParameter("fechaFrom");
		String fechaTo = request.getParameter("fechaTo");
		String estados = request.getParameter("estados");
		String causas = request.getParameter("causas").equals("")?"0":request.getParameter("causas");
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		List<Integer> listEstados = prettyGson.fromJson(estados, new TypeToken<List<Integer>>() {
		}.getType());
		
		List<Integer> listCausas = prettyGson.fromJson(causas, new TypeToken<List<Integer>>() {
		}.getType());
		
		List<ReporteHallazgoDTO> result = hallazgoFacade.getHallazgosPorRecurso(proyecto, fechaFrom, fechaTo, listEstados, listCausas);

		for (ReporteHallazgoDTO reporteHallazgoDTO : result) {
			total+=reporteHallazgoDTO.getTotalIndicador();
			reporteHallazgoDTO.setTotalIndicador(total);
		}
		totalPorcentaje=decimalFormat.format(total * 100) + "%";
		
		for (ReporteHallazgoDTO reporteHallazgoDTO : result) {
			
			reporteHallazgoDTO.setTotalIndicadorPorcentaje(totalPorcentaje);
		}
		model.put("list", result);
		return new ModelAndView("/pages/forms/reportes/hallazgoPorRecurso.jsp", model);
		}
		catch (AppBaseException ex) {//Excepcion controlada
			LOG.error(ex.getMessage(), ex);
			String customMessage = getAppBaseExceptionMessage(ex);
			return getExceptionPageContent(titulo, titulo, customMessage, false);
		} catch (ParseException ex) {
			LOG.error(ex.getMessage(), ex);
			return getExceptionPageContent(titulo, titulo, message + ex.getMessage(), false);
		} catch (NumberFormatException ex) {
			LOG.error(ex.getMessage(), ex);
			return getExceptionPageContent(titulo, titulo, message + ex.getMessage(), false);
		}
	}
	
	
	
	@RequestMapping(value = "getHallazgoPorGarantia", method = RequestMethod.POST)
	public ModelAndView getHallazgoPorGarantia(HttpServletRequest request) throws ServletException, AppBaseException, ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		String titulo = "Error al Generar el reporte";
		String message = "El sistema ha generado el siguiente error: ";
		Float valorIndicador=0F;
		try {
			String proyecto = request.getParameter("idProyecto");
			String fechaFrom = request.getParameter("fechaFrom");
			String fechaTo = request.getParameter("fechaTo");
			String causasGeneracion = request.getParameter("causasGeneracion");

			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			if(null==causasGeneracion){
				causasGeneracion="[Ninguno]";
			}
			List<String> listCausaGeneracion = prettyGson.fromJson(causasGeneracion,new TypeToken<List<String>>() {
					}.getType());
			
			if(listCausaGeneracion.isEmpty()){
				listCausaGeneracion.add("0");
			}
			
			HallazgoPorGarantiaDTO hallazgoGarantia = indicadoresFacade
					.hallazgosPorGarantia(Integer.parseInt(proyecto),fechaFrom,fechaTo,listCausaGeneracion);


			
			IndicadoresDTO indicadoresDTO = indicadoresFacade.getIndicador(INDICADOR_HALLAZGO_GARANTIA);
			hallazgoGarantia.setFormula(indicadoresDTO.getFormula());
			hallazgoGarantia.setValorMaximo(indicadoresDTO.getValorMaximo());
			hallazgoGarantia.setValorMinimo(indicadoresDTO.getValorMinimo());

			if(hallazgoGarantia.getTotalArtefacto()>0){
				valorIndicador = (hallazgoGarantia.getHallazgosReportados()
						.floatValue() / hallazgoGarantia.getTotalArtefacto()
						.floatValue());
			}
			
			
			
			DecimalFormat df = new DecimalFormat("0.00");
			hallazgoGarantia
					.setIndicadorString(df.format(valorIndicador) + "%");

			String semaforo = IndicadoresUtils
					.getSemaforoImageComparisonBetween(indicadoresDTO,
							valorIndicador.doubleValue());

			hallazgoGarantia.setSemaforo(semaforo);

			hallazgoGarantia.setIndicador(valorIndicador);

			model.put("hallazgoGarantia", hallazgoGarantia);

			return new ModelAndView(
					"/pages/forms/reportes/hallazgoPorGarantia.jsp", model);
		} catch (AppBaseException ex) {// Excepcion controlada
			LOG.error(ex.getMessage(), ex);
			String customMessage = getAppBaseExceptionMessage(ex);
			return getExceptionPageContent(titulo, titulo, customMessage, false);
		} catch (NumberFormatException ex) {
			LOG.error(ex.getMessage(), ex);
			return getExceptionPageContent(titulo, titulo,
					message + ex.getMessage(), false);
		}
	}
}
