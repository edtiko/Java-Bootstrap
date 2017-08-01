package com.premize.sgp.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class LogUttil
 * @description
 * @date 14/04/2014
 */
public class LogUtil {
	
	private static Logger logger;
	
	static {
		logger = Logger.getLogger("SGP");
		logger.setLevel(Level.ALL);

	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014
	 * @description 
	 * @param className
	 * @param message
	 * @param level
	 * @param throwable
	 */
	public static void log(String className, String message, Level level, Throwable throwable) {
		logger.log(className, level, message, throwable);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/05/2014
	 * @description 
	 * @param clase
	 * @param level
	 * @param message
	 * @param throwable
	 */
	public static void log(Class<?> clase, Level level, String message, Throwable throwable) {
		String mensaje = "["+clase.getName() + "] : "+message;
		logger.log(level, mensaje, throwable);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014
	 * @description 
	 * @return Logger : log4j.Logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}
