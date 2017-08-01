package com.premize.sgp.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import com.premize.sgp.dto.parametros.CiudadDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.parametros.CiudadFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class CiudadController
 * @since 27/03/2014
 *
 */
@Controller
@RequestMapping(value = "/ciudades")
public class CiudadController extends AbstractBaseMessageUI {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UtilFacade utilFacade;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private CiudadFacade ciudadFacade;
	
	
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
		
		return new ModelAndView("/pages/forms/localizacion/ciudad.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getCiudades", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<CiudadDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<CiudadDTO> resultset = ciudadFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param ciudaddto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarCiudad(@RequestBody CiudadDTO ciudaddto) throws Exception {
		
		Set<ConstraintViolation<CiudadDTO>> errors = getValidator().validate(ciudaddto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				ciudaddto.setUsuarioCreacion(getLogin());
				ciudadFacade.guardarCiudad(ciudaddto);
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
	 * @param ciudaddto
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarCiudad(@RequestBody CiudadDTO ciudaddto) throws AppBaseException {
		Set<ConstraintViolation<CiudadDTO>> errors = getValidator().validate(ciudaddto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				ciudaddto.setUsuarioEdita(getLogin());
				ciudadFacade.editarCiudad(ciudaddto);
				res.setResult(ConstantesWeb.MSG_EDITAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				
			}
		} catch (Exception e) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
		
	}
	
	@RequestMapping(value = "getCiudad", method = RequestMethod.GET)
	public @ResponseBody
	String getCiudad(@RequestParam String id) throws AppBaseException {
		
		Integer idCiudad = Integer.parseInt(id);
		
		CiudadDTO ciudad = ciudadFacade.getCiudad(idCiudad);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(ciudad);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<CiudadDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<CiudadDTO> failure : failures) {
			
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
	
}
