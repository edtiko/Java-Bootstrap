/**
 * 
 */
package com.premize.sgp.facade.pruebas.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CargueCasoPruebaDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.facade.pruebas.CasosDePruebaFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.service.pruebas.CasoDePruebaService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroFacadeImpl
 * @since 17/01/2014
 */
@Service("casosDePruebaFacadeImpl")
public class CasosDePruebaFacadeImpl extends AbstractEntityFacade<SgpCasoPrueba, Integer> implements
		CasosDePruebaFacade {
	
	@Autowired
	private CasoDePruebaService casoDePruebaService;
	
	@Override
	public CasoDePruebaService getEntityService() {
		
		return casoDePruebaService;
	}
	
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.CasosDePruebaFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<CasoDePruebaDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return casoDePruebaService.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.CasosDePruebaFacade#guardarCasosDePrueba(com.premize.sgp.dto.pruebas.CasoDePruebaDTO)
	 */
	@Override
	public void guardarCasosDePrueba(CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException {
		
		casoDePruebaService.guardarCasoDePrueba(casoDePruebaDTO);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.CasosDePruebaFacade#editarCasoDePrueba(com.premize.sgp.dto.pruebas.CasoDePruebaDTO)
	 */
	@Override
	public void editarCasoDePrueba(CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException {
		casoDePruebaService.editarCasoDePrueba(casoDePruebaDTO);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.CasosDePruebaFacade#getCasoDePrueba(java.lang.Integer)
	 */
	@Override
	public CasoDePruebaDTO getCasoDePrueba(Integer idCasoDePrueba) throws AppBaseException {
		
		return casoDePruebaService.getCasoDePrueba(idCasoDePrueba);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.CasosDePruebaFacade#eliminarCasoDePrueba(java.lang.Integer)
	 */
	@Override
	public void eliminarCasoDePrueba(Integer id) throws AppBaseException {
		
		casoDePruebaService.eliminarCasoPrueba(id);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.CasosDePruebaFacade#ejecutarCasoPrueba(com.premize.sgp.dto.pruebas.CasoDePruebaDTO)
	 */
	@Override
	public void ejecutarCasoPrueba(CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException {
		
		casoDePruebaService.ejecutarCasoPrueba(casoDePruebaDTO);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.CasosDePruebaFacade#cargarCasosPruebas(java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@Override
	public CargueCasoPruebaDTO cargarCasosPruebas(InputStream archivoImportar, Integer idMapaPrueba, String login)
			throws AppBaseException {
		
		return casoDePruebaService.cargarCasosPruebas(archivoImportar, idMapaPrueba, login);
	}
	
}
