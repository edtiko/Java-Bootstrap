/**
 * 
 */
package com.premize.sgp.service.pruebas;

import java.io.InputStream;
import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CargueCasoPruebaDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroService
 * @since 15/01/2014
 * 
 */
public interface CasoDePruebaService extends
		EntityService<SgpCasoPrueba, Integer> {


	/**
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 12/02/2014
	 * @param casoDePruebaDTO
	 * @throws AppBaseException
	 */
	void guardarCasoDePrueba(CasoDePruebaDTO casoDePruebaDTO)
			throws AppBaseException;

    /**
     * 
     * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
     * @since 5/05/2014
     * @param criteria
     * @return
     * @throws AppBaseException
     */
	ResultSet<CasoDePruebaDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 5/05/2014
	 * @param casoDePruebaDTO
	 * @throws AppBaseException
	 */
	void editarCasoDePrueba(CasoDePruebaDTO casoDePruebaDTO)
			throws AppBaseException;

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 28/01/2014
	 * @param idParametro
	 * @return
	 * @throws AppBaseException
	 */
	CasoDePruebaDTO getCasoDePrueba(Integer idCasoDePrueba)
			throws AppBaseException;

	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 20/02/2014
	 * @param id
	 */
	void eliminarCasoPrueba(Integer id) throws AppBaseException;

	/**
	 * @author <a href="mailto:hernand.ramirez@premize.com">Hernan David Ramirez
	 *         Mejia</a>
	 * @throws AppBaseException
	 * @since 20/02/2014
	 * 
	 */
	void guardarCasoDePrueba(List<CasoDePruebaDTO> listaCasoDePruebaDTO)
			throws AppBaseException;

	/**
	 * @author <a href="mailto:hernand.ramirez@premize.com">Hernan David Ramirez
	 *         Mejia</a>
	 * @param imstream
	 * @return
	 * @throws AppBaseException
	 */
	CargueCasoPruebaDTO cargarCasosPruebas(InputStream archivoImportar, Integer idMapaPruebas,
			String login) throws AppBaseException;

	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 25/02/2014
	 * @param casoDePruebaDTO
	 */
	void ejecutarCasoPrueba(CasoDePruebaDTO casoDePruebaDTO)
			throws AppBaseException;

}
