/**
 * 
 */
package com.premize.sgp.facade.pruebas;

import java.io.InputStream;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CargueCasoPruebaDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroFacade
 * @since 17/01/2014
 */
public interface CasosDePruebaFacade extends EntityFacade<SgpCasoPrueba, Integer> {
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param casoDePrueba
	 * @throws AppBaseException
	 */
	void guardarCasosDePrueba(CasoDePruebaDTO casoDePrueba) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<CasoDePruebaDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param casoDePruebaDTO
	 * @throws AppBaseException
	 */
	void editarCasoDePrueba(CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idProyecto
	 * @return
	 * @throws AppBaseException
	 */
	CasoDePruebaDTO getCasoDePrueba(Integer idCasoDePrueba) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 20/02/2014
	 * @param id
	 */
	void eliminarCasoDePrueba(Integer id) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 25/02/2014
	 * @param casoDePruebaDTO
	 */
	void ejecutarCasoPrueba(CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @param ruta
	 * @param integer
	 * @param login
	 * @return
	 */
	CargueCasoPruebaDTO cargarCasosPruebas(InputStream archivoImportar, Integer idMapaPrueba, String login) throws AppBaseException;
	
}
