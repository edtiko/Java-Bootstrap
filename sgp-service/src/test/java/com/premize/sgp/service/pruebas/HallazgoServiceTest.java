/**
 * 
 */
package com.premize.sgp.service.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.exception.MailServiceException;
import com.premize.sgp.dao.gestion.pruebas.HallazgoDao;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.dto.pruebas.HallazgoDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadDocumentacionDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.service.ParametroService;
import com.premize.sgp.service.parametros.UsuarioService;
import com.premize.sgp.utils.FilePmz;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class HallazgoServiceTest
 * @since 3/03/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class HallazgoServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private HallazgoService hallazgoService;
	
	@Autowired
	private AnexoHallazgoService anexoHallazgoService;
	
	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private FlujoHallazgoService flujoHallazgoService;
	
	@Autowired
	private HallazgoDao hallazgoDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CasoDePruebaService casoPruebaService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @throws AppBaseException
	 */
	@Test
	public void EditarHallazgo() throws AppBaseException {
		
		HallazgoDTO hallazgoDTO = new HallazgoDTO();
		FlujoHallazgoDTO flujoHallazgo = new FlujoHallazgoDTO();
		ArtefactoDTO artefacto = new ArtefactoDTO();
		CasoDePruebaDTO casoPrueba = new CasoDePruebaDTO();
		hallazgoDTO = hallazgoService.getHallazgo(351,"login");
		SgpFlujoHallazgo sgpFlujoHallazgo = new SgpFlujoHallazgo();
		ParametroDTO estadoHallazgo = parametroService.getParametro(19);
		UsuarioDTO usuarioAsignado = usuarioService.obtenerUsuario(40);
		
		hallazgoDTO.setArtefacto(artefacto);
		hallazgoDTO.setCasoPrueba(casoPrueba);
		hallazgoDTO.setDescripcion("Desceipcion");
		hallazgoDTO.setUsuarioCreacion("UsuarioTest");
		flujoHallazgo.setHallazgo(hallazgoDTO);
		flujoHallazgo.setEstadoHallazgo(estadoHallazgo);
		flujoHallazgo.setUsuarioCreacion("Valeria.Vasquez");
		flujoHallazgo.setUsuarioAsignado(usuarioAsignado);
		try {
			flujoHallazgoService.editarHallazgo(flujoHallazgo, sgpFlujoHallazgo);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @throws AppBaseException
	 */
	@Test
	public void guardarHallazgo() throws AppBaseException {
		
		FlujoHallazgoDTO flujoHallazgoDTO = new FlujoHallazgoDTO();
		HallazgoDTO hallazgodto = new HallazgoDTO();
		CasoDePruebaDTO casoPruebadto = casoPruebaService.getCasoDePrueba(73);
		ParametroDTO tipoHallazgo = parametroService.getParametro(10);
		ParametroDTO causaGenera = parametroService.getParametro(14);
		ParametroDTO severidad = parametroService.getParametro(7);
		ParametroDTO prioridad = parametroService.getParametro(4);
		hallazgodto.setCasoPrueba(casoPruebadto);
//		hallazgodto.setTipoHallazgo(tipoHallazgo);
//		hallazgodto.setCausaGeneracion(causaGenera);
//		hallazgodto.setSeveridad(severidad);
//		hallazgodto.setPrioridad(prioridad);
//		hallazgodto.setTitulo("titulo de prueba hallazgo");
		flujoHallazgoDTO.setUsuarioCreacion("UsuarioTest");
		flujoHallazgoDTO.setIndActivo(1);
		flujoHallazgoDTO.setNumeroEstado(1);
		flujoHallazgoDTO.setFecCreacion(new Date());
		flujoHallazgoDTO.setUsuarioCreacion("usuarioCreacion");
		flujoHallazgoDTO.setHallazgo(hallazgodto);
		try {
			hallazgoService.guardarHallazgo(flujoHallazgoDTO, new SgpHallazgo());
			flush();
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 */
	public void guardarAnexoHallazgo(){
		
		HallazgoDTO hallazgodto = new HallazgoDTO();
		FilePmz file = null;
		try {
			
			hallazgoService.guardarAnexo(hallazgodto, file);
			flush();
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @throws AppBaseException
	 */
	@Test
	public void getHallazgo() throws AppBaseException {
		guardarHallazgo();
		HallazgoDTO hallazgo = null;
		try {
			hallazgo = hallazgoService.getHallazgo(null,"login");
			assertNull(hallazgo);
			hallazgo = hallazgoService.getHallazgo(351,"login");
			assertNotNull(hallazgo);
		} catch (AppBaseException e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 5/05/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void getHallazgosPorSeveridad_conparametro()throws AppBaseException, ParseException{
		List<ReporteHallazgoDTO> list = hallazgoService.getHallazgosPorSeveridad(1,  "2014/04/11", "2014/04/11", "1");
		assertNotNull(list);
		assertTrue("Sin resultado",list.size() > 0);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 5/05/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	public void getHallazgosPorMapaPruebas_conparametros() throws AppBaseException, ParseException{
		List<ReporteHallazgoDTO> list = hallazgoService.getHallazgosPorMapaPruebas(1,  "2014/04/11", "2014/04/11", "1");
		assertNotNull(list);
		assertTrue("Sin resultado",list.size() > 0);
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
		List<ReporteHallazgoDTO> total = hallazgoService.getHallazgosPorSeveridad(idProyecto, null, null, null);
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
		List<ReporteHallazgoDTO> total = hallazgoService.getHallazgosPorMapaPruebas(idProyecto, null, null, null);
		assertNotNull(total);
		assertTrue(total.size() > 0);
	}

	
	
 
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 */
	@Test
	public void enviarEmailHallazgo(){
	 try{
		 SgpFlujoHallazgo sgpFlujoHallazgo = flujoHallazgoService.findById(251);
		 hallazgoService.enviarEmailHallazgo(sgpFlujoHallazgo);
	 }
	 catch(MailServiceException e){
		 fail(e.getMessage()); 
	 }
	 catch(AppBaseException e){
		 fail(e.getMessage());
	 }
  }
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/05/2014
	 */
	@Test
	public void eliminarAnexo(){
		try{
			Integer idAnexo = 1;
			anexoHallazgoService.eliminarAnexo(idAnexo);
		}
		catch(AppBaseException e){
			fail(e.getMessage());
		}
        catch(IOException e){
    	   fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 16/07/2014
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Test
	public void getHallazgosPorRecurso_null() throws AppBaseException, ParseException{
		
		List<Integer> listEstados = new ArrayList<Integer>();
		listEstados.add(24);
		listEstados.add(25);
		List<Integer> listCausas = new ArrayList<Integer>();
		listCausas.add(9);
		List<ReporteHallazgoDTO> resultado = hallazgoService.getHallazgosPorRecurso("19",  null, null, listEstados, listCausas);
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
	public void getHallazgosPorRecurso_conparametros() throws AppBaseException, ParseException{
		
		List<Integer> listEstados = new ArrayList<Integer>();
		listEstados.add(24);
		listEstados.add(25);
		List<Integer> listCausas = new ArrayList<Integer>();
		listCausas.add(9);
		List<ReporteHallazgoDTO> resultado = hallazgoService.getHallazgosPorRecurso("1",  "2014/04/11", "2014/07/16", listEstados, listCausas);
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
		List<ConsultaHallazgoDTO> resultado = hallazgoService.getSeveridadColorHallazgo(usuario);
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
		List<ConsultaHallazgoDTO> resultado = hallazgoService.getSeveridadColorHallazgo(usuario);
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
		
		
		Map<Integer,IndCalidadDocumentacionDTO> resultado = hallazgoService.consultarIndicadorCalidadDocumentacion("19", "2014/07/01", "2014/07/30");
		
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
		
		String fechaFrom = null;
		String fechaTo =   null;
		Map<Integer,IndCalidadDocumentacionDTO> resultado = hallazgoService.consultarIndicadorCalidadDocumentacion("19", fechaFrom, fechaTo);
		
		assertNotNull(resultado);
		assertTrue("Sin resultado",resultado.size() > 0);
	}
}
