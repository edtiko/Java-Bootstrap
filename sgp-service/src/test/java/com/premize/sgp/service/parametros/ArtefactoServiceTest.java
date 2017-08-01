/**
 * 
 */
package com.premize.sgp.service.parametros;

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
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.TipoArtefactoDTO;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.ProyectoDTO;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class ArtefactoServiceTest
 * @since 14/02/2014O
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class ArtefactoServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private ArtefactoService artefactoService;
	
	Integer idProyecto = 1;
	Integer idMapaPrueba = 1;
	

	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @throws Exception
	 */
	@Test
	public void test_artefactosSinAsociarAMapa() throws Exception {
		List<ArtefactoDTO> list = artefactoService.artefactosSinAsociarAMapa(idProyecto, idMapaPrueba);
		Assert.assertNotNull(list);
		Assert.assertTrue(!list.isEmpty());
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 17/06/2014
	 * @description
	 */
	@Test
	public void guardarArtefactoConUsuario() {
		ProyectoDTO proyecto = new ProyectoDTO();
		proyecto.setId(19); //Proyecto: Teleporter.
		
		TipoArtefactoDTO tipoArtefacto = new TipoArtefactoDTO();
		tipoArtefacto.setId(1); // Tipo Artefacto: Requerimiento.
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(45); // Usuario: Alexander.Filigrana.
		
		ArtefactoDTO artefacto = new ArtefactoDTO();
		artefacto.setNombre("Artefacto Test");
		artefacto.setUsuarioCreacion("Alexander.Filigrana");
		artefacto.setTipoArtefacto(tipoArtefacto);
		artefacto.setProyecto(proyecto);
		artefacto.setUsuario(usuario);
		
		try {
			artefactoService.guardar(artefacto);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 17/06/2014
	 * @description
	 */
	@Test
	public void guardarArtefactoSinUsuario() {
		ProyectoDTO proyecto = new ProyectoDTO();
		proyecto.setId(19); //Proyecto: Teleporter.
		
		TipoArtefactoDTO tipoArtefacto = new TipoArtefactoDTO();
		tipoArtefacto.setId(1); // Tipo Artefacto: Requerimiento.
		
		UsuarioDTO usuario = new UsuarioDTO();
		
		ArtefactoDTO artefacto = new ArtefactoDTO();
		artefacto.setNombre("Artefacto Test");
		artefacto.setUsuarioCreacion("Alexander.Filigrana");
		artefacto.setTipoArtefacto(tipoArtefacto);
		artefacto.setProyecto(proyecto);
		artefacto.setUsuario(usuario);
		
		try {
			artefactoService.guardar(artefacto);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 18/06/2014
	 * @description
	 */
	@Test
	public void actualizarArtefactoConUsuario() {
		TipoArtefactoDTO tipoArtefacto = new TipoArtefactoDTO();
		tipoArtefacto.setId(1); // Tipo Artefacto: Requerimiento.
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(45); // Usuario: Alexander.Filigrana.
		
		ArtefactoDTO artefacto = new ArtefactoDTO();
		artefacto.setId(73); // Artefacto: R234 Parametrizar modelos matematicos
		artefacto.setNombre("Artefacto Test");
		artefacto.setUsuarioCreacion("Alexander.Filigrana");
		artefacto.setTipoArtefacto(tipoArtefacto);
		artefacto.setUsuario(usuario);
		
		try {
			artefactoService.actualizar(artefacto);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 18/06/2014
	 * @description
	 */
	@Test
	public void actualizarArtefactoSinUsuario() {
		TipoArtefactoDTO tipoArtefacto = new TipoArtefactoDTO();
		tipoArtefacto.setId(1); // Tipo Artefacto: Requerimiento.
		
		UsuarioDTO usuario = new UsuarioDTO();
		
		ArtefactoDTO artefacto = new ArtefactoDTO();
		artefacto.setId(73); // Artefacto: R234 Parametrizar modelos matematicos
		artefacto.setNombre("Artefacto Test");
		artefacto.setUsuarioCreacion("Alexander.Filigrana");
		artefacto.setTipoArtefacto(tipoArtefacto);
		artefacto.setUsuario(usuario);
		
		try {
			artefactoService.actualizar(artefacto);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
	}
	
}
