package com.premize.sgp.service.pruebas;

import java.io.IOException;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;

public interface AnexoHallazgoService extends EntityService<SgpAnexoHallazgo, Integer>{

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
