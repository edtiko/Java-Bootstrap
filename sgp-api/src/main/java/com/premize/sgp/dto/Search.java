/**
 * 
 */
package com.premize.sgp.dto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class Search
 * @since 11/02/2014
 */
public class Search {
	
	private final String field;
	private final String value;
	
	public Search(String field, String value) {
		// TODO Mar 20, 2014 Search Search Valores quemados
		if (field.equals("indActivo")) {
			if (!value.isEmpty())
				value = value.equals("Activo") ? "1" : "0";
		}
		this.field = field;
		this.value = value.isEmpty() ? null : value;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/02/2014
	 * @return the field
	 */
	public String getField() {
		
		return field;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/02/2014
	 * @return the value
	 */
	public String getValue() {
		
		return value;
	}
}
