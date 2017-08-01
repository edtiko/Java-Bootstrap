package com.premize.sgp.dao.parametros;

/**
 * Prueba para Ciudad Autor John Hawer Bernal
 */
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class CiudadDaoTest
 * @since 27/03/2014
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class CiudadDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CiudadDao ciudadDao;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 5/05/2014
	 * @description
	 */
	@Test
	public void consultarCiudades() {
		try {
			List<SgpCiudad> ciudades = ciudadDao.consultarCiudades();
			Assert.assertNotNull(ciudades);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
	}
	
	
	
	
	
}
