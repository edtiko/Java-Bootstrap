package com.premize.sgp.dao.parametros.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpParametroDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpParametroDaoImpl extends HibernateDaoImpl<SgpParametro, Integer> implements SgpParametroDao {
	
	
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 15/01/2014
	 * @see com.premize.sgp.dao.parametros.SgpParametroDao#getListParametros()
	 */
	
	public List<SgpParametro> getListParametros() throws AppBaseException {
		List<SgpParametro> salida = null;
	
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpParametro.class);
			criteria.addOrder(Order.desc("id"));
			criteria.add(Restrictions.eq("indActivo", ACTIVO));
			if(!findByCriteria(criteria).isEmpty()){
			salida = findByCriteria(criteria);
			}
	
		
		return salida;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @throws HibernateException
	 * @since 23/01/2014
	 * @see com.premize.sgp.dao.parametros.SgpParametroDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpParametro.class).createAlias("sgpTipoParametro",
				"tipoParametro");
		
		// Filtering and Searching
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			
			disyuctionConsulta.add(Restrictions.like("valor", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("tipoParametro.nombre", searchLike, MatchMode.ANYWHERE)
					.ignoreCase());
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
		
		List<SgpParametro> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<ParametroDTO> resultList = new ArrayList<ParametroDTO>();
		for (SgpParametro sgpParametro : listaReturn) {
			ParametroDTO parametroDTO = new ParametroDTO(sgpParametro);
			resultList.add(parametroDTO);
		}
		
		return new ResultSet<ParametroDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 * @param idTipoParametro
	 * @return
	 */
	private DetachedCriteria listarParametrosPorTipoParametros(Integer idTipoParametro) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpParametro.class);
		consulta.add(Restrictions.eq("indActivo", 1));
		consulta.add(Restrictions.or(Restrictions.eq(TIPO_PARAMETRO_ID, idTipoParametro),
				Restrictions.isNull(TIPO_PARAMETRO)));
		return consulta;
	}
	
	/**
	 * ImplementaciÃ³n con hibernate del mÃ©todo
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 * @param idTipoParametro
	 * @return
	 */
	public List<ParametroDTO> listarParametrosPorTipoParametro(Integer idTipoParametro, Paginator page)
			throws AppBaseException {
		List<SgpParametro> parametros = this.findByCriteria(listarParametrosPorTipoParametros(idTipoParametro), page);
		
		List<ParametroDTO> parametroDTOs = new ArrayList<ParametroDTO>();
		
		for (SgpParametro sgpParametro : parametros) {
			ParametroDTO parametroDTO = new ParametroDTO(sgpParametro);
			parametroDTOs.add(parametroDTO);
		}
		return parametroDTOs;
	}
	
}
