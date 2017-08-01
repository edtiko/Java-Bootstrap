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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.premize.sgp.dto.parametros.UsuarioDTO;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-dao-hbxml
 * @class UsuarioDaoTest
 * @since 14/02/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
@Transactional
public class UsuarioDaoTest {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	/**
	 * Test para obtener los usuarios Por proyecto
	 * 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 */
	@Test
	public void test_obtenerUsuarioPorProyecto() {
		Integer idProyecto = 1;
		try {
			List<UsuarioDTO> listUsuario = usuarioDao.obtenerUsuarioPorProyecto(idProyecto);
			Assert.assertNotNull(listUsuario);
			Assert.assertTrue(!listUsuario.isEmpty());
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 */
	@Test
	public void test_obtenerUsuariosParaAsociar() {
		Integer idProyecto = null;
		try {
			List<UsuarioDTO> listUsuario = usuarioDao.obtenerUsuariosParaAsociar(idProyecto);
			Assert.assertNotNull(listUsuario);
			Assert.assertTrue(!listUsuario.isEmpty());
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
