package com.premize.sgp.dao.anexo;

import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Level;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.pruebas.AnexoHallazgoDTO;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;
import com.premize.sgp.utils.LogUtil;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-dao-hbxml
 * @class AnexoHallazgoTest
 * @description
 * @date 3/06/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class AnexoHallazgoDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private AnexoHallazgoDao anexoHallazgoDao;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/06/2014
	 * @description
	 */
	@Test
	public void getAnexosContainsRuta() {
		try {
			String ruta = "Dato 1.docx";
			List<AnexoHallazgoDTO> anexos = anexoHallazgoDao.getAnexosContainsName(ruta.toLowerCase());
			LogUtil.log(getClass(), Level.INFO, "List-Anexos = "+anexos.size(), null);
			Assert.assertNotNull(anexos);
		} catch (AppBaseException ex) {
			LogUtil.log(getClass(), Level.ERROR, ex.getMessage(), ex);
			fail(ex.getMessage());
		}
	}
}
