package com.premize.sgp.dao.gestion.pruebas;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-dao-hbxml
 * @class SgpCasoPruebaDao
 * @date 12/02/2014
 */
public interface CasoPruebaDao extends Dao<SgpCasoPrueba, Integer> {
	
	String NQ_CONSULTAR_MAXIMO_CONSECUTIVO = "consultarConsecutivo";
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 12/02/2014
	 * @return
	 * @throws AppBaseException 
	 */
	public List<SgpCasoPrueba> getListCasosDePruebas() throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 12/02/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	public ResultSet<CasoDePruebaDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 5/06/2014
	 * @description 
	 * @param idmapa
	 * @return
	 * @throws AppBaseException
	 */
	Integer consultarConsecutivo(Integer idmapa) throws AppBaseException;
}
