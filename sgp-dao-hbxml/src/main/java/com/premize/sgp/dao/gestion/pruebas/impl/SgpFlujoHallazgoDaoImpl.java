package com.premize.sgp.dao.gestion.pruebas.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.gestion.pruebas.FlujoHallazgoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpFlujoHallazgoDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpFlujoHallazgoDaoImpl extends HibernateDaoImpl<SgpFlujoHallazgo, Integer> implements FlujoHallazgoDao {
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 24/02/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.FlujoHallazgoDao#obtenerFlujoHallazgo(java.lang.Integer)
	 */
	@Override
	public SgpFlujoHallazgo obtenerFlujoPorHallazgo(Integer idHallazgo) throws AppBaseException {
		
		SgpFlujoHallazgo salida = null;

			
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpFlujoHallazgo.class);
			criteria.createAlias("sgpHallazgo", "hallazgo");
			criteria.add(Restrictions.eq("hallazgo.id", idHallazgo ));
			criteria.addOrder(Order.desc("fecCreacion"));
			if(!findByCriteria(criteria).isEmpty()){
				salida = findByCriteria(criteria).get(0);
			}
		
		return salida;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 7/03/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.FlujoHallazgoDao#getFlujosByHallazgo(java.lang.Integer)
	 */
	@Override
	public List<SgpFlujoHallazgo> getFlujosByHallazgo(Integer idHallazgo) throws AppBaseException {
		List<SgpFlujoHallazgo> salida = null;
	
			
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpFlujoHallazgo.class);
			criteria.createAlias("sgpHallazgo", "hallazgo");
			criteria.add(Restrictions.eq("hallazgo.id", idHallazgo));
			criteria.addOrder(Order.asc("fecCreacion"));
			
			if(!findByCriteria(criteria).isEmpty()){
				salida = findByCriteria(criteria);
			}
			
	
		return salida;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/03/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.FlujoHallazgoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<FlujoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpFlujoHallazgo.class);
		
		// Filtering and Searching
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			
			cq.add(disyuctionConsulta);
		}
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if (field.equals("id") || field.equals("indActivo")) {
				if (value != null)
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
			} else {
				if (value != null) {
					cq.add(Restrictions.like(field, value, MatchMode.ANYWHERE).ignoreCase());
				}
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
		
		List<SgpFlujoHallazgo> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<FlujoHallazgoDTO> resultList = new ArrayList<FlujoHallazgoDTO>();
		for (SgpFlujoHallazgo sgpFlujo : listaReturn) {
			FlujoHallazgoDTO flujoDTO = new FlujoHallazgoDTO(sgpFlujo);
			resultList.add(flujoDTO);
		}
		
		return new ResultSet<FlujoHallazgoDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
}
