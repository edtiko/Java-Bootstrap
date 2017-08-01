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
import com.google.gson.reflect.TypeToken;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.constantes.ConstantesWeb;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.TableParam;
import com.premize.sgp.dto.WebResultSet;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.dto.pruebas.UsuarioProyectoDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.parametros.UsuarioFacade;
import com.premize.sgp.facade.pruebas.ProyectoFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class ParametroController
 * @since 13/01/2014
 */
@Controller
@RequestMapping(value = "/proyectos")
public class ProyectoController extends AbstractBaseMessageUI {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ProyectoFacade proyectoFacade;
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	
	@Autowired
	private UtilFacade utilFacade;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return new ModelAndView("/pages/forms/admin/proyecto.jsp");
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "dataTable")
	public ModelAndView viewDataTable() throws AppBaseException {
		
		return new ModelAndView("/pages/general/proyectoDataTable.jsp");
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "list")
	public ModelAndView List(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		return new ModelAndView("/pages/popup/admin/listProyecto.jsp");
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getProyectos", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<ProyectoDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<ProyectoDTO> resultset = proyectoFacade.getRecords(criteria,true);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	
	@RequestMapping(value = "/getProyectosModal", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<ProyectoDTO> getDataTablesModal(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<ProyectoDTO> resultset = proyectoFacade.getRecords(criteria,false);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	/**
	 * Metodo que guarda un proyecto
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param proyectodto
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody JsonResponse guardarProyecto(@RequestBody ProyectoDTO proyectodto) throws AppBaseException {

		Set<ConstraintViolation<ProyectoDTO>> errors = getValidator().validate(
				proyectodto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				
				proyectodto.setUsuarioCreacion(getLogin());
				proyectoFacade.guardarProyecto(proyectodto);
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
	 * Metodo que actualiza un proyecto
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param proyectodto
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarProyecto(@RequestBody ProyectoDTO proyectodto) throws AppBaseException {
		
		Set<ConstraintViolation<ProyectoDTO>> errors = getValidator().validate(proyectodto);
		List<String> validationMessages = new ArrayList<String>();
		JsonResponse res = new JsonResponse();
		
		if (!errors.isEmpty()) {
			validationMessages = validationMessages(errors);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(validationMessages);
		} else {
			try {
				proyectodto.setUsuarioEdita(getLogin());
				proyectoFacade.editarProyecto(proyectodto);
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
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getProyecto", method = RequestMethod.GET)
	public @ResponseBody
	String getParametro(@RequestParam String id) throws AppBaseException {
		
		Integer idProyecto = Integer.parseInt(id);
		
		ProyectoDTO proyecto = proyectoFacade.getProyecto(idProyecto);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(proyecto);
		
	}
	
	/**
	 * Metodo que recibe los errores y los añade a una lista
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<ProyectoDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<ProyectoDTO> failure : failures) {
			
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
	 * Metodo que Obtiene todos Usuarios Asociados a determinado Proyecto.
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param idMapaPrueba
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "obtenerUsuariosAsociados", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerUsuarioPorProyecto(@RequestParam String idProyecto) throws AppBaseException {
		List<UsuarioDTO> listUsuarios = null;
		if (!idProyecto.equals("")) {
			try {
				listUsuarios = usuarioFacade.obtenerUsuarioPorProyecto(new Integer(idProyecto));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			
			return prettyGson.toJson(listUsuarios);
		}
		return "";
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param idMapaPrueba
	 * @return
	 */
	@RequestMapping(value = "obtenerBackLog", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerUsuariosParaAsociar(@RequestParam String idProyecto) {
		List<UsuarioDTO> listUsuarios = null;
		try {
			listUsuarios = usuarioFacade.obtenerUsuariosParaAsociar(new Integer(idProyecto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String json = prettyGson.toJson(listUsuarios);
		return json;
	}
	
	/**
	 * Procedimiento encargado de Actualizar la lista de Usuarios asociados a un Proyecto
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param usuarios
	 * @param idProyecto
	 * @return
	 */
	@RequestMapping(value = "actualizarUsuarios", method = RequestMethod.POST)
	private @ResponseBody
	JsonResponse actualizarUsuariosProyecto(@RequestParam String usuarios, @RequestParam String idProyecto)
			throws Exception {
		JsonResponse res = new JsonResponse();
		try {
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			List<String> listUsuarios = prettyGson.fromJson(usuarios, new TypeToken<List<String>>() {
			}.getType());
			proyectoFacade.actualizarUsuariosPorProyecto(listUsuarios, idProyecto, getLogin());
			res.setResult(ConstantesWeb.MSG_ASOCIACION);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
		} catch (AppBaseException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		return res;
	}
	
	/**
	 * Metodo encargado de levantar modal para asociacion de usuaarios
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "usuarios")
	public ModelAndView pageArtefactos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NumberFormatException, AppBaseException {
		Map<String, Object> model = new HashMap<String, Object>();
		String idProyecto = request.getParameter("idProyecto");
		ProyectoDTO  proyecto = proyectoFacade.getProyecto(new Integer(idProyecto));
		model.put("idProyecto", idProyecto);
		model.put("proyecto", proyecto);

		return new ModelAndView(
				"/pages/popup/admin/asociarUsuarioProyecto.jsp", model);
	}

	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "dataTableUsuarios")
	public ModelAndView viewDataTableUser() throws AppBaseException {
		
		return new ModelAndView("/pages/general/userProyectoDataTable.jsp");
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/dataUsuarios", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<UsuarioProyectoDTO> getDataTableUsuariosProyecto(@TableParam PagingCriteria criteria)
			throws AppBaseException {
		ResultSet<UsuarioProyectoDTO> resultset = usuarioFacade.getUsuarioProyectoRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "eliminarUsuarioProyecto", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse eliminarUsuarioProyecto(@RequestParam String id) throws AppBaseException {
		
		JsonResponse res = new JsonResponse();
		try {
			usuarioFacade.eliminarUsuarioProyecto(new Integer(id));
			res.setResult(ConstantesWeb.MSG_ELIMINAR);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
		} catch (AppBaseException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		
		return res;
		
	}
}
