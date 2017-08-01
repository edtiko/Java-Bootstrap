package com.premize.sgp.dao.anexo.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.anexo.AnexoHallazgoDao;
import com.premize.sgp.dto.pruebas.AnexoHallazgoDTO;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpAnexoHallazgoDaoImp
 * @since 27/03/2014
 *
 */
@Repository
public class SgpAnexoHallazgoDaoImp extends HibernateDaoImpl<SgpAnexoHallazgo, Integer> implements AnexoHallazgoDao {
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 9/03/2014
	 * @see com.premize.sgp.dao.anexo.AnexoHallazgoDao#getAnexosByHallazgo(java.lang.Integer)
	 */
	@Override
	public List<SgpAnexoHallazgo> getAnexosByHallazgo(Integer idHallazgo) throws AppBaseException {
		
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpAnexoHallazgo.class);
			criteria.createAlias("sgpHallazgo", "hallazgo");
			criteria.add(Restrictions.eq("hallazgo.id", idHallazgo));
			criteria.addOrder(Order.asc("fecCreacion"));
		    return findByCriteria(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/06/2014
	 * @description Consulta un anexo segun el nombre del archivo anexo.
	 * @param ruta
	 * @return
	 * @throws AppBaseException
	 */
	public List<AnexoHallazgoDTO> getAnexosContainsName(String nombre) throws AppBaseException {
		Query query = createNamedQuery(AnexoHallazgoDao.NQ_CONSULTAR_ANEXO_CONTAINS_RUTA);
		query.setParameter("rutaAnexo", "%"+nombre);
		query.setResultTransformer(Transformers.aliasToBean(AnexoHallazgoDTO.class));
		List<AnexoHallazgoDTO> anexos = findByOtherQuery(query);
		return anexos;
	}
	
}
