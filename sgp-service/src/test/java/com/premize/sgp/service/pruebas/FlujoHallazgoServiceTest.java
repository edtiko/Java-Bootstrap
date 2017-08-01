package com.premize.sgp.service.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.service.parametros.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class FlujoHallazgoServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private FlujoHallazgoService flujoHallazgoService;
	
	@Autowired
	private HallazgoService hallazgoService;
	
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 */
	@Test
	public void guardarFlujoHallazgo(){
		try{
			FlujoHallazgoDTO flujoHallazgodto = new FlujoHallazgoDTO();
			UsuarioDTO usuarioAsignado = usuarioService.obtenerUsuario(40);
			flujoHallazgodto.setUsuarioCreacion("Valeria.Vasquez");
			flujoHallazgodto.setUsuarioAsignado(usuarioAsignado);
			SgpHallazgo sgpHallazgo = hallazgoService.findById(351);
			SgpFlujoHallazgo sgpFlujoHallazgo = new SgpFlujoHallazgo();
			
			flujoHallazgoService.guardarFlujoHallazgo(flujoHallazgodto, sgpHallazgo, sgpFlujoHallazgo);
		}
		catch(Exception e){
          fail(e.getMessage());
		}
	}


	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 */
	@Test
	public void getFlujoHallazgo() {
		try{
		FlujoHallazgoDTO flujoHallazgo = flujoHallazgoService.getFlujoHallazgo(350);
		assertNotNull(flujoHallazgo);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 */
	@Test
	public void editarFlujoHallazgo(){
		try{
		FlujoHallazgoDTO flujoHallazgoDTO = flujoHallazgoService.getFlujoHallazgo(350);
		flujoHallazgoDTO.setObservacion("Observación de prueba");
		flujoHallazgoService.editarFlujoHallazgo(flujoHallazgoDTO);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}

}
