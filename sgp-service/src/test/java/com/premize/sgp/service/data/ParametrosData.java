package com.premize.sgp.service.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class LocalizacionesData
 * @since 21/01/2014
 */
public class ParametrosData {
	
	/**
	 * Regresa unos valores por defecto para los test de Paises
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 21/01/2014
	 * @return
	 */
	public static List<SgpUsuario> getListaUsuarios() {
		List<SgpUsuario> data = new ArrayList<SgpUsuario>();
		data.add(new SgpUsuario(1, "caro1", "empresa1", "analista1", "login1", "cooreo@email.com", 1, Calendar
				.getInstance().getTime(), ""));
		data.add(new SgpUsuario(2, "caro2", "empresa2", "analista2", "login2", "cooreo@email.com", 1, Calendar
				.getInstance().getTime(), ""));
		data.add(new SgpUsuario(3, "caro3", "empresa3", "analista3", "login3", "cooreo@email.com", 1, Calendar
				.getInstance().getTime(), ""));
		return data;
		
	}
	
}
