/**
 * 
 */
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
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.ParametroService;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class ParametroController
 * @since 13/01/2014
 */
@Controller
@RequestMapping(value = "/parametros")
public class ParametroController extends AbstractBaseMessageUI {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UtilFacade utilFacade;
	
	@Autowired
	private Validator validator;
	
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
		
		return new ModelAndView("/pages/forms/admin/parametro.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getParametros", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<ParametroDTO> getDataTables(@TableParam PagingCriteria criteria)
			throws AppBaseException {
		ResultSet<ParametroDTO> resultset = parametroFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param parametrodto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody JsonResponse guardarParametro(@RequestBody ParametroDTO parametrodto) throws Exception {
		
		Set<ConstraintViolation<ParametroDTO>> errors = getValidator().validate(parametrodto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				SgpParametro parametro= parametroFacade.findByProperty(ParametroService.PARAMETRO_FIELD_CLAVE, parametrodto.getNombre());
				if(null!=parametro){
					res.setStatus(ConstantesWeb.ERROR_PROCESO);
					res.setResult("Parámetro ya existe");
				}else{
					parametrodto.setUsuarioCreacion(getLogin());
					parametroFacade.guardarParametro(parametrodto);
					res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
					res.setResult(ConstantesWeb.MSG_GUARDAR);
				}
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
	 * @param parametrodto
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarParametro(@RequestBody ParametroDTO parametrodto) throws AppBaseException {
		Set<ConstraintViolation<ParametroDTO>> errors = getValidator().validate(parametrodto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				parametrodto.setUsuarioEdita(getLogin());
				parametroFacade.editarParametro(parametrodto);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				res.setResult(ConstantesWeb.MSG_EDITAR);
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
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getParametro", method = RequestMethod.GET)
	public @ResponseBody
	String getParametro(@RequestParam String id) throws AppBaseException {
		
		Integer idParametro = Integer.parseInt(id);
		
		ParametroDTO parametro = parametroFacade.getParametro(idParametro);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(parametro);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<ParametroDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<ParametroDTO> failure : failures) {
			
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
