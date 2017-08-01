package com.premize.sgp.dao.gestion.pruebas.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.premize.sgp.dao.gestion.pruebas.IndicadoresDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.indicadores.HallazgoPorGarantiaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpIndicadores;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.utils.NumericUtils;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-dao-hbxml
* @class SgpIndicadoresDaoImpl
* @date 9/07/2014
*
 */
@Repository
public class SgpIndicadoresDaoImpl extends HibernateDaoImpl<SgpIndicadores, Integer> implements IndicadoresDao {

	@Override
	public List<SgpIndicadores> consultarIndicadores() throws AppBaseException {
		List<SgpIndicadores> result = new ArrayList<SgpIndicadores>();

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpIndicadores.class);
		criteria.add(Restrictions.eq("indActivo", 1));
		
		if(!findByCriteria(criteria).isEmpty()){
		result = findByCriteria(criteria);
		}

	return result;
	}
	
	@Override
	public ResultSet<IndicadoresDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpIndicadores.class);
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("formula", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("fase", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("periodicidad", searchLike, MatchMode.ANYWHERE).ignoreCase());
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
		List<SgpIndicadores> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<IndicadoresDTO> resultList = new ArrayList<IndicadoresDTO>();
		for (SgpIndicadores sgpIndicadores : listaReturn) {
			IndicadoresDTO indicadoresDTO = new IndicadoresDTO(sgpIndicadores);
			resultList.add(indicadoresDTO);
			
		}
		
		
		return new ResultSet<IndicadoresDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	@Override
	public boolean validarIndicador(IndicadoresDTO indicadoresDTO) throws AppBaseException {
		Boolean result = false;

			DetachedCriteria criteria = DetachedCriteria.forClass(SgpIndicadores.class);
			criteria.add(Restrictions.eq("nombre", indicadoresDTO.getNombre()));
			criteria.add(Restrictions.eq("indActivo", 1));
			
			if (!findByCriteria(criteria).isEmpty()) {
				result = true;
			}
		return result;
	}

	@Override
	public HallazgoPorGarantiaDTO hallazgosPorGarantia(Integer idProyecto,Date fechaFrom,Date fechaTo,List<String> listEstados,List<Integer> listCodigoGarantias) throws AppBaseException {
		Query query ;
		if(listEstados.contains("Ninguno")){
			 query = createNamedQuery("hallazgoPorGarantiaSinCausaGeneracion");
		
		}else{
		 query = createNamedQuery("hallazgoPorGarantia");
			query.setParameterList("estadosHallazgos", listEstados);
		}
		query.setParameter("proyecto", idProyecto);

		
		query.setResultTransformer(Transformers.aliasToBean(HallazgoPorGarantiaDTO.class));
		query.setTimestamp("fechaFrom", fechaFrom);
		query.setTimestamp("fechaTo", fechaTo);
		if (listEstados.size()<1){
			listEstados.add("Ninguno");
		}
		

		query.setParameterList("codigoGarantia", listCodigoGarantias);
		
		return (HallazgoPorGarantiaDTO) findByOtherQuery(query).get(0);
	}
	
}
