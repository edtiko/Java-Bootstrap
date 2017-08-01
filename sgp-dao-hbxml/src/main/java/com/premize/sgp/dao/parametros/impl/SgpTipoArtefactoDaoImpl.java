package com.premize.sgp.dao.parametros.impl;

import org.springframework.stereotype.Repository;

import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.parametros.TipoArtefactoDao;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpTipoArtefactoDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpTipoArtefactoDaoImpl extends HibernateDaoImpl<SgpTipoArtefacto, Integer> implements TipoArtefactoDao {
	
}
