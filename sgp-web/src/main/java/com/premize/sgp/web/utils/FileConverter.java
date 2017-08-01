package com.premize.sgp.web.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.premize.sgp.utils.FilePmz;
import com.premize.sgp.utils.LogUtil;

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-web
 * @class FileConverter
 * @description
 * @date 3/06/2014
 */
public class FileConverter {
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/05/2014
	 * @description 
	 * @param request
	 * @return
	 */
	public static FilePmz upload(MultipartHttpServletRequest request) throws IOException {
		FilePmz ufile = null;

		// 1. get the files from the request object
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			mpf = request.getFile(itr.next());
			// just temporary save file info into ufile
			ufile = new FilePmz();
			ufile.setLength(mpf.getBytes().length);
			LogUtil.log(FileConverter.class, Level.INFO, ufile.getLength()+"", null);
			ufile.setBytes(mpf.getBytes());
			ufile.setType(mpf.getContentType());
			ufile.setName(mpf.getOriginalFilename());
			ufile.setInputStream(mpf.getInputStream());
		}

		return ufile;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/05/2014
	 * @description 
	 * @param response
	 * @param ufile
	 */
	public static void get(HttpServletResponse response, FilePmz ufile) throws IOException {
		response.setContentType(ufile.getType());
		response.setContentLength(ufile.getLength());
		FileCopyUtils.copy(ufile.getBytes(), response.getOutputStream());
	}

	
	/**
	 * obtiene la ruta de el servidor hasta la carpeta de despliegue
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 30/05/2014
	* @param request
	* @return
	 */
	public static String rutaRealDespliegue(HttpServletRequest request) {
		String contest=request.getSession().getServletContext().getContextPath();
		String rutaContexto=request.getSession().getServletContext().getRealPath("/");
		
		String[] rut=rutaContexto.split(Pattern.quote(File.separator));
		String rutaDespliegue = "";
		for (String string : rut) {
			if(!string.startsWith(contest.replaceAll("/", ""))){
				rutaDespliegue=rutaDespliegue+ string.concat(File.separator);
			}
		}
		return rutaDespliegue;
	}
}
