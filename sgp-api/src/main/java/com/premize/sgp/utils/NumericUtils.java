/**
 * 
 */
package com.premize.sgp.utils;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-api
 * @class NumericUtils
 * @date 5/08/2014 {time}
 *
 */
public abstract class NumericUtils {

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 5/08/2014
	* @param str
	* @return
	 */
	public static boolean isNumeric(String str)
	{
		if (null==str){
			return true;
		}
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

}
