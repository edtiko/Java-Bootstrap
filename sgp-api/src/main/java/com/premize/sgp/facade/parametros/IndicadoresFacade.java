/**
 * 
 */
package com.premize.sgp.facade.parametros;

import java.text.ParseException;
import java.util.List;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.indicadores.HallazgoPorGarantiaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpIndicadores;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-api
* @class IndicadoresFacade
* @date 9/07/2014
*
 */
public interface IndicadoresFacade extends EntityFacade<SgpIndicadores, Integer> {
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @return
	* @throws AppBaseException
	 */
	List<IndicadoresDTO> getPaises() throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param criteria
	* @return
	* @throws AppBaseException
	 */
	ResultSet<IndicadoresDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param idIndicador
	* @return
	* @throws AppBaseException
	 */
	IndicadoresDTO getIndicador(Integer idIndicador) throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param indicadoresDTO
	* @throws AppBaseException
	 */
	void guardarIndicador(IndicadoresDTO indicadoresDTO)
			throws AppBaseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param indicadoresDTO
	 */
	void editarIndicador(IndicadoresDTO indicadoresDTO)  throws AppBaseException;

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 14/07/2014
	* @param idProyecto
	* @return
	 */
	HallazgoPorGarantiaDTO hallazgosPorGarantia(Integer idProyecto,String fechaTo,String fechaFrom,List<String> listEstados) throws AppBaseException, ParseException ;
}
