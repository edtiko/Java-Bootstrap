/**
 * 
 */
package com.premize.sgp.web.controller;

import java.io.IOException;

import com.premize.sgp.dto.ResultSet;

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
import com.premize.sgp.dto.TableParam;
import com.premize.sgp.dto.WebResultSet;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.parametros.TipoParametroFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class TipoParametroController
 * @since 29/01/2014
 */
@Controller
@RequestMapping(value = "/tipoParametros")
public class TipoParametroController extends AbstractBaseMessageUI {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UtilFacade utilFacade;
	
	@Autowired
	private Validator validator;
	
	public Validator getValidator() {
		return validator;
	}
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	@Autowired
	private TipoParametroFacade tipoParametroFacade;
	
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
		
		return new ModelAndView("/pages/forms/admin/tipoParametro.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getTiposParametro", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<TipoParametroDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<TipoParametroDTO> resultset = tipoParametroFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param tipoParametrodto
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarTipoParametro(@RequestBody TipoParametroDTO tipoParametrodto) throws AppBaseException {
		Set<ConstraintViolation<TipoParametroDTO>> errors = getValidator().validate(tipoParametrodto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				tipoParametrodto.setUsuarioCreacion(getLogin());
				tipoParametroFacade.guardarTipoParametro(tipoParametrodto);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
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
	 * @param tipoParametrodto
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarTipoParametro(@RequestBody TipoParametroDTO tipoParametrodto) throws AppBaseException {
		Set<ConstraintViolation<TipoParametroDTO>> errors = getValidator().validate(tipoParametrodto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				tipoParametrodto.setUsuarioEdita(getLogin());
				tipoParametroFacade.editarTipoParametro(tipoParametrodto);
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
	@RequestMapping(value = "getTipoParametro", method = RequestMethod.GET)
	public @ResponseBody
	String getTipoParametro(@RequestParam String id) throws AppBaseException {
		
		Integer idTipoParametro = Integer.parseInt(id);
		
		TipoParametroDTO tipoParametro = tipoParametroFacade.getTipoParametro(idTipoParametro);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(tipoParametro);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<TipoParametroDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<TipoParametroDTO> failure : failures) {
			
			failureMessages
					.add("<strong>" + failure.getPropertyPath().toString() + "</strong>:" + failure.getMessage());
		}
		return failureMessages;
	}
	
}
