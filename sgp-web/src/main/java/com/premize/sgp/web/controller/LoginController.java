package com.premize.sgp.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class LoginController
 * @since 27/03/2014
 *
 */
@Controller
public class LoginController {
	
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/authfailed=true", method = RequestMethod.GET)
	public String login(ModelMap model) {
		model.addAttribute("error", "true");
		return "/login.jsp";
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		
		model.addAttribute("error", "true");
		return "/login.jsp";
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 */
	@RequestMapping(value = "/logout/success")
	public String logoutSuccess(ModelMap model) {
		return "/login.jsp";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "login";
 
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
	@RequestMapping(value = "/timeout", method = RequestMethod.GET)
	public ModelAndView viewExpired(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		return new ModelAndView("/viewExpired.jsp");
	}
	
}