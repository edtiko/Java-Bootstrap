package com.premize.sgp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.facade.pruebas.MapaPruebaFacade;
import com.premize.sgp.utils.LogUtil;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-web
 * @class Scheduler
 * @description
 * @date 2/05/2014
 */
public class ReportesScheduler {
	
	@Autowired
	private MapaPruebaFacade mapaPruebaFacade;
	
	public void deleteZipFiles() {
		
		//Logger logger = Logger.getLogger(ReportesScheduler.class);
		//System.out.println("deleteZipFile scheduler Start... "+new Date());
//		LogUtil.log(ReportesScheduler.class.getName(), "deleteZipFile scheduler Start... "+new Date(), Level.INFO, null);
		try {
			String path = getClass().getClassLoader().getResource("/").getPath();
			LogUtil.log(getClass(), Level.INFO, "deleteZipFile scheduler Start... "+new Date(), null);
			mapaPruebaFacade.deleteZipFolder(path);
			LogUtil.log(ReportesScheduler.class, Level.INFO, "deleteZipFile scheduler End... "+new Date(), null);
		} catch (AppBaseException ex) {
			LogUtil.log(ReportesScheduler.class, Level.ERROR, ex.getMessage(), ex);
		} catch (IOException ex) {
			LogUtil.log(ReportesScheduler.class, Level.ERROR, ex.getMessage(), ex);
		}
	}

}
