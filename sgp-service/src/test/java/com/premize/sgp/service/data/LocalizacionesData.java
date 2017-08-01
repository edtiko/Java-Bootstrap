package com.premize.sgp.service.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;
import com.premize.sgp.modelo.entities.parametros.SgpPais;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class LocalizacionesData
 * @since 21/01/2014
 */
public class LocalizacionesData {
	
	/**
	 * Regresa unos valores por defecto para los test de Paises
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 21/01/2014
	 * @return
	 */
	public static List<SgpPais> getListaPaises() {
		List<SgpPais> data = new ArrayList<SgpPais>();
		data.add(new SgpPais(1, "Colombia", 1, Calendar.getInstance().getTime(), ""));
		return data;
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 21/01/2014
	 * @return
	 */
	public static List<SgpDepartamento> getListadDerparamentos() {
		List<SgpDepartamento> data = new ArrayList<SgpDepartamento>();
		data.add(new SgpDepartamento());
		return data;
		
	}
}
