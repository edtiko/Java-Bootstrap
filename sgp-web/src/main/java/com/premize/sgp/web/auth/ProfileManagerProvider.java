package com.premize.sgp.web.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.StringUtils;
import com.premize.profilemanager.client.MensajeDTO;
import com.premize.profilemanager.client.TipoAutenticacionWs;
import com.premize.profilemanager.client.UsuarioDTO;
import com.premize.profilemanager.client.delegate.AutenticacionRolDelegate;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.facade.parametros.UsuarioFacade;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;


/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class ProfileManagerProvider
 * @since 27/03/2014
 *
 */
// @Service
public class ProfileManagerProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private static final String URL_WSDL_KEY_PARAM = "URL_WSDL";
	private static final String ID_APLICACION_PROFILEMANGER = "ID_APLICACION";
	private static final String PARAMETRO_FIELD_CLAVE = "nombre";
	
	@Autowired
	ParametroFacade parametroFacade;
	
	@Autowired
	UsuarioFacade usuarioFacade;
	
	private static final Logger LOG = LoggerFactory.getLogger(ProfileManagerProvider.class);
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) {
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
		
		if (StringUtils.isNull(username)) {
			throw new AuthenticationServiceException("No se ha ingresado el usuario");
		}
		
		if (authentication.getCredentials() == null
				|| String.valueOf(authentication.getCredentials()).trim().length() == 0) {
			throw new AuthenticationServiceException("No se ha ingresado la contraseña");
		}
		
		// Traer los parametros usuario, idaplicacion(parametros),
		// contrasenia(Local), url (parametros), tipoAutenticacion (LOCAL)
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		try {
			SgpParametro parametroUrlWsdl = parametroFacade.findByProperty(PARAMETRO_FIELD_CLAVE, URL_WSDL_KEY_PARAM);
			
			String urlWsdl = parametroUrlWsdl.getValor();
			
//		    String urlWsdl ="http://localhost:8081/pmz-profile-ws/AutenticacionWsPort?wsdl";
			if (urlWsdl == null) {
				throw new AppBaseException("sin configurar el parametro URL_WSDL", "001");
			}
			LOG.info("valor de urlWsdl " + urlWsdl);
			
			parametroUrlWsdl = parametroFacade.findByProperty(PARAMETRO_FIELD_CLAVE, ID_APLICACION_PROFILEMANGER);
			Long idAplicacionProfilemanager = Long.parseLong(parametroUrlWsdl.getValor());
			
			if (idAplicacionProfilemanager == null) {
				throw new AppBaseException("sin configurar el parametro ID_APLICACION", "001");
			}
			
			LOG.info("valor de idAplicacionProfilemanager " + idAplicacionProfilemanager);
		   
			usuarioDTO = AutenticacionRolDelegate.getInstance().autenticarUsuario(username, idAplicacionProfilemanager,
					String.valueOf(authentication.getCredentials()), TipoAutenticacionWs.LOCAL, urlWsdl);
			
			com.premize.sgp.dto.parametros.UsuarioDTO usuarioSgp = usuarioFacade.obtenerUsuario(username);
			
			/*
			 * Solo usar cuando el profile manager no este funcionando. 
			 * comentar todas las instruciones de arriba desde donde incia el try.
			 */
//			usuarioDTO.setNombre("Danielle");
//			usuarioDTO.setNumeroIdentificacion(187517541L);
//			usuarioDTO.setPrimerApellido("Campbell");
//			usuarioDTO.setUsuario("Valeria.Vasquez");
			if(usuarioSgp == null){
				MensajeDTO msg = new MensajeDTO();
				msg.setMensaje("No existe el usuario en la aplicación");
				msg.setError(true);
				usuarioDTO.setMensajeDTO(msg);
				//throw new AuthenticationServiceException("No existe el usuario en la aplicación");
			}
			// Validacion para ver si el usuario DTO tiene mensaje de error
			if (usuarioDTO.getMensajeDTO() != null && usuarioDTO.getMensajeDTO().isError() == true) {
				throw new Exception();
			}
			
			UserPremizeDetails user = new UserPremizeDetails(username, String.valueOf(authentication.getCredentials()),
					true, usuarioDTO);
			
			return user;
		} catch (Exception e) {
			// tirar excepcion del tipo AuthenticationServiceException
			// logear excepcion
			LOG.error("Error de Consulta en Base de Datos", e);
			throw new AuthenticationServiceException(usuarioDTO.getMensajeDTO().getMensaje());
		}
	}
	
}
