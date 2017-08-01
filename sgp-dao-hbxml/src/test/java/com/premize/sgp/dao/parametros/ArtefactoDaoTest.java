/**
 * 
 */
package com.premize.sgp.dao.parametros;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.premize.sgp.dto.parametros.ArtefactoDTO;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-dao-hbxml
 * @class SgpArtefactoDaoTest
 * @since 8/02/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
@Transactional
public class ArtefactoDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	ArtefactoDao artefactoDao;
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @throws Exception
	 */
	@Test
	public void test_obtenerArtefactosPorMapaPrueba() throws Exception {
		
		List<ArtefactoDTO> list = artefactoDao.obtenerArtefactosPorMapaPrueba(1);
		Assert.assertNotNull(list);
		//Solo tener en cuenta este caso si se asegura que la lista no esta vacia, de lo contrario la prueba fallara.
//		Assert.assertTrue(!list.isEmpty());
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 */
	@Test
	public void test_artefactosSinAsociarAMapa() {
		Integer proyecto = 1;
		Integer mapa = 1;
		List<ArtefactoDTO> list = null;
		try {
			list = artefactoDao.artefactosSinAsociarAMapa(proyecto, mapa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(list);
		Assert.assertTrue(!list.isEmpty());
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 */
	@Test
	public void test_artefactosSinAsociarAMapaSinMapa() {
		Integer proyecto = 1;
		Integer mapa = null;
		List<ArtefactoDTO> list = null;
		try {
			list = artefactoDao.artefactosSinAsociarAMapa(proyecto, mapa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(list);
		Assert.assertTrue(!list.isEmpty());
	}
}
