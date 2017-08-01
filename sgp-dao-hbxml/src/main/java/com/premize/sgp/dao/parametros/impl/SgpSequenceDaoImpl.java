package com.premize.sgp.dao.parametros.impl;

import org.springframework.stereotype.Service;

import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.parametros.SequenceDao;
import com.premize.sgp.modelo.entities.SgpSequence;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpSequenceDaoImpl
 * @since 27/03/2014
 *
 */
@Service
public class SgpSequenceDaoImpl extends HibernateDaoImpl<SgpSequence, String> implements SequenceDao {
	
}
