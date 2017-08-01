/**
 * 
 */
package com.premize.sgp.web.controller;

import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class FileController
 * @since 27/03/2014
 *
 */
@Controller
@RequestMapping("/files")
public class FileController {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param response
	 * @param value
	 */
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable String value) {
		// try {
		
		// response.setContentType(ufile.getType());
		// response.setContentLength(ufile.getLength());
		// FileCopyUtils.copy(ufile.getBytes(), response.getOutputStream());
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		
		// 1. get the files from the request object
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		
		// 2. get each file
		while (itr.hasNext()) {
			
			mpf = request.getFile(itr.next());
			//
			// try {
			// just temporary save file info into ufile
			// ufile.setLength(mpf.getBytes().length);
			// ufile.setBytes(mpf.getBytes());
			// ufile.setType(mpf.getContentType());
			// ufile.setName(mpf.getOriginalFilename());
			
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
		
		// 2. send it back to the client as <img> that calls get method
		// we are using getTimeInMillis to avoid server cached image
		return "<img src='/sgp/files/get/" + Calendar.getInstance().getTimeInMillis() + "' />";
	}
	
}
