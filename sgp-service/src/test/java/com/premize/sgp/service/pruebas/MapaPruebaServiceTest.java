package com.premize.sgp.service.pruebas;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.log4j.Level;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ReporteMapaPruebaDTO;
import com.premize.sgp.utils.ListSelector;
import com.premize.sgp.utils.LogUtil;
import com.premize.sgp.utils.ReporteExcelCons;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class MapaPruebaServiceTest
 * @description
 * @date 14/04/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class MapaPruebaServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String DIRECTORIO_WIN = "c:\\zipTest"; 
	
	@Autowired
	private MapaPruebaService mapaPruebaService;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014
	 * @description
	 */
	@Test
	public void generarMatrizContruccionPruebasExcel() {
		Integer idMapaPrueba = 30;
		try {
			Map<String, Object> reporte = mapaPruebaService.generarMatrizContruccionPruebasExcel(idMapaPrueba);
			Assert.assertNotNull(reporte);
			Assert.assertNotNull(reporte.get(ReporteExcelCons.KEY_MAP_REPORTE));
		} catch (AppBaseException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/05/2014
	 * @description
	 */
	@Test
	public void generarReporteAvanceEjecucionMapas() {
		Integer idProyecto = 1;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idProyecto", idProyecto);
		try {
			Map<String, Object> reporteAvance = mapaPruebaService.generarReporteAvanceEjecucionMapas(parametros);
			Assert.assertNotNull(reporteAvance);
			Assert.assertFalse(reporteAvance.isEmpty());
			Assert.assertNotNull(reporteAvance.get(ReporteExcelCons.KEY_MAP_REPORTE));
		} catch (AppBaseException ex) {
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
	public void generarMapasZip() {
		Integer idProyecto = 1;
		Map<String, Object> filtros = new HashMap<String, Object>();
		filtros.put("idProyecto", idProyecto);
		
		try {
			Map<String, Object> zipData = mapaPruebaService.generarMapasZip(filtros, DIRECTORIO_WIN);
			Assert.assertNotNull(zipData);
			Object zipStream = zipData.get("zip");
			Assert.assertNotNull(zipStream);
			Object zipName = zipData.get("zipName");
			Assert.assertNotNull(zipName);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		} catch (ParseException ex) {
			fail(ex.getMessage());
		} catch (IOException ioex) {
			fail(ioex.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description
	 */
	@Test
	public void getListSelectors() {
		List<ReporteMapaPruebaDTO> data = buildReporteMapa();
		try {
			List<ListSelector> selectorListaMapas = mapaPruebaService.getListSelectors(data);
			Assert.assertNotNull(selectorListaMapas);
			Assert.assertEquals(selectorListaMapas.size(), 5);
			Assert.assertNotNull(selectorListaMapas.get(4).getEnd());
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/05/2014
	 * @description
	 */
	@Test
	public void deleteZipFolder() {
		try {
			File directorio = new File(DIRECTORIO_WIN + File.separator + MapaPruebaService.ZIP_FOLDER);
			if(!directorio.exists()) {
				directorio.mkdirs();
			}
			Thread.sleep(5000);
			mapaPruebaService.deleteZipFolder(DIRECTORIO_WIN);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		} catch (IOException ex) {
			fail(ex.getMessage());
		} catch (InterruptedException e) {
			LogUtil.log(this.getClass(), Level.ERROR, e.getMessage(), e);
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
			ResultSet<MapaPruebasDTO> result = mapaPruebaService.getRecordsEjecucion(criteria);
			Assert.assertNotNull(result);
		} catch (AppBaseException ex) {
			LogUtil.log(getClass(), Level.ERROR, ex.getMessage(), ex);
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
	 * @return
	 */
    private List<ReporteMapaPruebaDTO> buildReporteMapa() {
        List<ReporteMapaPruebaDTO> listaCasosPrueba = new ArrayList<ReporteMapaPruebaDTO>();
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(1, "Mapa Prueba Test 1", 1,
                33, "Artefacto 1 bbbbbbbbbbb", "DSDSD", "Esperada", "DSDSA", new Date(),
                "admin", null, null, null, null, "Mapa Prueba 1",
                1, "prueba1", "otra prueba1", "No Conformidad", "Funcional", "Baja",
                "Valeria.Vasquez", "Actualización del Ambiente",
                "El cliente lo asumirá administrativamente", "Cerrado", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(1, "Mapa Prueba Test 1", 1,
                33, "Artefacto 1 bbbbbbbbbbb", "DSDSD", "Esperada", "DSDSA", new Date(),
                "admin", null, null, null, null, "Mapa Prueba 1",
                2, "prueba1", "otra prueba1", "No Conformidad", "Funcional", "Baja",
                "Valeria.Vasquez", "Actualización del Ambiente",
                "El cliente lo asumirá administrativamente", "Cerrado", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(1, "Mapa Prueba Test 1", 1,
                34, "Artefacto 1 bbbbbbbbbbb",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5, "sfdsf", "sdfsdf", "No Conformidad", "Bloqueante", "Media",
                "Valeria.Vasquez", "Actualización del Ambiente", "Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(2, "Mapa Prueba Test 2", 1,
                34, "Artefacto 2",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5, "sfdsf", "sdfsdf", "No Conformidad", "Bloqueante", "Media",
                "Valeria.Vasquez", "Actualización del Ambiente", "Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(3, "Mapa Prueba Test 3", 1,
                34, "Artefacto 3",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5, "sfdsf", "sdfsdf", "No Conformidad", "Bloqueante", "Media",
                "Valeria.Vasquez", "Actualización del Ambiente", "Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(3, "Mapa Prueba Test 3", 1,
                34, "Artefacto 3---1",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5, "sfdsf", "sdfsdf", "No Conformidad", "Bloqueante", "Media",
                "Valeria.Vasquez", "Actualización del Ambiente", "Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(4, "Mapa Prueba Test 4", 1,
                34, "Artefacto 4",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5, "sfdsf", "sdfsdf", "No Conformidad", "Bloqueante", "Media",
                "Valeria.Vasquez", "Actualización del Ambiente", "Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(5, "Mapa Prueba Test 5", 1,
                34, "Artefacto 5",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5, "sfdsf", "sdfsdf", "No Conformidad", "Bloqueante", "Media",
                "Valeria.Vasquez", "Actualización del Ambiente", "Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));
        
        return listaCasosPrueba;
    }

}
