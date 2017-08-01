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
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.constantes.ConstantesWeb;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.TableParam;
import com.premize.sgp.dto.WebResultSet;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.pruebas.TipoPrioridadFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-web
* @class TipoSeveridadController
* @date 17/06/2014
*
 */
@Controller
@RequestMapping(value = "/tipoPrioridad")
public class TipoPrioridadController extends AbstractBaseMessageUI {
	
	private static final Logger logger = LoggerFactory.getLogger(HallazgoController.class);

	
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
	private TipoPrioridadFacade tipoPrioridadFacade;
	
	
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
		
		return new ModelAndView("/pages/forms/pruebas/tipoPrioridad.jsp");
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "/getTipoPrioridades", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<TipoPrioridadDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<TipoPrioridadDTO> resultset = tipoPrioridadFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param tipoSeveridadDTO
	* @return
	* @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarTipoSeveridad(@RequestBody TipoPrioridadDTO tipoPrioridadDTO) throws Exception {
		
		Set<ConstraintViolation<TipoPrioridadDTO>> errors = getValidator().validate(tipoPrioridadDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				tipoPrioridadDTO.setUsuarioCreacion(getLogin());
				tipoPrioridadFacade.guardarTipoPrioridad(tipoPrioridadDTO);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
		
	}
	

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param tipoSeveridadDTO
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarDepartamento(@RequestBody TipoPrioridadDTO tipoPrioridadDTO) throws AppBaseException {
		Set<ConstraintViolation<TipoPrioridadDTO>> errors = getValidator().validate(tipoPrioridadDTO);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				tipoPrioridadDTO.setUsuarioEdita(getLogin());
				tipoPrioridadFacade.editarTipoPrioridad (tipoPrioridadDTO);
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
	@RequestMapping(value = "getTipoPrioridad", method = RequestMethod.GET)
	public @ResponseBody
	String getTipoPrioridad(@RequestParam String id) throws AppBaseException {
		
		Integer idTipoPrioridad = Integer.parseInt(id);
		
		TipoPrioridadDTO tipoPrioridadDTO = tipoPrioridadFacade.getTipoPrioridad(idTipoPrioridad);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(tipoPrioridadDTO);
		
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param failures
	* @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<TipoPrioridadDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<TipoPrioridadDTO> failure : failures) {
			
			failureMessages
					.add("<strong>" + failure.getPropertyPath().toString() + "</strong>:" + failure.getMessage());
		}
		return failureMessages;
	}
	
}
