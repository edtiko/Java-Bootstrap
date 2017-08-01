/**
 * 
 */
package com.premize.sgp.dao.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.sgp.dao.gestion.pruebas.CasoPruebaDao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.utils.LogUtil;

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @project sgp-dao-hbxml
 * @class CasoPruebaDaoTest
 * @since 5/05/2014
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class CasoPruebaDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private CasoPruebaDao casoPruebaDao;
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 5/05/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void getListCasosDePruebas() throws AppBaseException, ParseException{
		List<SgpCasoPrueba> total = casoPruebaDao.getListCasosDePruebas();
		assertNotNull(total);
		assertTrue(total.size() > 0);
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 5/05/2014
	 */
	@Test
	public void getRecords(){
		
		try{
		String search = null;
		Integer displayStart = 0;
		Integer displaySize = 10;
		Integer pageNumber = 1;
		List<SortField> sortFields = new ArrayList<SortField>();
		sortFields.add(new SortField("id", "asc"));
		List<Search> searchFields = new ArrayList<Search>();
		
		
		PagingCriteria criteria = new PagingCriteria(search, searchFields, displayStart, displaySize, pageNumber, sortFields);
		ResultSet<CasoDePruebaDTO> resultado =  casoPruebaDao.getRecords(criteria);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.getRows().size() > 0);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 5/06/2014
	 * @description
	 */
	@Test
	public void consultarConsecutivo() {
		Integer idmapa = 30;
		try {
			Integer consecutivo = casoPruebaDao.consultarConsecutivo(idmapa);
			LogUtil.log(getClass(), Level.INFO, consecutivo+"", null);
			Assert.assertNotNull(consecutivo);
		} catch (AppBaseException ex) {
			
		}
	}
}

