package com.premize.sgp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.premize.pmz.api.util.UrlAplicacionHolder;

/**
 * Filtro para obtener la url de la aplicacion y colocarla en el threadlocal para poder ser utilizada en el envio de
 * notificaciones.
 * 
 * @author <a href="mailto:jorge.middleton@gmail.com">Jorge L. Middleton</a>
 * @date Jan 6, 2011
 */
public class UrlAplicacionFilter implements Filter {
	
	public UrlAplicacionFilter() {
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Mar 21, 2014 UrlAplicacionFilter init Revisar
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		
		if (UrlAplicacionHolder.getInstance().getUrl() == null) {
			
			String scheme = request.getScheme(); // http
			String serverName = request.getServerName(); // hostname.com
			int serverPort = request.getServerPort(); // 80
			String contextPath = ((HttpServletRequest) request).getContextPath(); // /mywebapp
			
			String url = scheme + "://" + serverName + (serverPort > 0 ? (":" + serverPort) : "") + contextPath;
			UrlAplicacionHolder.getInstance().setUrl(url);
		}
		
		chain.doFilter(request, response);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Mar 21, 2014 UrlAplicacionFilter destroy revisar
	}
	
}
