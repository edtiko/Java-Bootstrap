/**
 * 
 */
package com.premize.sgp.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
import com.premize.pmz.api.exception.MailServiceException;
import com.premize.pmz.api.util.MessageBundleLoader;
import com.premize.profilemanager.client.RolDTO;
import com.premize.sgp.constantes.ConstantesWeb;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.TableParam;
import com.premize.sgp.dto.WebResultSet;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.dto.pruebas.HallazgoDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.SeveridadColorDTO;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.facade.parametros.UsuarioFacade;
import com.premize.sgp.facade.pruebas.AnexoHallazgoFacade;
import com.premize.sgp.facade.pruebas.CasosDePruebaFacade;
import com.premize.sgp.facade.pruebas.FlujoHallazgoFacade;
import com.premize.sgp.facade.pruebas.HallazgoFacade;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.utils.DateUtils;
import com.premize.sgp.utils.FilePmz;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;
import com.premize.sgp.web.auth.UserPremizeDetails;
import com.premize.sgp.web.utils.FileConverter;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class HallazgoController
 * @since 24/02/2014
 */
@Controller
@RequestMapping(value = "/hallazgos")
public class HallazgoController extends AbstractBaseMessageUI {
	
	@Autowired
	private HallazgoFacade hallazgoFacade;
	
	@Autowired
	private FlujoHallazgoFacade flujoHallazgoFacade;
	
	@Autowired
	private CasosDePruebaFacade casoPruebaFacade;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private AnexoHallazgoFacade anexoHallazgoFacade;
	
	@Autowired
	private ParametroFacade parametroFacade;
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	
	private static final String CODIGO_ROLES_ADMIN_KEY = "ROLES_ADMIN_HALLAZGO";
	private static final String CODIGO_ROLES_DESA_KEY =  "ROLES_DESA_HALLAZGO";
	private static final String CODIGO_MAX_SIZE_ANEXO_KEY = "PESO_MAXIMO";
	private static final String PARAMETRO_FIELD_CLAVE="nombre";
	
