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
import com.premize.sgp.dto.pruebas.CausaGeneracionDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.pruebas.CausaGeneracionFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-web
* @class CausaGeneracionController
* @date 17/06/2014
*
 */
@Controller
@RequestMapping(value = "/causaGeneracion")
public class CausaGeneracionController extends AbstractBaseMessageUI {
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
	private CausaGeneracionFacade causaGeneracionFacade;
	
	
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 17/06/2014
* @param request
* @param response
* @return
* @throws ServletException
* @throws IOException
 */
	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return new ModelAndView("/pages/forms/pruebas/causaGeneracion.jsp");
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "/getCausaGeneraciones", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<CausaGeneracionDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<CausaGeneracionDTO> resultset = causaGeneracionFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param causaGeneracionDTO
	* @return
	* @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarCausaGeneracion(@RequestBody CausaGeneracionDTO causaGeneracionDTO) throws Exception {
		
		Set<ConstraintViolation<CausaGeneracionDTO>> errors = getValidator().validate(causaGeneracionDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				causaGeneracionDTO.setUsuarioCreacion(getLogin());
				causaGeneracionFacade.guardarCausaGeneracion(causaGeneracionDTO);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			}
		} catch (Exception e) {
			logger.error(ConstantesWeb.ERROR_PROCESO,e);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
		
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param causaGeneracionDTO
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarCausaGeneracion(@RequestBody CausaGeneracionDTO causaGeneracionDTO) throws AppBaseException {
		Set<ConstraintViolation<CausaGeneracionDTO>> errors = getValidator().validate(causaGeneracionDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				causaGeneracionDTO.setUsuarioEdita(getLogin());
				causaGeneracionFacade.editarCausaGeneracion(causaGeneracionDTO);
				res.setResult(ConstantesWeb.MSG_EDITAR);
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
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getCausaGeneracion", method = RequestMethod.GET)
	public @ResponseBody
	String getCausaGeneracion(@RequestParam String id) throws AppBaseException {
		
		Integer idCausaGeneracion = Integer.parseInt(id);
		
		CausaGeneracionDTO causaGeneracionDTO = causaGeneracionFacade.getCausaGeneracion(idCausaGeneracion);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(causaGeneracionDTO);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<CausaGeneracionDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<CausaGeneracionDTO> failure : failures) {
			
			failureMessages
					.add("<strong>" + failure.getPropertyPath().toString() + "</strong>:" + failure.getMessage());
		}
		return failureMessages;
	}
	
}
