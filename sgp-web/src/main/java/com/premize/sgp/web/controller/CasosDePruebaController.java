/**
 * 
 */
package com.premize.sgp.web.controller;

import java.io.IOException;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.constantes.ConstantesWeb;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.TableParam;
import com.premize.sgp.dto.WebResultSet;
import com.premize.sgp.dto.pruebas.CargueCasoPruebaDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.facade.pruebas.CasosDePruebaFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.utils.FilePmz;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;
import com.premize.sgp.web.utils.FileConverter;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a Soto</a>
 * @project sgp-web
 * @class CasosDePruebaController
 * @since 27/01/2014
 */
@Controller
@RequestMapping(value = "/casodepruebas")
public class CasosDePruebaController extends AbstractBaseMessageUI {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private CasosDePruebaFacade casosDePruebaFacade;
	
	@Autowired
	private ParametroFacade parametroFacade;

	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("idMapaPrueba", request.getParameter("idMapaPrueba"));
		return new ModelAndView("/pages/forms/pruebas/casosdeprueba.jsp", model);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getcasosdeprueba", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<CasoDePruebaDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<CasoDePruebaDTO> resultset = casosDePruebaFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param casoDePruebaDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarParametro(@RequestBody CasoDePruebaDTO casoDePruebaDTO) throws Exception {
		
		Set<ConstraintViolation<CasoDePruebaDTO>> errors = getValidator().validate(casoDePruebaDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				
				casoDePruebaDTO.setUsuarioCreacion(getLogin());
				casosDePruebaFacade.guardarCasosDePrueba(casoDePruebaDTO);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			}
		} catch (Exception e) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		return res;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param casoDePruebaDTO
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarParametro(@RequestBody CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException {
		Set<ConstraintViolation<CasoDePruebaDTO>> errors = getValidator().validate(casoDePruebaDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		if (!errors.isEmpty()) {
			validationMessages = validationMessages(errors);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(validationMessages);
		} else {
			try {
				casoDePruebaDTO.setUsuarioEdita(getLogin());
				casosDePruebaFacade.editarCasoDePrueba(casoDePruebaDTO);
				res.setResult(ConstantesWeb.MSG_EDITAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				
			} catch (Exception e) {
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(e.getMessage());
			}
			
		}
		return res;
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getCasoPrueba", method = RequestMethod.GET)
	public @ResponseBody
	String getCasoPrueba(@RequestParam String id) throws AppBaseException {
		
		Integer idCasoDePrueba = Integer.parseInt(id);
		
		CasoDePruebaDTO casoDePrueba = casosDePruebaFacade.getCasoDePrueba(idCasoDePrueba);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(casoDePrueba);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<CasoDePruebaDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<CasoDePruebaDTO> failure : failures) {
			
			failureMessages
					.add("<strong>" + failure.getPropertyPath().toString() + "</strong>:" + failure.getMessage());
		}
		return failureMessages;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 20/02/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "eliminar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse eliminarCasoPrueba(@RequestParam String id) throws AppBaseException {
		JsonResponse res = new JsonResponse();
		try {
			casosDePruebaFacade.eliminarCasoDePrueba(new Integer(id));
			res.setResult(ConstantesWeb.MSG_ELIMINAR);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
		} catch (AppBaseException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		
		return res;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param casoDePruebaDTO
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "ejecutarCaso", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse ejecutarCaso(@RequestBody CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException {
		JsonResponse res = new JsonResponse();
		try {
			casoDePruebaDTO.setUsuarioEjecuta(getLogin());
			casosDePruebaFacade.ejecutarCasoPrueba(casoDePruebaDTO);
			res.setResult(ConstantesWeb.MSG_EJECUTARCASO);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
		} catch (AppBaseException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		
		return res;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		CargueCasoPruebaDTO cargueArchivo = null;
		String mensaje = "";
		
		try {	
			String idMapaPruebas = request.getParameter("idMapaPruebas");
			FilePmz file2 = FileConverter.upload(request);
			
			if (idMapaPruebas == null || idMapaPruebas.toString().isEmpty()) {
				mensaje = "No fue posible obtener el mapa de pruebas";
			}
			
			if (mensaje == "") {
				if (file2.getType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
						|| file2.getType().equals("application/vnd.ms-excel")) {
					cargueArchivo = casosDePruebaFacade
							.cargarCasosPruebas(file2.getInputStream(), new Integer(idMapaPruebas), getLogin());
					
					if (cargueArchivo.getMsgFalloCargue() == null) {
						
						model.put("lists", cargueArchivo.getListaDatosCargadosFallidos());
						model.put("registrosLeidos", cargueArchivo.getRegistrosLeidos());
						model.put("registrosFallo", cargueArchivo.getRegistrosConFallos());
						model.put("registrosExito", cargueArchivo.getRegistrosCargadosBien());
						
						if (cargueArchivo.getRegistrosConFallos() > 0)
							mensaje = "Cargue falló ";
						else
							mensaje = "Cargue realizado con éxito";
						
					} else {
						mensaje = "Cargue falló: " + cargueArchivo.getMsgFalloCargue();
					}
					
				} else {
					mensaje = "Formato de archivo no válido, debe ser .xls ó .xlsx";
				}
			}
			
		} catch (Exception e) {
			mensaje = "No se logró cargar el archivo. Verifique si el archivo esta seleccionado";
		}
		
		model.put("mensaje", mensaje);
		return new ModelAndView("/pages/popup/pruebas/logErroresImportarExcel.jsp", model);
	}
	
	public Validator getValidator() {
		return validator;
	}
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
}
