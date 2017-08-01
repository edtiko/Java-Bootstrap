package com.premize.sgp.dao.parametros;

/**
 * Prueba para Departamento Autor John Hawer Bernal
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.sgp.dao.test.AbstractDaoTest;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;
import com.premize.sgp.modelo.entities.parametros.SgpPais;

/**
 * @author <a href="mailto:jhonh.bernal@premize.com">John Hawer Bernal Gonzalez;
 * @project sgp-dao-hbxml
 * @class DepartamentoDaoTest
 * @since 17/02/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class DepartamentoDaoTest extends AbstractDaoTest {
	
	private static final String SGP_TABLE = "sgp_departamento";
	private static final Integer SGP_ID = 1;
	
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
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @throws Exception
	 */
	@Test
	public void EditarDepartamento() throws Exception {
		
		SgpDepartamento sgpDepartamento = departamentoDao.get(1);
		SgpPais sgpPais = paisDao.get(1);
		sgpDepartamento.setDescripcion("descripcion");
		sgpDepartamento.setNombre("nombre");
		sgpDepartamento.setSgpPais(sgpPais);
		sgpDepartamento.setFecEdita(Calendar.getInstance().getTime());
		sgpDepartamento.setUsuarioEdita("John Hawer Bernal");
		
		try {
			this.departamentoDao.update(sgpDepartamento);
			flush();
			
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
	public void guardarDepartamento_test() throws Exception {
		int registrosAntes = super.countRowsInTable(SGP_TABLE);
		SgpPais sgpPais = paisDao.get(1);
		SgpDepartamento sgpDepartamento = new SgpDepartamento();
		
		sgpDepartamento.setId(SGP_ID);
		sgpDepartamento.setDescripcion("descripcion");
		sgpDepartamento.setNombre("nombre");
		sgpDepartamento.setSgpPais(sgpPais);
		sgpDepartamento.setIndActivo(1);
		sgpDepartamento.setFecCreacion(Calendar.getInstance().getTime());
		sgpDepartamento.setUsuarioCrea("John Hawer");
		
		try {
			departamentoDao.save(sgpDepartamento);
			flush();
			assertEquals(registrosAntes + 1, super.countRowsInTable(SGP_TABLE));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
