/**
 * 
 */
package com.premize.sgp.service.parametros;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.service.TipoParametroService;
import com.premize.sgp.service.test.AbstractServiceTest;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class TipoParametroServiceTest
 * @since 30/01/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class TipoParametroServiceTest  extends AbstractServiceTest  {
	
	@Autowired
	private TipoParametroService tipoParametroService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 */
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws Exception
	 */
	@Test
	public void guardarTipoParametro() throws Exception{
		
		TipoParametroDTO tipoParametro = new TipoParametroDTO();
		
		tipoParametro.setId(2);
		tipoParametro.setNombre("Nuevo Parametro");
		tipoParametro.setIndActivo(1);
		tipoParametro.setFecCreacion(Calendar.getInstance().getTime());
		tipoParametro.setUsuarioCreacion("Ed Gómez");
		
		try {
			tipoParametroService.guardarTipoParametro(tipoParametro);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	
		
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws Exception
	 */
	@Test
	public void EditarTipoParametro() throws Exception{
		
		TipoParametroDTO tipoParametro = tipoParametroService.getTipoParametro(1);
		
		tipoParametro.setNombre("Nuevo Parametro");
		tipoParametro.setIndActivo(1);
		tipoParametro.setFechaEdita(Calendar.getInstance().getTime());
		tipoParametro.setUsuarioEdita("Ed Gómez");
		
		try {
			tipoParametroService.editarTipoParametro(tipoParametro);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	
		
	}
}
