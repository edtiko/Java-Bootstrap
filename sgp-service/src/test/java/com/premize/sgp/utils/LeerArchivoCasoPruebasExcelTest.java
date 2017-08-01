package com.premize.sgp.utils;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.sgp.dto.pruebas.CargueCasoPruebaDTO;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class LeerArchivoCasoPruebasExcelTest
 * @since 27/03/2014
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class LeerArchivoCasoPruebasExcelTest {

	
	@Autowired
	private LeerArchivoCasoPruebasExcel leerArchivoService;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 */
	@Test
	public void leerArchivo(){
		String tmp ="C:\\Users\\PC\\Documents\\mapaPruebasTest2.xlsx";
		try {
			CargueCasoPruebaDTO datosRespuesta = null;
			//datosRespuesta = leerArchivoService.leerArchivoCasoPrueba(tmp);
			
			Assert.assertNotNull(datosRespuesta.getListaDatosCargados());
		
		}catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
}
