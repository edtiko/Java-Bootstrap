package com.premize.sgp.dao.pruebas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Level;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dao.gestion.pruebas.TipoPrioridadDao;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.utils.LogUtil;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-dao-hbxml
 * @class SgpTipoPrioridadDaoTest
 * @description
 * @date 15/07/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-map.xml" })
public class SgpTipoPrioridadDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
    @Autowired
	private TipoPrioridadDao tipoPrioridadDao;
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 15/07/2014
     * @description
     */
    @Test
    public void consultarPrioridadPorTipoHallazgo() {
    	Integer idTipoHallazgo = 5;
    	
    	try {
    		List<TipoPrioridadDTO> result = tipoPrioridadDao.consultarPrioridadPorTipoHallazgo(idTipoHallazgo);
    		Assert.assertNotNull(result);
    	} catch (AppBaseException ex) {
    		LogUtil.log(getClass(), Level.ERROR, ex.getMessage(), ex);
    		fail(ex.getMessage());
    	}
    			
    }

}
