/**
 * 
 */
package com.premize.sgp.service.parametros;

import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.TipoArtefactoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class PaisServiceTest
 * @since 21/01/2014
 */
public interface TipoArtefactoService extends EntityService<SgpTipoArtefacto, Integer> {
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
