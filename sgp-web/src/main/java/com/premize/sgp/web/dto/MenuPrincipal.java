package com.premize.sgp.web.dto;

import java.util.List;

import com.premize.profilemanager.client.OpcionesMenuDTO;

/**
 * Clase para generar json y poder renderizarlo con Jscript
 * 
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-web
 * @class Menuppal
 * @date 10/02/2014
 */
public class MenuPrincipal {
	
	private String usuario;
	
	private List<OpcionesMenuDTO> opcion;
	
	/**
	 * Metodo con la informacion de la Funcionalidad(Menu) de pmz
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 10/02/2014
	 * @return
	 */
	public List<OpcionesMenuDTO> getOpcion() {
		return opcion;
	}
	
	public void setOpcion(List<OpcionesMenuDTO> opcion) {
		this.opcion = opcion;
	}
	
	/**
	 * u
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 10/02/2014suario autenticado para renderizarlo en el menu
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
