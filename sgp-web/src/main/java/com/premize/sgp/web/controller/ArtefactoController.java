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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.MessageBundleLoader;
import com.premize.sgp.constantes.ConstantesWeb;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.TableParam;
import com.premize.sgp.dto.WebResultSet;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.parametros.ArtefactoFacade;
import com.premize.sgp.facade.parametros.UsuarioFacade;
import com.premize.sgp.facade.pruebas.ProyectoFacade;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class ArtefactoController
 * @since 21/01/2014
 */
@Controller
@RequestMapping(value = "/asociarArtefactoProyecto")
public class ArtefactoController extends AbstractBaseMessageUI {
	
	@Autowired
	private ArtefactoFacade artefactoFacade;
	
	@Autowired
	private ProyectoFacade proyectoFacade;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private UtilFacade utilFacade;
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 14/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NumberFormatException, AppBaseException {
		String idProyecto = request.getParameter("id");
		ProyectoDTO  proyecto = proyectoFacade.getProyecto(new Integer(idProyecto));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", idProyecto);
		model.put("proyecto", proyecto);
	    return new ModelAndView("/pages/forms/pruebas/asociarProyectoArtefacto.jsp",model);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "dataTable")
	public ModelAndView viewDataTable() throws AppBaseException {
		
		return new ModelAndView("/pages/general/artefactoDataTable.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param id
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewArtefactoByProyecto(@PathVariable Integer id) throws ServletException, IOException,
			AppBaseException {
		Map<String, Object> model = new HashMap<String, Object>();
		ProyectoDTO proyecto = proyectoFacade.getProyecto(id);
		model.put("proyecto", proyecto);
		return new ModelAndView("/pages/forms/pruebas/asociarProyectoArtefacto.jsp", model);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param artefactoDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardar(@RequestBody ArtefactoDTO artefactoDto) throws Exception {
		JsonResponse res = new JsonResponse();
		try {
			Set<ConstraintViolation<ArtefactoDTO>> errors = validator.validate(artefactoDto);
			List<String> validationMessages = new ArrayList<String>();
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setResult(validationMessages);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				
			} else {
				artefactoDto.setUsuarioCreacion(getLogin());
				artefactoFacade.guardar(artefactoDto);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			}
		} catch (AppBaseException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		return res;
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param artefactoDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse actualizar(@RequestBody ArtefactoDTO artefactoDto) throws Exception {
		JsonResponse res = new JsonResponse();
		Set<ConstraintViolation<ArtefactoDTO>> errors = validator.validate(artefactoDto);
		List<String> validationMessages = new ArrayList<String>();
		if (!errors.isEmpty()) {
			validationMessages = validationMessages(errors);
			res.setResult(validationMessages);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		} else {
			
			try {
				artefactoDto.setUsuarioEdita(getLogin());
				artefactoFacade.actualizar(artefactoDto);
				res.setResult(ConstantesWeb.MSG_GUARDAR);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			} catch (AppBaseException e) {
				res.setResult(e.getMessage());
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
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
	@RequestMapping(value = "/getDataTable", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<ArtefactoDTO> getDataTables(@TableParam PagingCriteria criteria) throws Exception {
		ResultSet<ArtefactoDTO> resultset = artefactoFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMapaArtefactos", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<MapaArtefactoDTO> getMapaArtefactos(@TableParam PagingCriteria criteria) throws Exception {
		ResultSet<MapaArtefactoDTO> resultset = artefactoFacade.getMapaArtefactos(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getArtefacto", method = RequestMethod.GET)
	public @ResponseBody
	String obtenerArtefacto(@RequestParam String id) throws Exception {
		
		Integer idArtefacto = Integer.parseInt(id);
		ArtefactoDTO artefacto = artefactoFacade.obtenerArtefacto(idArtefacto);
		List<UsuarioDTO> usuariosProyecto = usuarioFacade
				.obtenerUsuarioPorProyecto(artefacto.getProyecto().getId());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("artefacto", artefacto);
		map.put("usuariosProyecto", usuariosProyecto);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(map);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<ArtefactoDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<ArtefactoDTO> failure : failures) {
			
			failureMessages
					.add("<strong>" + failure.getPropertyPath().toString() + "</strong>:" + failure.getMessage());
		}
		return failureMessages;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "dataTableArtefactosMapaPruebas")
	public ModelAndView viewDataTableArtefactosMapaPruebas() throws AppBaseException {
		
		return new ModelAndView("/pages/general/asociarArtefactoMapaPruebasDataTable.jsp");
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "eliminarArtefacto", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse eliminarArtefacto(@RequestParam String id) throws AppBaseException {
		
		JsonResponse res = new JsonResponse();
		try {
			artefactoFacade.eliminarArtefacto(new Integer(id));
			res.setResult(ConstantesWeb.MSG_ELIMINAR);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
		} catch (AppBaseException e) {
			String message = MessageBundleLoader.getMessage("i18n.msg_exception",
					e.getErrorCode(),LocaleContextHolder.getLocale(),e.getParameter());
			res.setResult(message);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		
		return res;
		
	}
}
