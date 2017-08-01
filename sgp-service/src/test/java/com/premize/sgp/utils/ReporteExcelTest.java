package com.premize.sgp.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;
import static org.junit.Assert.fail;

import org.apache.log4j.Level;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ReporteMapaPruebaDTO;
import com.premize.sgp.service.pruebas.MapaPruebaService;
import com.premize.sgp.service.pruebas.ProyectoService;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class ReporteExcelTest
 * @description
 * @date 11/04/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class ReporteExcelTest {
	
	private static final String LOCATION = "d:\\";
	private static final String REPORTE_MAPA_PRUEBAS_NAME = "reporteMapaPruebasTest.xls";
	private static final String REPORTE_AVANCE_MAPAS_NAME = "reporteAvanceMapasTest.xls";
	
	@Autowired
	private ReporteExcel reporteExcel;
	
	@Autowired
	private MapaPruebaService mapaPruebaService;
	
	@Autowired
	private ProyectoService proyectoService;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/04/2014
	 * @description
	 */
	@Test
	public void getArchivoProperties() {
		try {
			Properties prop = reporteExcel.getArchivoProperties();
			//System.out.println(prop.getProperty("descripcionPrueba"));
			Assert.assertNotNull(prop);
		} catch (IOException ioex) {
			fail(ioex.getMessage());
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/04/2014
	 * @description
	 */
	@Test
	public void construirReporteMapaPruebas() {
        List<ReporteMapaPruebaDTO> listaCasosPrueba = new ArrayList<ReporteMapaPruebaDTO>();
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(null,null,null,
                33, "Artefacto 1 bbbbbbbbbbb", "DSDSD", "Esperada", "DSDSA", new Date(), 
                "admin", null, null, null, null, "Mapa Prueba 1", 
                1, "prueba1", "otra prueba1", "No Conformidad", "Funcional","Baja",
                "Valeria.Vasquez","Actualización del Ambiente",
                "El cliente lo asumirá administrativamente","Cerrado", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(null,null,null,
                33, "Artefacto 1 bbbbbbbbbbb", "DSDSD", "Esperada", "DSDSA", new Date(), 
                "admin", null, null, null, null, "Mapa Prueba 1", 
                2, "prueba1", "otra prueba1", "No Conformidad", "Funcional","Baja",
                "Valeria.Vasquez","Actualización del Ambiente",
                "El cliente lo asumirá administrativamente","Cerrado", new Date()));
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(null,null,null,
                34, "Artefacto 1 bbbbbbbbbbb",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5,"sfdsf","sdfsdf","No Conformidad","Bloqueante","Media",
                "Valeria.Vasquez","Actualización del Ambiente","Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));
		
		Workbook libroExcel = null;
		
		try {
			String nombreMapaPrueba = "Mapa Prueba";
			libroExcel = reporteExcel.construirReporteMapaPruebas(listaCasosPrueba, nombreMapaPrueba);
			Assert.assertNotNull(libroExcel);
		} catch (AppBaseException e) {
			//LogUtil.log(ReporteExcelTest.class.getName(), e.getMessage(), Level.ERROR, e);
			fail(e.getMessage());			
		} catch (IOException e) {
			//LogUtil.log(ReporteExcelTest.class.getName(), e.getMessage(), Level.ERROR, e);
			fail(e.getMessage());
		} finally {
			if(libroExcel != null) {
				try {
					String strFile = LOCATION+REPORTE_MAPA_PRUEBAS_NAME;
		            FileOutputStream fileOut = new FileOutputStream(strFile);
		            libroExcel.write(fileOut);
		            Desktop d = Desktop.getDesktop();
		            d.open(new File(strFile));
		        } catch (FileNotFoundException ex) {
		            ex.printStackTrace();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
			}
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/04/2014
	 * @description
	 */
	@Test
	public void construirNombreArchivoExcel() {
		MapaPruebasDTO mpDTO = new MapaPruebasDTO();
		mpDTO.setNombre("Parametrizacion de Usuarios");
		mpDTO.setFecCreacion(new Date());
		try {
			String fileName = reporteExcel.construirNombreArchivoExcel(mpDTO);
			LogUtil.log(ReporteExcelTest.class.getName(), fileName, Level.ERROR, null);
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 22/04/2014
	 * @description
	 */
	@Test
	public void construirReporteAvanceEjcucionMapas() {
		List<MapaPruebasDTO> listaMapas = new ArrayList<MapaPruebasDTO>();
		listaMapas.add(new MapaPruebasDTO("001 Autenticar Usuario", 10, 8, 4, 4, 2));
		listaMapas.add(new MapaPruebasDTO("002 Crear Funcionarios", 10, 5, 2, 3, 0));
		listaMapas.add(new MapaPruebasDTO("003 Modificar Funcionarios", 10, 0, 0, 0, 0));
//		listaMapas.add(new MapaPruebasDTO("Mapa de Pruebas 1", 140, 5, 1, 4, 1));
//		listaMapas.add(new MapaPruebasDTO("Mapa de Pruebas 2", 5, 2, 0, 2, 0));
//		listaMapas.add(new MapaPruebasDTO("Mapa de Pruebas 3", 100, 85, 10, 5, 5));
		
		Workbook libroExcel = null;
		try {
			String nombreProyecto = "Proyecto SGP";
			libroExcel = reporteExcel
					.construirReporteAvanceEjcucionMapas(listaMapas, nombreProyecto);
			Assert.assertNotNull(libroExcel);
		} catch (AppBaseException ex) {
			LogUtil.log(ReporteExcelTest.class.getName(), ex.getMessage(), Level.ERROR, ex);
			fail(ex.getMessage());
		}
		
		if(libroExcel != null) {
			try {
				String strFile = LOCATION+REPORTE_AVANCE_MAPAS_NAME;
				FileOutputStream fileOut = new FileOutputStream(strFile);
				libroExcel.write(fileOut);
				Desktop d = Desktop.getDesktop();
				d.open(new File(strFile));
			} catch (FileNotFoundException ex) {
				LogUtil.log(this.getClass().getName(), ex.getMessage(), Level.ERROR, ex);
			} catch (IOException ioex) {
				LogUtil.log(this.getClass().getName(), ioex.getMessage(), Level.ERROR, ioex);
			}
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 26/06/2014
	 * @description
	 */
	@Test
	public void construirReporteAvanceEjcucionMapasEmptyData() {
		List<MapaPruebasDTO> listaMapas = new ArrayList<MapaPruebasDTO>();
		Workbook libroExcel = null;
		try {
			String nombreProyecto = "Proyecto SGP";
			libroExcel = reporteExcel
					.construirReporteAvanceEjcucionMapas(listaMapas, nombreProyecto);
			Assert.assertNotNull(libroExcel);
		} catch (AppBaseException ex) {
			LogUtil.log(ReporteExcelTest.class.getName(), ex.getMessage(), Level.ERROR, ex);
			fail(ex.getMessage());
		}
		
		if(libroExcel != null) {
			try {
				String strFile = LOCATION+REPORTE_AVANCE_MAPAS_NAME;
				FileOutputStream fileOut = new FileOutputStream(strFile);
				libroExcel.write(fileOut);
				Desktop d = Desktop.getDesktop();
				d.open(new File(strFile));
			} catch (FileNotFoundException ex) {
				LogUtil.log(this.getClass().getName(), ex.getMessage(), Level.ERROR, ex);
			} catch (IOException ioex) {
				LogUtil.log(this.getClass().getName(), ioex.getMessage(), Level.ERROR, ioex);
			}
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/05/2014
	 * @description
	 */
	@Test
	public void construirNombreArchivoExcelPrefijoProyecto() {
		String nombreMapa = "Parametrizacion de Usuarios";
		String prefijoProyecto = "SOF"; //SOFIB
		Date fechaCreacion = new Date();
		
		try {
			String nombre = reporteExcel.construirNombreArchivoExcel(nombreMapa, prefijoProyecto, fechaCreacion);
			Assert.assertNotNull(nombre);
			DateFormat df = new SimpleDateFormat(ReporteExcelCons.FORMATO_FECHA_NOMBRE_ARCHIVO);
			String nombreEsperado = ReporteExcelCons.PREFIJO_MP 
					              + prefijoProyecto
					              + df.format(fechaCreacion) + "_"
					              + nombreMapa;
			Assert.assertEquals(nombreEsperado, nombre);
			System.out.println(this.getClass().getName());
			LogUtil.log(this.getClass(), Level.INFO, "PRUEBA EXITOSA: "+nombre, null);
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
	public void construirReporteMapaPruebasZip() {
		List<ReporteMapaPruebaDTO> mapas = buildReporteMapa();
		try {
			List<ListSelector> selectors = mapaPruebaService.getListSelectors(mapas);
			String prefijoProyecto = "SOFIB";
			String nombreMapa = "Parametrizacion de Usuarios";
			Date fechaCreacionMapa = new Date();
			ReporteMapaPruebaDTO mapaDto;
			Workbook libro;
			File directorio = new File(LOCATION + MapaPruebaService.ZIP_FOLDER);
			if(!directorio.exists()) {
				directorio.mkdir();
			}
			for(ListSelector ls : selectors) {
				mapaDto = mapas.get(ls.getStart());
				nombreMapa = mapaDto.getNombreMapa();
				//fechaCreacionMapa = mapaDto.getFechaCreacionMapa();
				libro = reporteExcel.construirReporteMapaPruebasZip(mapas, ls, nombreMapa);
				String excelFileName = reporteExcel.construirNombreArchivoExcel(nombreMapa, prefijoProyecto, fechaCreacionMapa);
				excelFileName = excelFileName + ReporteExcelCons.EXT_XLS;
				String location = directorio.getAbsolutePath() + File.separator + excelFileName;
				saveWorkbook(libro, location);
			}
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		} catch (IOException ex) {
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
        listaCasosPrueba.add(new ReporteMapaPruebaDTO(5, "Mapa Prueba Test 5", 1,
                34, "Artefacto 5-1",
                "Logger is a subclass of Category, i.e. it extends Category. In other words, a logger is a category. "
                + "Thus, all operations that can be performed on a category can be performed on a logger. Internally, "
                + "whenever log4j is asked to produce a Category object, it will instead produce a Logger object. ",
                "Esperada", "DSDSA", new Date(), "admin", null, null, null, null, "Mapa Prueba 2",
                5, "sfdsf", "sdfsdf", "No Conformidad", "Bloqueante", "Media",
                "Valeria.Vasquez", "Actualización del Ambiente", "Lo reportado no se encuentra en el alcance",
                "Inicial", new Date()));
        
        return listaCasosPrueba;
    }
    

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description 
	 * @param libro
	 * @param location
	 * @throws AppBaseException
	 */
	private void saveWorkbook(Workbook libro, String location) throws AppBaseException {
        try {
            FileOutputStream fileOut = new FileOutputStream(location);
            libro.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
        	new AppBaseException("Error al ejecutar el metodo saveWorkbook; location = "+location,"FILE_NOT_FOUND");
        } catch (IOException ex) {
        	new AppBaseException("Error al ejcutar el metodo saveWorkbook: "+ex.getMessage(), "IO_EXCEPTION");
        }
		
	}
	

}
