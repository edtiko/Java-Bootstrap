package com.premize.sgp.web.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.profilemanager.client.OpcionesMenuDTO;
import com.premize.profilemanager.client.RolDTO;
import com.premize.sgp.web.auth.AbstractBaseMessageUI;
import com.premize.sgp.web.auth.UserPremizeDetails;
import com.premize.sgp.web.dto.MenuPrincipal;

/**
 * Controlador para renderizar el menu
 * 
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-web
 * @class MenuBarController
 * @date 10/02/2014
 */
@Controller
@RequestMapping(value = "/menubar")
public class MenuBarController extends AbstractBaseMessageUI implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3445138022115432261L;
	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * Metodo para consultar la funcionalidad de acuerdo al usuario logueado
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 10/02/2014
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	@Secured("Analista Funcional - Tester")
	public @ResponseBody
	String getMenu() throws AppBaseException {
		
		MenuPrincipal menuppal = new MenuPrincipal();
		
		UserPremizeDetails premizeDetails = (UserPremizeDetails) getUserDetails();
		List<OpcionesMenuDTO> opcion = premizeDetails.getUsuarioDTO().getListOpciones();
		
		List<RolDTO> roles = premizeDetails.getUsuarioDTO().getRoles();
		
		StringBuffer buffer = new StringBuffer();
		
		boolean bandera = true ;
		for (RolDTO rolDTO : roles) {
			if (!bandera) {
				buffer.append(" - ");
			}
			buffer.append(rolDTO.getNombreRol());
			bandera = false;
		}
		
		menuppal.setUsuario(premizeDetails.getUsername() + " [" + buffer.toString() + "]");
		menuppal.setOpcion(opcion);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String json = prettyGson.toJson(menuppal);
		
		return json;
	}
}
