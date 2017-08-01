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
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.parametros.UsuarioFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class UsuarioController
 * @since 21/01/2014
 */
@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController extends AbstractBaseMessageUI {
	
	@Autowired
	private UtilFacade utilFacade;
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private Validator validator;
	
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
		return new ModelAndView("/pages/forms/admin/usuario.jsp", "model", Model);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param usuarioDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	private @ResponseBody
	JsonResponse guardar(@RequestBody UsuarioDTO usuarioDto) throws Exception {
		
		Set<ConstraintViolation<UsuarioDTO>> errors = validator.validate(usuarioDto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
				
			} else {
				usuarioDto.setUsuarioCreacion(getLogin());
				usuarioFacade.guardar(usuarioDto);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
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
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param usuarioDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "actualizar", method = RequestMethod.POST)
	private @ResponseBody
	JsonResponse actualizar(@RequestBody UsuarioDTO usuarioDto) throws Exception {
		
		Set<ConstraintViolation<UsuarioDTO>> errors = validator.validate(usuarioDto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
				
			} else {
				
				usuarioDto.setUsuarioEdita(getLogin());
				usuarioFacade.actualizar(usuarioDto);
				
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				res.setResult(ConstantesWeb.MSG_EDITAR);
			}
			
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(ex.getMessage());
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
	@RequestMapping(value = "/obtenerUsuarios", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<UsuarioDTO> getDataTables(@TableParam PagingCriteria criteria) throws Exception {
		ResultSet<UsuarioDTO> resultset = usuarioFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/obtenerUsuario", method = RequestMethod.GET)
	public @ResponseBody
	String obtenerUsuario(@RequestParam String id) throws Exception {
		
		Integer idUsuario = Integer.parseInt(id);
		UsuarioDTO usuario = usuarioFacade.obtenerUsuario(idUsuario);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(usuario);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 26/02/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<UsuarioDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<UsuarioDTO> failure : failures) {
			
			failureMessages
					.add("<strong>" + failure.getPropertyPath().toString() + "</strong>:" + failure.getMessage());
		}
		return failureMessages;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 3/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "list")
	public ModelAndView List(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("/pages/popup/admin/listUsuario.jsp", model);
	}
	
}
