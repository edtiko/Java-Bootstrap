package com.premize.sgp.web.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.MessageBundleLoader;

/**
 * Clase Abstracta obtenida de la arquictetura de Premize
 * 
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-api
 * @class AbstractBaseMessageUI
 * @since 22/01/2014
 */
public abstract class AbstractBaseMessageUI extends AbstractBaseUI {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param messageBundle
	 * @param key
	 * @return
	 */
	protected static String getMessage(String messageBundle, String key) {
		try {
			return MessageBundleLoader.getMessage(messageBundle, key, getLocale());
		} catch (Exception e) {
			return MESSAGE_NOT_FOUND_PREFIX + key + MESSAGE_NOT_FOUND_PREFIX;
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param key
	 * @return
	 */
	protected static String getMessage(String key) {
		return getMessage(null, key);
	}
	
	/**
	 * Appbaseexception
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/04/2014
	 * @param a
	 */
	protected String errorAppBaseException(AppBaseException a) {
		return MessageBundleLoader.getMessage("i18n.msg_exception", a.getErrorCode() , LocaleContextHolder.getLocale());
	}
	
	// /**
	// *
	// * @author <a href="mailto:julioc.chaves@premize.com">Julio C&eacute;sar
	// * Chaves</a>
	// * @date 12/06/2012
	// */
	// private static final String BEAN_TRAZA = "trazaUI";
	private static final String MESSAGE_NOT_FOUND_PREFIX = "??";
	
	public AbstractBaseMessageUI() {
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014
	 * @description 
	 * @param ex
	 * @return
	 */
	protected String getAppBaseExceptionMessage(AppBaseException ex) {
		return MessageBundleLoader.getMessage("i18n.msg_exception", ex.getErrorCode(), 
				LocaleContextHolder.getLocale(), ex.getParameter());
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014
	 * @description 
	 * @param titulo
	 * @param tituloPanel
	 * @param result
	 * @param backButton
	 * @return
	 */
	protected ModelAndView getExceptionPage(String titulo, String tituloPanel, String result, 
			Boolean backButton) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("titulo", titulo);
		model.put("tituloPanel", titulo);
		model.put("result", result);
		model.put("backButton", backButton);
		return new ModelAndView("/pages/general/exceptionContent.jsp", model);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014
	 * @description 
	 * @param titulo
	 * @param tituloPanel
	 * @param result
	 * @param backButton
	 * @return
	 */
	protected ModelAndView getExceptionPageContent(String titulo, String tituloPanel, String result, 
			Boolean backButton) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("titulo", titulo);
		model.put("tituloPanel", titulo);
		model.put("result", result);
		model.put("backButton", backButton);
		return new ModelAndView("/pages/general/exceptionContent.jsp", model);
	}

}