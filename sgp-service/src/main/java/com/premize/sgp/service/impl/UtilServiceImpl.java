/**
 * 
 */
package com.premize.sgp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.parametros.SequenceDao;
import com.premize.sgp.modelo.entities.SgpSequence;
import com.premize.sgp.service.UtilService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class UtilServiceImpl
 * @since 18/02/2014
 */
@Service
public class UtilServiceImpl implements UtilService {
	
	@Autowired
	private SequenceDao sequenceDao;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 18/02/2014
	 * @see com.premize.sgp.service.UtilService#ResolverSequence(java.lang.String)
	 */
	@Override
	public void ResolverSequence(String sgpTable) throws AppBaseException {
		
		SgpSequence sequence = sequenceDao.get(sgpTable);
		sequence.setSequence(sequence.getSequence() - 1);
		sequenceDao.update(sequence);
		
	}
	
}
