package com.premize.sgp.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
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
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.dto.pruebas.TotalHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalesHallazgoDTO;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.facade.parametros.UsuarioFacade;
import com.premize.sgp.facade.pruebas.HallazgoFacade;
import com.premize.sgp.facade.pruebas.MapaPruebaFacade;
import com.premize.sgp.facade.pruebas.ProyectoFacade;
import com.premize.sgp.utils.DateUtils;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class ReporteController
 * @since 10/04/2014
 *
 */
@Controller
@RequestMapping(value = "/reportes")
public class ReporteController extends AbstractBaseMessageUI{
	
	@Autowired
	ProyectoFacade proyectoFacade;
	
	@Autowired
	ParametroFacade parametroFacade;
	
	@Autowired
	UsuarioFacade usuarioFacade;
	
	@Autowired
	MapaPruebaFacade mapaPruebaFacade;
	
	@Autowired
	HallazgoFacade hallazgoFacade;
	
	private static final Logger LOG = LoggerFactory.getLogger(HallazgoController.class);
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/04/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/seguimiento")
	public ModelAndView seguimientoIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return new ModelAndView("/pages/forms/pruebas/reporteSeguimiento.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 21/04/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/avance")
	public ModelAndView avanceIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return new ModelAndView("/pages/forms/pruebas/informeAvanceEjecucionMapasPruebas.jsp");
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/04/2014
	 * @param idProyecto
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "getInformeSeguimiento", method = RequestMethod.POST)
	public ModelAndView getInformeSeguimiento(HttpServletRequest request) throws Exception{
		
		String proyectoStr = request.getParameter("idProyecto");
		String fechaFrom = "";//request.getParameter("fechaFrom");
		String fechaTo = request.getParameter("fechaTo");
		
		
		String tipoHallazgo = request.getParameter("tipoHallazgo");
		String tiposMapa = parametroFacade.getParametro(45).getValor();
		String tiposArtefacto = parametroFacade.getParametro(44).getValor();
		Integer idProyecto = new Integer(proyectoStr);
		Map<String, Object> model = new HashMap<String, Object>();

			try{
		ProyectoDTO proyecto = proyectoFacade.getProyecto(idProyecto);
		List<UsuarioDTO> usuariosProyecto = usuarioFacade.obtenerUsuarioPorProyecto(idProyecto);
		List<TotalesHallazgoDTO> totales = hallazgoFacade.getTotalesHallazgoProyecto(idProyecto,fechaFrom,fechaTo,tipoHallazgo);
		MapaPruebasDTO mapaPruebas = mapaPruebaFacade.getTotalesMapaPruebaProyecto(idProyecto,fechaFrom,fechaTo,tipoHallazgo);
		
		SimpleDateFormat dFormat= new SimpleDateFormat("dd-MM-yyyy");
	    UsuarioDTO usuarioLogin = usuarioFacade.obtenerUsuario(getLogin());
		model.put("idProyecto", idProyecto);
		model.put("proyecto", proyecto);
		model.put("usuarios", usuariosProyecto);
		model.put("fecha", dFormat.format(new Date()));
		model.put("totales", totales);
		model.put("mapaPrueba", mapaPruebas);
		model.put("usuario", usuarioLogin);
		model.put("tiposMapa", tiposMapa);
		model.put("tiposArtefacto", tiposArtefacto);
		return new ModelAndView("/pages/forms/pruebas/informeSeguimiento.jsp", model);
	}
	catch(AppBaseException e){
		model.put("error", errorAppBaseException(e));
		return new ModelAndView("/pages/forms/pruebas/informeSeguimiento.jsp", model);
	}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/04/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws AppBaseException
	 * @throws IOException
	 */
	@RequestMapping(value = "reporteHallazgosXls", method = RequestMethod.POST)
	public ModelAndView generarReporteExcelHallazgos(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String titulo = "Error al Generar el Reporte";
		String message = "El sistema ha generado el siguiente error: ";
		try {
			Map<String, Object> filtros = new HashMap<String, Object>();
			filtros.put("proyecto",     request.getParameter("proyecto"));
			filtros.put("mapaPrueba",   request.getParameter("mapaPruebas"));
			filtros.put("causaAnula",   request.getParameter("causaAnula"));
			filtros.put("causaGenera",  request.getParameter("causaGenera"));
			filtros.put("usuarioAsigna",request.getParameter("usuario"));
			filtros.put("fechaFrom",    request.getParameter("fechaFrom"));
			filtros.put("fechaTo",      request.getParameter("fechaTo"));

			//Filtros grilla
			filtros.put("idHallazgo",      request.getParameter("gf_idHallazgo"));
			filtros.put("artefacto",       request.getParameter("gf_artefacto"));
			filtros.put("severidad",       request.getParameter("gf_severidad"));
			filtros.put("prioridad",       request.getParameter("gf_prioridad"));
			filtros.put("tipoHallazgo",    request.getParameter("gf_tipoHallazgo"));
			filtros.put("usuarioAsignado", request.getParameter("gf_usuAsignado"));
			filtros.put("titulo",          request.getParameter("gf_titulo"));
			filtros.put("estado",          request.getParameter("gf_estado"));

			Workbook libroExcel = hallazgoFacade.consultaHallazgosExcel(filtros);
			String fileName = ConstantesWeb.EXCEL_HALLAZGOS;
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			libroExcel.write(response.getOutputStream());
			
			return null;

		} catch (AppBaseException ex) {
			LOG.error(ex.getMessage(), ex);
			return getErrorPage(titulo, titulo, message+ex.getMessage());
		} catch (NumberFormatException ex) {
			LOG.error(ex.getMessage(), ex);
			return getErrorPage(titulo, titulo, message+ex.getMessage());
		}

	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description 
	 * @param id
	 * @param response
	 * @throws AppBaseException
	 * @throws IOException
	 */
	@RequestMapping(value = "descargarReporteAvanceMapas", method = RequestMethod.POST)
	public ModelAndView descargerReporteAvanceEjecucionMapas(HttpServletRequest request, HttpServletResponse response) {
		String titulo = "Error al Generar el Reporte";
		String message = "El sistema ha generado el siguiente error: ";
		try {
			
			Map<String,Object> parametros = new HashMap<String,Object>();
			parametros.put("idProyecto", request.getParameter("proyectoId"));
			parametros.put("fechaFrom", request.getParameter("fechaFrom"));
			parametros.put("fechaTo", request.getParameter("fechaTo"));
			
			Map<String, Object> map = mapaPruebaFacade.generarReporteAvanceEjecucionMapas(parametros);
			Workbook libroExcel = (Workbook) map.get("reporte");
			String fileName = (String) map.get("fileName");
			
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			libroExcel.write(response.getOutputStream());
			
			return null;
		} catch (AppBaseException ex) { // Excepcion controlada.
			LOG.error(ex.getMessage(), ex);
			String customMessage = MessageBundleLoader.getMessage("i18n.msg_exception", ex.getErrorCode(), 
					LocaleContextHolder.getLocale(), ex.getParameter());
			return getErrorPage(titulo, titulo, customMessage);
		} catch (ParseException ex) {
			LOG.error(ex.getMessage(), ex);
			return getErrorPage(titulo, titulo, message+ex.getMessage());
		} catch (IOException ioex) {
			LOG.error(ioex.getMessage(), ioex);
			return getErrorPage(titulo, titulo, message+ioex.getMessage());
		}
	}
	
	private ModelAndView getErrorPage(String titulo, String tituloPanel, String result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("titulo", titulo);
		model.put("tituloPanel", titulo);
		model.put("result", result);
		return new ModelAndView("/pages/forms/pruebas/errorDescargarArchivo.jsp", model);
	}
	
	
	
	@RequestMapping(value = "/getReporteSeveridad", method = RequestMethod.GET)
	public @ResponseBody
	String[] getReporteSeveridad(@RequestParam String idProyecto,@RequestParam String fechaFrom,@RequestParam String fechaTo, @RequestParam String tipoHallazgoNom) throws AppBaseException, ParseException {
		List<String> totales=new ArrayList<String>();
		String tipoHallazgo="";
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		List<TotalHallazgoDTO> usuariosHallazgos = hallazgoFacade.getUsuarios_Hallazgos(Integer.parseInt(idProyecto), fechaFrom,fechaTo, tipoHallazgoNom);
		String total="";
		String json ="";
		for (TotalHallazgoDTO totalHallazgoDTO : usuariosHallazgos) {
		
			if(!tipoHallazgo.equals(totalHallazgoDTO.getTipoHallazgo())){
				if (!total.equals("")){
				totales.add(total);
				json="";
				total="";
				}
				
				json= prettyGson.toJson(totalHallazgoDTO);
				total +=json;

			}else{
				json= prettyGson.toJson(totalHallazgoDTO);
				 total+=","+json;
				
			}
			tipoHallazgo=totalHallazgoDTO.getTipoHallazgo();
		}
		totales.add(total);
		String[] retorno=new String[totales.size()];
		int i=0;
		for (String total_ : totales) {
			retorno[i]="["+total_+"]";
			i++;
		}

		return retorno;
	}
}
