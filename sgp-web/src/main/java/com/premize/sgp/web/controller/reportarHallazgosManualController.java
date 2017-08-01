/**
 * 
 */
package com.premize.sgp.web.controller;

import java.io.IOException;
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
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.facade.parametros.ArtefactoFacade;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-web
 * @class reportarHallazgosManualController
 * @date 3/07/2014
 *
 */
@Controller
@RequestMapping(value = "/reportarhallazgomanual")
public class reportarHallazgosManualController {
	
	@Autowired
	ArtefactoFacade artefactoFacade;
	

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 3/07/2014
	* @param request
	* @param response
	* @return
	* @throws ServletException
	* @throws IOException
	 */
	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("idMapaPrueba", request.getParameter("idMapaPrueba"));
		return new ModelAndView("/pages/forms/pruebas/reportarHallazgosManual.jsp", model);
	}


	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 3/07/2014
	* @param idProyecto
	* @return
	* @throws NumberFormatException
	* @throws Exception
	 */
	@RequestMapping(value = "artefacto", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerTipoPrioridad(@RequestParam String idProyecto) throws NumberFormatException, Exception {

		List<ArtefactoDTO> listTipoHallazgo = artefactoFacade.obtenerArtefactosPorProyecto(Integer.parseInt(idProyecto));
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		return prettyGson.toJson(listTipoHallazgo);
	}
	
}
