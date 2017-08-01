package com.premize.sgp.utils;

import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class ListUtils
 * @description
 * @date 20/06/2014
 */
public class ListUtils {
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 20/06/2014
	 * @description Retorna true si la coleccion contiene un elemento con el parametro value, de lo contrario
	 * retorna false. Los elementos de la lista deben seguir el patron bean, dado que el metodo hace la comparacion
	 * usando el parametro property usando BeanWrapper para obtener el valor del bean.
	 * @param lista
	 * @param property
	 * @param value
	 * @return
	 */
	public static boolean containsInBeanList(List<?> lista, String property, Object value) {
		return indexOf(lista, property, value) != -1;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 20/06/2014
	 * @description 
	 * @param lista
	 * @param property
	 * @param value
	 * @return
	 */
	public static int indexContainsInBeanList(List<?> lista, String property, Object value) {
		return indexOf(lista, property, value);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 20/06/2014
	 * @description 
	 * @param lista
	 * @param property
	 * @param value
	 * @return
	 */
	private static int indexOf(List<?> lista, String property, Object value) {
		if(value == null) {
			for(int i = 0; i < lista.size(); i++) {
				Object e = lista.get(i);
				if(e == null) {
					return i;
				}
			}
		} else {
			BeanWrapper beanElement;
			for(int i = 0; i < lista.size(); i++) {
				Object e = lista.get(i);
				beanElement = new BeanWrapperImpl(e);
				if(e.equals(beanElement.getPropertyValue(property))) {
					return i;
				}
			}
		}
		return -1;
	}

}
