/**
 * 
 */
package com.premize.sgp.facade.parametros;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.TipoArtefactoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-api
 * @class TipoArtefectoFacade
 * @since 24/01/2014
 */
public interface TipoArtefactoFacade extends EntityFacade<SgpTipoArtefacto, Integer> {
	
	/**
	 * Metodo que obtiene todos los tipo de artefactos y los recoje como un DTO
	 * 
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<TipoArtefactoDTO> obtenerTiposArtefactos() throws AppBaseException;
	
}
