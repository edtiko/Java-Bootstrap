/**
 * 
 */
package com.premize.sgp.dto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class SortField
 * @since 21/01/2014
 */
public class SortField {
	
	private final String field;
	private final SortDirection direction;
	
	public SortField(String field, SortDirection direction) {
		this.field = field;
		this.direction = direction;
	}
	
	public SortField(String field, String direction) {
		this.field = field;
		this.direction = SortDirection.valueOfCaseInsensitive(direction);
	}
	
	public String getField() {
		return field;
	}
	
	public SortDirection getDirection() {
		return direction;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 25/06/2014
	 * @description 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SortField other = (SortField) obj;
		//Comparando cadenas
		if (!field.equals(other.field))
			return false;
		return true;
	}
	
	
}
