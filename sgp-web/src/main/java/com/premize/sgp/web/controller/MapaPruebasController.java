/**
 * 
 */
package com.premize.sgp.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.premize.sgp.dto.ResultSet;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.google.gson.reflect.TypeToken;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.constantes.ConstantesWeb;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.TableParam;
import com.premize.sgp.dto.WebResultSet;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.facade.parametros.ArtefactoFacade;
import com.premize.sgp.facade.pruebas.MapaPruebaFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.utils.ControllerUtils;
import com.premize.sgp.utils.ReporteExcelCons;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-web
 * @class MapaPruebasController
 * @since 20/01/2014
 */
@Controller
@RequestMapping(value = "/gestionarPrueba")
public class MapaPruebasController extends AbstractBaseMessageUI {

	@Autowired
	private UtilFacade utilFacade;

	@Autowired
	private MapaPruebaFacade mapaPruebaFacade;

	@Autowired
	private ArtefactoFacade artefactoFacade;

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 20/01/2014
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws ServletExceptionx
	 *             |
	 * @throws IOException
	 */
	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		return new ModelAndView("/pages/forms/pruebas/gestionarPrueba.jsp");
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
	 *         Filigrana Cardona</a>
	 * @date 11/04/2014
	 * @description
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/descargarMapa")
	public ModelAndView descargarMapa(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		return new ModelAndView(
				"/pages/forms/pruebas/descargarMapasPruebas.jsp");
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
	 *         Filigrana Cardona</a>
	 * @date 24/04/2014
	 * @description
	 * @param id
	 * @param response
	 * @return
	 * @throws AppBaseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/descargarMapaExcel/{id}", method = RequestMethod.GET)
	public void descargarMapaExcel(@PathVariable Integer id,
			HttpServletResponse response) throws AppBaseException, IOException {
		// Integer id2 = id;
		Map<String, Object> reporte = mapaPruebaFacade
				.generarMatrizContruccionPruebasExcel(id);
		Workbook libroExcel = (Workbook) reporte
				.get(ReporteExcelCons.KEY_MAP_REPORTE);
		String fileName = (String) reporte
				.get(ReporteExcelCons.KEY_MAP_FILENAME);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		libroExcel.write(response.getOutputStream());
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @param codPaise
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	private @ResponseBody
	JsonResponse guardarMapaPruebas(@RequestBody MapaPruebasDTO mapaDto)
			throws AppBaseException {
		JsonResponse res = new JsonResponse();
		try {

			mapaDto.setUsuarioCreacion(getLogin());
			mapaPruebaFacade.guardarMapaPrueba(mapaDto);
			res.setResult(ConstantesWeb.MSG_GUARDAR);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);

		} catch (AppBaseException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}
		return res;
	}

	/**
	 * Metodo encargado de Obtener los Artefactos Asociados al Mapa de Pruebas
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @param idMapaPrueba
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "obtenerArtefactos", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerArtefactosAsociados(@RequestParam String idMapaPrueba)
			throws AppBaseException {
		List<ArtefactoDTO> listArtefactos = null;
		if (!idMapaPrueba.equals("")) {
			try {
				listArtefactos = artefactoFacade
						.obtenerArtefactoPorMapaPrueba(new Integer(idMapaPrueba));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

			return prettyGson.toJson(listArtefactos);
		}
		return "";
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @param idProyecto
	 * @param idMapaPrueba
	 * @return
	 */
	@RequestMapping(value = "obtenerBackLog", method = RequestMethod.GET)
	private @ResponseBody
	String artefactosSinAsociarAMapa(@RequestParam String idMapaPrueba) {
		List<ArtefactoDTO> listArtefactos = null;
		try {
			SgpMapaPrueba mapaPrueba = mapaPruebaFacade.findById(new Integer(
					idMapaPrueba));
			listArtefactos = artefactoFacade.artefactosSinAsociarAMapa(
					mapaPrueba.getSgpProyecto().getId(), new Integer(
							idMapaPrueba));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

		return prettyGson.toJson(listArtefactos);
	}

	/**
	 * Procedimiento Encargado de Actualizar los Artefactos por Mapa de Prueba.
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @param artefactos
	 * @param idMapa
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "actualizarArtefactos", method = RequestMethod.POST)
	private @ResponseBody
	JsonResponse actualizarArtefactos(@RequestParam String artefactos,
			@RequestParam String idMapa) throws Exception {
		JsonResponse res = new JsonResponse();
		try {
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			List<String> listArtefactos = prettyGson.fromJson(artefactos,
					new TypeToken<List<String>>() {
					}.getType());

			mapaPruebaFacade.actualizarArtefactosPorMapa(listArtefactos,
					idMapa, getLogin());
			res.setResult(ConstantesWeb.MSG_ASOCIACION);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
		} catch (Exception e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);

		}
		return res;
	}

	/**
	 * Metodo encargado de Obtener y Paginar los registros de Mapa de Pruebas
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getMapasPrueba", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<MapaPruebasDTO> getDataTables(
			@TableParam PagingCriteria criteria) throws AppBaseException {
		ResultSet<MapaPruebasDTO> resultset = mapaPruebaFacade
				.getRecords(criteria);
		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/04/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getMapasPruebaReporte", method = RequestMethod.GET)
	public @ResponseBody
	WebResultSet<MapaPruebasDTO> getDataMapasReporte(
			@TableParam PagingCriteria criteria) throws AppBaseException,
			ParseException {
		ResultSet<MapaPruebasDTO> resultset = mapaPruebaFacade
				.getRecordsEjecucion(criteria);

		return ControllerUtils.getWebResultSet(criteria, resultset);
	}

	/**
	 * Metodo encargado de obtener la informacion para levantar el Popop de
	 * asociacion.
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "artefactos")
	public ModelAndView pageArtefactos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("idMapa", request.getParameter("idmapa"));
		model.put("nombreMapa", request.getParameter("nombreMapa"));

		return new ModelAndView(
				"/pages/popup/pruebas/asociarArtefactoMapa.jsp", model);
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getMapa", method = RequestMethod.GET)
	public @ResponseBody
	String getParametro(@RequestParam String id) throws AppBaseException {

		Integer idMapa = Integer.parseInt(id);
		MapaPruebasDTO mapaSearch = mapaPruebaFacade.getMapaDTO(idMapa);

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

		return prettyGson.toJson(mapaSearch);
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @param mapaPruebasDTO
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse editarMapaPrueba(@RequestBody MapaPruebasDTO mapaPruebasDTO)
			throws AppBaseException {
		JsonResponse res = new JsonResponse();
		try {
			mapaPruebasDTO.setUsuarioEdita(getLogin());
			mapaPruebaFacade.editarMapaPrueba(mapaPruebasDTO);
			res.setResult(ConstantesWeb.MSG_EDITAR);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
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
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "dataTableMapaPruebas")
	public ModelAndView viewDataTableMapa() throws AppBaseException {

		return new ModelAndView("/pages/general/mapaPruebasDataTable.jsp");
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param id
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "eliminarArtefactoMapa", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse eliminarArtefactoMapa(@RequestParam String id)
			throws AppBaseException {

		JsonResponse res = new JsonResponse();
		try {
			mapaPruebaFacade.eliminarArtefactoMapa(new Integer(id));
			res.setResult(ConstantesWeb.MSG_ELIMINAR);
			res.setStatus(ConstantesWeb.PROCESO_EXITOSO);
		} catch (AppBaseException e) {
			res.setResult(e.getMessage());
			res.setStatus(ConstantesWeb.ERROR_PROCESO);
		}

		return res;
	}

	@RequestMapping(value = "descargarMapasZip", method = RequestMethod.POST)
	public void descargarMapasZip(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			AppBaseException, ParseException, IOException {
		Map<String, Object> filtros = new HashMap<String, Object>();
		filtros.put("idProyecto", request.getParameter("idProyecto"));
		filtros.put("fechaFrom", request.getParameter("fechaFrom"));
		filtros.put("fechaTo", request.getParameter("fechaTo"));

		// String directorio = request.getServletContext().getRealPath("/");
		String directorio = getClass().getClassLoader().getResource("/")
				.getPath();
		Map<String, Object> zipData = mapaPruebaFacade.generarMapasZip(filtros,
				directorio);

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ zipData.get("zipName") + "\"");

		FileInputStream in = (FileInputStream) zipData.get("zip");
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.flush();

		// System.out.println("zipName: "+zipData.get("zipName"));
		// System.out.println("zipStream: "+zipData.get("zip"));
		// System.out.println("proyecto = "+filtros.get("proyecto"));
		// System.out.println("proyectoId = "+request.getParameter("idProyecto"));

	}

}