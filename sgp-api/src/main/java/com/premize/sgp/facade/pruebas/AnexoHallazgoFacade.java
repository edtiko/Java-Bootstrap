package com.premize.sgp.facade.pruebas;

import java.io.IOException;

import com.premize.pmz.api.EntityFacade;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class AnexoHallazgoFacade
 * @since 27/03/2014
 *
 */
public interface AnexoHallazgoFacade extends EntityFacade<SgpAnexoHallazgo, Integer> {

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/05/2014
	 * @param id
	 * @throws AppBaseException
	 * @throws IOException 
	 */
	public void eliminarAnexo(Integer id)throws AppBaseException, IOException;
	
}
