/**
 * 
 */
package com.premize.sgp.service;

import java.util.List;
import java.util.Map;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.Filter;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroService
 * @since 15/01/2014
 */
public interface ParametroService extends EntityService<SgpParametro, Integer> {
	
	String CODIGO_MAX_SIZE_ANEXO = "PESO_MAXIMO";
	String CODIGO_ALLOWED_FILE_TYPES = "TIPOS_ARCHIVOS_PERMITIDOS";
	String CODIGO_ALLOWED_LOGO_TYPES = "TIPOS_IMAGENES_PERMITIDAS";
	String PARAMETRO_FIELD_CLAVE="nombre";
	String ESTADO_NO_ACTUALIZA_USUARIO_KEY="ESTADO_NO_ACTUALIZA_USUARIO";
	String RUTA_ANEXOS = "RUTA_ANEXOS";
	
	//llaves Map restricciones anexo.
	String ANEXO_REST_MAP_KEY_ALLOWED_FILES = "allowedfiles"; 
	String ANEXO_REST_MAP_KEY_ALLOWED_LOGO_FILES = "allowedfilesLogo"; 
	String ANEXO_REST_MAP_KEY_FILE_SIZE = "filesize";
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 15/01/2014
	 * @return
	 * @throws AppBaseException 
	 */
	List<ParametroDTO> getListParametros() throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param parametro
	 * @throws AppBaseException
	 */
	void guardarParametro(ParametroDTO parametro) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param filters
	 * @param paginator
	 * @return
	 */
	List<SgpParametro> findAllParametrosByFilter(Filter[] filters, Paginator paginator);
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 */
	ResultSet<ParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param parametro
	 * @throws AppBaseException
	 */
	void editarParametro(ParametroDTO parametro) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 28/01/2014
	 * @param idParametro
	 * @return
	 * @throws AppBaseException
	 */
	ParametroDTO getParametro(Integer idParametro) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 6/06/2014
	 * @description 
	 * @param parametroKey
	 * @return
	 * @throws AppBaseException
	 */
	ParametroDTO getParametro(String parametroKey) throws AppBaseException;
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 * @param idTipoParametro
	 * @return
	 */
	List<ParametroDTO> listarParametrosPorTipoParametro(Integer idTipoParametro, Paginator page)
			throws AppBaseException;

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 29/05/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
	Map<String,Object> getMapRestriccionesAnexo() throws AppBaseException;
   /**
    * 
   * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
   * @date 11/06/2014
   * @param estado
   * @return
   * @throws AppBaseException
    */
	boolean getEstadoNoSolicitaUsuario(String estado) throws AppBaseException;
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 11/06/2014
* @return
* @throws AppBaseException
 */
	Map<String, Object> getMapRestriccionesLogo() throws AppBaseException;
	
}
