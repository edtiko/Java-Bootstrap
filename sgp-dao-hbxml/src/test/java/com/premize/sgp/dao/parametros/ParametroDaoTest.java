/**
 * 
 */
package com.premize.sgp.dao.parametros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.test.AbstractDaoTest;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpPaisDaoTest
 * @since 30/01/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class ParametroDaoTest extends AbstractDaoTest {
	
	private static final String SGP_TABLE = "sgp_parametro";
	
	private static final Integer SGP_ID = 1;
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private TipoParametroDao tipoParametroDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @throws AppBaseException
	 */
	@Test
	public void findByAll() throws AppBaseException {
		
		Assert.assertNotNull(parametroDao.getListParametros());
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws Exception
	 */
	public void salvar_TipoIdentificacionSinParametros() throws Exception {
		int registrosAntes = super.countRowsInTable(SGP_TABLE);
		SgpParametro sgpParametro = new SgpParametro();
		SgpTipoParametro sgpTipoParametro = tipoParametroDao.get(1);
		sgpParametro.setId(SGP_ID);
		sgpParametro.setDescripcion("descripcion");
		sgpParametro.setSgpTipoParametro(sgpTipoParametro);
		sgpParametro.setNombre("nombre");
		sgpParametro.setValor("valor");
		sgpParametro.setIndActivo(1);
		sgpParametro.setFecCreacion(Calendar.getInstance().getTime());
		sgpParametro.setUsuarioCrea("");
		
		try {
			this.parametroDao.save(sgpParametro);
			flush();
			assertEquals(registrosAntes + 1, super.countRowsInTable(SGP_TABLE));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
