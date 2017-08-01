/**
 * 
 */
package com.premize.sgp.service.parametros;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.service.ParametroService;
import com.premize.sgp.service.TipoParametroService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroServiceTest
 * @since 30/01/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class ParametroServiceTest extends AbstractTransactionalJUnit4SpringContextTests  {

	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private TipoParametroService tipoParametroService;
	
	
	@Autowired
	private SessionFactory sessionFactory;

	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}

	@Test
	public void guardarParametro() throws Exception{
		
		ParametroDTO parametro = new ParametroDTO();
		TipoParametroDTO tipoParametro = tipoParametroService.getTipoParametro(1);
		
		parametro.setId(1);
		parametro.setNombre("Nuevo Parametro");
		parametro.setTipoParametro(tipoParametro);
		parametro.setValor("Valor Parametro");
		parametro.setIndActivo(1);
		parametro.setFecCreacion(Calendar.getInstance().getTime());
		parametro.setUsuarioCreacion("Ed Gómez");
		
		try {
			parametroService.guardarParametro(parametro);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	
		
	}

	@Test
	public void EditarParametro() throws Exception{
		
		ParametroDTO parametro = parametroService.getParametro(2);
		
		parametro.setNombre("Nuevo Parametro");
		parametro.setValor("Valor Parametro");
		parametro.setIndActivo(1);
		parametro.setFechaEdita(Calendar.getInstance().getTime());
		parametro.setUsuarioEdita("Ed Gómez");
		
		try {
			parametroService.editarParametro(parametro);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	
		
	}
	
	public void consultarParametros() throws AppBaseException{
		List<ParametroDTO> listaParametros = parametroService.getListParametros();
		Assert.assertNotNull(listaParametros);
		Assert.assertTrue(!listaParametros.isEmpty());
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 5/06/2014
	 * @description
	 */
	@Test
	public void getMapRestriccionesAnexo() {
		try {
			Map<String,Object> restricciones = parametroService.getMapRestriccionesAnexo();
			Assert.assertNotNull(restricciones);
			Assert.assertTrue(!restricciones.isEmpty());
			Assert.assertNotNull(restricciones.get(ParametroService.ANEXO_REST_MAP_KEY_ALLOWED_FILES));
			Assert.assertNotNull(restricciones.get(ParametroService.ANEXO_REST_MAP_KEY_FILE_SIZE));
		} catch (AppBaseException ex) {
			fail(ex.getMessage());
		}
	}
	
}
