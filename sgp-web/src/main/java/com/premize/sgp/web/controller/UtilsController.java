/**
 * 
 */
package com.premize.sgp.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.CiudadDTO;
import com.premize.sgp.dto.parametros.DepartamentoDTO;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.dto.pruebas.CausaGeneracionDTO;
import com.premize.sgp.dto.pruebas.HallazgoDTO;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.dto.pruebas.TipoSeveridadDTO;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.facade.parametros.CiudadFacade;
import com.premize.sgp.facade.parametros.DepartamentoFacade;
import com.premize.sgp.facade.parametros.EmpresaFacade;
import com.premize.sgp.facade.parametros.PaisFacade;
import com.premize.sgp.facade.parametros.TipoParametroFacade;
import com.premize.sgp.facade.pruebas.CausaGeneracionFacade;
import com.premize.sgp.facade.pruebas.HallazgoFacade;
import com.premize.sgp.facade.pruebas.MapaArtefactoFacade;
import com.premize.sgp.facade.pruebas.MapaPruebaFacade;
import com.premize.sgp.facade.pruebas.ProyectoFacade;
import com.premize.sgp.facade.pruebas.TipoHallazgoFacade;
import com.premize.sgp.facade.pruebas.TipoPrioridadFacade;
import com.premize.sgp.facade.pruebas.TipoSeveridadFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;

/**
 * Controlador que posee metodos Utiles para aplicacion en General
 * 
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-web
 * @class Utils
 * @since 21/01/2014
 */
@Controller
@RequestMapping(value = "/utils")
public class UtilsController {
	
	@Autowired
	private PaisFacade paisFacade;
	
	@Autowired
	private DepartamentoFacade departamentoFacade;
	
	@Autowired
	private CiudadFacade ciudadFacade;
	
	@Autowired
	private EmpresaFacade empresaFacade;
	
	@Autowired
	private ProyectoFacade proyectoFacade;
	
	@Autowired
	private TipoParametroFacade tipoParametroFacade;
	
	@Autowired
	private MapaPruebaFacade mapaPruebaFacade;
	
	@Autowired
	private MapaArtefactoFacade mapaArtefactoFacade;
	
	@Autowired
	private ParametroFacade parametroFacade;
	
	@Autowired
	private HallazgoFacade hallazgoFacade;
	
	@Autowired
	private TipoHallazgoFacade tipoHallazgoFacade;
	
	@Autowired
	private CausaGeneracionFacade causaGeneracionFacade;
	
	@Autowired
	private TipoSeveridadFacade tipoSeveridadFacade;
	
