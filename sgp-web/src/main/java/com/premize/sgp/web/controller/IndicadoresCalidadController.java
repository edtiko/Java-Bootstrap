package com.premize.sgp.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.premize.sgp.web.auth.AbstractBaseMessageUI;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-web
* @class IndicadoresCalidadController
* @date 17/07/2014
*
 */
@Controller
@RequestMapping(value = "/indicadoresCalidad")
public class IndicadoresCalidadController extends AbstractBaseMessageUI {
	

	
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 17/07/2014
* @param request
* @param response
* @return
* @throws ServletException
* @throws IOException
 */
	@RequestMapping(value = "/index")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return new ModelAndView("/pages/forms/pruebas/indicadoresCalidad.jsp");
	}
	
	
}
