/**
 * 
 */
package com.premize.sgp.dto.enums;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class TipoPruebaEnum
 * @since 20/02/2014
 */
public enum TipoPruebaEnum {
	
	ESPERADA("46", "Esperada"), INCONSISTENE("47", "Inconsistente");
	
	private String nombre;
	private String codigo;
	
	/**
	 * Constructor
	 */
	TipoPruebaEnum(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
}
