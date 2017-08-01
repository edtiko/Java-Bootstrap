/**
 * 
 */
package com.premize.sgp.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.TipoArtefactoDTO;
import com.premize.sgp.facade.parametros.TipoArtefactoFacade;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class ArtefactoController
 * @since 21/01/2014
 */
@Controller
@RequestMapping(value = "/tiposartefactos")
public class TipoArtefactoController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private TipoArtefactoFacade tipoArtefactoFacade;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @return
	 * @throws AppBaseException
	 */
	@RequestMapping(value = "obtenerTipos", method = RequestMethod.GET)
	private @ResponseBody
	String obtenerTiposArtefactos() throws AppBaseException {
		
		List<TipoArtefactoDTO> listado = tipoArtefactoFacade.obtenerTiposArtefactos();
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		
		String dato = prettyGson.toJson(listado);
		return dato;
	}
}
