/**
 * 
 */
package com.premize.sgp.service.parametros;

import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;
import com.premize.sgp.utils.FilePmz;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class EmpresaService
 * @since 21/01/2014
 */
public interface EmpresaService extends EntityService<SgpEmpresa, Integer> {
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param pEmpresa
	 */
	public void guardar(EmpresaDTO pEmpresa, FilePmz logo) throws Exception;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param pEmpresa
	 */
	public void actualizar(EmpresaDTO pEmpresa, FilePmz logo) throws AppBaseException;
	
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
	 * @param idPais
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<EmpresaDTO> consultarEmpresasPorCiudad(Integer idCiudad, Paginator page) throws AppBaseException;
}
