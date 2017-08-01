package com.premize.sgp.dao.parametros;

import com.premize.sgp.dto.ResultSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class EmpresaDao
 * @since 27/03/2014
 *
 */
@Repository
public interface EmpresaDao extends Dao<SgpEmpresa, Integer> {
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @param criteria
	 * @return
	 */
	ResultSet<EmpresaDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idPais
	 * @param page
	 * @return
	 * @throws AppBaseException
	 */
	public List<EmpresaDTO> consultarEmpresaPorCiudad(Integer idCiudad, Paginator page) throws AppBaseException;
}
