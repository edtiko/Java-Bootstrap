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
import com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.utils.NumericUtils;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-dao-hbxml
* @class SgpTipoHallazgoDaoImpl
* @date 13/06/2014
*
 */
@Repository
public class TipoHallazgoDaoImpl extends HibernateDaoImpl<SgpTipoHallazgo, Integer> implements TipoHallazgoDao {
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoHallazgoDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpTipoHallazgo.class);
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disyuctionConsulta);
		}
		
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if (field.equals("indActivo")) {
				if (value != null)
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
			}else if (field.equals("id")){
				if (value != null){
					if(!NumericUtils.isNumeric(value)){
						value="0";
					}
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
				}
			}else if(field.equals("esPuntuado")){
				if (value != null){
				    if(value.equals("No")){
				    	value="0";
				    }else{
				    	value="1";
				    }
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
					value=null;
				}
			} else if(field.equals("tieneCausaGeneracion")){
				if (value != null){
				    if(value.equals("No")){
				    	value="0";
				    }else{
				    	value="1";
				    }
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
					value=null;
				}
			}else {
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
		List<SgpTipoHallazgo> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<TipoHallazgoDTO> resultList = new ArrayList<TipoHallazgoDTO>();
		for (SgpTipoHallazgo sgpTipoHallazgo : listaReturn) {
			TipoHallazgoDTO tipoHallazgoDTO = new TipoHallazgoDTO(sgpTipoHallazgo);
			resultList.add(tipoHallazgoDTO);
			
		}
		return new ResultSet<TipoHallazgoDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
/*
 * (non-Javadoc)
 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#validarTipoHallazgo(com.premize.sgp.dto.pruebas.TipoHallazgoDTO)
 */
	@Override
	public boolean validarTipoHallazgo(TipoHallazgoDTO tipoHallazgoDTO)
			throws AppBaseException {
		Boolean result = false;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoHallazgo.class);
		if(null!=tipoHallazgoDTO ){
			if( null !=tipoHallazgoDTO.getNombre()){
			criteria.add(Restrictions.eq("nombre", tipoHallazgoDTO.getNombre().trim()).ignoreCase());
			criteria.add(Restrictions.eq("indActivo", 1));
			
			if (!findByCriteria(criteria).isEmpty()) {
				result = true;
			}
			}
		}
		
	return result;
}
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#consultarTipoHallazgos(java.lang.String[])
	 */
	@Override
	public List<SgpTipoHallazgo> consultarTipoHallazgos(String[] listTiposHallazgos)
			throws AppBaseException {
		List<SgpTipoHallazgo> result = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoHallazgo.class);
		criteria.add(Restrictions.eq("indActivo", 1));
		criteria.add(Restrictions.in("nombre", listTiposHallazgos));
		criteria.addOrder(Order.asc("nombre"));
		if(!findByCriteria(criteria).isEmpty()){
		result = findByCriteria(criteria);
		}

	return result;
	}
	
	@Override
	public List<SgpTipoHallazgo> tipoHallazgos() throws AppBaseException {
		List<SgpTipoHallazgo> result = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoHallazgo.class);
		criteria.add(Restrictions.eq("indActivo", 1));
	
		criteria.addOrder(Order.asc("nombre"));
		if(!findByCriteria(criteria).isEmpty()){
		result = findByCriteria(criteria);
		}

	return result;
	}

}
