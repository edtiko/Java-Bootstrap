/**
 * 
 */
package com.premize.sgp.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.parametros.EmpresaFacade;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.utils.FilePmz;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;
import com.premize.sgp.web.utils.FileConverter;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class EmpresaController
 * @since 21/01/2014
 */
@Controller
@RequestMapping(value = "/empresas")
public class EmpresaController extends AbstractBaseMessageUI {
	
	 
	private static Logger log = LoggerFactory.getLogger(EmpresaController.class);
	private static final String PARAMETRO_FIELD_CLAVE="nombre";
	private static final String TIPOS_IMAGENES_PERMITIDAS_KEY="TIPOS_IMAGENES_PERMITIDAS";
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private UtilFacade utilFacade;
	
	@Autowired
	private EmpresaFacade empresaFacade;
	
	@Autowired
	private ParametroFacade parametroFacade;
	
	private FilePmz logo;
	
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
		
		Map<String, Object> Model = new HashMap<String, Object>();
		return new ModelAndView("/pages/forms/admin/empresa.jsp", "model", Model);
	}
	
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
	@RequestMapping(value = "list")
	public ModelAndView ListEmpresas(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		return new ModelAndView("/pages/popup/admin/listEmpresa.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param empresaDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	private @ResponseBody
	JsonResponse guardar(@RequestBody EmpresaDTO empresaDto,HttpServletRequest request) throws Exception {
		Set<ConstraintViolation<EmpresaDTO>> errors = getValidator().validate(empresaDto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		String anexoExtension ="";
		String rutalRealDespliegue=FileConverter.rutaRealDespliegue(request);
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				if(null!=logo){

					logo.setRuta(rutalRealDespliegue);
					anexoExtension= FilenameUtils.getExtension(logo.getName());
				}

				
				SgpParametro sgpParametro=parametroFacade.findByProperty(PARAMETRO_FIELD_CLAVE, TIPOS_IMAGENES_PERMITIDAS_KEY);
				if(!sgpParametro.getValor().contains(anexoExtension.toLowerCase())){
					res.setResult(ConstantesWeb.TIPO_ARCHIVO_NO_PERMITIDO);
					res.setStatus(ConstantesWeb.ERROR_PROCESO);
				}else{
				
				empresaDto.setUsuarioCreacion(getLogin());

				empresaFacade.guardar(empresaDto, logo);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				
				}
				}
			
		} catch (Exception e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}finally{
			logo = null;
		}
		
		return res;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param empresaDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "actualizar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse actualizar(@RequestBody EmpresaDTO empresaDto,HttpServletRequest request) throws Exception {
		Set<ConstraintViolation<EmpresaDTO>> errors = getValidator().validate(empresaDto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		String anexoExtension ="";
		String rutalRealDespliegue=FileConverter.rutaRealDespliegue(request);
		if (!errors.isEmpty()) {
			validationMessages = validationMessages(errors);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(validationMessages);
		} else {
			try {
				empresaDto.setUsuarioEdita(getLogin());
				if(null!=logo){

					logo.setRuta(rutalRealDespliegue);
					anexoExtension= FilenameUtils.getExtension(logo.getName());
				}
				
				SgpParametro sgpParametro=parametroFacade.findByProperty(PARAMETRO_FIELD_CLAVE, TIPOS_IMAGENES_PERMITIDAS_KEY);
				
				if(!sgpParametro.getValor().contains(anexoExtension.toLowerCase() )&& null!=logo){
					res.setResult(ConstantesWeb.TIPO_ARCHIVO_NO_PERMITIDO);
					res.setStatus(ConstantesWeb.ERROR_PROCESO);
				}else{
				empresaFacade.actualizar(empresaDto, logo);
				res.setResult(ConstantesWeb.MSG_EDITAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				}
			} catch (Exception e) {
				log.error("Excepcion en EmpresaController-actualizar", e);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(e.getMessage());
			}finally{
				logo = null;
			}
			
		}
		
		return res;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/obtenerEmpresas", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<EmpresaDTO> getDataTables(@TableParam PagingCriteria criteria) throws Exception {
		ResultSet<EmpresaDTO> resultset = empresaFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/obtenerEmpresa", method = RequestMethod.GET)
	public @ResponseBody
	String obtenerEmpresa(@RequestParam String id) throws Exception {
		
		Integer idEmpresa = Integer.parseInt(id);
		EmpresaDTO empresa = empresaFacade.obtenerEmpresa(idEmpresa);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(empresa);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		try {
			logo = FileConverter.upload(request);
			if (logo != null) {
				// we are using getTimeInMillis to avoid server cached image
				return "<img src='/sgp/empresas/get/" + Calendar.getInstance().getTimeInMillis()
						+ "' width='60' height='42'/>";
			}
		} catch (Exception e) {
			return "No se logro Cargar la Imagen";
		}
		
		return null;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @param response
	 * @param value
	 */
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable String value) throws IOException {
		response.setContentType(logo.getType());
		response.setContentLength(logo.getLength());
		FileCopyUtils.copy(logo.getBytes(), response.getOutputStream());
	}
	
	/**
	 * Metodo que recibe los errores y los añade a una lista
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<EmpresaDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<EmpresaDTO> failure : failures) {
			
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
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 11/06/2014
	* @return
	 */
	@RequestMapping(value = "getRestriccionesLogo", method = RequestMethod.GET)
	public @ResponseBody JsonResponse getRestriccionesArchivoAnexo() {
		JsonResponse res = new JsonResponse();
		try {
			Map<String, Object> restricciones = parametroFacade.getMapRestriccionesLogo();
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			res.setData(restricciones);
		} catch (AppBaseException ex) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(ex.getMessage());
		}
		return res;
	}

}
