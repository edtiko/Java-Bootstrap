/**
 * 
 */
package com.premize.sgp.dao.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.gestion.pruebas.FlujoHallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.HallazgoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalesHallazgoDTO;
import com.premize.sgp.dto.pruebas.indicadores.CalidadDocumentacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadFuncionalidadDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndEfectividadHallazgosReportadosDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndMejorasIdentificadasDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.utils.DateUtils;
/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpPaisDaoTest
 * @since 30/01/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class HallazgoDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private HallazgoDao hallazgoDao;
	
	@Autowired
	private FlujoHallazgoDao flujoHallazgoDao;
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @throws ParseException 
	 * @since 24/04/2014
	 */
	@Test
	public void getTotalesHallazgoProyecto_conparametros() throws AppBaseException, ParseException{
		List<TotalesHallazgoDTO> total = hallazgoDao.getTotalesHallazgoProyecto(34, "2014/04/11", "2014/04/11","1");
		assertNotNull(total);
		assertTrue(total.size() > 0);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @throws ParseException 
	 * @since 24/04/2014
	 */
	@Test
	public void getTotalesHallazgoProyecto_null() throws AppBaseException, ParseException{
		List<TotalesHallazgoDTO> total = hallazgoDao.getTotalesHallazgoProyecto(34, null, null, null);
		assertNotNull(total);
		assertTrue(total.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 2/05/2014
	 * @throws AppBaseException
	 */
	@Test
	public void getHallazgosPorCasoPrueba_conparametros() throws AppBaseException{
		List<SgpHallazgo> resultado = hallazgoDao.getHallazgosPorCasoPrueba(56);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 2/05/2014
	 * @throws AppBaseException
	 */
	@Test
	public void getEstadosHallazgo_conparametro()throws AppBaseException{
		List<ParametroDTO> resultado = hallazgoDao.getEstadosHallazgo(351);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 2/05/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void getHallazgosPorSeveridad_conparametro()throws AppBaseException, ParseException{
		List<ReporteHallazgoDTO> list = hallazgoDao.getHallazgosPorSeveridad(34,  "2014/04/11", "2014/04/11", "1");
		assertNotNull(list);
		assertTrue("Sin resultado",list.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 2/05/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	public void getHallazgosPorMapaPruebas_conparametros() throws AppBaseException, ParseException{
		List<ReporteHallazgoDTO> list = hallazgoDao.getHallazgosPorMapaPruebas(1,  "2014/04/11", "2014/04/11", "1");
		assertNotNull(list);
		assertTrue("Sin resultado",list.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 8/07/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void getHallazgosPorArtefacto_null() throws AppBaseException, ParseException{
		List<ReporteHallazgoDTO> resultado = hallazgoDao.getHallazgosPorArtefacto(1, null, null, null);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	public void getHallazgosPorArtefacto_conparametros() throws AppBaseException, ParseException{
		List<ReporteHallazgoDTO> resultado = hallazgoDao.getHallazgosPorArtefacto(1, "2014/04/11", "2014/04/11", "1");
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 5/05/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void getHallazgosPorSeveridad_null() throws AppBaseException, ParseException
	{
		int idProyecto = 1;	
		List<ReporteHallazgoDTO> total = hallazgoDao.getHallazgosPorSeveridad(idProyecto, null, null, null);
		assertNotNull(total);
		assertTrue(total.size() > 0);
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 5/05/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void getHallazgosPorMapaPruebas_null() throws AppBaseException, ParseException
	{
		int idProyecto = 1;
		List<ReporteHallazgoDTO> total = hallazgoDao.getHallazgosPorMapaPruebas(idProyecto, null, null, null);
		assertNotNull(total);
		assertTrue(total.size() > 0);
	}
	
	public void consultarHallazgos_conparametros() throws AppBaseException{
		HashMap<String, Object> filtros = new HashMap<String, Object>();
		filtros.put("proyecto",     "2");
		filtros.put("mapaPrueba",   "2");
		filtros.put("causaAnula",   "1");
		filtros.put("causaGenera",  "2");
		filtros.put("usuarioAsigna","Edw");
		//filtros.put("fechaFrom",    request.getParameter("fechaFrom"));
		//filtros.put("fechaTo",      request.getParameter("fechaTo"));
		List<ConsultaHallazgoDTO> resultado = hallazgoDao.consultarHallazgos(filtros);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
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
		ResultSet<FlujoHallazgoDTO> resultado =  flujoHallazgoDao.getRecords(criteria);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.getRows().size() > 0);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @throws AppBaseException 
	 * @since 16/07/2014
	 */
	@Test
	public void getHallazgosPorRecurso_conparametros() throws AppBaseException, ParseException{
		
		List<Integer> listEstados = new ArrayList<Integer>();
		listEstados.add(24);
		listEstados.add(25);
		List<Integer> listCausas = new ArrayList<Integer>();
		listCausas.add(9);
		List<ReporteHallazgoDTO> resultado = hallazgoDao.getHallazgosPorRecurso("1",  "2014/04/11", "2014/07/16", listEstados, listCausas);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @throws AppBaseException 
	 * @since 16/07/2014
	 */
	@Test
	public void getHallazgosPorRecurso_null() throws AppBaseException, ParseException{
		
		List<Integer> listEstados = new ArrayList<Integer>();
		listEstados.add(24);
		listEstados.add(25);
		List<Integer> listCausas = new ArrayList<Integer>();
		listCausas.add(9);
		List<ReporteHallazgoDTO> resultado = hallazgoDao.getHallazgosPorRecurso("5",  null, null, listEstados, listCausas);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 16/07/2014
	 */
	@Test
	public void getSeveridadColorHallazgo_conparametros() throws AppBaseException{
		String usuario = "admin";
		List<ConsultaHallazgoDTO> resultado = hallazgoDao.getSeveridadColorHallazgo(usuario);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 16/07/2014
	 */
	@Test
	public void getSeveridadColorHallazgo_null() throws AppBaseException{
		String usuario = null;
		List<ConsultaHallazgoDTO> resultado = hallazgoDao.getSeveridadColorHallazgo(usuario);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @throws ParseException 
	 * @since 16/07/2014
	 */
	@Test
	public void consultarMejorasIndentificadas_conparametros() throws AppBaseException, ParseException{
		
		Date fechaIn = DateUtils.getDateFromString("yyyy/MM/dd", "2014/07/01", true);
		Date fechaFrom = DateUtils.getDateFromString("yyyy/MM/dd", "2014/07/30", false);
		List<IndMejorasIdentificadasDTO> resultado = hallazgoDao.consultarMejorasIndentificadas(19, fechaIn, fechaFrom);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @throws ParseException 
	 * @since 16/07/2014
	 */
	@Test
	public void consultarMejorasIndentificadas_null() throws AppBaseException, ParseException{
		
		Date fechaIn = null;
		Date fechaFrom = null;
		List<IndMejorasIdentificadasDTO> resultado = hallazgoDao.consultarMejorasIndentificadas(19, fechaIn, fechaFrom);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 16/07/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void consultarIndicadorEfectividadHallazgosReportados_conparametros() throws AppBaseException, ParseException{
		
		Date fechaFrom = DateUtils.getDateFromString("yyyy/MM/dd", "2014/07/01", true);
		Date fechaTo = DateUtils.getDateFromString("yyyy/MM/dd", "2014/07/30", false);
		IndEfectividadHallazgosReportadosDTO resultado = hallazgoDao.consultarIndicadorEfectividadHallazgosReportados(19, fechaFrom, fechaTo);
		assertNotNull(resultado);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 16/07/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void consultarIndicadorEfectividadHallazgosReportados_null() throws AppBaseException, ParseException{
		
		Date fechaFrom = null;
		Date fechaTo = null;
		IndEfectividadHallazgosReportadosDTO resultado = hallazgoDao.consultarIndicadorEfectividadHallazgosReportados(19, fechaFrom, fechaTo);
		assertNotNull(resultado);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @throws AppBaseException 
	 * @since 16/07/2014
	 */
	@Test
	public void consultarIndicadorFuncionalidad_conparametros() throws ParseException, AppBaseException{
		
		Date fechaFrom = DateUtils.getDateFromString("yyyy/MM/dd", "2014/07/01", true);
		Date fechaTo = DateUtils.getDateFromString("yyyy/MM/dd", "2014/07/30", false);
		List<IndCalidadFuncionalidadDTO> resultado = hallazgoDao.consultarIndicadorFuncionalidad(19, fechaFrom, fechaTo);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @throws AppBaseException 
	 * @since 16/07/2014
	 */
	@Test
	public void consultarIndicadorFuncionalidad_null() throws ParseException, AppBaseException{
		
		Date fechaFrom = null;
		Date fechaTo = null;
		List<IndCalidadFuncionalidadDTO> resultado = hallazgoDao.consultarIndicadorFuncionalidad(19, fechaFrom, fechaTo);
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 16/07/2014
	 * @throws ParseException
	 * @throws AppBaseException
	 */
	@Test
	public void consultarIndicadorCalidadDocumentacion_conparametros() throws ParseException, AppBaseException{
		
		Date fechaFrom = DateUtils.getDateFromString("yyyy/MM/dd", "2014/07/01", true);
		Date fechaTo =   DateUtils.getDateFromString("yyyy/MM/dd", "2014/07/30", false);
		List<CalidadDocumentacionDTO> resultado = hallazgoDao.consultarIndicadorCalidadDocumentacion(19, fechaFrom, fechaTo);
		
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 16/07/2014
	 * @throws ParseException
	 * @throws AppBaseException
	 */
	@Test
	public void consultarIndicadorCalidadDocumentacion_null() throws ParseException, AppBaseException{
		
		Date fechaFrom = null;
		Date fechaTo =   null;
		List<CalidadDocumentacionDTO> resultado = hallazgoDao.consultarIndicadorCalidadDocumentacion(19, fechaFrom, fechaTo);
		
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}

}

