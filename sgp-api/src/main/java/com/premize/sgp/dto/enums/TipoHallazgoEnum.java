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
public enum TipoHallazgoEnum {
	
	NO_CONFORMIDAD(10), MEJORA(11);
	
	private Integer codigo;
	
	/**
	 * Constructor
	 */
	TipoHallazgoEnum(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
}
