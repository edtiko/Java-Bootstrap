package com.premize.sgp.dao.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.parametros.PaisDao;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class MapperTest
 * @since 27/03/2014
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext-map.xml")
@Transactional
public class MapperTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private PaisDao sgpPaisDao;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws AppBaseException
	 */
	@Test
	public void mapping() throws AppBaseException {
		Assert.assertNotNull(this.sgpPaisDao);
		
		Paginator paginator = new Paginator(0, 10);
		
		Assert.assertNotNull(this.sgpPaisDao.findAllEntries(paginator));
		
	}
}
