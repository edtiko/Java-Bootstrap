/**
 * 
 */
package com.premize.sgp.service.pruebas;

import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroService
 * @since 15/01/2014
 * 
 */
public interface MapaArtefactoService extends
		EntityService<SgpMapaArtefacto, Integer> {

	/**
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idMapaPruebas
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<MapaArtefactoDTO> consultarArtefactosPorMapaPruebas(
			Integer idMapaPruebas, Paginator page) throws AppBaseException;

	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @param idMapaPruebas
	 * @param page
	 * @return
	 */
	public List<MapaArtefactoDTO> getArtefactosActivosMapaDePruebas(
			Integer idMapaPruebas, Paginator page) throws AppBaseException;
}
