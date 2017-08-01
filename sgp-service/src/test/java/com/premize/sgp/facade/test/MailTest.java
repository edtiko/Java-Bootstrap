/**
 * 
 */
package com.premize.sgp.facade.test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.MailService;
import com.premize.pmz.api.dto.EMailInfo;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.exception.MailServiceException;
import com.premize.sgp.dao.anexo.AnexoHallazgoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.test.AbstractServiceTest;

/**
 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
 * @project sgp-service
 * @class MailTest
 * @date Mar 5, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class MailTest extends AbstractServiceTest {
	
	private static final int RUTA_GUARDAR_ARCHIVO = 3;
	
	@Autowired
	private MailService mailService;
	
	@Autowired 
	private AnexoHallazgoDao anexoHallazgoDao;
	
	@Autowired 
	private SgpParametroDao parametroDao;
	
	/**
	 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
	 * @throws AppBaseException 
	 * @date Mar 5, 2014
	 */
	@Test
	public void sendMail() throws MailServiceException, AppBaseException {
		
		SgpFlujoHallazgo sgpFlujoHallazgo = new SgpFlujoHallazgo();
		SgpParametro paramRuta = parametroDao.get(RUTA_GUARDAR_ARCHIVO);
		List<SgpAnexoHallazgo> listAnexos = anexoHallazgoDao.getAnexosByHallazgo(sgpFlujoHallazgo.getSgpHallazgo().getId());
		EMailInfo eMailInfo = new EMailInfo();
		eMailInfo.setEmails(new String[] { "wisdom.dev@gmail.com" });
		eMailInfo.setFormato(EMailInfo.FORMATO_HTML);
		eMailInfo.setTemplateName("reportarHallazgo.vm");
		Map<String, Object> templateVariables = new HashMap<String, Object>();
		templateVariables.put("nombre", "Jhon");
		eMailInfo.setTemplateVariables(templateVariables);
		eMailInfo.setSubject("Notificación del Hallazgo 56 Asignado");
		for(SgpAnexoHallazgo anexo : listAnexos){
	        File file = new File(paramRuta.getValor()+anexo.getRuta());
	        eMailInfo.addAttachment(anexo.getRuta(), file);
			}
		mailService.enviarEMailTemplate(eMailInfo);
	}
	
}
