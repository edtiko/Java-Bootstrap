package com.premize.sgp.facade.parametros;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.CiudadDTO;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class CiudadFacade
 * @since 27/03/2014
 *
 */
public interface CiudadFacade extends EntityFacade<SgpCiudad, Integer> {
	
	/**
	 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
	 * @date 31/01/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<CiudadDTO> getListCiudades() throws AppBaseException;
	
	/**
	 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
	 * @date 30/01/2014
	 * @param pais
	 * @throws AppBaseException
	 */
	void guardarCiudad(CiudadDTO ciudad) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<CiudadDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param ciudaddto
	 * @throws AppBaseException
	 */
	void editarCiudad(CiudadDTO ciudaddto) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idCiudad
	 * @return
	 * @throws AppBaseException
	 */
	CiudadDTO getCiudad(Integer idCiudad) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param id_hallazgo
	 * @param default1
	 * @return
	 */
	List<CiudadDTO> consultarCiudadDTOs(Integer id_hallazgo, Paginator default1);
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 12/02/2014
	 * @param idDepartamento
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<CiudadDTO> consultarCiudadPorDepartamento(Integer idDepartamento, Paginator page)
			throws AppBaseException;
	
}
