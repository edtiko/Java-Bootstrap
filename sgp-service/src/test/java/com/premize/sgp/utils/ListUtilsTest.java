package com.premize.sgp.utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import static junit.framework.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class ListUtilsTest
 * @description
 * @date 24/06/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/applicationContext-map.xml"})
public class ListUtilsTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/06/2014
	 * @description
	 */
	@Test
	public void containsInBeanList() {
		try {
			List<SgpUsuario> usuarios = new ArrayList<SgpUsuario>();
			usuarios.add(new SgpUsuario(1));
			usuarios.add(new SgpUsuario(2));
			usuarios.add(new SgpUsuario(3));
			usuarios.add(new SgpUsuario(4));
			
			Integer value = 3;
			boolean result = ListUtils.containsInBeanList(usuarios, "id", value);
			Assert.assertNotNull(result);
			
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/06/2014
	 * @description
	 */
	@Test
	public void containsInBeanListNullElements() {
		try {
			List<SgpUsuario> usuarios = new ArrayList<SgpUsuario>();
			usuarios.add(new SgpUsuario(1));
			usuarios.add(new SgpUsuario(2));
			usuarios.add(null);
			usuarios.add(new SgpUsuario(4));

			boolean result = ListUtils.containsInBeanList(usuarios, "id", null);
			Assert.assertNotNull(result);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/06/2014
	 * @description
	 */
	@Test
	public void indexContainsInBeanList() {
		try {
			List<SgpUsuario> usuarios = new ArrayList<SgpUsuario>();
			usuarios.add(new SgpUsuario(1));
			usuarios.add(new SgpUsuario(2));
			usuarios.add(new SgpUsuario(3));
			usuarios.add(new SgpUsuario(4));
			
			Integer value = 3;
			Integer result = ListUtils.indexContainsInBeanList(usuarios, "id", value);
			Assert.assertNotNull(result);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	

}
