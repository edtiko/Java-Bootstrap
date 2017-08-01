package com.premize.sgp.utils;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.DataTablesResultSet;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.WebResultSet;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class ControllerUtils
 * @since 27/03/2014
 *
 */
public class ControllerUtils {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param pc
	 * @param rs
	 * @return
	 */
	public static <T extends BaseEntity> WebResultSet<T> getWebResultSet(PagingCriteria pc, ResultSet<T> rs) {
		return new DataTablesResultSet<T>(pc, rs);
	}
	
}
