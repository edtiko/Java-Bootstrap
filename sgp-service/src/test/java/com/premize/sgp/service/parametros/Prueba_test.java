package com.premize.sgp.service.parametros;

import java.lang.reflect.Field;

import org.junit.Test;

import com.premize.sgp.dto.parametros.ParametroDTO;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class Prueba_test
 * @since 27/03/2014
 *
 */
public class Prueba_test {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@Test
	public void test() throws NoSuchFieldException, SecurityException {
		ParametroDTO parametro = new ParametroDTO();
		Field[] prue = parametro.getClass().getDeclaredFields();
		Class<?> s = prue[1].getType();
		String typo = s.getName();
		
		String dati = parametro.getClass().getField("getId").getName();
		
	}
	
}