	@Autowired
	private TipoPrioridadFacade tipoPrioridadFacade;
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 20/01/2014
	 * @return selectItems
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "paises", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerPaises() throws AppBaseException {
		List<PaisDTO> listPaises = paisFacade.getPaises();
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listPaises);
	}
	
	/**
	 * Obtiene Departamentos A partir de Pais
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 21/01/2014
	 * @param codPaises
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "departamentos", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerDepartamentosPorPais(@RequestParam String idPais) throws AppBaseException {
		Integer id_Pais = Integer.parseInt(idPais);
		List<DepartamentoDTO> listDepartamentos = departamentoFacade.consultarDepartamentosPorPais(id_Pais,
				Paginator.getDefault());
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listDepartamentos);
	}
	
	/**
	 * Obtiene Departamentos A partir de Pais
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 21/01/2014
	 * @param codPaise
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "ciudades", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerCiudadesPorDepartamento(@RequestParam String idDepartamento) throws AppBaseException {
		Integer id_Departamento = Integer.parseInt(idDepartamento);
		List<CiudadDTO> listCiudades = ciudadFacade.consultarCiudadPorDepartamento(id_Departamento,
				Paginator.getDefault());
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listCiudades);
	}
	
	@RequestMapping(value = "tiposParametro", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerTipoParametros() throws AppBaseException {
		List<TipoParametroDTO> tiposParametros = tipoParametroFacade.getTipos();
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		return prettyGson.toJson(tiposParametros);
	}
	
	/**
	 * Obtiene Empresas a partir de la ciudad
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @return
	 */
	
	@RequestMapping(value = "empresas", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerEmpresas(@RequestParam String idCiudad) throws AppBaseException {
		Integer id_Ciudad = Integer.parseInt(idCiudad);
		List<EmpresaDTO> listEmpresas = empresaFacade.consultarEmpresasPorCiudad(id_Ciudad, Paginator.getDefault());
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listEmpresas);
	}
	
	/**
	 * Obtiene Empresas a partir de la ciudad
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @return
	 */
	
	@RequestMapping(value = "proyectos", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerProyectos(@RequestParam String idEmpresa) throws AppBaseException {
		String retorna = "";
		if (!idEmpresa.equals("")) {
			Integer id_Empresa = Integer.parseInt(idEmpresa);
			List<ProyectoDTO> listProyecto = proyectoFacade.consultarProyectoPorEmpresa(id_Empresa,
					Paginator.getDefault());
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			retorna = prettyGson.toJson(listProyecto);
			
		}
		
		return retorna;
	}
	
	/**
	 * Obtiene Empresas a partir de la ciudad
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @return
	 */
	
	@RequestMapping(value = "mapaPruebas", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerMapaDePruebas(@RequestParam String idProyecto) throws AppBaseException {
		Integer id_proyecto = Integer.parseInt(idProyecto);
		List<MapaPruebasDTO> listProyecto = mapaPruebaFacade.consultarMapaPruebasPorProyecto(id_proyecto,
				Paginator.getDefault());
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listProyecto);
	}
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @return
	 */
	
	@RequestMapping(value = "artefactoMapaPruebas", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerArtefactoMapaDePruebas(@RequestParam String idMapaPruebas) throws AppBaseException {
		Integer id_MapaArtefacto = Integer.parseInt(idMapaPruebas);
		List<MapaArtefactoDTO> listArtefactos = mapaArtefactoFacade.consultarArtefactosPorMapaPruebas(id_MapaArtefacto,
				Paginator.getDefault());
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listArtefactos);
	}
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "parametroPorTiposParametro", method = RequestMethod.GET)
	private @ResponseBody
	String getParametrosPorTipoParametro(@RequestParam String id) throws AppBaseException {
		List<ParametroDTO> parametros = parametroFacade.listarParametrosPorTipoParametro(new Integer(id),
				Paginator.getDefault());
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		return prettyGson.toJson(parametros);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/02/2014
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "getHallazgosPorCaso", method = RequestMethod.GET)
	private @ResponseBody
	String getHallazgosPorCaso(@RequestParam String id) throws AppBaseException {
		List<HallazgoDTO> listHallazgos = hallazgoFacade.getHallazgosPorCaso(new Integer(id));
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listHallazgos);
	}
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @return
	 */
	
	@RequestMapping(value = "artefactoActivoMapaPruebas", method = RequestMethod.GET)
	private @ResponseBody
	String getArtefactosActivosMapaDePruebas(@RequestParam String idMapaPruebas) throws AppBaseException {
		Integer id_MapaArtefacto = Integer.parseInt(idMapaPruebas);
		List<MapaArtefactoDTO> listArtefactos = mapaArtefactoFacade.getArtefactosActivosMapaDePruebas(id_MapaArtefacto,
				Paginator.getDefault());
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listArtefactos);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 12/03/2014
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/confirmaEliminar", method = RequestMethod.POST)
	public ModelAndView confirmaEliminarView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String elemento = request.getParameter("elemento");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", id);
		model.put("elemento", elemento);
		return new ModelAndView("/pages/popup/admin/confirmacionEliminar.jsp", model);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/05/2014
	 * @description 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "loadingDialog")
	public ModelAndView loadingDialog(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		return new ModelAndView("/pages/popup/pruebas/loadingDialog.jsp");
	}
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 20/01/2014
	 * @return selectItems
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "tipoHallazgos", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerTipoHallazgos(@RequestParam String tipoIngresoHallazgo) throws AppBaseException {
		
		List<TipoHallazgoDTO> listTipoHallazgo = tipoHallazgoFacade.getTipoHallazgos(Byte.valueOf(tipoIngresoHallazgo));
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listTipoHallazgo);
	}
	
	@RequestMapping(value = "tiposHallazgo", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerTipoHallazgos() throws AppBaseException {
		
		List<TipoHallazgoDTO> listTipos = tipoHallazgoFacade.tipoHallazgos();

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

		return prettyGson.toJson(listTipos);
	}
	

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param idTipoHallazgo
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "causaGeneracion", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerCausaGeneracion(@RequestParam String idTipoHallazgo) throws AppBaseException {
		List<CausaGeneracionDTO> listTipoHallazgo=new ArrayList<CausaGeneracionDTO>();
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		if(!idTipoHallazgo.equals("")){
			listTipoHallazgo = causaGeneracionFacade.consultaCausaPorTipoHallazgo(Integer.parseInt(idTipoHallazgo) ,Paginator.getDefault());

		}
		return prettyGson.toJson(listTipoHallazgo);
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param idTipoHallazgo
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "tipoSeveridad", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerTipoSeveridad(@RequestParam String idTipoHallazgo) throws AppBaseException {
		List<TipoSeveridadDTO> listTipoHallazgo=new ArrayList<TipoSeveridadDTO>();
		if(!idTipoHallazgo.equals("")){
			listTipoHallazgo = tipoSeveridadFacade.consultaSeveridadPorTipoHallazgo(Integer.parseInt(idTipoHallazgo) ,Paginator.getDefault());
		}

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listTipoHallazgo);
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param idTipoHallazgo
	* @return
	* @throws AppBaseException
	 */
	@RequestMapping(value = "tipoPrioridad", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerTipoPrioridad(@RequestParam String idTipoSeveridad) throws AppBaseException {

		List<TipoPrioridadDTO> listTipoHallazgo = tipoPrioridadFacade.consultaPrioridadPorTipoHallazgo(Integer.parseInt(idTipoSeveridad) ,Paginator.getDefault());
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listTipoHallazgo);
	}
	
	@RequestMapping(value = "parametro", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerParametro() throws AppBaseException {

		ParametroDTO parametroDTO= parametroFacade.getParametro("PIDEN_FECHA_SOLICITUD");
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(parametroDTO.getValor().split(","));
	}
	
}
