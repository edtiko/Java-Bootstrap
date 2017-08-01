package com.premize.sgp.dao.parametros.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.parametros.PaisDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.modelo.entities.parametros.SgpPais;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpPaisDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpPaisDaoImpl extends HibernateDaoImpl<SgpPais, Integer> implements PaisDao {
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @throws AppBaseException
	 * @since 21/01/2014
	 * @see com.premize.sgp.dao.parametros.PaisDao#obtenerPaises()
	 */
	@Override
	public List<SgpPais> consultarPaises() throws AppBaseException {
		List<SgpPais> result = new ArrayList<SgpPais>();

			DetachedCriteria criteria = DetachedCriteria.forClass(SgpPais.class);
			criteria.add(Restrictions.eq("indActivo", 1));
			criteria.addOrder(Order.asc("nombre"));
			if(!findByCriteria(criteria).isEmpty()){
			result = findByCriteria(criteria);
			}
	
		return result;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.parametros.PaisDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<PaisDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpPais.class);
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disyuctionConsulta);
		}
		
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if (field.equals("id") || field.equals("indActivo")) {
				if (value != null)
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
			} else {
				if (value != null)
					cq.add(Restrictions.like(field, value, MatchMode.ANYWHERE).ignoreCase());
			}
		}
		Integer countTotalRegistros = findByCriteria(cq).size();
		// Sorting
		for (SortField sortField : sortFields) {
			
			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();
			if (direction.equalsIgnoreCase("asc")) {
				cq.addOrder(Order.asc(field));
			} else if (direction.equalsIgnoreCase("desc")) {
				cq.addOrder(Order.desc(field));
				
			}
			
		}
		List<SgpPais> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<PaisDTO> resultList = new ArrayList<PaisDTO>();
		for (SgpPais sgpPais : listaReturn) {
			PaisDTO paisDTO = new PaisDTO(sgpPais);
			resultList.add(paisDTO);
			
		}
		return new ResultSet<PaisDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 12/02/2014
	 * @see com.premize.sgp.dao.parametros.PaisDao#getPaisByCiudad(java.lang.Integer)
	 */
	@Override
	public SgpPais getPaisByCiudad(Integer idCiudad) throws AppBaseException {
		return null;
	}
	
	/**
	 * @see com.premize.sgp.dao.parametros.PaisDao#getRecordsByHSQL()
	 */
	@Override
	public List<PaisDTO> getRecordsByHSQL() throws AppBaseException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT pais.nombre as nombre, ");
		buffer.append("(SELECT COUNT(depa.id) as totalDepartamentos FROM com.premize.sgp.modelo.entities.parametros.SgpDepartamento depa WHERE depa.sgpPais.id = pais.id) as totalDepartamentos ");
		buffer.append("FROM com.premize.sgp.modelo.entities.parametros.SgpPais pais");
		
		Query query = (Query) createQuery(buffer.toString());
		((org.hibernate.Query) query).setResultTransformer(Transformers.aliasToBean(PaisDTO.class));
		
		return findByOtherQuery((org.hibernate.Query) query);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 7/03/2014
	 * @see com.premize.sgp.dao.parametros.PaisDao#validarPais(com.premize.sgp.dto.parametros.PaisDTO)
	 */
	@Override
	public boolean validarPais(PaisDTO pais) throws AppBaseException {
		Boolean result = false;

			DetachedCriteria criteria = DetachedCriteria.forClass(SgpPais.class);
			criteria.add(Restrictions.eq("nombre", pais.getNombre()));
			criteria.add(Restrictions.eq("indActivo", 1));
			
			if (!findByCriteria(criteria).isEmpty()) {
				result = true;
			}
		return result;
	}
	
}