	private static final Logger LOG = LoggerFactory.getLogger(HallazgoController.class);

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/02/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return new ModelAndView("/pages/forms/pruebas/consultarHallazgo.jsp");
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @param idHallazgo
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getHallazgo")
	public @ResponseBody String getHallazgo(@RequestParam Integer idHallazgo) throws ServletException, IOException, AppBaseException {
		
		HallazgoDTO hallazgo = hallazgoFacade.getHallazgo(idHallazgo,getLogin());

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

		return prettyGson.toJson(hallazgo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException 
	 */
	@RequestMapping(value = "editObservacion", method = RequestMethod.POST)
	public ModelAndView editObservacion(@RequestParam Integer idFlujo) throws ServletException, IOException, AppBaseException {

		Map<String, Object> model = new HashMap<String, Object>();
		FlujoHallazgoDTO flujo = flujoHallazgoFacade.getFlujoHallazgo(idFlujo);
		model.put("flujo", flujo);
		return new ModelAndView("/pages/popup/pruebas/editarObservacion.jsp", model);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 6/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/confirmaHallazgo")
	public ModelAndView confirmaView(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result", request.getParameter("result"));
		return new ModelAndView("/pages/popup/pruebas/confirmaHallazgo.jsp", model);
	}
	
	
	@RequestMapping(value = "vistaVolver")
	public ModelAndView confirmaViewBack(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		return new ModelAndView("/pages/popup/pruebas/confirmaVolverHallazgo.jsp");
	}
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 28/02/2014
	 * @param id
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "reportarHallazgo/{id}", method = RequestMethod.GET)
	public ModelAndView viewReportarHallazgo(@PathVariable Integer id) throws ServletException, IOException,
			AppBaseException {
		Map<String, Object> model = new HashMap<String, Object>();
		CasoDePruebaDTO casoPrueba = casoPruebaFacade.getCasoDePrueba(id);
		model.put("caso", casoPrueba);
		return new ModelAndView("/pages/forms/pruebas/reportarHallazgos.jsp", model);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param idHallazgo
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getDetalle", method = RequestMethod.POST)
	public ModelAndView viewDetalleHallazgo(@RequestParam Integer idHallazgo) throws ServletException, IOException,
			AppBaseException {
		HallazgoDTO hallazgo = hallazgoFacade.getHallazgo(idHallazgo,getLogin());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("hallazgo", hallazgo);
		model.put("flujos", hallazgo.getFlujos());
		return new ModelAndView("/pages/forms/pruebas/detalleHallazgo.jsp", model);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param idHallazgo
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getAnexos", method = RequestMethod.POST)
	public ModelAndView viewAnexosHallazgo(@RequestParam Integer idHallazgo) throws ServletException, IOException,
			AppBaseException {
		HallazgoDTO hallazgo = hallazgoFacade.getHallazgo(idHallazgo,getLogin());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("anexos", hallazgo.getAnexos());
		return new ModelAndView("/pages/forms/pruebas/anexosHallazgo.jsp", model);
	}
	
	@RequestMapping(value="getDatosHallazgo", method = RequestMethod.POST)
	public ModelAndView viewDatosHallazgo(@RequestParam Integer idHallazgo, @RequestParam String accion)
			throws ServletException, IOException, AppBaseException {
		
		HallazgoDTO hallazgo = hallazgoFacade.getHallazgo(idHallazgo,getLogin());
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("hallazgo", hallazgo);
		if(null!=hallazgo.getCasoPrueba()){
			model.put("proyecto", hallazgo.getCasoPrueba().getMapaPrueba().getProyecto());
			model.put("mapaPrueba",  hallazgo.getCasoPrueba().getMapaPrueba().getNombre());
			model.put("empresa",  hallazgo.getCasoPrueba().getMapaPrueba().getProyecto().getEmpresa().getNombre());
			model.put("artefacto",  hallazgo.getCasoPrueba().getArtefacto().getNombre());
		}else{
			model.put("proyecto", hallazgo.getArtefacto().getProyecto());
			model.put("mapaPrueba", "" );
			model.put("empresa",  hallazgo.getArtefacto().getProyecto().getEmpresa().getNombre());
			model.put("artefacto",  hallazgo.getArtefacto().getNombre());
		}
				
		model.put("estadoHallazgo", hallazgo.getFlujoActual().getEstadoHallazgo().getNombre());
		model.put("severidad",  hallazgo.getSeveridad());
		model.put("prioridad",  hallazgo.getPrioridad());
		model.put("tipoHallazgo",  hallazgo.getTipoHallazgo());
		model.put("usuario", hallazgo.getFlujoActual().getUsuarioAsignado());
		if(null!=hallazgo.getCausaGeneracion()){
			model.put("causaGeneracion",  hallazgo.getCausaGeneracion());
		}
		
		if(hallazgo.getCausaAnulacion() != null){
			model.put("causaAnulacion",  hallazgo.getCausaAnulacion());
		}
		String template = null;
		if(accion.equals("editar")){
			template = "/pages/forms/pruebas/datosHallazgoEdit.jsp";
		}
		else if(accion.equals("info")){
			template = "/pages/forms/pruebas/datosHallazgo.jsp";
		}
		
		return new ModelAndView(template, model);
	}
	/**
	 * Guarda un hallazgo y su archivo anexo
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 6/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarHallazgo(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonResponse res = new JsonResponse();
		String respuestaEmail="";
		String dto = request.getParameter("dto");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FlujoHallazgoDTO flujoHallazgodto = gson.fromJson(dto, FlujoHallazgoDTO.class);
		
		if(null!=flujoHallazgodto.getHallazgo()){
		String fechaSolicitud= flujoHallazgodto.getHallazgo().getFechaSolicitudString();
		if(null!=fechaSolicitud ){
			if (!fechaSolicitud.equals("")){
				
			
			Date fechaActual = new Date();
			Date fechaSolicutitud = DateUtils.getDateFromString(
					"yyyy/MM/dd hh:mm", flujoHallazgodto.getHallazgo()
							.getFechaSolicitudString());

			if (fechaSolicutitud.after(fechaActual)) {
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(ConstantesWeb.MSG_FECHASOLICITUD);
				return res;
			}
			}
		}
		}
		Set<ConstraintViolation<FlujoHallazgoDTO>> errors = getValidator().validate(flujoHallazgodto);
		List<String> validationMessages = new ArrayList<String>();
	
		
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				flujoHallazgodto.setUsuarioCreacion(getLogin());
				FilePmz file = FileConverter.upload(request);
				hallazgoFacade.guardarHallazgo(flujoHallazgodto, file);
				try {
					
				
				hallazgoFacade.enviarEmailHallazgo(flujoHallazgodto);
		
				} catch (MailServiceException e) {
					respuestaEmail="</br></br> <b>Nota:</b> El correo de notificación no pudo ser enviado ";
				}catch (RuntimeException e){
					respuestaEmail="</br></br> <b>Nota:</b> El correo de notificación no pudo ser enviado ";
				}
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				res.setResult("Se registró con éxito el hallazgo " + flujoHallazgodto.getHallazgo().getId()
						+ " y fue asignado al usuario " + flujoHallazgodto.getUsuarioAsignado().getNombre()+respuestaEmail);
				
			}
			
		} catch (AppBaseException e) {
			LOG.error(e.getMessage(), e);
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse EditarHallazgo(@RequestBody FlujoHallazgoDTO flujoHallazgoDTO) throws Exception {
		
		Set<ConstraintViolation<FlujoHallazgoDTO>> errors = getValidator().validate(flujoHallazgoDTO);
		List<String> validationMessages = new ArrayList<String>();
		String respuestaEmail="";
		JsonResponse res = new JsonResponse();
		
		try {
			if (!errors.isEmpty()) {
				validationMessages = validationMessages(errors);
				res.setStatus(ConstantesWeb.ERROR_PROCESO);
				res.setResult(validationMessages);
			} else {
				flujoHallazgoDTO.setUsuarioCreacion(getLogin());
				hallazgoFacade.editarHallazgo(flujoHallazgoDTO);
				try {
					
					
					hallazgoFacade.enviarEmailHallazgo(flujoHallazgoDTO);
			
					} catch (MailServiceException e) {
						respuestaEmail="</br></br> <b>Nota:</b> El correo de notificación no pudo ser enviado ";
					} catch (RuntimeException e){
						respuestaEmail="</br></br> <b>Nota:</b> El correo de notificación no pudo ser enviado ";
					}
				
				
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				res.setResult("Se actualizó con éxito el hallazgo " + flujoHallazgoDTO.getHallazgo().getId()
						+ " y fue asignado al usuario " + flujoHallazgoDTO.getUsuarioAsignado().getNombre()+respuestaEmail);
			}
			
		} catch (AppBaseException e) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
	}
	
	@RequestMapping(value = "editarObservacion", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarObservacionFlujo(@RequestBody FlujoHallazgoDTO flujoHallazgoDTO) throws Exception {

		JsonResponse res = new JsonResponse();

		try{
		
			flujoHallazgoDTO.setUsuarioEdita(getLogin());
			flujoHallazgoFacade.editarFlujoHallazgo(flujoHallazgoDTO);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				res.setResult(ConstantesWeb.MSG_EDITAR);

			

		} catch (AppBaseException e) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/03/2014
	 * @param flujoHallazgoDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modificarDatos", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse ModificarDatosHallazgo(@RequestBody HallazgoDTO hallazgoDTO) throws Exception {

		JsonResponse res = new JsonResponse();

		try{
			
				hallazgoDTO.setUsuarioEdita(getLogin());
				hallazgoFacade.modificarDatosHallazgo(hallazgoDTO);
				res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
				res.setResult(ConstantesWeb.MSG_EDITAR);
		

		} catch (AppBaseException e) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
	}
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "guardarAnexo", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse guardarAnexoHallazgo(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String dto = request.getParameter("dto");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HallazgoDTO hallazgodto = gson.fromJson(dto, HallazgoDTO.class);
		
		JsonResponse res = new JsonResponse();
		
		try {
			
			hallazgodto.setUsuarioCreacion(getLogin());
			FilePmz file = FileConverter.upload(request);
			hallazgoFacade.guardarAnexoHallazgo(hallazgodto, file);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			res.setResult(ConstantesWeb.MSG_GUARDAR);
			
		} catch (AppBaseException e) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/getHallazgos", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<ConsultaHallazgoDTO> getDataTables(@TableParam PagingCriteria criteria) throws AppBaseException, ParseException {
		ResultSet<ConsultaHallazgoDTO> resultset = hallazgoFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getFlujo", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<FlujoHallazgoDTO> getDataTableFlujo(@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<FlujoHallazgoDTO> resultset = flujoHallazgoFacade.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param failures
	 * @return
	 */
	public List<String> validationMessages(Set<ConstraintViolation<FlujoHallazgoDTO>> failures) {
		List<String> failureMessages = new ArrayList<String>();
		for (ConstraintViolation<FlujoHallazgoDTO> failure : failures) {
			
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
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/02/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "list")
	public ModelAndView List(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, AppBaseException {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("idCasoPrueba", request.getParameter("idCasoPrueba"));
	
		return new ModelAndView("/pages/popup/pruebas/listHallazgos.jsp", model);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 5/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getBandeja")
	public ModelAndView getBandeja(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException, AppBaseException {
		SgpUsuario user = usuarioFacade.findByProperty("login", getLogin());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userLogin", user.getNombre());
	  
		return new ModelAndView("/pages/forms/pruebas/bandejaHallazgos.jsp", model);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param id
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView gestionarHallazgo(@PathVariable Integer id) throws ServletException, IOException,
			AppBaseException {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		UserPremizeDetails premizeDetails = (UserPremizeDetails) getUserDetails();
		String rolesAdmin = (parametroFacade.findByProperty(PARAMETRO_FIELD_CLAVE,CODIGO_ROLES_ADMIN_KEY)).getValor();
		String rolesDesa =  (parametroFacade.findByProperty(PARAMETRO_FIELD_CLAVE, CODIGO_ROLES_DESA_KEY)).getValor();
		List<RolDTO> rolesDto = premizeDetails.getUsuarioDTO().getRoles();
		List<String>  roles = new ArrayList<String>();
		for (RolDTO rolDTO : rolesDto) {
			roles.add(rolDTO.getClaveRol());
		}
		String rolesJson = prettyGson.toJson(roles);
		HallazgoDTO hallazgo = hallazgoFacade.getHallazgo(id,getLogin());
		Map<String, Object> model = new HashMap<String, Object>();
		if(null!=hallazgo.getCasoPrueba()){
			model.put("proyecto", hallazgo.getCasoPrueba().getMapaPrueba().getProyecto());
			model.put("mapaPrueba",  hallazgo.getCasoPrueba().getMapaPrueba().getNombre());
			model.put("empresa",  hallazgo.getCasoPrueba().getMapaPrueba().getProyecto().getEmpresa().getNombre());
			model.put("artefacto",  hallazgo.getCasoPrueba().getArtefacto().getNombre());
			model.put("manual","No");
		}else{
			model.put("proyecto", hallazgo.getArtefacto().getProyecto());
			model.put("mapaPrueba", "" );
			model.put("empresa",  hallazgo.getArtefacto().getProyecto().getEmpresa().getNombre());
			model.put("artefacto",  hallazgo.getArtefacto().getNombre());
			model.put("manual","Si");
		}
		
		model.put("rolesAdmin", rolesAdmin);
		model.put("rolesDesa", rolesDesa);
		model.put("roles", rolesJson);
		model.put("hallazgo", hallazgo);
		model.put("usuario", hallazgo.getFlujoActual().getUsuarioAsignado());
		model.put("estadoHallazgo", hallazgo.getFlujoActual().getEstadoHallazgo().getNombre());

		

		model.put("ocultarGestion",parametroFacade.
				getEstadoNoSolicitaUsuario(hallazgo.getFlujoActual().getEstadoHallazgo().getNombre()));
		return new ModelAndView("/pages/forms/pruebas/gestionarHallazgo.jsp", model);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param idHallazgo
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getEstadosHallazgo", method = RequestMethod.GET)
	private @ResponseBody
	String getEstadosHallazgo(@RequestParam Integer idHallazgo) throws AppBaseException {
		
		List<ParametroDTO> listEstados = hallazgoFacade.getEstadosHallazgo(idHallazgo);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listEstados);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 14/04/2014
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "getHallazgosPorSeveridad", method = RequestMethod.GET)
	private @ResponseBody
	List<ReporteHallazgoDTO> getHallazgosPorSeveridad(@RequestParam Integer idProyecto, @RequestParam String fechaFrom, @RequestParam String fechaTo, @RequestParam String tipoHallazgo ) throws AppBaseException, ParseException {
		
		return hallazgoFacade.getHallazgosPorSeveridad(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 14/04/2014
	 * @param idProyecto
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "getHallazgosPorMapaPruebas", method = RequestMethod.GET)
	private @ResponseBody
	List<ReporteHallazgoDTO> getHallazgosPorMapaPruebas(@RequestParam Integer idProyecto, @RequestParam String fechaFrom, @RequestParam String fechaTo, @RequestParam String tipoHallazgo) throws AppBaseException, ParseException {
		
		return hallazgoFacade.getHallazgosPorMapaPruebas(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 8/07/2014
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @param tipoHallazgo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@RequestMapping(value = "getHallazgosPorArtefacto", method = RequestMethod.GET)
	private @ResponseBody
	List<ReporteHallazgoDTO> getHallazgosPorArtefacto(@RequestParam Integer idProyecto, @RequestParam String fechaFrom, @RequestParam String fechaTo, @RequestParam String tipoHallazgo) throws AppBaseException, ParseException {
		
		return hallazgoFacade.getHallazgosPorArtefacto(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param idAnexo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAnexo/{idAnexo}", method = RequestMethod.GET)
	public ModelAndView descargarAnexo(@PathVariable Integer idAnexo, HttpServletResponse response) throws ServletException,
	IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			//Integer idAnexo = Integer.parseInt(request.getParameter("id"));
			File file = hallazgoFacade.getArchivoAnexo(idAnexo);
			
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
			String mimetype = servletContext.getMimeType(file.getName());
			
			response.setBufferSize((int) file.length());
			response.setContentType(mimetype);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			response.setContentLength((int) file.length());
			
			FileCopyUtils.copy(input, response.getOutputStream());
			response.flushBuffer();
			
			model.put("result", ConstantesWeb.PROCESO_EXITOSO);
			return null;
		} catch (AppBaseException ex) {
			LOG.error(ex.getMessage(), ex);
			String message = MessageBundleLoader.getMessage("i18n.msg_exception", ex.getErrorCode(), 
					LocaleContextHolder.getLocale(), ex.getParameter());
			String titulo = "Error al Descargar Archivo Anexo";
			model.put("titulo", titulo);
			model.put("tituloPanel", titulo);
			model.put("result", message);
			return new ModelAndView("/pages/forms/pruebas/errorDescargarArchivo.jsp", model);
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 * @throws IOException 
	 */
	@RequestMapping(value = "eliminarAnexo", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse eliminarAnexo(@RequestParam Integer id) {
		
		JsonResponse res = new JsonResponse();
		try {
			
			anexoHallazgoFacade.eliminarAnexo(id);
			res.setResult(ConstantesWeb.MSG_ELIMINAR);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
		} catch (AppBaseException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		 catch (IOException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		
		return res;
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 28/05/2014
	 * @description 
	 * @return
	 */
	@RequestMapping(value = "getMaxSizeAnexo", method = RequestMethod.GET)
	public @ResponseBody JsonResponse getMaxSizeArchivoAnexo() {
		JsonResponse res = new JsonResponse();
		try {
			Integer maxSizeAnexo = Integer.parseInt((parametroFacade
					.findByProperty(PARAMETRO_FIELD_CLAVE,CODIGO_MAX_SIZE_ANEXO_KEY).getValor()));
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			res.setData(maxSizeAnexo);
		} catch (AppBaseException ex) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(ex.getMessage());
		}
		return res;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 29/05/2014
	 * @description 
	 * @return
	 */
	@RequestMapping(value = "getRestriccionesAnexo", method = RequestMethod.GET)
	public @ResponseBody JsonResponse getRestriccionesArchivoAnexo() {
		JsonResponse res = new JsonResponse();
		try {
			Map<String, Object> restricciones = parametroFacade.getMapRestriccionesAnexo();
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			res.setData(restricciones);
		} catch (AppBaseException ex) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(ex.getMessage());
		}
		return res;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 4/06/2014
	* @return
	 */
	@RequestMapping(value = "getEstadoNoSolicitaUsuario", method = RequestMethod.GET)
	public @ResponseBody JsonResponse getEstadoNoSolicitaUsuario(String estado) {
		JsonResponse res = new JsonResponse();
		try {
			boolean solicitaUsuario = parametroFacade.getEstadoNoSolicitaUsuario(estado);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
			res.setData(solicitaUsuario);
		} catch (AppBaseException ex) {
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
			res.setResult(ex.getMessage());
		}
		return res;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 11/07/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "/tipoSeveridadesColor", method = RequestMethod.GET)
	public @ResponseBody
	String hallazgoSeveridadColor() throws AppBaseException {
		List<ConsultaHallazgoDTO> resultset = hallazgoFacade.getSeveridadColorHallazgo(getLogin());
		List <SeveridadColorDTO> coloresSeveridad=new ArrayList<SeveridadColorDTO>();
		
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		for (ConsultaHallazgoDTO consultaHallazgoDTO : resultset) {
			SeveridadColorDTO severidadColorDTO=new SeveridadColorDTO();
			severidadColorDTO.setColor(consultaHallazgoDTO.getColor());
			severidadColorDTO.setSeveridad(consultaHallazgoDTO.getSeveridad());
			coloresSeveridad.add(severidadColorDTO);
		}
		
		return prettyGson.toJson(coloresSeveridad);

	}
}
