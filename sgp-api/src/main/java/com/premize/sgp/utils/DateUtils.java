package com.premize.sgp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class DateUtils
 * @description
 * @date 3/07/2014
 */
public abstract class DateUtils {

	/**
	 * convierte String en Date y agrega la hora 00:00:00 o 23:59:59
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/07/2014
	 * @description 
	 * @param formatoFecha
	 * @param value
	 * @param initial
	 * @return
	 * @throws ParseException
	 */  
	public static Date getDateFromString(String formatoFecha, String value, boolean initial) 
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(formatoFecha);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(value));
		if (initial) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}
		return calendar.getTime();
	}
	/**
	 * convierte String en Date
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param formatoFecha
	* @param value
	* @return
	* @throws ParseException
	 */
	public static Date getDateFromString(String formatoFecha, String value) 
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(formatoFecha);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(value));

		return calendar.getTime();
	}
	
	final static String DATE_FORMAT = "dd-MM-yyyy";
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 30/08/2014
* @param date
* @return
 */
	public static boolean isDateValid(String date,String formato) throws ParseException
	{
	        try {
	            DateFormat df = new SimpleDateFormat(formato);
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	}
}
