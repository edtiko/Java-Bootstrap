package com.premize.sgp.dao.anexo;

import java.util.List;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.pruebas.AnexoHallazgoDTO;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class AnexoHallazgoDao
 * @since 27/03/2014
 *
 */
public interface AnexoHallazgoDao extends Dao<SgpAnexoHallazgo, Integer> {
	
	String NQ_CONSULTAR_ANEXO_CONTAINS_RUTA = "consultarAnexoContainsRuta";
	
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param idHallazgo
	 * @return
	 * @throws AppBaseException 
	 */
	List<SgpAnexoHallazgo> getAnexosByHallazgo(Integer idHallazgo) throws AppBaseException;
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/06/2014
	 * @description 
	 * @param ruta
	 * @return
	 * @throws AppBaseException
	 */
	List<AnexoHallazgoDTO> getAnexosContainsName(String ruta) throws AppBaseException;
	
}
