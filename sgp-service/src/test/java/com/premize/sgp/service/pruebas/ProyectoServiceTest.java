/**
 * 
 */
package com.premize.sgp.service.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.parametros.ProyectoDao;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.dto.pruebas.ProyectoDTO;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ProyectoServiceTest
 * @since 6/02/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class ProyectoServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private ProyectoDao proyectoDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @throws AppBaseException
	 */
	@Test
	public void EditarProyecto() throws AppBaseException {
		
		ProyectoDTO proyectoDTO = new ProyectoDTO();
		
		proyectoDTO = proyectoService.getProyecto(1);
		
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(1);
		
		empresaDTO.setNombre("Premize");
		proyectoDTO.setId(1);
		proyectoDTO.setNombre("Ed Gómez");
		proyectoDTO.setDescripcion("Desceipcion");
		proyectoDTO.setUsuarioCreacion("UsuarioTest");
		proyectoDTO.setEmpresa(empresaDTO);
		
		try {
			proyectoService.editarProyecto(proyectoDTO);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @throws AppBaseException
	 */
	@Test
	public void guardarProyecto() throws AppBaseException {
		
		ProyectoDTO proyectoDTO = new ProyectoDTO();
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(1);
		empresaDTO.setNombre("Premize");
		proyectoDTO.setNombre("Ed Gómez");
		proyectoDTO.setDescripcion("Desceipcion");
		proyectoDTO.setUsuarioCreacion("UsuarioTest");
		proyectoDTO.setIndActivo(1);
		proyectoDTO.setNumeroEstado(1);
		proyectoDTO.setFecCreacion(new Date());
		proyectoDTO.setUsuarioCreacion("usuarioCreacion");
		proyectoDTO.setEmpresa(empresaDTO);
		
		try {
			proyectoService.guardarProyecto(proyectoDTO);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @throws AppBaseException
	 */
	@Test
	public void getProyecto(){

		ProyectoDTO proyecto = null;
		try {
			proyecto = proyectoService.getProyecto(null);
			assertNull(proyecto);
			proyecto = proyectoService.getProyecto(1);
			assertNotNull(proyecto);
		} catch (AppBaseException e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws  
	 * @since 7/05/2014
	 */
	@Test
	public void actualizarUsuariosPorProyecto(){
		try{
			List<String> usuarios = new ArrayList<String>();
			usuarios.add("1");
			usuarios.add("2");
			usuarios.add("3");
			usuarios.add("4");
			String idProyecto= "19";
			String login = "Alexander.Filigrana";
			proyectoService.actualizarUsuariosPorProyecto(usuarios, idProyecto, login);
		}
		catch(Exception e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/06/2014
	 * @description
	 */
	@Test
	public void actualizarUsuariosPorProyectoEmptyUsuariosAsociados() {
		try{
			List<String> usuarios = new ArrayList<String>();
			usuarios.add("1");
			usuarios.add("2");
			usuarios.add("3");
			usuarios.add("4");
			String idProyecto= "-1";
			String login = "Alexander.Filigrana";
			proyectoService.actualizarUsuariosPorProyecto(usuarios, idProyecto, login);
		}
		catch(Exception e){
			fail(e.getMessage());
		}
	}

}
