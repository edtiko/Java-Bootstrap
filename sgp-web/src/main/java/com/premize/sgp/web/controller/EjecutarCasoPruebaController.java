/**
 * 
 */
package com.premize.sgp.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-web
 * @class EjecutarCasoPruebaController
 * @since 14/02/2014
 */
@Controller
@RequestMapping(value = "/ejecutarPrueba")
public class EjecutarCasoPruebaController {
	
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
		return new ModelAndView("/pages/forms/pruebas/ejecutarPrueba.jsp");
	}
	

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/05/2014
	 * @description 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "checkNoCumple")
	public ModelAndView confirmaViewBack(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		return new ModelAndView("/pages/popup/pruebas/confirmarCasoPruebaNoCumple.jsp");
	}
	
	
}
