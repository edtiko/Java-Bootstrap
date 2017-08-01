package com.premize.sgp.service.pruebas;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class ProyectoServiceNoDBTest
 * @description
 * @date 5/05/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class ProyectoServiceNoDBTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private ProyectoService proyectoService;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 5/05/2014
	 * @description
	 */
	@Test
	public void construirPrefijoProyecto() {
		String proyectoNombre = "Sofib IV";
		String prefijoEsperado = "SOF";
		try {
			String prefijo = proyectoService.construirPrefijoProyecto(proyectoNombre);
			Assert.assertNotNull(prefijo);
			Assert.assertEquals(prefijo, prefijoEsperado);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
	}

}
