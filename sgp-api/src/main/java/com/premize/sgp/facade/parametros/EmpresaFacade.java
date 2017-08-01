/**
 * 
 */
package com.premize.sgp.facade.parametros;

import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;
import com.premize.sgp.utils.FilePmz;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class EmpresaFacade
 * @since 21/01/2014
 */
public interface EmpresaFacade extends EntityFacade<SgpEmpresa, Integer> {
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param empresa
	 */
	void guardar(EmpresaDTO pEmpresa, FilePmz logo) throws Exception;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param empresa
	 */
	void actualizar(EmpresaDTO pEmpresa, FilePmz logo) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<EmpresaDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @param idEmpresa
	 * @return
	 */
	EmpresaDTO obtenerEmpresa(Integer idEmpresa) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idCiudad
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	List<EmpresaDTO> consultarEmpresasPorCiudad(Integer idCiudad, Paginator page) throws AppBaseException;
}
