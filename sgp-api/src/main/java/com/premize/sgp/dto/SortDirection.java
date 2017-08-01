/**
 * 
 */
package com.premize.sgp.dto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class SortDirection
 * @since 21/01/2014
 */
public enum SortDirection {
	ASC("asc"), DESC("desc");
	
	private String direction;
	private static final Log logger = LogFactory.getLog(SortDirection.class);
	
	private SortDirection(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() {
		return this.direction;
	}
	
	public static SortDirection valueOfCaseInsensitive(String value) {
		String valueUpper = value.toUpperCase();
		SortDirection sd = SortDirection.valueOf(valueUpper);
		logger.debug("SortDirection converted " + value + " to " + sd);
		return sd;
	}
}
