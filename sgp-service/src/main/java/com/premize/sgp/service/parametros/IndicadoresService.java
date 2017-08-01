/**
 * 
 */
package com.premize.sgp.service.parametros;

import java.text.ParseException;
import java.util.List;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.indicadores.HallazgoPorGarantiaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpIndicadores;

/**
 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
 * @project sgp-service
 * @class PaisService
 * @date 31/01/2014
 */

public interface IndicadoresService extends EntityService<SgpIndicadores, Integer> {


	public static final String PARAMETRO_FIELD_CLAVE="nombre";
	public static final String CODIGO_HALLAZGOS_POR_GARANTIA="HALLAZGO_POR_GARANTIA";
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @return
	* @throws AppBaseException
	 */
	List<IndicadoresDTO> getIndicadores() throws AppBaseException ;
	
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
	 */
	IndicadoresDTO getIndicador(Integer idIndicador) throws AppBaseException;
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param indicadoresDTO
	 */
	void guardarIndicador(IndicadoresDTO indicadoresDTO) throws AppBaseException;

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
    HallazgoPorGarantiaDTO hallazgosPorGarantia(Integer idProyecto,String fechaTo,String fechaFrom,List<String> listEstados)  throws AppBaseException, ParseException;
	

	
}
