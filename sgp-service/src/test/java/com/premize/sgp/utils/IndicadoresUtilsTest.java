package com.premize.sgp.utils;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class IndicadoresUtilsTest
 * @description
 * @date 16/07/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/applicationContext-map.xml"})
public class IndicadoresUtilsTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014
	 * @description
	 */
	@Test
	public void calculateIndicadorCalidadDocumentacion() {
		Double puntuacion = 9.0;
		Double puntosPosibles = 15.0;
		
		Double indicador = IndicadoresUtils.calculateIndicadorCalidadDocumentacion(puntuacion, puntosPosibles);
		Assert.assertNotNull(indicador);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014
	 * @description
	 */
	@Test
	public void calculateIndicadorCalidadFuncionalidad() {
		Integer pruebasEjecutadas = 10;
		Integer hallazgosReportados = 3;
		Integer hallazgosAnulados = 1;
		Integer hallazgosInvalidados = 1;
		
		Double indicador = IndicadoresUtils
				.calculateIndicadorCalidadFuncionalidad(pruebasEjecutadas, hallazgosReportados, hallazgosAnulados, hallazgosInvalidados);
		Assert.assertNotNull(indicador);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014
	 * @description
	 */
	@Test
	public void dateDifferenceInHours() {
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		Integer diff = 6;
		date2.add(Calendar.HOUR, diff);
		Double result = IndicadoresUtils.dateDifferenceInHours(date2.getTime(), date1.getTime());
		Assert.assertNotNull(result);
		Assert.assertEquals(diff.doubleValue(), result, 0);
	}

}
