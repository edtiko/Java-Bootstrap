/**
 * 
 */
package com.premize.sgp.dao.test;

import static org.junit.Assert.fail;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.sgp.dao.parametros.SequenceDao;
import com.premize.sgp.modelo.entities.SgpSequence;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpProyectoDaoTest
 * @since 18/02/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class SequenceDaoTest extends AbstractDaoTest {
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 18/02/2014
	 * @throws Exception
	 */
	@Test
	public void UpdateSequence() throws Exception {
		
		SgpSequence sequence = sequenceDao.get("SgpProyecto");
		sequence.setSequence(sequence.getSequence() - 1);
		
		try {
			this.sequenceDao.update(sequence);
			flush();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
