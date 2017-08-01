/**
 * 
 */
package com.premize.sgp.dao.pruebas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-dao-hbxml
 * @class SgpMapaArtefactoDaoTest
 * @since 12/02/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class SgpMapaArtefactoDaoTest {
	
	@Autowired
	MapaArtefactoDao artefactoDao;
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 */
	@Test
	public void test_desasociarArtefactosMapa() {
		Integer idMapa = 1;
		try {
			artefactoDao.desasociarArtefactosMapa(idMapa, "admin");
		} catch (AppBaseException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 */
	@Test
	public void test_asociarArtefactosPorMapa() {
		List<SgpMapaArtefacto> artefactos = new ArrayList<SgpMapaArtefacto>();
		SgpMapaArtefacto artefacto = new SgpMapaArtefacto();
		artefacto.setFecCreacion(Calendar.getInstance().getTime());
		artefacto.setUsuarioCrea("admin");
		
		artefactos.add(artefacto);
		try {
			artefactoDao.asociarArtefactosPorMapa(artefactos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
