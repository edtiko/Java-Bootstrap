/**
 * 
 */
package com.premize.sgp.dao.parametros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.modelo.entities.parametros.SgpPais;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-dao-hbxml
 * @class SgpPaisDaoTest
 * @since 21/01/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class PaisDaoTest extends AbstractDaoTest {
	
	private static final String SGP_PAIS_TABLE = "sgp_pais";
	
	private static final Integer SGP_PAIS_ID = 1;
	
	@Autowired
	private PaisDao paisDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 21/01/2014
	 */
	private static final RowMapper<SgpPais> SGP_PAIS_MAPPER = new RowMapper<SgpPais>() {
		public SgpPais mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			SgpPais sgpPais = new SgpPais(999, "pais", 1, new Date(), "");
			return sgpPais;
		}
	};
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 21/01/2014
	 * @throws AppBaseException
	 */
	@Test
	public void findByAll() throws AppBaseException {
		
		Assert.assertNotNull(this.paisDao.consultarPaises());
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 */
	@Test
	public void grabarPais() {
		int registrosAntes = super.countRowsInTable(SGP_PAIS_TABLE);
		SgpPais sgpPais = new SgpPais();
		sgpPais.setId(SGP_PAIS_ID);
		sgpPais.setDescripcion("descripcion");
		sgpPais.setNombre("nombre");
		sgpPais.setIndActivo(1);
		sgpPais.setFecCreacion(new Date());
		sgpPais.setUsuarioCrea("");
		
		try {
			this.paisDao.save(sgpPais);
			flush();
			assertEquals(registrosAntes + 1, super.countRowsInTable(SGP_PAIS_TABLE));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws AppBaseException
	 */
	@Test
	public void consultaHSQL() throws AppBaseException {
		List<PaisDTO> paises = paisDao.getRecordsByHSQL();
		Assert.assertNotNull(paises);
	}
}
