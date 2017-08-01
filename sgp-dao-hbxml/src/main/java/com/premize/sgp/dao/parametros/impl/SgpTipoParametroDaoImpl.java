package com.premize.sgp.dao.parametros.impl;

import com.premize.sgp.dto.ResultSet;
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
import com.premize.sgp.dao.parametros.TipoParametroDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpTipoParametroDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpTipoParametroDaoImpl extends HibernateDaoImpl<SgpTipoParametro, Integer> implements TipoParametroDao {
	
	private static final Integer ACTIVO = 1;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 31/01/2014
	 * @see com.premize.sgp.dao.parametros.TipoParametroDao#getTipos()
	 */
	@Override
	public List<SgpTipoParametro> getTipos() throws AppBaseException {
		List<SgpTipoParametro> salida = null;

			DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoParametro.class);
			criteria.add(Restrictions.eq("indActivo", ACTIVO));
			criteria.addOrder(Order.desc("id"));
			if(!findByCriteria(criteria).isEmpty()){
			salida = findByCriteria(criteria);
			}
	
		
		return salida;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 29/01/2014
	 * @see com.premize.sgp.dao.parametros.TipoParametroDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpTipoParametro.class);
		
		// Filtering and Searching
		
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
		
		List<SgpTipoParametro> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<TipoParametroDTO> resultList = new ArrayList<TipoParametroDTO>();
		for (SgpTipoParametro sgpTipoParametro : listaReturn) {
			TipoParametroDTO tipoParametroDTO = new TipoParametroDTO(sgpTipoParametro);
			resultList.add(tipoParametroDTO);
		}
		
		return new ResultSet<TipoParametroDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
}
