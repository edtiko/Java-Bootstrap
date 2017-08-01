/**
 * 
 */
package com.premize.sgp.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.facade.UtilFacade;
import com.premize.sgp.service.UtilService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class UtilFacadeImpl
 * @since 18/02/2014
 */
@Service
public class UtilFacadeImpl implements UtilFacade {
	
	@Autowired
	private UtilService utilService;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 18/02/2014
	 * @see com.premize.sgp.facade.UtilFacade#ResolverSequence(java.lang.String)
	 */
	@Override
	public void ResolverSequence(String sgpTable) throws AppBaseException {
		
		utilService.ResolverSequence(sgpTable);
		
	}
	
}
