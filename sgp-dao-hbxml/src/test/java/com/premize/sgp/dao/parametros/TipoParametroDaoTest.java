/**
 * 
 */
package com.premize.sgp.dao.parametros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.test.AbstractDaoTest;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpPaisDaoTest
 * @since 30/01/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class TipoParametroDaoTest extends AbstractDaoTest {
	
	private static final String SGP_TABLE = "sgp_tipo_parametro";
	
	private static final Integer SGP_ID = 1;
	
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
	 */
	private static final RowMapper<SgpTipoParametro> SGP_MAPPER = new RowMapper<SgpTipoParametro>() {
		public SgpTipoParametro mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			SgpTipoParametro sgpTipoParametro = new SgpTipoParametro(1, "nombre", 1, Calendar.getInstance().getTime(),
					"");
			return sgpTipoParametro;
		}
	};
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @throws AppBaseException
	 */
	@Test
	public void findByAll() throws AppBaseException {
		
		Assert.assertNotNull(tipoParametroDao.getTipos());
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws Exception
	 */
	@Test
	public void salvar_TipoIdentificacionSinParametros() throws Exception {
		int registrosAntes = super.countRowsInTable(SGP_TABLE);
		SgpTipoParametro sgpTipoParametro = new SgpTipoParametro();
		
		sgpTipoParametro.setId(SGP_ID);
		sgpTipoParametro.setDescripcion("descripcion");
		sgpTipoParametro.setNombre("nombre");
		sgpTipoParametro.setIndActivo(1);
		sgpTipoParametro.setFecCreacion(new Date());
		sgpTipoParametro.setUsuarioCrea("");
		
		try {
			this.tipoParametroDao.save(sgpTipoParametro);
			flush();
			assertEquals(registrosAntes + 1, super.countRowsInTable(SGP_TABLE));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
