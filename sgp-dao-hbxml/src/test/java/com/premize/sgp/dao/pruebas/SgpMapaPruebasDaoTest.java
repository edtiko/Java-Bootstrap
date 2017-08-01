package com.premize.sgp.dao.pruebas;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.log4j.Level;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.gestion.pruebas.MapaPruebasDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ReporteMapaPruebaDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndReprocesoConstruccionPruebasDTO;
import com.premize.sgp.utils.LogUtil;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-dao-hbxml
 * @class SgpMapaPruebasDaoTest
 * @description
 * @date 14/04/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
@Transactional
public class SgpMapaPruebasDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private MapaPruebasDao mapaPruebasDao;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/04/2014
	 * @description
	 */
	@Test
	public void consultarCasosDePrueba() {
		Integer idMapaPrueba = 30;
		try {
			List<ReporteMapaPruebaDTO> casosDePrueba = mapaPruebasDao
					.consultarCasosDePrueba(idMapaPrueba);
			Assert.assertNotNull(casosDePrueba);
			LogUtil.log(SgpMapaPruebasDaoTest.class.getName(), "Prueba exitosa", Level.INFO, null);
		} catch (AppBaseException ex) {
			LogUtil.log(SgpMapaPruebasDaoTest.class.getName(), ex.getMessage(), Level.ERROR, ex);
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description
	 */
	@Test
	public void consultarCasosPruebaHallazgos() {
		Integer idMapaPrueba = 30;
		try {
			List<ReporteMapaPruebaDTO> casosPrueba = mapaPruebasDao
					.consultarCasosPruebaHallazgos(idMapaPrueba);
			Assert.assertNotNull(casosPrueba);
		} catch (AppBaseException ex) {
			LogUtil.log(SgpMapaPruebasDaoTest.class.getName(), ex.getMessage(), Level.ERROR, ex);
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description
	 */
	@Test
	public void consultarAvanceEjecucionMapas() {
		Integer idProyecto = 1;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idProyecto", idProyecto);
		try {
			List<MapaPruebasDTO> mapas = mapaPruebasDao
					.consultarAvanceEjecucionMapas(parametros);
			Assert.assertNotNull(mapas);
			LogUtil.log(SgpMapaPruebasDaoTest.class.getName(), "Prueba exitosa", Level.INFO, null);
		} catch (AppBaseException ex) {
			LogUtil.log(SgpMapaPruebasDaoTest.class.getName(), ex.getMessage(), Level.ERROR, ex);
			fail(ex.getMessage());
		} catch (ParseException ex) {
			fail(ex.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description
	 */
	@Test
	public void consultarMapaPruebasReporte() {
		Integer idProyecto = 1;
		Map<String, Object> filtros = new HashMap<String, Object>();
		filtros.put("idProyecto", idProyecto);
		try {
			List<ReporteMapaPruebaDTO> listaMapas = mapaPruebasDao.consultarMapaPruebasReporte(filtros);
			Assert.assertNotNull(listaMapas);
			if(!listaMapas.isEmpty()) {
				Assert.assertEquals(listaMapas.get(0).getIdProyecto(), idProyecto);
			}
			LogUtil.log(SgpMapaPruebasDaoTest.class.getName(), "Prueba exitosa: consultarMapaPruebasZip()", Level.INFO, null);
			
		} catch (AppBaseException ex) {
			LogUtil.log(SgpMapaPruebasDaoTest.class.getName(),ex.getMessage(),Level.ERROR, ex);
			fail(ex.getMessage());
		} catch (ParseException ex) {
			LogUtil.log(SgpMapaPruebasDaoTest.class.getName(),ex.getMessage(),Level.ERROR, ex);
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014
	 * @description
	 */
	@Test
	public void getRecordsAvanceEjecucion() {
		try {
			String search = null;
			Integer displayStart = 0;
			Integer displaySize = 10;
			Integer pageNumber = 1;
			List<SortField> sortFields = new ArrayList<SortField>();
			sortFields.add(new SortField("id", "asc"));
			List<Search> searchFields = new ArrayList<Search>();
			
			PagingCriteria criteria = new PagingCriteria(search, searchFields, displayStart, displaySize, pageNumber, sortFields);
			ResultSet<MapaPruebasDTO> result = mapaPruebasDao.getRecordsAvanceEjecucion(criteria);
			Assert.assertNotNull(result);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		} catch (ParseException ex) {
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 17/07/2014
	 * @description
	 */
	@Test
	public void consultarIndicadorReprocesoConstruccionPruebas() {
		Integer idProyecto = 19;
		Date fechaFrom = null;
		Date fechaTo = null;
		
		try {
			IndReprocesoConstruccionPruebasDTO result = mapaPruebasDao
					.consultarIndicadorReprocesoConstruccionPruebas(idProyecto, fechaFrom, fechaTo);
			Assert.assertNotNull(result);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
	}

}
