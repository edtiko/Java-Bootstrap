package com.premize.sgp.service.pruebas.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.anexo.AnexoHallazgoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.facade.ParametroFacade;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.ParametroService;
import com.premize.sgp.service.pruebas.AnexoHallazgoService;
import com.premize.sgp.utils.LogUtil;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class AnexoHallazgoServiceImpl
 * @since 27/03/2014
 *
 */
@Service
public class AnexoHallazgoServiceImpl extends AbstractEntityService<SgpAnexoHallazgo, Integer> implements
		AnexoHallazgoService {
	
	private static final String CODIGO_RUTA_KEY = "RUTA";
	private static final String PARAMETRO_FIELD_CLAVE="nombre";
	private static final String RUTA_ANEXOS = "RUTA_ANEXOS";
	
	@Autowired
	AnexoHallazgoDao anexoHallazgoDao;
	
	@Autowired
	SgpParametroDao parametroDao;
	
	@Autowired
	ParametroFacade parametroFacade;

	
	@Override
	public Dao<SgpAnexoHallazgo, Integer> getDao() {
		
		return anexoHallazgoDao;
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws IOException 
	 * @since 7/05/2014
	 * @see com.premize.sgp.service.pruebas.AnexoHallazgoService#eliminarAnexo(java.lang.Integer)
	 */
	@Override
	public void eliminarAnexo(Integer id) throws AppBaseException, IOException {
	
		SgpAnexoHallazgo anexoHallazgo =  anexoHallazgoDao.get(id);
		SgpParametro ruta = parametroDao.findByProperty(PARAMETRO_FIELD_CLAVE, RUTA_ANEXOS);
		String rutaAnexo = ruta.getValor()+anexoHallazgo.getRuta();
		LogUtil.log(getClass(), Level.INFO, rutaAnexo, null);
		File file = new File(rutaAnexo);
		
		if(!file.exists()) {
			throw new AppBaseException("No se pudo borrar el archivo", "FILE_NOT_FOUND");
		}
		FileUtils.deleteQuietly(file);
		anexoHallazgoDao.deleteById(id);
		
	}
	
}
