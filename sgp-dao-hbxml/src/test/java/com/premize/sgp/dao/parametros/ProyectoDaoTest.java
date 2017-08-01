/**
 * 
 */
package com.premize.sgp.dao.parametros;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.usuarios.UsuarioProyectoDao;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-dao-hbxml
 * @class ProyectoDaoTest
 * @since 14/02/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class ProyectoDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final Integer SGP_ID = 1;
	
	@Autowired
	private ProyectoDao dao;
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Autowired
	private ProyectoDao proyectoDao;
	
	@Autowired
	private EmpresaDao empresaDao;
	
	
	@Autowired
	private UsuarioProyectoDao usuarioProyectoDao;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @throws AppBaseException
	 */
	@Test
	public void findByAll() throws AppBaseException {
		
		Assert.assertNotNull(proyectoDao.getListProyectos());
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @throws Exception
	 */
	@Test
	public void guardarProyecto() throws Exception {

		SgpProyecto sgpProyecto = new SgpProyecto();
		SgpEmpresa sgpEmpresa = empresaDao.get(1);
		
		sgpProyecto.setId(SGP_ID);
		sgpProyecto.setDescripcion("descripcion");
		sgpProyecto.setNombre("nombre");
		sgpProyecto.setSgpEmpresa(sgpEmpresa);
		sgpProyecto.setIndActivo(1);
		sgpProyecto.setFecCreacion(Calendar.getInstance().getTime());
		sgpProyecto.setUsuarioCrea("Ed Gomez");
		
		try {
			proyectoDao.save(sgpProyecto);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @throws Exception
	 */
	@Test
	public void EditarProyecto() throws Exception {
		
		SgpProyecto sgpProyecto = proyectoDao.get(SGP_ID);
		SgpEmpresa sgpEmpresa = empresaDao.get(1);
		
		sgpProyecto.setId(SGP_ID);
		sgpProyecto.setDescripcion("descripcion");
		sgpProyecto.setNombre("nombre");
		sgpProyecto.setSgpEmpresa(sgpEmpresa);
		sgpProyecto.setIndActivo(1);
		sgpProyecto.setFecEdita(Calendar.getInstance().getTime());
		sgpProyecto.setUsuarioEdita("Ed Gomez");
		
		try {
			this.proyectoDao.update(sgpProyecto);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 */
	@Test
	public void test_desasociarUsuariosProyecto() {
		try {
			Integer idProyecto = 2;
			usuarioProyectoDao.desasociarUsuariosProyecto(idProyecto, "admin");
			
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 */
	@Test
	public void test_asociarArtefactosPorMapa() {
		try {
			
			List<SgpUsuarioProyecto> usuario = new ArrayList<SgpUsuarioProyecto>();
			SgpUsuarioProyecto usuarioProyecto = new SgpUsuarioProyecto();
			usuarioProyecto.setFecCreacion(Calendar.getInstance().getTime());
			usuarioProyecto.setIndActivo(1);
			usuarioProyecto.setUsuarioCrea("admin");
			usuarioProyecto.setSgpProyecto(new SgpProyecto());
			usuarioProyecto.setSgpUsuario(new SgpUsuario());
			
			usuario.add(usuarioProyecto);
			usuarioProyectoDao.asociarArtefactosPorMapa(usuario);
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
