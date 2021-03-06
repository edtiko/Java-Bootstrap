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
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.facade.pruebas.TipoHallazgoFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class PaisController
 * @since 27/03/2014
 *
 */
@Controller
@RequestMapping(value = "/tipoHallazgo")
public class TipoHallazgoController extends AbstractBaseMessageUI {
	protected final Log logger = LogFactory.getLog(getClass());
	

	@Autowired
	private Validator validator;
	
	@Autowired
	private TipoHallazgoFacade tipoHallazgoFacade;
	
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
		
		return new ModelAndView("/pages/forms/pruebas/tipoHallazgo.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getTipoHallazgos", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<TipoHallazgoDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<TipoHallazgoDTO> resultset = tipoHallazgoFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param paisdto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarTipoHallazgo(@RequestBody TipoHallazgoDTO tipoHallazgoDTO) throws Exception {
		
		Set<ConstraintViolation<TipoHallazgoDTO>> errors = getValidator().validate(tipoHallazgoDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				tipoHallazgoDTO.setUsuarioCreacion(getLogin());
				tipoHallazgoFacade.guardarTipoHallazgo(tipoHallazgoDTO);
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
	 * @param paisdto
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarTipoHallazgo(@RequestBody TipoHallazgoDTO tipoHallazgoDTO) throws AppBaseException {
		Set<ConstraintViolation<TipoHallazgoDTO>> errors = getValidator().validate(tipoHallazgoDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				
				tipoHallazgoDTO.setUsuarioEdita(getLogin());
				
				tipoHallazgoFacade.editarTipoHallazgo(tipoHallazgoDTO);
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
	@RequestMapping(value = "getTipoHallazgo", method = RequestMethod.GET)
	public @ResponseBody
	String getTipoHallazgo(@RequestParam String id) throws AppBaseException {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		TipoHallazgoDTO tipoHallazgoDTO=new TipoHallazgoDTO();
		if (isNumeric(id)){
		Integer idTipoHallazgo = Integer.parseInt(id);
		
		 tipoHallazgoDTO = tipoHallazgoFacade.getTipoHallazgo(idTipoHallazgo);
		

		}
		return prettyGson.toJson(tipoHallazgoDTO);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<TipoHallazgoDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<TipoHallazgoDTO> failure : failures) {
			
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
	* @date 5/08/2014
	* @param str
	* @return
	 */
		private boolean isNumeric(String str)
		{
		  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
		}
	
}
