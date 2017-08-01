/**
 * 
 */
package com.premize.sgp.facade;

import com.premize.pmz.api.exception.AppBaseException;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class UtilFacade
 * @since 18/02/2014
 */
public interface UtilFacade {
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @param sgpTable
	 * @throws AppBaseException
	 */
	void ResolverSequence(String sgpTable) throws AppBaseException;
	
}
