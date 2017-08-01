package com.premize.sgp.web.auth;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Clase base para los bean de UI.
 * 
 * @author <a href="mailto:julioc.chaves@premize.com">Julio C&eacute;sar
 *         Chaves</a>
 * @date 26/06/2012
 * 
 */
public class AbstractBaseUI {

	public static final String CURRENT_LOCALE = "currentLocale";


	/**
	 * Devuelve el locale de la aplicacion.
	 * 
	 * @return
	 */
	protected static Locale getLocale() {
		Locale locale = LocaleContextHolder.getLocale();
		return locale;
	}


	public String getLogin() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			} else {
				return principal.toString();
			}
		}
		return null;
	}

	
	/**
	 * Obtiene objeto {@link UserDetails} del cache de autenticaciÃ³n
	 * 
	 * @author <a href="mailto:ricardoa.chiriboga@premize.com">Ricardo Alberto
	 *         Chiriboga</a>
	 * @date 19/06/2013
	 * @return
	 */
	protected UserDetails getUserDetails() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return (UserDetails) principal;
	}


}