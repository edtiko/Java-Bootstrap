/**
 * 
 */
package com.premize.sgp.service;

import com.premize.pmz.api.exception.AppBaseException;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class UtilService
 * @since 18/02/2014
 */
public interface UtilService {
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param sgpTable
	 * @throws AppBaseException
	 */
	void ResolverSequence(String sgpTable) throws AppBaseException;
	
}
