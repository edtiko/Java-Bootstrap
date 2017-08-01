/**
 * 
 */
package com.premize.sgp.service.parametros;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.parametros.DepartamentoDao;
import com.premize.sgp.dao.parametros.PaisDao;
import com.premize.sgp.dto.parametros.DepartamentoDTO;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;
import com.premize.sgp.modelo.entities.parametros.SgpPais;
import com.premize.sgp.service.test.AbstractServiceTest;

/**
 * @author <a href="mailto:jhonh.bernal@premize.com">John Hawer Bernal Gonzalez;
 * @project sgp-service
 * @class DepartamentoServiceTest
 * @since 18/02/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class DepartamentoServiceTest extends AbstractServiceTest {
	
	private static final String SGP_TABLE = "sgp_departamento";
	private static final Integer SGP_ID = 1;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@Autowired
	private DepartamentoDao departamentoDao;
	
	@Autowired
	private PaisDao paisDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws Exception
	 */
	public void EditarDepartamento() throws Exception {
		
		DepartamentoDTO departamentoDTO = new DepartamentoDTO();
		departamentoDTO = departamentoService.getDepartamento(1);
		
		PaisDTO paisDTO = new PaisDTO();
		paisDTO.setId(1);
		
		paisDTO.setNombre("PruebaPais");
		departamentoDTO.setId(1);
		departamentoDTO.setDescripcion("descripcion");
		departamentoDTO.setNombre("nombre");
		departamentoDTO.setPais(paisDTO);
		departamentoDTO.setFechaEdita(Calendar.getInstance().getTime());
		departamentoDTO.setUsuarioEdita("John Hawer Bernal");
		
		try {
			
			departamentoService.editarDepartamento(departamentoDTO);
			flush();
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws Exception
	 */
	@Test
	public void guardarDepartamento() throws Exception {
		
		SgpPais sgpPais = paisDao.get(1);
		SgpDepartamento sgpDepartamento = new SgpDepartamento();
		
		sgpDepartamento.setId(1);
		sgpDepartamento.setDescripcion("descripcion");
		sgpDepartamento.setNombre("nombre");
		sgpDepartamento.setSgpPais(sgpPais);
		sgpDepartamento.setIndActivo(1);
		sgpDepartamento.setFecCreacion(Calendar.getInstance().getTime());
		sgpDepartamento.setUsuarioCrea("John Hawer");
		
		try {
			departamentoDao.save(sgpDepartamento);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws Exception
	 */
	@Test
	public void getDepartamento() throws Exception {
		guardarDepartamento();
		DepartamentoDTO departamento = null;
		try {
			departamento = departamentoService.getDepartamento(null);
			assertNull(departamento);
			departamento = departamentoService.getDepartamento(1);
			assertNotNull(departamento);
		} catch (AppBaseException e) {
			fail(e.getMessage());
		}
		
	}
}
