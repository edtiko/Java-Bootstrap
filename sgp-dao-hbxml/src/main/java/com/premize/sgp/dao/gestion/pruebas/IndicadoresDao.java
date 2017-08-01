package com.premize.sgp.dao.gestion.pruebas;

import java.util.Date;
import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.indicadores.HallazgoPorGarantiaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpIndicadores;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-dao-hbxml
* @class IndicadoresDao
* @date 9/07/2014
*
 */
public interface IndicadoresDao extends Dao<SgpIndicadores, Integer>  {

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @return
	* @throws AppBaseException
	 */
	List<SgpIndicadores> consultarIndicadores() throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	ResultSet<IndicadoresDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param indicadoresDTO
	* @return
	* @throws AppBaseException
	 */
	boolean validarIndicador(IndicadoresDTO indicadoresDTO)
			throws AppBaseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 14/07/2014
	* @param idProyecto
	* @return
	* @throws AppBaseException
	 */
	HallazgoPorGarantiaDTO hallazgosPorGarantia(Integer idProyecto,Date fechaFrom,Date fechaTo,List<String> listEstados,List<Integer> listCodigoGarantias)  throws AppBaseException;
	
}
