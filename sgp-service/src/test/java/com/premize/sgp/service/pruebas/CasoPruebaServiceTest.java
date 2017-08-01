package com.premize.sgp.service.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.CargueCasoPruebaDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.service.ParametroService;
import com.premize.sgp.service.parametros.ArtefactoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class CasoPruebaServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private CasoDePruebaService casoPruebaService;
	
	@Autowired
	private ArtefactoService artefactoService;
	
	@Autowired
	private MapaPruebaService mapaPruebaService;
	
	@Autowired
	private ParametroService parametroService;
	
	private static Logger log = Logger.getLogger(CasoPruebaServiceTest.class.getName());
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 * @throws Exception
	 */
	@Test
	public void guardarCasoPrueba() {
		try{
			CasoDePruebaDTO casoDePruebaDTO = new CasoDePruebaDTO();
			ArtefactoDTO artefacto = artefactoService.obtenerArtefacto(3);
			MapaPruebasDTO mapaPrueba = mapaPruebaService.getMapaDTO(1);
			ParametroDTO tipoPrueba = parametroService.getParametro(12);
			casoDePruebaDTO.setArtefacto(artefacto);
			casoDePruebaDTO.setMapaPrueba(mapaPrueba);
			casoDePruebaDTO.setTipoPrueba(tipoPrueba);
			casoDePruebaDTO.setCumple("");
			casoDePruebaDTO.setDescripcion("Descripcion caso de prueba");
			casoDePruebaDTO.setFecCreacion(Calendar.getInstance().getTime());
			casoDePruebaDTO.setUsuarioCreacion("Edwin Gómez");
			casoDePruebaDTO.setResultado("Resultado caso de prueba");
			casoPruebaService.guardarCasoDePrueba(casoDePruebaDTO);
			
		}
		catch(AppBaseException e){
			log.fatal("Prueba de log");
			fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.fatal("Prueba de log",e);
		}
	}
	
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 * @throws Exception
	 */
	@Test
	public void guardarCasoPruebaList() throws Exception{
		try{
			List<CasoDePruebaDTO> casoDePruebaList = new ArrayList<CasoDePruebaDTO>();
			CasoDePruebaDTO casoDePruebaDTO = new CasoDePruebaDTO();
			ArtefactoDTO artefacto = artefactoService.obtenerArtefacto(3);
			MapaPruebasDTO mapaPrueba = mapaPruebaService.getMapaDTO(1);
			ParametroDTO tipoPrueba = parametroService.getParametro(12);
			casoDePruebaDTO.setArtefacto(artefacto);
			casoDePruebaDTO.setMapaPrueba(mapaPrueba);
			casoDePruebaDTO.setTipoPrueba(tipoPrueba);
			casoDePruebaDTO.setCumple("");
			casoDePruebaDTO.setDescripcion("Descripcion caso de prueba");
			casoDePruebaDTO.setFecCreacion(Calendar.getInstance().getTime());
			casoDePruebaDTO.setUsuarioCreacion("Edwin Gómez");
			casoDePruebaDTO.setResultado("Resultado caso de prueba");
			casoDePruebaList.add(casoDePruebaDTO);
			casoPruebaService.guardarCasoDePrueba(casoDePruebaList);
			
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 */
	@Test
	public void getCasoPrueba(){
		CasoDePruebaDTO casoPrueba = null;
		try{
			 casoPrueba = casoPruebaService.getCasoDePrueba(null);
			assertNull(casoPrueba);
			casoPrueba = casoPruebaService.getCasoDePrueba(56);
			assertNotNull(casoPrueba);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws Exception 
	 * @since 5/05/2014
	 */
	@Test
	public void editarCasoPrueba() throws Exception{
		try{
			CasoDePruebaDTO casoDePruebaDTO = casoPruebaService.getCasoDePrueba(56);
			ArtefactoDTO artefacto = artefactoService.obtenerArtefacto(3);
			MapaPruebasDTO mapaPrueba = mapaPruebaService.getMapaDTO(1);
			ParametroDTO tipoPrueba = parametroService.getParametro(12);
			casoDePruebaDTO.setArtefacto(artefacto);
			casoDePruebaDTO.setMapaPrueba(mapaPrueba);
			casoDePruebaDTO.setTipoPrueba(tipoPrueba);
			casoDePruebaDTO.setArtefacto(artefacto);
			casoPruebaService.editarCasoDePrueba(casoDePruebaDTO);	
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws FileNotFoundException 
	 * @since 5/05/2014
	 */
	@Test
	public void cargarCasosPruebas() throws FileNotFoundException{
		CargueCasoPruebaDTO cargueCasoPrueba = null;
		try{

			String ruta = "C:\\Users\\PC\\Pictures\\archivoExcel.xls";
			Integer idMapaPruebas = 1;
		    String login = "Edwin.Gomez";
			cargueCasoPrueba = casoPruebaService.cargarCasosPruebas(null, null, null);
			assertNull(cargueCasoPrueba);
			InputStream archivoImportar=new FileInputStream(ruta);
			cargueCasoPrueba = casoPruebaService.cargarCasosPruebas(archivoImportar, idMapaPruebas, login);
			assertNotNull(cargueCasoPrueba);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 */
	@Test
	public void eliminarCasoPrueba(){
		try{
			casoPruebaService.eliminarCasoPrueba(56);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 */
	@Test
	public void ejecutarCasoPrueba(){
		try{
			CasoDePruebaDTO casoDePruebaDTO = new CasoDePruebaDTO();
			casoDePruebaDTO.setId(56);
			casoDePruebaDTO.setCumple("Si");
			casoDePruebaDTO.setUsuarioEjecuta("Edwin.Gomez");
			casoPruebaService.ejecutarCasoPrueba(casoDePruebaDTO);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
	}

}
