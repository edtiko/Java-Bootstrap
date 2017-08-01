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

import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.service.pruebas.TipoHallazgoService;
import com.premize.sgp.service.test.AbstractServiceTest;
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-service
* @class TipoHallazgoServiceTest
* @date 17/07/2014
*
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class TipoHallazgoServiceTest  extends AbstractServiceTest  {
	
	@Autowired
	private TipoHallazgoService tipoHallazgoService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 17/07/2014
 */
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 17/07/2014
* @throws Exception
 */
	@Test
	public void guardarTipoHallazgo() throws Exception{
		
		TipoHallazgoDTO tipoHallazgoDTO=new TipoHallazgoDTO();
		
		tipoHallazgoDTO.setId(2);
		tipoHallazgoDTO.setNombre("Nuevo Parametro");
		tipoHallazgoDTO.setIndActivo(1);
		tipoHallazgoDTO.setFecCreacion(Calendar.getInstance().getTime());
		tipoHallazgoDTO.setUsuarioCreacion("gustavoa.soto");

		try {
			tipoHallazgoService.guardarTipoHallazgo(tipoHallazgoDTO);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	
		
	}

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 17/07/2014
* @throws Exception
 */
	@Test
	public void EditarTipoHallazgo() throws Exception{
		
		TipoHallazgoDTO tipoHallazgoDTO = tipoHallazgoService.getTipoHallazgo(1);
		
		tipoHallazgoDTO.setNombre("Nuevo Parametro");
		tipoHallazgoDTO.setIndActivo(1);
		tipoHallazgoDTO.setFechaEdita(Calendar.getInstance().getTime());
		tipoHallazgoDTO.setUsuarioEdita("Ed Gómez");
		
		try {
			tipoHallazgoService.editarTipoHallazgo(tipoHallazgoDTO);;

		} catch (Exception e) {
			fail(e.getMessage());
		}
	
		
	}
}
