/**
 * 
 */
package com.premize.sgp.facade.parametros.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.facade.parametros.PaisFacade;
import com.premize.sgp.modelo.entities.parametros.SgpPais;
import com.premize.sgp.service.parametros.PaisService;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class PaisFacadeImpl
 * @since 21/01/2014
 */
@Service("paisFacade")
public class PaisFacadeImpl extends AbstractEntityFacade<SgpPais, Integer> implements PaisFacade {
	
	@Autowired
	private PaisService paisService;
	
	@Override
	public ResultSet<PaisDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return paisService.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.PaisFacade#guardarPais(com.premize.sgp.dto.parametros.PaisDTO)
	 */
	@Override
	public void guardarPais(PaisDTO pais) throws AppBaseException {
		
		paisService.guardarPais(pais);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.PaisFacade#editarPais(com.premize.sgp.dto.parametros.PaisDTO)
	 */
	@Override
	public void editarPais(PaisDTO pais) throws AppBaseException {
		paisService.editarPais(pais);
	}
	
	public EntityService<SgpPais, Integer> getEntityService() {
		return null;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 31/01/2014
	 * @see com.premize.sgp.facade.parametros.PaisFacade#getPaises()
	 */
	
	@Override
	public List<PaisDTO> getPaises() throws AppBaseException {
		return paisService.getPaises();
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.PaisFacade#getPais(java.lang.Integer)
	 */
	public PaisDTO getPais(Integer idPais) throws AppBaseException {
		
		return paisService.getPais(idPais);
		
	}
	
}