package com.premize.sgp.service.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.Filter;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.CiudadDTO;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class CiudadServiceImpl
 * @date 31/01/2014
 */
public interface CiudadService extends EntityService<SgpCiudad, Integer> {
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idDepartamento
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<CiudadDTO> consultarCiudadPorDepartamento(Integer idDepartamento, Paginator page)
			throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 * @throws AppBaseException
	 */
	List<CiudadDTO> getListCiudades() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param filters
	 * @param paginator
	 * @return
	 */
	List<SgpCiudad> findAllParametrosByFilter(Filter[] filters, Paginator paginator);
	
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
	 * @param ciudad
	 * @throws AppBaseException
	 */
	void editarCiudad(CiudadDTO ciudad) throws AppBaseException;
	
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
	 * @param ciudad
	 * @throws AppBaseException
	 */
	void guardarCiudad(CiudadDTO ciudad) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @return
	 */
	List<SgpCiudad> obtenerCiudades();
	
}
