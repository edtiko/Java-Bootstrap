package com.premize.sgp.dao.test;

import org.junit.Before;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class AbstractDaoTest
 * @since 27/03/2014
 *
 */
public abstract class AbstractDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		Resource sqlTestData = super.applicationContext
				.getResource("classpath:META-INF/hibernate/sqlTestData.sql");
		JdbcTestUtils.executeSqlScript(super.jdbcTemplate,
				sqlTestData, false);
	}

}
